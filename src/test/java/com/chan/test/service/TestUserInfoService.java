package com.chan.test.service;

import com.chan.info.service.UserInfoService;
import com.chan.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2016/9/27.
 */
public class TestUserInfoService extends BaseTest {
    @Resource
    private UserInfoService userInfoService;

    private List<String> amours;
    @Test
    public void test(){
//        userInfoService.getUserByUsername("admin");
    }
}
