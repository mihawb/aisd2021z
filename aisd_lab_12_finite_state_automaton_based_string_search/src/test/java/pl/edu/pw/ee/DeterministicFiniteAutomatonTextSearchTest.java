package pl.edu.pw.ee;

import org.junit.Test;

import pl.edu.pw.ee.services.PatternSearch;

public class DeterministicFiniteAutomatonTextSearchTest {
    @Test
    public void test0() {
        // PatternSearch as = new DeterministicFiniteAutomatonTextSearch("AB");
        // int index = as.findFirst("ABCABDABK");

        PatternSearch as = new DeterministicFiniteAutomatonTextSearch("AB");
        int index = as.findFirst("ZAABCABDABK");

        System.out.println(index);
    }
}
