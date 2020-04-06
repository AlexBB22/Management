package nl.tudelft.oopp.communication;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class RoleTest {

    private Role role;

    @BeforeEach
    void setUp() {
        role = new Role(1, "Student");
    }

    @Test
    void getRoleId() {
        assertEquals(1, role.getRoleId());
    }

    @Test
    void setRoleId() {
        role.setRoleId(2);
        assertEquals(2, role.getRoleId());
    }

    @Test
    void getRoleName() {
        assertEquals("Student", role.getRoleName());
    }

    @Test
    void setRoleName() {
        role.setRoleName("Teacher");
        assertEquals("Teacher", role.getRoleName());
    }
}