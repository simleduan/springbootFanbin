package com.chan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.chan.config.shiro.MyExceptionResolver;

/**
 * 
 * @PackageName : com.chan
 * @author      : 樊斌
 * @date        : 2017年2月16日
 * @time        : 下午3:33:27
 * @version     : 1.0
 * @Description : TODO
 * 
 */
@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class,args);
    }
    
  //注入异常处理类
    @Bean
    public MyExceptionResolver myExceptionResolver(){
        return new MyExceptionResolver();
    }
}
