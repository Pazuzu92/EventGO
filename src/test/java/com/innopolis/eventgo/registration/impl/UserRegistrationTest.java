package com.innopolis.eventgo.registration.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
public class UserRegistrationTest {
    @Autowired
    private MockMvc mockMvc;

//    @Test
//    public void submitRegistrationPasswordNotValid() throws Exception {
//        this.mockMvc
//                .perform(
//                        post("/registration")
//                                .param("name", "Memory")
//                                .param("login", "den")
//                                .param("email", "new@memorynotfound.com")
//                                .param("password", "password")
//                                .param("matchingPassword", "password")
//                )
//                .andExpect(model().hasErrors())
//                .andExpect(model().attributeHasFieldErrors("user", "password", "matchingPassword"))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void submitRegistrationPasswordNotMatching() throws Exception {
//        this.mockMvc
//                .perform(
//                        post("/registration")
//                                .param("name", "Memory")
//                                .param("login", "den")
//                                .param("email", "new@memorynotfound.com")
//                                .param("password", "xjD1!3djk4")
//                                .param("matchingPassword", "xjD1!3djk3")
//                )
//                .andExpect(model().hasErrors())
//                .andExpect(model().attributeHasErrors("user"))
//                .andExpect(status().isOk());
//    }
}
