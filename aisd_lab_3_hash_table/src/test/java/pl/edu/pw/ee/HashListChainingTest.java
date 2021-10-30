package pl.edu.pw.ee;

import pl.edu.pw.ee.services.HashTable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

public class HashListChainingTest {
    HashTable<String> hashtab;

    @Before
    public void init() {
        hashtab = new HashListChaining<String>(10);
    }

    @After
    public void teardown() {
        hashtab = null;
    }

    @Test 
    public void addTest() {
        hashtab.add("OLA");
        hashtab.add("ALA");
        hashtab.add("EWA");
        // test passed without failures on runtime =>
        // => initialisation and add method implemented correctly;
        // method does not implement any side effects 
    }

    @Test
    public void getExistingValueTest() {
        hashtab.add("OLA");
        hashtab.add("ALA");
        hashtab.add("EWA");
        
        assertEquals("OLA", hashtab.get("OLA"));
    }

    @Test
    public void getNonExistingValueTest() {
        hashtab.add("OLA");
        hashtab.add("ALA");
        hashtab.add("EWA");
        
        assertEquals(null, hashtab.get("KASIA"));
    }

    @Test
    public void deleteExistingValueTest() {
        hashtab.add("OLA");
        hashtab.add("ALA");
        hashtab.add("EWA");
        
        String beforeDel = hashtab.get("OLA");
        hashtab.delete("OLA");
        String afterDel = hashtab.get("OLA");

        assertTrue("Delete method not implemented correctly!", beforeDel == "OLA" && afterDel == null);
    }

    @Test
    public void deleteNonExistingValueTest() {
        hashtab.add("OLA");
        hashtab.add("ALA");
        hashtab.add("EWA");
        
        hashtab.delete("KASIA");
        // test passed without failures on runtime =>
        // => delete method correctly deals with non-existent values;
        // method does not implement any side effects 
    }

    @Test(expected = IllegalArgumentException.class)
    public void maliciousAddTest() {
        hashtab.add(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void maliciousGetTest() {
        hashtab.get(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void maliciousDeleteTest() {
        hashtab.delete(null);
    }
}
