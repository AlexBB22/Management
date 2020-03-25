package nl.tudelft.oopp.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class FoodTest {

    @Test
    public void foodConstructorTest() {
        assertNotNull(new Food("Burger", 10));
    }

    @Test
    public void getFoodId() {
        Food burger = new Food("Burger", 10);
        assertNotNull(burger.getFood_id());
    }

    @Test
    public void getPrice() {
        Food burger = new Food("Burger", 10);
        assertEquals(10, burger.getPrice());
    }

    @Test
    public void setPrice() {
        Food grillSandwich = new Food("grillSandwich", 100);
        grillSandwich.setPrice(50);
        assertEquals(50, grillSandwich.getPrice());
    }

    @Test
    public void setPriceNegative() {
        Food grillSandwich = new Food("grillSandwich", 100);
        grillSandwich.setPrice(-10);
        assertEquals(0, grillSandwich.getPrice());
    }

}
