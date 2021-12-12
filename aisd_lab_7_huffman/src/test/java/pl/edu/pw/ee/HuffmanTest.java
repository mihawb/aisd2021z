package pl.edu.pw.ee;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.InputMismatchException;
import java.nio.charset.StandardCharsets;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import org.junit.Test;

public class HuffmanTest {
    @Test(expected = IllegalArgumentException.class)
    public void invalidFilepath() {
        Huffman decoder = new Huffman();
        int n = decoder.huffman("invalid_path", true);
    }

    @Test(expected = InputMismatchException.class)
    public void shouldNotCompressEmptyFile() {
        Huffman decoder = new Huffman();
        int n = decoder.huffman("tests/empty_file", true);
    }

    @Test(expected = InputMismatchException.class)
    public void shouldNotDecompressEmptyFile() {
        Huffman decoder = new Huffman();
        int n = decoder.huffman("tests/empty_file", false);
    }

    @Test
    public void shouldCorrectlyEncodeNiemanie() {
        Huffman decoder = new Huffman();
        int numberOfBits = decoder.huffman("tests/niemanie", true);
        int numberOfCharactersInSrc = decoder.huffman("tests/niemanie", false);

        // including all whitespaces
        assertEquals(8937, numberOfBits);
        assertEquals(1908, numberOfCharactersInSrc);
    }

    @Test
    public void shouldCorrectlyEncodeNiemanieRefren() {
        Huffman decoder = new Huffman();
        int numberOfBits = decoder.huffman("tests/niemanie_refren", true);
        int numberOfCharactersInSrc = decoder.huffman("tests/niemanie_refren", false);

        assertEquals(571, numberOfBits);
        assertEquals(167, numberOfCharactersInSrc);
    }

    @Test
    public void checkIfSourceAndDecodedFilesAreEqual() throws IOException {
        String pathToRootDir = "tests/niemanie";

        File file1 = new File(pathToRootDir + "/compressedFile.txt");
        FileInputStream fis1 = new FileInputStream(file1);
        InputStreamReader isr1 = new InputStreamReader(fis1, StandardCharsets.UTF_8);
        BufferedReader originalReader = new BufferedReader(isr1);

        File file2 = new File(pathToRootDir + "/compressedFile.txt");
        FileInputStream fis2 = new FileInputStream(file2);
        InputStreamReader isr2 = new InputStreamReader(fis2, StandardCharsets.UTF_8);
        BufferedReader decodedReader = new BufferedReader(isr2);

        int i, j;
        while ((i = originalReader.read()) != -1 && (j = decodedReader.read()) != -1) {
            assertEquals(i, j);
        }

        originalReader.close();
        decodedReader.close();
    }
}