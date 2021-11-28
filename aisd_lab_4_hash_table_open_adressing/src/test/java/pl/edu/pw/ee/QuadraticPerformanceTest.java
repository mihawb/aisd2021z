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

public class QuadraticPerformanceTest {
    int sampleSize = 30;
    BufferedWriter writer;
    BufferedReader reader;
    ArrayList<String> words;
    HashTable<String> hashtab;
    double [] A, B;

    @Before
    public void init() throws IOException {
        writer = new BufferedWriter(new FileWriter("quadraticResults.txt", false));
        reader = new BufferedReader(new FileReader("words.txt"));
        words = new ArrayList<String>();
        A = new double[]{5.7, 17.19, 29.31, 59.79, 89.97, -5.7, -17.19, -29.31, -59.79, -89.97};
        B = new double[]{2.3, -2.3, 11.13, -11.13, 19.23, -19.23, 43.47, -43.47, 79.83, -79.83};

        String header = "Initial hash size";
        for (int i = 0; i < 10; i++) {
            header += ", Avg write time (a=" + A[i] + " b=" + B[i] + ")";
            header += ", Avg read time (a=" + A[i] + " b=" + B[i] + ")"; 
        }
        writer.append(header + "\n");

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
        for (int size = 512; size <= 262144; size *= 2) {
            String line = "" + size;

            for (int i = 0; i < 10; i++) {
                System.out.println(size + ", parametry nr " + i);
                ArrayList<Double> writeTimesArr = new ArrayList<Double>();
                ArrayList<Double> readTimesArr = new ArrayList<Double>();
                double [] results;

                for (int prob = 0; prob < sampleSize; prob++) {
                    results = testTemplate(size, A[i], B[i]);
                    writeTimesArr.add(results[0]);
                    readTimesArr.add(results[1]);
                }

                Collections.sort(writeTimesArr);
                Collections.sort(readTimesArr);

                while (writeTimesArr.size() > sampleSize * 2 / 3) {
                    writeTimesArr.remove(0);
                    readTimesArr.remove(0);
                }

                while (writeTimesArr.size() > sampleSize / 3){
                    writeTimesArr.remove(writeTimesArr.size() - 1);
                    readTimesArr.remove(readTimesArr.size() - 1);
                }

                double writeTotalTime = 0, writeAvgTime;
                double readTotalTime = 0, readAvgTime;

                for (int j = 0; j < writeTimesArr.size(); j++) {
                    writeTotalTime += writeTimesArr.get(j);
                    readTotalTime += readTimesArr.get(j);                
                }

                writeAvgTime = writeTotalTime / (sampleSize * 2 / 3);
                readAvgTime = readTotalTime / (sampleSize * 2 / 3);

                line += ", " + writeAvgTime + ", " + readAvgTime;
            }
            writer.append(line + "\n");
        }
    }

    private double [] testTemplate(int s, double a, double b) {
        hashtab = new HashQuadraticProbing<String>(s, a, b);

        long writeTimeStart = System.nanoTime();
        for (String word : words)
            hashtab.put(word);
        long writeTimeElapsed = System.nanoTime() - writeTimeStart;

        long readTimeStart = System.nanoTime();
        for (String word : words)
            hashtab.get(word);
        long readTimeElapsed = System.nanoTime() - readTimeStart;

        hashtab = null;
        return new double[]{(double) writeTimeElapsed / 1000000000, (double) readTimeElapsed / 1000000000};
        // casting primitives is safe, loss of precision shall not occur
    }
}
