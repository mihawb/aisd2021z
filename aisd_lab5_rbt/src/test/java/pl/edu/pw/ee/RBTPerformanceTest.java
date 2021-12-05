package pl.edu.pw.ee;

import java.lang.reflect.Field;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import org.junit.Test;

public class RBTPerformanceTest {
    @Test
    public void recursivePutCallsTest() throws IOException {
        BufferedWriter writer1 = new BufferedWriter(new FileWriter("results1.txt", false));
        BufferedWriter writer10 = new BufferedWriter(new FileWriter("results10.txt", false));
        BufferedReader reader = new BufferedReader(new FileReader("words.txt"));
        RedBlackTree<String, String> rbt = new RedBlackTree<>();

        writer1.append("Size, Recursive put calls\n");
        writer10.append("Size, Recursive put calls\n");

        int nOfNodes = 0;
        String word;

        while ((word = reader.readLine()) != null) {
            for (int i = 0; i < 10; i++) {
                rbt.put(word + i, word + i);
                nOfNodes++;

                if (nOfNodes <= 1000) {
                    String line = String.format("%d, %d\n", nOfNodes, getNumOfRecursivePutCalls(rbt));
                    writer1.append(line);
                }
            }

            String line = String.format("%d, %d\n", nOfNodes, getNumOfRecursivePutCalls(rbt));
            writer10.append(line);
        }

        writer1.close();
        writer10.close();
        reader.close();
    }

    private int getNumOfRecursivePutCalls(RedBlackTree<?, ?> rbt) {
        try {
            Field field = rbt.getClass().getDeclaredField("nOfRecursivePutCalls");
            field.setAccessible(true);

            int numOfCalls = field.getInt(rbt);

            return numOfCalls;

        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
