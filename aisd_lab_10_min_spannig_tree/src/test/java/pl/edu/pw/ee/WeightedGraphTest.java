package pl.edu.pw.ee;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class WeightedGraphTest {
    @Test
    public void addConnectionTest() {
        WeightedGraphOld graph = new WeightedGraphOld();

        graph.addEdge("A", "C", 3);
        graph.addEdge("A", "E", 3);
        graph.addEdge("A", "B", 35);
        graph.addEdge("A", "D", 3);
        graph.addEdge("A", "D", 40);
        graph.addEdge("A", "B", 32);

        graph.addEdge("G", "E", 3);
        graph.addEdge("G", "D", 3);

        for (int i = 0; i < graph.nodesCons.size(); i++) {
            NodeOld iter = graph.nodesCons.get(i);
            System.out.printf("Nodes visible from %s:\n", iter.getValue());
            iter = iter.getConnection();

            while(iter != null) {
                System.out.println(iter);
                iter = iter.getConnection();
            }
        }

        // System.out.println(graph.checkConectivity());
    }

    // @Test
    // public void isintest() {
    //     WeightedGraph graph = new WeightedGraph();

    //     graph.addEdge("A", "C", 3);
    //     graph.addEdge("A", "E", 3);
    //     graph.addEdge("C", "B", 35);
    //     graph.addEdge("B", "D", 3);
    //     graph.addEdge("A", "D", 40);
    //     graph.addEdge("A", "B", 32);
    //     graph.addEdge("G", "E", 3);
    //     graph.addEdge("G", "D", 3);

    //     for (Node node : graph.nodesCons) {
    //         Node testnode = new Node(node.getValue());
    //         System.out.println(node);
    //         System.out.println(testnode);
    //         System.out.println(graph.nodeIsIn(graph.nodesCons, testnode));
    //     }
    // }

    // private void addConnection(WeightedGraph graph) {
    //     try {
    //         Method printht = castedHash.getClass().getSuperclass().getDeclaredMethod("printHashTab");
    //         printht.setAccessible(true);
    //         printht.invoke(castedHash);

    //     } catch (NoSuchMethodException | IllegalArgumentException | IllegalAccessException
    //             | InvocationTargetException e) {
    //         throw new RuntimeException(e);
    //     }
    // }
}
