package com.example.accessingdatabasemysql;

import org.testng.annotations.*;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//import org.junit.jupiter.api.Test;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AccessingMockMvcTests  extends AbstractTestNGSpringContextTests {

    @Autowired
    private MockMvc mockMvc;

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("MockMvcTests -- @BeforeSuite--------------------");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("MockMvcTests -- @AfterSuite--------------------\n");
    }

    @BeforeTest
    public void beforeTest() {
        System.out.println("MockMvcTests -- @BeforeTest----------");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("MockMvcTests -- @AfterTest----------\n");
    }

    @Test
    public void shouldReturnUserInfoMessage() throws Exception {

        this.mockMvc.perform(get("/demo/user")
                    .param("id", "1"))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("hahaha"))
                .andExpect(jsonPath("$.email").value("myjhaha@qq.com"));
    }

    @Test
    public void shouldReturnUserInfoMessage2() throws Exception {

        this.mockMvc.perform(get("/demo/user")
                .param("id", "1"))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("hahaha"))
                .andExpect(jsonPath("$.email").value("myjhaha@qq.com"));
    }
    @Test
    public void shouldReturnUserInfoMessage3() throws Exception {

        this.mockMvc.perform(get("/demo/user")
                .param("id", "1"))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("hahaha"))
                .andExpect(jsonPath("$.email").value("myjhaha@qq.com"));
    }
}
