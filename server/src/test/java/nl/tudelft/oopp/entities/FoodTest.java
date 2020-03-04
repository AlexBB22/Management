package nl.tudelft.oopp.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FoodTest {

    @Test
    public void foodConstructorTest(){
        assertNotNull(new Food("Burger", 10));
    }

    @Test
    public void getFoodId(){
        Food burger = new Food("Burger", 10);
        assertEquals("Burger", burger.getFood_id());
    }

    @Test
    public void getPrice(){
        Food burger = new Food("Burger", 10);
        assertEquals(10, burger.getPrice());
    }

    @Test
    public void setPrice(){
        Food grillSandwich = new Food("grillSandwich", 100);
        grillSandwich.setPrice(50);
        assertEquals(50, grillSandwich.getPrice());
    }

    @Test
    public void setPriceNegative(){
        Food grillSandwich = new Food("grillSandwich", 100);
        grillSandwich.setPrice(-10);
        assertEquals(0, grillSandwich.getPrice());
    }

    @Test
    public void setPriceNotEquals(){
        Food fishSandwich = new Food("fishSandwich", 20);
        assertNotEquals(30, fishSandwich.getPrice());
    }

}
