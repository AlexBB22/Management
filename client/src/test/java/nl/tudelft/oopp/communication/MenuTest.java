package nl.tudelft.oopp.communication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MenuTest {

    private Menu m1;
    private Food f1;
    private Food f2;
    private List<Food> foods;

    @BeforeEach
    void setUp() {
        f1 = new Food("Pasta", 5);
        f2 = new Food("Noodles", 10);
        foods = new ArrayList<>();
        foods.add(f1);
        foods.add(f2);
        m1 = new Menu(1);
    }

    @Test
    void testConstructor() {
        assertNotNull(m1);
    }

    @Test
    void testEmptyConstructor() {
        Menu m2 = new Menu();
        assertNotNull(m2);
    }

    @Test
    void getMenuId() {
        assertEquals(1, m1.getMenuId());
    }

    @Test
    void getFoods() {
        m1.setFoods(foods);
        assertEquals(foods, m1.getFoods());
    }

    @Test
    void setFoods() {
        Food f3 = new Food("Rice", 5);
        Food f4 = new Food("Curry", 10);
        List<Food> foodList = new ArrayList<>();
        m1.setFoods(foodList);
        assertEquals(foodList, m1.getFoods());
    }

    @Test
    void getRestaurant() {
        Restaurant r1 = new Restaurant(1);
        m1.setRestaurant(r1);
        assertEquals(r1, m1.getRestaurant());
    }

    @Test
    void setRestaurant() {
        Restaurant r2 = new Restaurant(2);
        m1.setRestaurant(r2);
        assertEquals(r2, m1.getRestaurant());
    }
}