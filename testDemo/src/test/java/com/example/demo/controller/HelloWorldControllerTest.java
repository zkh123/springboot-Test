package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.TestDemoApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletOutputStream;

/**
 * Created by huanglijun on 2017/9/14.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestDemoApplication.class)
@WebAppConfiguration
public class HelloWorldControllerTest {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void getHello() throws Exception {
        String param = "黄----李";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/hello").accept(MediaType.APPLICATION_JSON_UTF8).param("username",param))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String result = mvcResult.getResponse().getContentAsString();
        System.out.println(result);
    }

    @Test
    public void postHello() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("key","拍---***贷");
        String param = JSONObject.toJSONString(jsonObject);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/hello").accept(MediaType.APPLICATION_JSON_UTF8).content(param))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String result = mvcResult.getResponse().getContentAsString();
        System.out.println(result);
    }
}
