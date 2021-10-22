package pl.edu.pw.ee;
import pl.edu.pw.ee.services.HeapInterface;

// import static org.junit.Assert.assertEquals;
// import static org.junit.Assert.assertTrue;
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
    public void test0() {
        heapobj.put(7.0);
        heapobj.put(3.0);
        heapobj.put(19.0);
        heapobj.put(81.0);
        heapobj.put(0.0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void emptyHeap() {
        heapobj.pop();
    }

    @Test
    public void popTest() {
        heapobj.put(7.0);
        heapobj.put(3.0);
        heapobj.put(19.0);
        heapobj.put(81.0);
        heapobj.put(0.0);

        double result = heapobj.pop();
        System.out.println(result);
        result = heapobj.pop();
        System.out.println(result);
        result = heapobj.pop();
        System.out.println(result);
        result = heapobj.pop();
        System.out.println(result);
        result = heapobj.pop();
        System.out.println(result);
    }
}
