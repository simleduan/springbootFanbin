package com.chan.config.shiro;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.Order;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;

/**
 * 
 * @PackageName : com.chan.config.shiro
 * @author : 樊斌
 * @date : 2017年2月16日
 * @time : 上午9:51:52
 * @version : 1.0
 * @Description : 权限验证核心类Apache Shiro 核心通过 Filter 来实现，就好像SpringMvc 通过DispachServlet 来主控制一样。
 * 既然是使用 Filter 一般也就能猜到，是通过URL规则来进行过滤和权限校验，所以我们需要定义一系列关于URL的规则和访问权限。
 * 
 *
 */
@Configuration
@Order(1)
public class ShiroConfiguration {

	/**
	 * 
	 * ShiroFilterFactoryBean，是个factorybean，为了生成ShiroFilter。
	 * 它主要保持了三项数据，securityManager，filters，filterChainDefinitionManager。
	 * @return
	 */
	@Bean
	public ShiroFilterFactoryBean shiroFilterFactoryBean() {
		System.out.println("shiroConfiguration.ShiroFilterFactoryBean-------------1");
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		// 必须设置 SecurityManager
		shiroFilterFactoryBean.setSecurityManager(securityManager());

		// 过滤器
		Map<String, Filter> filters = shiroFilterFactoryBean.getFilters();
		//添加退出过滤器
		LogoutFilter logoutFilter = new LogoutFilter();
		logoutFilter.setRedirectUrl("/login");
		filters.put("logout", logoutFilter);
		//添加角色过滤器
		RoleFilter roleFilter = new RoleFilter();
		filters.put("roles", roleFilter);
		shiroFilterFactoryBean.setFilters(filters);

		// 拦截器
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String,String>();
		// 配置退出过滤器,其中的具体代码Shiro已经替我们实现了
		filterChainDefinitionMap.put("/logout", "logout");

		// 配置记住我或认证通过可以访问的地址
		filterChainDefinitionMap.put("/index", "user");
		filterChainDefinitionMap.put("/", "user");

		// <!-- 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
		// <!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
		filterChainDefinitionMap.put("/everyone", "anon");// 设置，每一个人，不需要登陆，都可以访问
		filterChainDefinitionMap.put("/favicon.ico", "anon");
		filterChainDefinitionMap.put("/loginCheck", "anon");
		filterChainDefinitionMap.put("/personOption", "roles[admin,vip,god]");
		filterChainDefinitionMap.put("/roleNoPer", "roles[admin,god]");
		filterChainDefinitionMap.put("/**", "authc");// 所有地址访问都必须经过认证
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		
		// 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
		shiroFilterFactoryBean.setLoginUrl("/login");// 认证相关：当用户没有登陆就访问资源时，跳转到此页面
		// 登录成功后要跳转的链接
		shiroFilterFactoryBean.setSuccessUrl("/index");
		// 授权相关：当用户访问没有权限的资源时，跳转到此页面
		shiroFilterFactoryBean.setUnauthorizedUrl("/403");
		return shiroFilterFactoryBean;
	}

	/**
	 * SecurityManager，权限管理，这个类组合了登陆，登出，权限，session的处理，是个比较重要的类。
	 * 安全管理器 shiro的核心组件(相当与军队的司令) 需要注入realm(此处注入自定义的realm)
	 * @return
	 */
	@Bean
	public SecurityManager securityManager() {
		System.out.println("shiroConfiguration.SecurityManager-------------2");
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		// 设置realm.
		securityManager.setRealm(fanBinRealm());
		// 注入缓存管理器;
		// 注意:开发时请先关闭，如不关闭热启动会报错
		securityManager.setCacheManager(ehCacheManager());// 这个如果执行多次，也是同样的一个对象;
		// 注入记住我管理器;
		securityManager.setRememberMeManager(rememberMeManager());
		return securityManager;
	}

	/**
	 * 自定义的Realm认证类
	 * @return
	 */
	@Bean
	public FanBinRealm fanBinRealm() {
		System.out.println("shiroConfiguration.FanBinRealm-------------3");
		FanBinRealm realm = new FanBinRealm();
		realm.setCredentialsMatcher(hashedCredentialsMatcher());
		return realm;
	}

