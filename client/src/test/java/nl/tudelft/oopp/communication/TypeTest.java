package nl.tudelft.oopp.communication;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TypeTest {

    @Test
    public void typeConstructor(){
        assertNotNull(new Type("someRoom", true, true, true));
    }

    @Test
    public void getTypeId(){
        Type type = new Type("someRoom", true, true, true);
        assertEquals(0, type.getType_id());
    }

    @Test
    public void getSetAttributes(){
        Type type = new Type("someRoom", true, true, true);
        type.setName("someOtherRoom");
        type.setClicker(false);
        type.setPowerOutlets(false);
        type.setWhiteBoard(false);
        type.setTv(true);
        assertEquals("someOtherRoom", type.getName());
        assertEquals(false, type.isWhiteBoard());
        assertEquals(false, type.isClicker());
        assertEquals(false, type.isPowerOutlets());
        assertEquals(false, type.isWhiteBoard());
        assertEquals(true, type.isTv());
    }

    @Test
    public void toStringTest(){
        Type type = new Type("someRoom", true, true, true);
        String toString = "type_id: " + type.getType_id() + ", name: " + type.getName() + ", clicker: " + type.isClicker() + ", tv: " + type.isTv()
                + ", power_outlets: " + type.isPowerOutlets() + ", whiteboard: " + type.isWhiteBoard();
        assertEquals(toString, type.toString());
    }

}
