package com.example.springbootpostgres.modeltest;

import com.example.springbootpostgres.model.Role;
import com.example.springbootpostgres.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import static org.junit.jupiter.api.Assertions.*;

public class UserModelTest {

    private final String ID = "1234";
    private final String USERNAME = "testuser";
    private final String EMAIL = "testuser@example.com";
    private final String PASSWORD = "password";
    private final Role ROLE = Role.USER;

    @Test
    public void testUserDetails() {
        User user = new User();
        user.setId(ID);
        user.setUsername(USERNAME);
        user.setEmail(EMAIL);
        user.setPassword(PASSWORD);
        user.setRole(ROLE);



        assertEquals(ID, user.getId());
        assertEquals(USERNAME, user.getUsername());
        assertEquals(EMAIL, user.getEmail());
        assertEquals(PASSWORD, user.getPassword());
        assertEquals(ROLE, user.getRole());

        UserDetails userDetails = user;
        assertEquals(userDetails.getUsername(), USERNAME);
        assertEquals(userDetails.getPassword(), PASSWORD);
    }
}