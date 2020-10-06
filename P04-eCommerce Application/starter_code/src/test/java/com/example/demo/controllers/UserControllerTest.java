package com.example.demo.controllers;

import com.example.demo.TestUtils;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.repositories.CartRepository;
import com.example.demo.model.persistence.repositories.UserRepository;
import com.example.demo.model.requests.CreateUserRequest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.Assert.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserControllerTest {

    private UserController userController;
    private UserRepository userRepository = mock(UserRepository.class);
    private CartRepository cartRepository = mock(CartRepository.class);
    private BCryptPasswordEncoder encoder = mock(BCryptPasswordEncoder.class);

    @Before
    public void setUp(){
        userController = new UserController();
        TestUtils.injectObject(userController, "userRepository", userRepository);
        TestUtils.injectObject(userController, "cartRepository", cartRepository);
        TestUtils.injectObject(userController, "bCryptPasswordEncoder", encoder);

        User user = new User();
        user.setId(0);
        when(userRepository.findById(0L)).thenReturn(java.util.Optional.of(user));
    }

    @Test
    public void create_user_happy_path(){

        when(encoder.encode("testPassword")).thenReturn("thisIsHashed");
        CreateUserRequest r = new CreateUserRequest();
        r.setUsername("test");
        r.setPassword("testPassword");
        r.setConfirmPassword("testPassword");

        final ResponseEntity<User> response = userController.createUser(r);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());

        User u = response.getBody();
        assertNotNull(u);
        assertEquals(0, u.getId());
        assertEquals("test", u.getUsername());
        assertEquals("thisIsHashed", u.getPassword());

    }

    @Test
    public void verify_password_length_criteria(){
        CreateUserRequest r = new CreateUserRequest();
        r.setUsername("test");
        r.setPassword("abcde");
        r.setConfirmPassword("abcde");

        final ResponseEntity<User> response = userController.createUser(r);
        assertEquals(400, response.getStatusCodeValue());
    }

    @Test
    public void verify_find_user_by_id(){
        final ResponseEntity<User> response = userController.findById(0L);
        assertEquals(200, response.getStatusCodeValue());

        User user = response.getBody();
        assertEquals(0, user.getId());
    }

    @Test
    public void verify_confirm_password_criteria(){
        CreateUserRequest r = new CreateUserRequest();
        r.setUsername("test");
        r.setPassword("testPassword");
        r.setConfirmPassword("testPassword1");

        final ResponseEntity<User> response = userController.createUser(r);
        assertEquals(400, response.getStatusCodeValue());
    }
}
