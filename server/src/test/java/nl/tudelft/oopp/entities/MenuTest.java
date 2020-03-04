package nl.tudelft.oopp.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MenuTest {

    @Test
    public void menuConstructor(){
        assertNotNull(new Menu(2));
    }

    @Test
    public void getId(){
        Menu menu = new Menu(1);
        assertEquals(1, menu.getMenu_id());
    }

}
