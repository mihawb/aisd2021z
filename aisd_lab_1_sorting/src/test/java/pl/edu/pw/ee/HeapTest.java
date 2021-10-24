package pl.edu.pw.ee;
import pl.edu.pw.ee.services.HeapInterface;

import java.util.Arrays;
// import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

public class HeapTest {
    HeapInterface<Double> heapobj;

    @Before
    public void init() {
        heapobj = new Heap<Double>();
    }

    @After
    public void teardown() {
        heapobj = null;
    }

    @Test
    public void putTest() {
        heapobj.put(7.0);
        heapobj.put(3.0);
        heapobj.put(19.0);
        heapobj.put(81.0);
        heapobj.put(0.0);
        // no exceptions thrown => test passed successfully
    }

    @Test
    public void popTest() {
        heapobj.put(7.0);
        heapobj.put(3.0);
        heapobj.put(19.0);
        heapobj.put(81.0);
        heapobj.put(0.0);

        double [] res = new double[5];
        double [] prop = new double[] {81.0, 19.0, 7.0, 3.0, 0.0};
        
        for (int i = 0; i < 5; i++) {
            res[i] = heapobj.pop();
        }

        assertTrue("Heap-up and heap-down not implemented correctly!", Arrays.equals(res, prop));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void emptyHeap() {
        heapobj.pop();
    }
}
