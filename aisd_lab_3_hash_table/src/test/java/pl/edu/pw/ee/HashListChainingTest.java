package pl.edu.pw.ee;

import pl.edu.pw.ee.services.HashTable;

// import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

// import org.junit.Before;
import org.junit.After;
import org.junit.Test;

public class HashListChainingTest {
    HashTable<String> hashtab;

    @After
    public void teardown() {
        hashtab = null;
    }

    @Test 
    public void test0() {
        hashtab = new HashListChaining<String>(10);
        hashtab.add("OLA");
        hashtab.add("ALA");
        hashtab.add("EWA");

        System.out.println(hashtab.get("OLA"));
    }
}