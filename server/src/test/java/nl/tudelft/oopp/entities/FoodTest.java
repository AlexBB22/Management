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

import org.assertj.core.util.VisibleForTesting;
import org.junit.jupiter.api.Test;

public class FoodTest {

    @Test
    public void foodConstructorTest() {
        assertNotNull(new Food("Burger", 10));
    }

    @Test
    void emptyConstructorTest() {
        Food f = new Food();
        assertNotNull(f);
    }

    @Test
    public void getFoodId() {
        Food burger = new Food("Burger", 10);
        assertNotNull(burger.getFoodId());
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

    @Test
    void getNameTest() {
        Food f = new Food("Burger", 10);
        assertEquals("Burger", f.getName());
    }

    @Test
    void setNameTest() {
        Food f = new Food("Burger", 10);
        f.setName("Fries");
        assertEquals("Fries", f.getName());
    }

    @Test
    void getMenusTest() {
        Menu m1 = new Menu(1);
        Menu m2 = new Menu(2);
        List<Menu> menus = new ArrayList<>();
        menus.add(m1);
        menus.add(m2);
        Food f = new Food("Burger", 4);
        f.setMenus(menus);
        assertEquals(menus, f.getMenus());

    }

    @Test
    void setMenusTest() {
        Menu m1 = new Menu(1);
        Menu m2 = new Menu(2);
        List<Menu> menus = new ArrayList<>();
        Food f = new Food("Burger", 4);
        f.setMenus(menus);

        Menu m3 = new Menu(3);
        List<Menu> menus2 = new ArrayList<>();
        menus2.add(m1);
        menus2.add(m2);
        menus2.add(m3);
        f.setMenus(menus2);

        assertEquals(menus2, f.getMenus());
    }




}
