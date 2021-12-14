package pl.edu.pw.ee;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class LongestCommonSubsequenceTest {
    @Test
    public void proofOfConcept() {
        LongestCommonSubsequence lcs = new LongestCommonSubsequence("PREZENT", "REZERWAT");
        String result = lcs.findLCS();
        System.out.println(result);
        lcs.display();
    }
}
