package nl.tudelft.oopp.communication;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTodoTest {

    private UserTodo ut;

    @BeforeEach
    void setUp() {
        ut = new UserTodo("Write tests", "2020-04-01");
    }

    @Test
    void testConstructor() {
        assertNotNull(ut);
    }

    @Test
    void testEmptyConstructor() {
        UserTodo ut2 = new UserTodo();
        assertNotNull(ut2);
    }

    @Test
    void getTitle() {
        assertEquals("Write tests", ut.getTitle());
    }

    @Test
    void setTitle() {
        ut.setTitle("Write more tests");
        assertEquals("Write more tests", ut.getTitle());
    }

    @Test
    void getDay() {
        assertEquals("2020-04-01", ut.getDay());
    }

    @Test
    void setDay() {
        ut.setDay("2020-04-02");
        assertEquals("2020-04-02", ut.getDay());
    }

    @Test
    void testToString() {
        String res = "UserTodo{title='Write tests', day='2020-04-01'}";
        assertEquals(res, ut.toString());
    }
}