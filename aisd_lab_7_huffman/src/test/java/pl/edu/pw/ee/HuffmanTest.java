package pl.edu.pw.ee;

import org.junit.Test;

public class HuffmanTest {
    @Test
    public void test0() {
        Huffman decoder = new Huffman();
        int numberOfBits = decoder.huffman("to_compress", true);
        System.out.println(numberOfBits);
    }

    // @Test 
    // public void countertest() {
    //     Huffman decoder = new Huffman();
    //     try {
    //         decoder.abilitytoread("to_compress");
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    // }
}
