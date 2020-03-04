package nl.tudelft.oopp.entities;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void constructorUser(){
        User user = new User(2, "email", "username", "password");
        assertNotNull(user);
    }

    @Test
    public void getSetAttributes(){
        User user = new User(2, "email", "username", "password");
        Role role = new Role(1, "admin");
        user.setEmail("notEmail");
        user.setUser_id(5);
        user.setUser_password("notPassword");
        user.setUser_name("notUsername");
        user.setRole(role);
        assertEquals("notEmail", user.getEmail());
        assertEquals("notPassword", user.getUser_password());
        assertEquals(5, user.getUser_id());
        assertEquals("notUsername", user.getUser_name());
        assertEquals(role, user.getRole());
    }
}
