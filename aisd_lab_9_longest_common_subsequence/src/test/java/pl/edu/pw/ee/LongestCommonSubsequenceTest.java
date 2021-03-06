package pl.edu.pw.ee;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class LongestCommonSubsequenceTest {
    @Test(expected = IllegalArgumentException.class)
    public void nullEntries() {
        LongestCommonSubsequence lcs = new LongestCommonSubsequence(null, null);
        String result = lcs.findLCS();
        assertNull(result);
    }

    @Test
    public void emptyEntries() {
        LongestCommonSubsequence lcs = new LongestCommonSubsequence("", "");
        String result = lcs.findLCS();
        assertNull(result);
        lcs.display();
        /*
         * display method intentionally works for every non-null input imagianble
         * and in this case its output should look like the following:
         *         |        |
         * --------+--------+
         *         |      0 |
         * --------+--------+
         */
    }

    @Test
    public void shouldNotFindLCSForSingleEmptyEntry() {
        LongestCommonSubsequence lcs1 = new LongestCommonSubsequence("BANANA", "");
        String result1 = lcs1.findLCS();

        LongestCommonSubsequence lcs2 = new LongestCommonSubsequence("", "EXCESS");
        String result2 = lcs2.findLCS();

        assertNull(result1);
        assertNull(result2);
    }

    @Test
    public void shouldFindLCS() {
        LongestCommonSubsequence lcs = new LongestCommonSubsequence("PREZENT", "REZERWAT");
        String result = lcs.findLCS();
        assertEquals("REZET", result);
        lcs.display();
        /*
         * display method should render path characters
         * only in the cells going along the path of LCS
         * favouring going upward rather than sideways
         */

    }

    @Test
    public void shouldNotFindLCS() {
        LongestCommonSubsequence lcs = new LongestCommonSubsequence("VOODOO", "PULLUP");
        String result = lcs.findLCS();
        assertNull(result);
        lcs.display();
        // no path should be rendered in the table full of zeros
    }

    @Test
    public void shouldFindLCSOccuringIn1stStr() {
        LongestCommonSubsequence lcs = new LongestCommonSubsequence("XABXXDXCXX", "YYYABCDYYY");
        String result = lcs.findLCS();
        assertEquals("ABD", result);
    }

    @Test
    public void shouldProperlyEncodeAndDrawWhitespaces() {
        LongestCommonSubsequence lcs = new LongestCommonSubsequence("\b\t\n \f\r\'\"\\", "\b\t\n \f\r\'\"\\");
        String result = lcs.findLCS();
        assertEquals("\\b\\t\\n \\f\\r\\'\\\"\\", result);
        lcs.display();
    }

    @Test
    public void displayStressTest() {
        LongestCommonSubsequence lcs = new LongestCommonSubsequence("POLITECHNIKAPOLITECHNIKAPOLITECHNIKAPOLITECHNIKA",
                "TOALETATOALETATOALETATOALETA");
        String result = lcs.findLCS();
        assertEquals("OLTAOLTAOLTAOLTA", result);
        lcs.display();
    }
}
