package com.lixin.springsecuritylearn.controller;

import lombok.AllArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author lx
 * @date 2021/6/1
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AllArgsConstructor
public class LoginControllerTest {

    private final MockMvc mockMvc;

    @Test
    public void testLogin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/login"))
                .andExpect(status().isOk());
    }

}
