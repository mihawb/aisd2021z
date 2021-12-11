package pl.edu.pw.ee;

import org.junit.Test;

public class HuffmanTest {
    @Test
    public void test0() {
        Huffman decoder = new Huffman();
        int numberOfBits = decoder.huffman("to_compress", true);
        System.out.println(numberOfBits);
    }

    @Test
    public void test1() {
        Huffman decoder = new Huffman();
        int numberOfCharactersInSrc = decoder.huffman("to_decompress", false);
        System.out.println(numberOfCharactersInSrc);
    }
}