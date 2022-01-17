package pl.edu.pw.ee;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

import pl.edu.pw.ee.services.PatternSearch;

public class DeterministicFiniteAutomatonTextSearchTest {
    @Test(expected = IllegalArgumentException.class)
    public void shouldNotAllowNullPattern() {
        PatternSearch as = new DeterministicFiniteAutomatonTextSearch(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotAllowEmptyPattern() {
        PatternSearch as = new DeterministicFiniteAutomatonTextSearch("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotAllowNullText() {
        PatternSearch as = new DeterministicFiniteAutomatonTextSearch("AB");
        as.findFirst(null);
    }

    @Test
    public void shouldHandleEmptyText() {
        PatternSearch as = new DeterministicFiniteAutomatonTextSearch("AB");
        assertEquals(-1, as.findFirst(""));
    }

    @Test
    public void shouldFindPatternInstanceInAllTexts() {
        PatternSearch as = new DeterministicFiniteAutomatonTextSearch("AB");
        assertEquals(1, as.findFirst("ZABCABDABBK"));
        assertEquals(7, as.findFirst("DGDGDGAABDG"));
        assertEquals(9, as.findFirst("AAAAAAAAAAB"));
    }

    @Test
    public void shouldFindAllPatternInstanceInText1() {
        PatternSearch as = new DeterministicFiniteAutomatonTextSearch("AB");
        int[] arr = as.findAll("DGDGABDGABAB");
        assertArrayEquals(new int[] { 4, 8, 10 }, arr);
    }

    @Test
    public void shouldFindAllPatternInstanceInText2() {
        PatternSearch as = new DeterministicFiniteAutomatonTextSearch("DGD");
        int[] arr = as.findAll("DGDGABDGDABAB");
        assertArrayEquals(new int[] { 0, 6 }, arr);
    }

    @Test
    public void shouldFindNoPatternInstanceInText() {
        PatternSearch as = new DeterministicFiniteAutomatonTextSearch("DGD");
        int[] arr = as.findAll("FFFFFFF");
        assertArrayEquals(new int[0], arr);
    }
}
