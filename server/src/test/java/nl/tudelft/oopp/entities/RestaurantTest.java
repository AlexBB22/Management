package nl.tudelft.oopp.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Time;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



class RestaurantTest {
    private Restaurant r1;
    private Restaurant r2;
    private Building b1;
    private Menu m1;

    @BeforeEach
    void setUp() {
        Time time = new Time(12345);
        Time time2 = new Time(6789);
        b1 = new Building("EWI", true, 300,
                "Tallest building on campus, has an elevator", time, time2);
        r1 = new Restaurant(2);
        r2 = new Restaurant();
        m1 = new Menu(2);


        r1.setBuilding(b1);
        r1.setMenu(m1);
        r1.setResId(1);
    }

    @Test
    void emptyConstructorTest() {
        assertNotNull(r2);
    }

    @Test
    void constructorTest() {
        assertNotNull(r1);
    }


    @Test
    void getMenu() {
        assertEquals(m1, r1.getMenu());
    }

    @Test
    void getBuilding() {
        assertEquals(b1, r1.getBuilding());
    }

    @Test
    void setBuilding() {
        Time time = new Time(12345);
        Time time2 = new Time(6789);
        Building b2 = new Building("CSE", false, 122,
                "Tallest building on campus, has an elevator", time, time2);
        r1.setBuilding(b2);
        assertEquals(b2, r1.getBuilding());
    }

    @Test
    void getResId() {
        assertEquals(1, r1.getResId());
    }

    @Test
    void setResId() {
        r1.setResId(7);
        assertEquals(7, r1.getResId());
    }

    @Test
    void setMenu() {
        Menu m2 = new Menu(6);
        r1.setMenu(m2);
        assertEquals(m2, r1.getMenu());
    }
}