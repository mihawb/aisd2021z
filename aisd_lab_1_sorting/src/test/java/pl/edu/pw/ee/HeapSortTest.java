package pl.edu.pw.ee;

import pl.edu.pw.ee.services.Sorting;
import pl.edu.pw.ee.services.Utils;

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
    public void test0() {
        arr = new double[]{7.7, 9.3, 4.2, 1.1};
        sortobj.sort(arr);
        assertTrue("Heap sort is not implemented correctly!", Utils.assertSorted(arr));
    }
}
