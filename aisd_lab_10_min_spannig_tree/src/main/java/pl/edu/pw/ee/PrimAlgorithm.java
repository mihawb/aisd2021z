package pl.edu.pw.ee;

import pl.edu.pw.ee.services.MinSpanningTree;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.BufferedReader;

public class PrimAlgorithm implements MinSpanningTree {
    public String findMST(String pathToFile){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(pathToFile));

            String line;
            while ((line = reader.readLine()) != null) {
                
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        
        // TODOcd
        return null;
    }
}
