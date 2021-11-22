package pl.edu.pw.ee;

import pl.edu.pw.ee.services.MapInterface;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

public class RbtMapTest {
    @Test
    public void test0() {
        MapInterface<String, String> mapa = new RbtMap<String, String>();
        mapa.setValue("key", "value");
        System.out.println(mapa.getValue("key"));
        // ???
    }

}
