package pl.edu.pw.ee;

import pl.edu.pw.ee.services.Sorting;
import pl.edu.pw.ee.services.Utils;

import java.util.Random;
// import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

public class HeapSortTest {
    Sorting sortobj;
    double [] arr;

    @Before
    public void init() {
        sortobj = new HeapSort();
    }

    @After
    public void teardown() {
        sortobj = null;
        arr = null;
    }

    @Test
    public void arrOfLenZero() {
        arr = new double[0];
        sortobj.sort(arr);
    }

    @Test
    public void arrOfLenOne() {
        arr = new double[]{9.3};
        sortobj.sort(arr);
    }

    @Test
    public void arrOfLenTwo() {
        arr = new double[]{9.3, 4.2};
        sortobj.sort(arr);
        assertTrue("Array of length 2 was not sorted properly!", Utils.assertSorted(arr));
    }

    @Test
    public void pessimisticScenario() {
        arr = new double[]{7.7, 6.6, 5.5, 4.4, 3.3, 2.2, 1.1, 0};
        sortobj.sort(arr);
        assertTrue("Pessimistic scenario was not sorted properly!", Utils.assertSorted(arr));
    }

    @Test
    public void generalScenario() {
        arr = new double[]{4.4, 0, 2.2, 1.1, 6.6, 3.3, 7.7, 5.5};
        sortobj.sort(arr);
        assertTrue("General scenario was not sorted properly!", Utils.assertSorted(arr));
    }

    @Test
    public void randomScenario() {
        int len = 100000; 
        long seed = 1337;

        arr = new double[len];
        arr = new Random(seed).doubles(len).toArray();

        sortobj.sort(arr);
        assertTrue("Random scenario was not sorted properly!", Utils.assertSorted(arr));
    }
}
