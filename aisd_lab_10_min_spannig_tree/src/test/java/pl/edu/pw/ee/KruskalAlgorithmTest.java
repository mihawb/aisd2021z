package pl.edu.pw.ee;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import pl.edu.pw.ee.services.MinSpanningTree;

public class KruskalAlgorithmTest {
    @Test(expected = IllegalArgumentException.class)
    public void shouldNotHandleNullFileName() {
        MinSpanningTree mst = new KruskalAlgorithm();
        mst.findMST(null);
    }

    @Test(expected = RuntimeException.class)
    public void shouldNotHandleNonExistentFile() {
        MinSpanningTree mst = new KruskalAlgorithm();
        mst.findMST("thisfiledoesntexist.txt");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotHandleEmptyFile() {
        MinSpanningTree mst = new KruskalAlgorithm();
        mst.findMST("empty_data.txt");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotHandleIncorrectlyFormattedFile1() {
        MinSpanningTree mst = new KruskalAlgorithm();
        mst.findMST("invalid1_data.txt");
    }

    @Test(expected = NumberFormatException.class)
    public void shouldNotHandleIncorrectlyFormattedFile2() {
        MinSpanningTree mst = new KruskalAlgorithm();
        mst.findMST("invalid2_data.txt");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotHandleNotConnectedgraph() {
        MinSpanningTree mst = new KruskalAlgorithm();
        mst.findMST("notconnected_data.txt");
    }

    ///////////////////////////////////////

    @Test
    public void shouldNotTakeRepeatingEdgeIntoMST() {
        MinSpanningTree mst = new KruskalAlgorithm();
        String result = mst.findMST("repeating_data.txt");
        assertEquals("E_4_F|H_2_A|H_5_C|A_4_D|B_3_G|G_3_D|D_3_F", result);
    }

    @Test
    public void shouldFindMST1() {
        MinSpanningTree mst = new KruskalAlgorithm();
        String result = mst.findMST("lab1_data.txt");
        assertEquals("E_4_F|H_2_A|H_5_C|A_4_D|B_3_G|G_3_D|D_3_F", result);
    }

    @Test
    public void shouldFindMST2() {
        MinSpanningTree mst = new KruskalAlgorithm();
        String result = mst.findMST("lab2_data.txt");
        assertEquals("F_6_G|B_3_C|B_4_G|C_5_E|A_2_D|D_3_E", result);
    }

    @Test
    public void shouldFindMST3() {
        MinSpanningTree mst = new KruskalAlgorithm();
        String result = mst.findMST("correct_small_data.txt");
        assertEquals("A_3_B|B_1_C|C_1_D|D_7_E", result);
    }
}
