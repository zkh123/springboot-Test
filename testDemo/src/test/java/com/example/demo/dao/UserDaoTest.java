package com.example.demo.dao;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.TestDemoApplication;
import com.example.demo.dao.master.UserDao;
import com.example.demo.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

/**
 * Created by huanglijun on 2017/9/14.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestDemoApplication.class)
@WebAppConfiguration
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void findAllTest(){
        List<User> list = userDao.findAll();
        System.out.println(JSONObject.toJSONString(list));
    }
}
