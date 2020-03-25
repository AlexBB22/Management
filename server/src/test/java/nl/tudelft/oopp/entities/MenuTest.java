package nl.tudelft.oopp.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MenuTest {

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

}
