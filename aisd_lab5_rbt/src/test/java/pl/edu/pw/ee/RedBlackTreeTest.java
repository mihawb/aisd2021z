package pl.edu.pw.ee;

import java.lang.reflect.Field;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

public class RedBlackTreeTest {
    RedBlackTree<String, String> rbt;

    @Before
    public void setup() {
        rbt = new RedBlackTree<>();
    }

    @After
    public void teardown() {
        rbt = null;
    }

    @Test
    public void setAndGetTest() {
        rbt.put("J", "JOT");
        rbt.put("A", "A");
        rbt.put("C", "CE");
        rbt.put("E", "E");
        rbt.put("K", "KA");

        assertEquals("KA", rbt.get("K"));
        assertEquals("CE", rbt.get("C"));
        assertEquals("JOT", rbt.get("J"));
    }

    @Test
    public void traverseEmptyRBT() {
        assertNull(rbt.getInOrder());
    }

    @Test
    public void traverseAllOrders() {
        rbt.put("K", "1");
        rbt.put("A", "2");
        rbt.put("P", "3");
        rbt.put("E", "4");
        rbt.put("L", "5");
        rbt.put("U", "6");
        rbt.put("S", "7");
        rbt.put("Z", "7");

        String inResult = rbt.getInOrder();
        String preResult = rbt.getPreOrder();
        String postResult = rbt.getPostOrder();

        assertEquals("A:2 E:4 K:1 L:5 P:3 S:7 U:6 Z:7", inResult);
        assertEquals("P:3 K:1 E:4 A:2 L:5 U:6 S:7 Z:7", preResult);
        assertEquals("A:2 E:4 L:5 K:1 S:7 Z:7 U:6 P:3", postResult);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void deleteFromEmptyRBT() {
        rbt.deleteMax();
    }

    @Test
    public void tescikDelete() {
        for (int i = 65; i < 80; i++) {
            rbt.put(Character.toString((char) i), Character.toString((char) i));
        }

        String preDeletion = getPreOrderFromPrivateRoot(rbt);
        rbt.deleteMax();
        String afterOneDeletion = getPreOrderFromPrivateRoot(rbt);
        rbt.deleteMax();
        String afterTwoDeletions = getPreOrderFromPrivateRoot(rbt);

        assertEquals(
                "H:H:BLACK D:D:BLACK B:B:BLACK A:A:BLACK C:C:BLACK F:F:BLACK E:E:BLACK G:G:BLACK L:L:BLACK J:J:BLACK I:I:BLACK K:K:BLACK N:N:BLACK M:M:BLACK O:O:BLACK",
                preDeletion);
        assertEquals(
                "H:H:BLACK D:D:RED B:B:BLACK A:A:BLACK C:C:BLACK F:F:BLACK E:E:BLACK G:G:BLACK L:L:BLACK J:J:RED I:I:BLACK K:K:BLACK N:N:BLACK M:M:RED",
                afterOneDeletion);
        assertEquals(
                "H:H:BLACK D:D:RED B:B:BLACK A:A:BLACK C:C:BLACK F:F:BLACK E:E:BLACK G:G:BLACK L:L:BLACK J:J:RED I:I:BLACK K:K:BLACK M:M:BLACK",
                afterTwoDeletions);
    }

    private String getPreOrderFromPrivateRoot(RedBlackTree<?, ?> rbt) {
        try {
            Field rootField = rbt.getClass().getDeclaredField("root");
            rootField.setAccessible(true);
            Node<?, ?> r = (Node<?, ?>) rootField.get(rbt);

            String preOrder = getPreOrderWithColor(r);
            return preOrder.trim();
        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private String getPreOrderWithColor(Node<?, ?> node) {
        if (node == null) {
            throw new NullPointerException("Invalid node passed as a parameter!");
        }

        String result = "";
        String leftside = node.getLeft() != null ? getPreOrderWithColor(node.getLeft()) : "";
        String rightside = node.getRight() != null ? getPreOrderWithColor(node.getRight()) : "";

        result = node.toStringWithColor() + " " + leftside + rightside;

        return result;
    }
}
