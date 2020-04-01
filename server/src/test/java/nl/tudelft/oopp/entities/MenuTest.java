package nl.tudelft.oopp.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class MenuTest {
    private Menu m1;
    private List<Food> foods;
    private Restaurant r;


    @BeforeEach
    void setUp() {
        m1 = new Menu(1);
        r = new Restaurant(3);
        Food f1 = new Food("Burger", 10);
        Food f2 = new Food("Fries", 5);
        foods = new ArrayList<>();
        foods.add(f1);
        foods.add(f2);
        m1.setFoods(foods);
        m1.setRestaurant(r);
    }


    @Test
    public void menuConstructor() {
        assertNotNull(new Menu());
    }

    @Test
    public void getId() {
        Menu menu = new Menu(1);
        assertEquals(1, menu.getMenuId());

        Menu menu2 = new Menu();
        assertNotNull(menu2.getMenuId());
    }

    @Test
    void getFoodsTest() {
        assertEquals(foods, m1.getFoods());
    }

    @Test
    void setFoodsTest() {
        Food f1 = new Food("Chips", 10);
        Food f2 = new Food("Fish", 5);
        List<Food> foods2 = new ArrayList<>();
        foods2.add(f1);
        foods2.add(f2);
        m1.setFoods(foods2);
        assertEquals(foods2, m1.getFoods());
    }

    @Test
    void getRestaurantTest() {
        assertEquals(r, m1.getRestaurant());
    }

    @Test
    void setRestaurantTest() {
        Restaurant r2 = new Restaurant(5);
        m1.setRestaurant(r2);
        assertEquals(r2, m1.getRestaurant());
    }

}
