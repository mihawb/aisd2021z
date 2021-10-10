package pl.edu.pw.ee;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BinSearchTest {
    @Test
    public void test1_wartosci_niebezpieczne() {
        BinarySearch testobj = new BinarySearch();
        int result = testobj.search(null, 5.0);

        assertEquals(result, -1);
    }

    @Test
    public void test2_1_jeden_element_jest() {
        double[] array = { 5.0 };
        BinarySearch testobj = new BinarySearch();
        int result = testobj.search(array, 5.0);

        assertEquals(result, 0);
    }

    @Test
    public void test2_2_jeden_element_brak() {
        double[] array = { 5.0 };
        BinarySearch testobj = new BinarySearch();
        int result = testobj.search(array, 7.0);

        assertEquals(result, -1);
    }
}
