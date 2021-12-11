package pl.edu.pw.ee;

import pl.edu.pw.ee.dict.Dictionary;
import pl.edu.pw.ee.heap.MinHeap;

import java.util.ArrayList;
import java.nio.charset.StandardCharsets;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Huffman {
    Node huffamnTreeRoot = null;
    String codeAfterCompression = "";

    public int huffman(String pathToRootDir, boolean compress) {
        // pathToRootDir = sciezka do folderu
        // w folderze ma sie znajdowac jeden plik o zdefiniowanej nazwie zawierajacy
        // tekst zrodlowy lub bit po kopresji
        // compress decyduje czy zwracamy ilosc bitow po kompresji czy ilosc liter w
        // pliku zrodlowym
        int result = -1;
        if (compress) {
            result = compress(pathToRootDir);
        }

        return result;
    }

    private int compress(String pathToRootDir) {
        ArrayList<Node> entries;

        try {
            entries = characterCounter(pathToRootDir);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        MinHeap<Node> forestPQ = new MinHeap<>(entries);

        while (forestPQ.getLength() > 1) {
            Node smol1 = forestPQ.pop();
            Node smol2 = forestPQ.pop();

            Node container = new Node(smol1.getFreq() + smol2.getFreq());
            container.setLeft(smol1);
            container.setRight(smol2);

            forestPQ.put(container);
        }

        huffamnTreeRoot = forestPQ.pop();

        assignCodes(huffamnTreeRoot);

        try {
            createCompressedFile(pathToRootDir, huffamnTreeRoot);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        printNodes(huffamnTreeRoot);

        return calculateNumbersOfBits(huffamnTreeRoot);
    }

    private void createCompressedFile(String pathToRootDir, Node node) throws IOException {
        File file1 = new File(pathToRootDir + "/compressedFile.txt");
        OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file1), StandardCharsets.UTF_8);

        addDictionaryToCompressedFile(writer, node);

        File file2 = new File(pathToRootDir + "/sourceFile.txt");
        FileInputStream fis = new FileInputStream(file2);
        InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(isr);

        String compressed = "";
        int i;
        while ((i = reader.read()) != -1) {
            char c = (char) i;
            if (c == ' ' || c == '\n' || (int)c == 13) {
                c = '_'; // mam 39 stopni goraczki nie chce mi sie myslec nad lepszym rozwiazaniem
            }
            compressed += huffamnTreeRoot.findCodeOfNode(c);
        }

        writer.append(compressed);

        reader.close();
        writer.close();
    }

    private void addDictionaryToCompressedFile(OutputStreamWriter writer, Node node) throws IOException {
        if (node == null) {
            return;
        }
        if (node.isLeaf() && (int)node.getCharacter() != 13) {
            writer.append(node.getCharacter() + ":" + node.getCode() + "\n");
        }
        addDictionaryToCompressedFile(writer, node.getLeft());
        addDictionaryToCompressedFile(writer, node.getRight());
    }

    private ArrayList<Node> characterCounter(String pathToRootDir) throws IOException {
        File file = new File(pathToRootDir + "/sourceFile.txt");
        FileInputStream fis = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(isr);

        Dictionary<Character, Node> dict = new Dictionary<>();

        int i;
        while ((i = reader.read()) != -1) {
            char c = (char) i;
            if (c == ' ' || c == '\n' || (int)c == 13) {
                c = '_'; // mam 39 stopni goraczki nie chce mi sie myslec nad lepszym rozwiazaniem
            }
            if (dict.getValue(c) == null) {
                dict.setValue(c, new Node(c, 1));
            } else {
                Node newV = new Node(c, dict.getValue(c).getFreq() + 1);
                dict.setValue(c, newV);
            }
        }
        reader.close();

        ArrayList<Node> cc = dict.getAllEntries();

        return cc;
    }

    private void printNodes(Node node) {
        if (node == null) {
            return;
        }
        if (node.isLeaf()) {
            System.out.println(node);
        }
        printNodes(node.getLeft());
        printNodes(node.getRight());
    }

    private void assignCodes(Node node) {
        if (node.getLeft() != null && node.getRight() != null) {
            node.getLeft().setCode(node.getCode() + "0");
            node.getRight().setCode(node.getCode() + "1");
            assignCodes(node.getLeft());
            assignCodes(node.getRight());
        }
    }

    private int calculateNumbersOfBits(Node node) {
        int total = 0;
        if (node.isLeaf()) {
            total += node.getFreq() * node.getCode().length();
        }

        if (node.getLeft() != null && node.getRight() != null) {
            total += calculateNumbersOfBits(node.getLeft());
            total += calculateNumbersOfBits(node.getRight());
        }

        return total;
    }
}
