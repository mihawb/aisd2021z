package pl.edu.pw.ee;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import pl.edu.pw.ee.services.HashTable;
import pl.edu.pw.ee.services.EasyHashClass;

public class HashLinearProbingTest {
    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_WhenInitialSizeIsLowerThanOne() {
        int initialSize = 0;

        HashTable<Double> hash = new HashLinearProbing<>(initialSize);

        assertTrue(false);
    }

    @Test
    public void should_CorrectlyAddNewElems_WhenNotExistInHashTable() {
        HashTable<String> emptyHash = new HashLinearProbing<>();
        String newEleme = "nothing special";

        int nOfElemsBeforePut = getNumOfElems(emptyHash);
        emptyHash.put(newEleme);
        int nOfElemsAfterPut = getNumOfElems(emptyHash);

        assertEquals(0, nOfElemsBeforePut);
        assertEquals(1, nOfElemsAfterPut);
    }

    @Test
    public void should_CorrectlyAddNewElems_DifferentHashes() {
        HashTable<EasyHashClass> nonEmptyHash = new HashLinearProbing<>();
        EasyHashClass [] newElems = new EasyHashClass[4];

        for (int i = 0; i < 4; i++)
            newElems[i] = new EasyHashClass("a".repeat(i+1));

        int nOfElemsBeforePut = getNumOfElems(nonEmptyHash);
        for (int i = 0; i < 4; i++)
            nonEmptyHash.put(newElems[i]);
        int nOfElemsAfterPut = getNumOfElems(nonEmptyHash);

        assertEquals(0, nOfElemsBeforePut);
        assertEquals(4, nOfElemsAfterPut);
    }

    @Test
    public void should_CorrectlyGetNewElems_DifferentHashes() {
        HashTable<EasyHashClass> nonEmptyHash = new HashLinearProbing<>();
        EasyHashClass [] newElems = new EasyHashClass[4];

        for (int i = 0; i < 4; i++)
            newElems[i] = new EasyHashClass("a".repeat(i+1));

        for (int i = 0; i < 4; i++)
            nonEmptyHash.put(newElems[i]);

        EasyHashClass two = nonEmptyHash.get(new EasyHashClass("aa"));
        EasyHashClass four = nonEmptyHash.get(new EasyHashClass("aaaa"));

        EasyHashClass properTwo = new EasyHashClass("aa");
        EasyHashClass properFour = new EasyHashClass("aaaa");

        assertEquals(properTwo, two);
        assertEquals(properFour, four);
    }

    @Test
    public void should_CorrectlyAddNewElems_SameHashes() {
        HashTable<EasyHashClass> nonEmptyHash = new HashLinearProbing<>();
        EasyHashClass [] newElems = new EasyHashClass[6];

        for (int i = 0; i < 4; i++)
            newElems[i] = new EasyHashClass("a".repeat(i+1));
        newElems[4] = new EasyHashClass("b".repeat(3));
        newElems[5] = new EasyHashClass("c".repeat(3));

        int nOfElemsBeforePut = getNumOfElems(nonEmptyHash);
        for (int i = 0; i < 6; i++)
            nonEmptyHash.put(newElems[i]);
        int nOfElemsAfterPut = getNumOfElems(nonEmptyHash);

        assertEquals(0, nOfElemsBeforePut);
        assertEquals(6, nOfElemsAfterPut);
    }

    @Test
    public void should_CorrectlyGetNewElems_SameHashes() {
        HashTable<EasyHashClass> nonEmptyHash = new HashLinearProbing<>();
        EasyHashClass [] newElems = new EasyHashClass[6];

        for (int i = 0; i < 4; i++)
            newElems[i] = new EasyHashClass("a".repeat(i+1));
        newElems[4] = new EasyHashClass("b".repeat(3));
        newElems[5] = new EasyHashClass("c".repeat(3));

        for (int i = 0; i < 6; i++)
            nonEmptyHash.put(newElems[i]);

        EasyHashClass a3 = nonEmptyHash.get(new EasyHashClass("aaa"));
        EasyHashClass b3 = nonEmptyHash.get(new EasyHashClass("bbb"));
        EasyHashClass c3 = nonEmptyHash.get(new EasyHashClass("ccc"));

        EasyHashClass properA3 = new EasyHashClass("aaa");
        EasyHashClass properB3 = new EasyHashClass("bbb");
        EasyHashClass properC3 = new EasyHashClass("ccc");

        assertEquals(properA3, a3);
        assertEquals(properB3, b3);
        assertEquals(properC3, c3);

        // ((HashLinearProbing<EasyHashClass>)nonEmptyHash).printhashtab();
    }

    @Test
    public void should_CorrectlyDeleteAndGetNewElems_SameHashes() {
        HashTable<EasyHashClass> nonEmptyHash = new HashLinearProbing<>();
        EasyHashClass [] newElems = new EasyHashClass[6];

        for (int i = 0; i < 4; i++)
            newElems[i] = new EasyHashClass("a".repeat(i+1));
        newElems[4] = new EasyHashClass("b".repeat(3));
        newElems[5] = new EasyHashClass("c".repeat(3));

        for (int i = 0; i < 6; i++)
            nonEmptyHash.put(newElems[i]);
        nonEmptyHash.delete(new EasyHashClass("bbb"));

        EasyHashClass a3 = nonEmptyHash.get(new EasyHashClass("aaa"));
        EasyHashClass b3 = nonEmptyHash.get(new EasyHashClass("bbb"));
        EasyHashClass c3 = nonEmptyHash.get(new EasyHashClass("ccc"));

        EasyHashClass properA3 = new EasyHashClass("aaa");
        EasyHashClass properB3 = null;
        EasyHashClass properC3 = new EasyHashClass("ccc");

        assertEquals(properA3, a3);
        assertEquals(properB3, b3);
        assertEquals(properC3, c3);

        // ((HashLinearProbing<EasyHashClass>)nonEmptyHash).printhashtab();
        printHashTable(nonEmptyHash);
    }

    private int getNumOfElems(HashTable<?> hash) {
        String fieldNumOfElems = "nElems";
        try {
            // System.out.println(hash.getClass().getSuperclass().getName());
            Field field = hash.getClass().getSuperclass().getDeclaredField(fieldNumOfElems);
            field.setAccessible(true);

            int numOfElems = field.getInt(hash);

            return numOfElems;

        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private void printHashTable(HashTable<?> hash) {
        HashOpenAdressing<?> castedHash = (HashOpenAdressing<?>) hash;

        try {
            Method printht = castedHash.getClass().getSuperclass().getDeclaredMethod("printhashtab");
            printht.setAccessible(true); 
            printht.invoke(castedHash);

        } catch (NoSuchMethodException | IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
