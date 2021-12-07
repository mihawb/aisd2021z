package pl.edu.pw.ee;

import pl.edu.pw.ee.dict.Dictionary;

import java.io.IOException;
import java.util.ArrayList;
import java.io.FileReader;

public class Huffman {

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
        ArrayList<Node> forest;

        try {
            forest = characterCounter(pathToRootDir);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        while (forest.size() > 1) {
            Node smol1 = getSmallestFromForest(forest);
            Node smol2 = getSmallestFromForest(forest);

            Node container = new Node(smol1.getFreq() + smol2.getFreq());
            container.setLeft(smol1);
            container.setRight(smol2);

            forest.add(container);
        }

        Node root = forest.get(0);

        assignCodes(root);

        return calculateNumbersOfBits(root);
    }

    private ArrayList<Node> characterCounter(String pathToRootDir) throws IOException {
        FileReader reader = new FileReader(pathToRootDir + "/file.txt");
        Dictionary<Character, Node> dict = new Dictionary<>();

        int i;
        // whitespaces also count as characters
        while ((i = reader.read()) != -1) {
            char c = (char) i;
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

    private Node getSmallestFromForest(ArrayList<Node> forest) {
        Node smallest = forest.get(0);
        int sInd = 0;
        int size = forest.size();

        for (int i = 1; i < size; i++) {
            if (smallest.compareTo(forest.get(i)) > 0) {
                smallest = forest.get(i);
                sInd = i;
            }
        }

        forest.remove(sInd);
        return smallest;
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
