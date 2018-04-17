// Copyright (c) 1998-2018 Core Solutions Limited. All rights reserved.
// ============================================================================
// CURRENT VERSION CNT.5.0.1
// ============================================================================
// CHANGE LOG
// CNT.5.0.1 : 2018-03-31, derrick.liang, creation
// ============================================================================
package com.star.demo;

import com.star.demo.controller.AuthorController;
import com.star.demo.domain.Author;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

/**
 * @author derrick.liang
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class AuthorTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mvc;

    private static String uuid = UUID.randomUUID().toString();

    JSONObject jsonObject = new JSONObject();

    @Before
    public void setup() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
        jsonObject.put("id", uuid);
        jsonObject.put("realName", "001-1");
        jsonObject.put("nickName", "002");
    }

    @Test
    public void add() throws Exception {
        System.out.println("add id --" + uuid);
        String content = jsonObject.toString();
        mvc.perform(MockMvcRequestBuilders.post("/author/add").contentType(MediaType.APPLICATION_JSON).content(content))
                .andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
    }

    @Test
    public void get() throws Exception {
        System.out.println("get id --" + uuid);
        String responseString = mvc
                .perform(MockMvcRequestBuilders.get("/author/get/" + uuid).accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk()).andExpect(content().json(jsonObject.toString())).andReturn()
                .getResponse().getContentAsString();
        System.out.println("test" + responseString);
    }
}
