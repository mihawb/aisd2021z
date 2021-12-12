package pl.edu.pw.ee;

import pl.edu.pw.ee.dict.Dictionary;
import pl.edu.pw.ee.heap.MinHeap;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;

public class Huffman {
    private Node huffamnTreeRoot;

    public Huffman() {
        this.huffamnTreeRoot = new Node();
    }

    public int huffman(String pathToRootDir, boolean compress) {
        int result = -1;
        if (compress) {
            File source = new File(pathToRootDir + "/sourceFile.txt");
            if (!source.exists()) {
                throw new IllegalArgumentException("Couldn't locate source files in specified directory!");
            }
            result = compress(pathToRootDir);
        } else {
            File source = new File(pathToRootDir + "/compressedFile.txt");
            if (!source.exists()) {
                throw new IllegalArgumentException("Couldn't locate source files in specified directory!");
            }
            result = decompress(pathToRootDir);
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

        if (entries.size() == 0) {
            throw new InputMismatchException("Tried to compress an empty file!");
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

        return calculateNumbersOfBits(huffamnTreeRoot);
    }

    private int decompress(String pathToRootDir) {
        String codedFile;
        try {
            codedFile = createHuffTreeFromCompressedDict(pathToRootDir);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (codedFile == null) {
            throw new InputMismatchException("Tried to decompress empty file!");
        }

        int numberOfCharsInSource = -1;
        try {
            numberOfCharsInSource = decodeAndWriteToFile(pathToRootDir, codedFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return numberOfCharsInSource;
    }

    private ArrayList<Node> characterCounter(String pathToRootDir) throws IOException {
        File file = new File(pathToRootDir + "/sourceFile.txt");
        FileInputStream fis = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(isr);

        Dictionary<Integer, Node> dict = new Dictionary<>();

        // all whitespaces are intentionally counted as characters
        int i;
        while ((i = reader.read()) != -1) {
            if (dict.getValue(i) == null) {
                dict.setValue(i, new Node(i, 1));
            } else {
                Node newV = new Node(i, dict.getValue(i).getFreq() + 1);
                dict.setValue(i, newV);
            }
        }
        reader.close();

        ArrayList<Node> cc = dict.getAllEntries();

        return cc;
    }

    private void assignCodes(Node node) {
        if (node.getLeft() != null && node.getRight() != null) {
            node.getLeft().setCode(node.getCode() + "0");
            node.getRight().setCode(node.getCode() + "1");
            assignCodes(node.getLeft());
            assignCodes(node.getRight());
        }
    }

    private void createCompressedFile(String pathToRootDir, Node node) throws IOException {
        File file1 = new File(pathToRootDir + "/compressedFile.txt");
        FileOutputStream fos = new FileOutputStream(file1);
        OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
        BufferedWriter writer = new BufferedWriter(osw);

        addDictionaryToCompressedFile(writer, node);

        File file2 = new File(pathToRootDir + "/sourceFile.txt");
        FileInputStream fis = new FileInputStream(file2);
        InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(isr);

        String compressed = "";
        int i;
        while ((i = reader.read()) != -1) {
            compressed += huffamnTreeRoot.findCodeOfNode(i);
        }

        writer.append(compressed);

        reader.close();
        writer.close();
    }

    private void addDictionaryToCompressedFile(BufferedWriter writer, Node node) throws IOException {
        if (node == null) {
            return;
        }

        if (node.isLeaf()) {
            writer.append(node.getCharacter() + ":" + node.getCode() + "\n");
        }

        addDictionaryToCompressedFile(writer, node.getLeft());
        addDictionaryToCompressedFile(writer, node.getRight());
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

    private String createHuffTreeFromCompressedDict(String pathToRootDir) throws IOException {
        File file = new File(pathToRootDir + "/compressedFile.txt");
        FileInputStream fis = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(isr);
        String codedFile = null;

        String entry;
        while ((entry = reader.readLine()) != null) {
            int colonIndex = entry.indexOf(':');

            if (colonIndex == -1) {
                codedFile = entry;
                break;
            }

            int eChar = Integer.parseInt(entry.substring(0, colonIndex));
            String eCode = entry.substring(colonIndex + 1);
            Node newNode = new Node(eChar, 1, eCode);
            Node iternode = huffamnTreeRoot;

            for (int i = 0; i < eCode.length() - 1; i++) {
                if (eCode.charAt(i) == '0') {
                    if (iternode.getLeft() == null) {
                        iternode.setLeft(new Node());
                    }
                    iternode = iternode.getLeft();
                } else if (eCode.charAt(i) == '1') {
                    if (iternode.getRight() == null) {
                        iternode.setRight(new Node());
                    }
                    iternode = iternode.getRight();
                }
            }

            if (eCode.charAt(eCode.length() - 1) == '0') {
                iternode.setLeft(newNode);
            } else if (eCode.charAt(eCode.length() - 1) == '1') {
                iternode.setRight(newNode);
            }
        }

        reader.close();
        return codedFile;
    }

    private int decodeAndWriteToFile(String pathToRootDir, String codedFile) throws IOException {
        int numberOfCharsInSource = 0;

        File file1 = new File(pathToRootDir + "/decompressedFile.txt");
        FileOutputStream fos = new FileOutputStream(file1);
        OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
        BufferedWriter writer = new BufferedWriter(osw);

        Node iternode = huffamnTreeRoot;
        for (int i = 0; i < codedFile.length(); i++) {
            if (codedFile.charAt(i) == '0') {
                iternode = iternode.getLeft();
            } else if (codedFile.charAt(i) == '1') {
                iternode = iternode.getRight();
            }
            if (iternode.isLeaf()) {
                writer.append((char) iternode.getCharacter());
                numberOfCharsInSource++;
                iternode = huffamnTreeRoot;
            }
        }

        writer.close();
        return numberOfCharsInSource;
    }
}
