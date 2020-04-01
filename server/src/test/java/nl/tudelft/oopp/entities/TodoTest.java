package nl.tudelft.oopp.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.sql.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TodoTest {

    private Todo t1;
    private Todo t2;

    @BeforeEach
    void setUp() {
        t1 = new Todo("Writing tests", new Date(20200401));
        t2 = new Todo();
    }

    @Test
    void emptyConstructorTest() {
        assertNotNull(t2);
    }

    @Test
    void constructorTest() {
        assertNotNull(t1);
    }


    @Test
    void getTitle() {
        assertEquals("Writing tests", t1.getTitle());
    }

    @Test
    void setTitle() {
        t1.setTitle("Writing more tests");
        assertEquals("Writing more tests", t1.getTitle());
    }

    @Test
    void getDay() {
        assertEquals(new Date(20200401), t1.getDay());
    }

    @Test
    void setDay() {
        t1.setDay(new Date((20200402)));
        assertEquals(new Date(20200402), t1.getDay());
    }

    @Test
    void setUserFk() {
        User u = new User(1, "123@test", "kanish", "password");
        t1.setUserFk(u);
    }

    @Test
    void testToString1() {
        assertThrows(NullPointerException.class, () -> {
            t1.toString();
        });
    }

    @Test
    void testToString2() {
        User u = new User(1, "123@test", "kanish", "password");
        t1.setUserFk(u);
        String res = "Todo{todoID=0, userFk=kanish, title='Writing tests', day=1970-01-01}";
        assertEquals(res, t1.toString());
    }
}