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

public class MenuTest {

    @Test
    public void menuConstructor() {
        assertNotNull(new Menu());
    }

    @Test
    public void getId() {
        Menu menu = new Menu();
        assertNotNull(menu.getMenu_id());
    }

}
