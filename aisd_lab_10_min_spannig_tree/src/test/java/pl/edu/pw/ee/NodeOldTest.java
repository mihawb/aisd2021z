package pl.edu.pw.ee;

import org.junit.Test;

public class NodeOldTest {
     @Test
     public void equalsTest() {
        NodeOld node1 = new NodeOld("value", 10);
        NodeOld node2 = new NodeOld("value", 55);
        node1.setConnection(node2);
        System.out.println("TEST EQUALS NODE " + node1.equals(node2));
     }
}
