package pl.edu.pw.ee;

import pl.edu.pw.ee.services.HashTable;

import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Collections;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

public class PerformanceTest {
    int sampleSize = 30;
    BufferedWriter writer;
    BufferedReader reader;
    ArrayList<String> words;
    HashTable<String> hashtab;

    @Before
    public void init() throws IOException {
        writer = new BufferedWriter(new FileWriter("results.txt", false));
        reader = new BufferedReader(new FileReader("words.txt"));
        words = new ArrayList<String>();

        writer.append("Size, Avg time\n");

        String line;
        while ((line = reader.readLine()) != null)
            words.add(line);
    }

    @After
    public void teardown() throws IOException {
        writer.close();
        reader.close();
    }

    @Test
    public void testFactory() throws IOException {
        for (int size = 4096; size <= 262144; size *= 2) {
            ArrayList<Double> execTimesArr = new ArrayList<Double>();

            for (int i = 0; i < sampleSize; i++)
                execTimesArr.add(testTemplate(size));

            Collections.sort(execTimesArr);

            while (execTimesArr.size() > sampleSize * 2 / 3) execTimesArr.remove(0);
            while (execTimesArr.size() > sampleSize / 3) execTimesArr.remove(execTimesArr.size() - 1);

            double totalTime = 0, avgTime;

            for (double t : execTimesArr)
                totalTime += t;

            avgTime = totalTime / (sampleSize * 2 / 3);

            String line = String.format("%d, %g\n", size, avgTime);
            writer.append(line);
        }
    }

    private double testTemplate(int s) {
        hashtab = new HashListChaining<String>(s);

        for (String word : words)
            hashtab.add(word);

        long timeStart = System.nanoTime();
        for (String word : words)
            hashtab.get(word);
        long timeElapsed = System.nanoTime() - timeStart;

        hashtab = null;
        return (double) timeElapsed / 1000000000;
        // casting primitives is safe, loss of precision shall not occur
    }
}
