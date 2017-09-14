package com.example.demo.service;

import com.example.demo.TestDemoApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by huanglijun on 2017/9/14.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestDemoApplication.class)
@WebAppConfiguration
public class HelloWorldServiceTest {

    @Autowired
    private HelloWorldService helloWorldService;

    @Test
    public void helloWorldServiceTest(){
        String result = helloWorldService.hello();
        System.out.println(result);
    }
}
