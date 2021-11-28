// package pl.edu.pw.ee;

// import pl.edu.pw.ee.services.HashTable;

// import java.io.IOException;
// import java.io.FileWriter;
// import java.io.BufferedWriter;
// import java.io.FileReader;
// import java.io.BufferedReader;
// import java.util.ArrayList;
// import java.util.Collections;
// import java.lang.reflect.Field; // debug hash size

// import org.junit.Before;
// import org.junit.After;
// import org.junit.Test;

// public class DoublePerformanceTest {
//     int sampleSize = 30;
//     BufferedWriter writer;
//     BufferedReader reader;
//     ArrayList<String> words;
//     HashTable<String> hashtab;

//     @Before
//     public void init() throws IOException {
//         writer = new BufferedWriter(new FileWriter("doubleResults.txt", false));
//         reader = new BufferedReader(new FileReader("words.txt"));
//         words = new ArrayList<String>();

//         writer.append("Initial hash size, Avg write time, Avg read time, Eventual hash size\n");

//         String line;
//         while ((line = reader.readLine()) != null)
//             words.add(line);
//     }

//     @After
//     public void teardown() throws IOException {
//         writer.close();
//         reader.close();
//     }

//     @Test
//     public void testFactory() throws IOException {
//         for (int size = 512; size <= 262144; size *= 2) {
//             ArrayList<Double> writeTimesArr = new ArrayList<Double>();
//             ArrayList<Double> readTimesArr = new ArrayList<Double>();
//             double [] results = new double[]{-1.0, -1.0, -1.0}; // debug hash size

//             for (int i = 0; i < sampleSize; i++) {
//                 results = testTemplate(size);
//                 writeTimesArr.add(results[0]);
//                 readTimesArr.add(results[1]);
//             }

//             Collections.sort(writeTimesArr);
//             Collections.sort(readTimesArr);

//             while (writeTimesArr.size() > sampleSize * 2 / 3) {
//                 writeTimesArr.remove(0);
//                 readTimesArr.remove(0);
//             }

//             while (writeTimesArr.size() > sampleSize / 3){
//                 writeTimesArr.remove(writeTimesArr.size() - 1);
//                 readTimesArr.remove(readTimesArr.size() - 1);
//             }

//             double writeTotalTime = 0, writeAvgTime;
//             double readTotalTime = 0, readAvgTime;

//             for (int i = 0; i < writeTimesArr.size(); i++) {
//                 writeTotalTime += writeTimesArr.get(i);
//                 readTotalTime += readTimesArr.get(i);                
//             }

//             writeAvgTime = writeTotalTime / (sampleSize * 2 / 3);
//             readAvgTime = readTotalTime / (sampleSize * 2 / 3);
//                                                                                         // debug hash size
//             String line = String.format("%d, %g, %g, %g\n", size, writeAvgTime, readAvgTime, results[2]);
//             writer.append(line);
//         }
//     }

//     private double [] testTemplate(int s) {
//         System.out.println(s);
//         hashtab = new HashDoubleHashing<String>(s);

//         long writeTimeStart = System.nanoTime();
//         for (String word : words) {
//             hashtab.put(word);
//         }
//         long writeTimeElapsed = System.nanoTime() - writeTimeStart;

//         long readTimeStart = System.nanoTime();
//         for (String word : words)
//             hashtab.get(word);
//         long readTimeElapsed = System.nanoTime() - readTimeStart;

//         //hashtab = null;                                                                                     // debug hash size
//         return new double[]{(double) writeTimeElapsed / 1000000000, (double) readTimeElapsed / 1000000000, (double) getHashSize(hashtab)};
//         // casting primitives is safe, loss of precision shall not occur
//     }

//     private int getHashSize(HashTable<?> hash) {
//         try {
//             Field field = hash.getClass().getSuperclass().getDeclaredField("size");
//             field.setAccessible(true);

//             int numOfElems = field.getInt(hash);

//             return numOfElems;

//         } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
//             throw new RuntimeException(e);
//         }
//     }
// }
