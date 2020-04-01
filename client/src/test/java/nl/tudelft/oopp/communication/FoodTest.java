package nl.tudelft.oopp.communication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;




class FoodTest {

    private Food f1;
    private Food f2;
    private Menu m1;
    private Menu m2;
    private List<Menu> menuList;


    @BeforeEach
    void setUp() {
        m1 = new Menu(1);
        m2 = new Menu(2);
        menuList = new ArrayList<>();
        menuList.add(m1);
        menuList.add(m2);

        f1 = new Food("Pasta", 5);
        f1.setFoodId(1);
        f1.setMenus(menuList);
        f2 = new Food();
    }


    @Test
    void testConstructor() {
        assertNotNull(f1);
    }

    @Test
    void testEmptyConstructor() {
        assertNotNull(f2);
    }

    @Test
    void getPrice() {
        assertEquals(5, f1.getPrice());
    }

    @Test
    void setPrice() {
        f1.setPrice(10);
        assertEquals(10, f1.getPrice());
    }

    @Test
    void setPrice2() {
        f1.setPrice(-1);
        assertEquals(0, f1.getPrice());
    }

    @Test
    void getName() {
        assertEquals("Pasta", f1.getName());
    }

    @Test
    void setName() {
        f1.setName("Noodles");
        assertEquals("Noodles", f1.getName());
    }

    @Test
    void getFoodId() {
        assertEquals(1, f1.getFoodId());
    }

    @Test
    void setFoodId() {
        f1.setFoodId(2);
        assertEquals(2, f1.getFoodId());
    }

    @Test
    void getMenus() {
        assertEquals(menuList, f1.getMenus());
    }

    @Test
    void setMenus() {
        Menu m3 = new Menu(3);
        Menu m4 = new Menu(4);
        List<Menu> list = new ArrayList<>();
        list.add(m3);
        list.add(m4);
        f1.setMenus(list);
        assertEquals(list, f1.getMenus());
    }
}