	/**
	 * HashedCredentialsMatcher，这个类是为了对密码进行编码的， 防止密码在数据库里明码保存，当然在登陆认证的时候，
	 * 这个类也负责对form里输入的密码进行编码。
	 */
	@Bean
	public HashedCredentialsMatcher hashedCredentialsMatcher() {
		System.out.println("shiroConfiguration.HashedCredentialsMatcher-------------4");
		// HashedCredentialsMatcher hashedCredentialsMatcher = new
		// RetryLimitHashedCredentialsMatcher(ehCacheManager());
		
		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
		matcher.setHashAlgorithmName("md5");// 散列算法:这里使用MD5算法;
		// matcher.setHashIterations(2);// 散列的次数，比如散列两次，相当于md5(md5(""));
		matcher.setStoredCredentialsHexEncoded(true);// 表示是否存储散列后的密码为16进制，需要和生成密码时的一样，默认是base64；
		return matcher;
	}

	/**
	 * 这是新加的，如果有错误就注释1
     * DefaultAdvisorAutoProxyCreator，Spring的一个bean，由Advisor决定对哪些类的方法进行AOP代理。
     */
    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
    	System.out.println("shiroConfiguration.DefaultAdvisorAutoProxyCreator-------------5");
        DefaultAdvisorAutoProxyCreator defaultAAP = new DefaultAdvisorAutoProxyCreator();
        defaultAAP.setProxyTargetClass(true);
        return defaultAAP;
    }
	
	/**
	 * 开启shiro aop注解支持. 使用代理方式;所以需要开启代码支持;
	 * AuthorizationAttributeSourceAdvisor，shiro里实现的Advisor类，
	 * 内部使用AopAllianceAnnotationsAuthorizingMethodInterceptor来拦截用以下注解的方法。
	 * @return
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
		System.out.println("shiroConfiguration.AuthorizationAttributeSourceAdvisor-------------6");
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
		return authorizationAttributeSourceAdvisor;
	}

	/**
	 * shiro缓存管理器; 用户登陆成功后，把用户信息和权限信息缓存起来，
	 * 然后每次用户请求时，放入用户的session中，如果不设置这个bean，每个请求都会查询一次数据库
	 * 需要注入对应的其它的实体类中： 1、安全管理器：securityManager
	 * 可见securityManager是整个shiro的核心；
	 * 
	 * @return
	 */
	@Bean
	@DependsOn("lifecycleBeanPostProcessor")
	public EhCacheManager ehCacheManager() {
		System.out.println("shiroConfiguration.EhCacheManager-------------7");
		EhCacheManager cacheManager = new EhCacheManager();
		cacheManager.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");
		return cacheManager;
	}

	/**
	 * 记住我功能的配置
	 * 
	 * @return
	 */
	@Bean
	public SimpleCookie rememberMeCookie() {
		System.out.println("shiroConfiguration.SimpleCookie-------------8");
		// 这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
		SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
		// <!-- 记住我cookie生效时间30天 ,单位秒;-->
		simpleCookie.setMaxAge(259200);
		return simpleCookie;
	}

	/**
	 * cookie管理对象;
	 * 
	 * @return
	 */
	@Bean
	public CookieRememberMeManager rememberMeManager() {
		System.out.println("shiroConfiguration.CookieRememberMeManager-------------9");
		CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
		cookieRememberMeManager.setCookie(rememberMeCookie());
		return cookieRememberMeManager;
	}

	/**
	 * ShiroDialect，为了在thymeleaf里使用shiro的标签的bean
	 * 
	 * @return
	 */
	@Bean
	public ShiroDialect shiroDialect() {
		System.out.println("shiroConfiguration.ShiroDialect-------------10");
		return new ShiroDialect();
	}
	
	/**
	 * 这是新加的，如果有错误就注释2
     * LifecycleBeanPostProcessor，这是个DestructionAwareBeanPostProcessor的子类，
     * 负责org.apache.shiro.util.Initializable类型bean的生命周期的，初始化和销毁。
     * 主要是AuthorizingRealm类的子类，以及EhCacheManager类。
     */
    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
    	System.out.println("shiroConfiguration.ShiroDialect-------------10");
        return new LifecycleBeanPostProcessor();
    }
}
