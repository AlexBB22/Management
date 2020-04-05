package nl.tudelft.oopp.communication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



class UserTest {

    private User u1;
    private User u2;
    private Role role;

    @BeforeEach
    void setUp() {
        role = new Role(2, "Staff");
        u1 = new User(1, "123@test", "Kanish", "password", role);
    }

    @Test
    void testConstructor() {
        assertNotNull(u1);
    }

    @Test
    void getUserId() {
        assertEquals(1, u1.getUserId());
    }

    @Test
    void setUserId() {
        u1.setUserId(5);
        assertEquals(5, u1.getUserId());
    }

    @Test
    void getEmail() {
        assertEquals("123@test", u1.getEmail());
    }

    @Test
    void setEmail() {
        u1.setEmail("456@test");
        assertEquals("456@test", u1.getEmail());
    }

    @Test
    void getUserName() {
        assertEquals("Kanish", u1.getUserName());
    }

    @Test
    void setUserName() {
        u1.setUserName("Eli");
        assertEquals("Eli", u1.getUserName());
    }

    @Test
    void getUserPassword() {
        assertEquals("password", u1.getUserPassword());
    }

    @Test
    void setUserPassword() {
        u1.setUserPassword("pass");
        assertEquals("pass", u1.getUserPassword());
    }

    @Test
    void getRole() {
        assertEquals(role, u1.getRole());
    }

    @Test
    void setRole() {
        Role temp = new Role(3, "Teacher");
        u1.setRole(temp);
        assertEquals(temp, u1.getRole());
    }

    @Test
    void testToString() {
        String res = "User{userId=1, email='123@test', userName='Kanish', userPassword='password', role=Staff}";
        assertEquals(res, u1.toString());
    }
}