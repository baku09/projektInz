package com.example.projektInz.controller;

import com.example.projektInz.model.User;
import com.example.projektInz.security.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private Model model;

    private MockMvc mockMvc;


    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void testRegistrationGet() {
        String viewName = userController.registration(model);
        verify(model).addAttribute(eq("user"), any(User.class));
        assertEquals("registration", viewName);
    }

    @Test
    public void testRegistrationPost() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password123"); // Ustawienie hasła
        user.setEmail("test@example.com"); // Ustawienie email
        String viewName = userController.registration(user);
        verify(userService).save(user);
        assertEquals("redirect:/login", viewName);
    }

    @Test
    public void testLogin2() {
        String username = "testuser";
        String password = "password123";

        String viewName = userController.login();
        assertEquals("login", viewName);
    }
}