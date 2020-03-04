package nl.tudelft.oopp.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RoleTest {

    @Test
    public void roleConstructor(){
        assertNotNull(new Role(1, "admin"));
    }

    @Test
    public void getRoleId(){
        Role role = new Role(1, "admin");
        assertEquals(1, role.getRole_id());
    }

    @Test
    public void setRoleId(){
        Role role = new Role(1, "admin");
        role.setRole_id(2);
        assertEquals(2, role.getRole_id());
        assertNotEquals(1, role.getRole_id());
    }

    @Test
    public void getRoleName(){
        Role role = new Role(1, "noob");
        assertEquals("noob", role.getRole_name());
    }

    @Test
    public void setRoleName(){
        Role role = new Role(1, "noob");
        role.setRole_name("boss");
        assertEquals("boss", role.getRole_name());
        assertNotEquals("noob", role.getRole_name());
    }

    @Test
    public void toStringTest(){
        Role role = new Role(1, "boss");
        String toString = "Role id is: " + role.getRole_id() + " Role name is: " + role.getRole_name();
        assertEquals(role.toString(), toString);
    }
}
