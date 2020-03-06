package nl.tudelft.oopp.communication;

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

public class TypeTest {

    @Test
    public void typeConstructor() {
        assertNotNull(new Type("someRoom", true, true, true));
    }

    @Test
    public void getTypeId() {
        Type type = new Type("someRoom", true, true, true);
        assertEquals(0, type.getType_id());
    }

    @Test
    public void getSetAttributes() {
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

}
