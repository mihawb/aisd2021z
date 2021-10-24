package pl.edu.pw.ee;
import pl.edu.pw.ee.services.Sorting;
import pl.edu.pw.ee.services.Utils;

import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
// import java.util.Arrays;
// import java.util.stream.IntStream; 

// import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
// import static org.junit.Assert.assertThrows; // works since junit 4.13+
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

public class T_SEL_RAND_Test {
    Sorting sortobj;
    double [] arr;
    BufferedWriter writer;
    long seed = 1337;

    @Before
    public void init() {
        sortobj = new SelectionSort();
        try {
            writer = new BufferedWriter(new FileWriter("sel_rand.txt", true));
        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    @After
    public void teardown() {
        sortobj = null;
        arr = null;
        try {
            writer.close();
        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    @Test
    public void optimistic1k() {
        int len = 1000;
        long startTime, elapsedTime;

        arr = new Random(seed).doubles(len).toArray();


        startTime = System.nanoTime();
        sortobj.sort(arr);
        elapsedTime = System.nanoTime() - startTime;

        try {
            writer.append(len + ", " + elapsedTime + "\n");
        } catch (IOException e) {
            e.getStackTrace();
        }

        assertTrue("Optimistic scenario was not sorted properly!", Utils.assertSorted(arr));
    }

    @Test
    public void optimistic5k() {
        int len = 5000;
        long startTime, elapsedTime;

        arr = new Random(seed).doubles(len).toArray();

        startTime = System.nanoTime();
        sortobj.sort(arr);
        elapsedTime = System.nanoTime() - startTime;

        try {
            writer.append(len + ", " + elapsedTime + "\n");
        } catch (IOException e) {
            e.getStackTrace();
        }

        assertTrue("Optimistic scenario was not sorted properly!", Utils.assertSorted(arr));
    }

    @Test
    public void optimistic10k() {
        int len = 10000;
        long startTime, elapsedTime;

        arr = new Random(seed).doubles(len).toArray();

        startTime = System.nanoTime();
        sortobj.sort(arr);
        elapsedTime = System.nanoTime() - startTime;

        try {
            writer.append(len + ", " + elapsedTime + "\n");
        } catch (IOException e) {
            e.getStackTrace();
        }

        assertTrue("Optimistic scenario was not sorted properly!", Utils.assertSorted(arr));
    }

    @Test
    public void optimistic15k() {
        int len = 15000;
        long startTime, elapsedTime;

        arr = new Random(seed).doubles(len).toArray();

        startTime = System.nanoTime();
        sortobj.sort(arr);
        elapsedTime = System.nanoTime() - startTime;

        try {
            writer.append(len + ", " + elapsedTime + "\n");
        } catch (IOException e) {
            e.getStackTrace();
        }

        assertTrue("Optimistic scenario was not sorted properly!", Utils.assertSorted(arr));
    }

    @Test
    public void optimistic25k() {
        int len = 25000;
        long startTime, elapsedTime;

        arr = new Random(seed).doubles(len).toArray();

        startTime = System.nanoTime();
        sortobj.sort(arr);
        elapsedTime = System.nanoTime() - startTime;

        try {
            writer.append(len + ", " + elapsedTime + "\n");
        } catch (IOException e) {
            e.getStackTrace();
        }

        assertTrue("Optimistic scenario was not sorted properly!", Utils.assertSorted(arr));
    }

    @Test
    public void optimistic50k() {
        int len = 50000;
        long startTime, elapsedTime;

        arr = new Random(seed).doubles(len).toArray();

        startTime = System.nanoTime();
        sortobj.sort(arr);
        elapsedTime = System.nanoTime() - startTime;

        try {
            writer.append(len + ", " + elapsedTime + "\n");
        } catch (IOException e) {
            e.getStackTrace();
        }

        assertTrue("Optimistic scenario was not sorted properly!", Utils.assertSorted(arr));
    }

    @Test
    public void optimistic100k() {
        int len = 100000;
        long startTime, elapsedTime;

        arr = new Random(seed).doubles(len).toArray();

        startTime = System.nanoTime();
        sortobj.sort(arr);
        elapsedTime = System.nanoTime() - startTime;

        try {
            writer.append(len + ", " + elapsedTime + "\n");
        } catch (IOException e) {
            e.getStackTrace();
        }

        assertTrue("Optimistic scenario was not sorted properly!", Utils.assertSorted(arr));
    }

    @Test
    public void optimistic150k() {
        int len = 150000;
        long startTime, elapsedTime;

        arr = new Random(seed).doubles(len).toArray();

        startTime = System.nanoTime();
        sortobj.sort(arr);
        elapsedTime = System.nanoTime() - startTime;

        try {
            writer.append(len + ", " + elapsedTime + "\n");
        } catch (IOException e) {
            e.getStackTrace();
        }

        assertTrue("Optimistic scenario was not sorted properly!", Utils.assertSorted(arr));
    }

    @Test
    public void optimistic200k() {
        int len = 200000;
        long startTime, elapsedTime;

        arr = new Random(seed).doubles(len).toArray();

        startTime = System.nanoTime();
        sortobj.sort(arr);
        elapsedTime = System.nanoTime() - startTime;

        try {
            writer.append(len + ", " + elapsedTime + "\n");
        } catch (IOException e) {
            e.getStackTrace();
        }

        assertTrue("Optimistic scenario was not sorted properly!", Utils.assertSorted(arr));
    }

    @Test
    public void optimistic300k() {
        int len = 300000;
        long startTime, elapsedTime;

        arr = new Random(seed).doubles(len).toArray();

        startTime = System.nanoTime();
        sortobj.sort(arr);
        elapsedTime = System.nanoTime() - startTime;

        try {
            writer.append(len + ", " + elapsedTime + "\n");
        } catch (IOException e) {
            e.getStackTrace();
        }

        assertTrue("Optimistic scenario was not sorted properly!", Utils.assertSorted(arr));
    }
}
