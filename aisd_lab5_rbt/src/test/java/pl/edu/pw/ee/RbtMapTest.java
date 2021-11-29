package pl.edu.pw.ee;

import pl.edu.pw.ee.services.MapInterface;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

public class RbtMapTest {
    MapInterface<String, String> mapa;

    @Before
    public void setup() {
        mapa = new RbtMap<>();
    }

    @After
    public void teardown() {
        mapa = null;
    }

    @Test(expected=IllegalArgumentException.class)
    public void setInvalidKey() {
        mapa.setValue(null, "valid");

        assertTrue(false);
    }

    @Test(expected=IllegalArgumentException.class)
    public void setInvalidValue() {
        mapa.setValue("valid", null);

        assertTrue(false);
    }

    @Test(expected=IllegalArgumentException.class)
    public void getInvalidKey() {
        mapa.getValue(null);

        assertTrue(false);
    }

    @Test
    public void setAndGetTest() {
        mapa.setValue("J", "JOT");
        mapa.setValue("A", "A");
        mapa.setValue("C", "CE");
        mapa.setValue("E", "E");
        mapa.setValue("K", "KA");

        assertEquals("KA", mapa.getValue("K"));
        assertEquals("CE", mapa.getValue("C"));
        assertEquals("JOT", mapa.getValue("J"));
    }

    @Test
    public void duplicateInsteadOfOriginalKeyTest() {
        mapa.setValue("KEY", "ORIGINAL");
        String first = mapa.getValue("KEY");
        mapa.setValue("KEY", "DUPLICATE");
        
        assertEquals("ORIGINAL", first);
        assertEquals("DUPLICATE", mapa.getValue("KEY"));
    }

    @Test
    public void duplicateInsteadOfOriginalValueTest() {
        mapa.setValue("KEY1", "DUPLICATE");
        mapa.setValue("KEY2", "DUPLICATE");
        
        assertTrue(mapa.getValue("KEY1").compareTo(mapa.getValue("KEY2")) == 0);
    }

    @Test
    public void getFromEmptyMap_OrNonExistentKey() {
        assertNull(mapa.getValue("KEY"));
    }
}
