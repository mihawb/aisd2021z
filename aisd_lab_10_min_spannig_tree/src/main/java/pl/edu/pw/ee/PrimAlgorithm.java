package pl.edu.pw.ee;

import pl.edu.pw.ee.services.MinSpanningTree;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.util.Collections;

public class PrimAlgorithm implements MinSpanningTree {
    WeightedGraph mst;
    ArrayList<Edge> edgesPQ;

    public PrimAlgorithm() {
        this.mst = new WeightedGraph();
        this.edgesPQ = new ArrayList<>();
    }

    public String findMST(String pathToFile) {
        if (pathToFile == null) {
            throw new IllegalArgumentException();
        }

        WeightedGraph graph = new WeightedGraph();
        ArrayList<Edge> edgesPQ = new ArrayList<>();
        ArrayList<Node> visited = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(pathToFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                int ind1 = line.indexOf(" ");
                int ind2 = line.indexOf(" ", ind1 + 1);

                if (ind1 == -1 || ind2 == -1) {
                    throw new IllegalArgumentException("Invalid data format!");
                }

                String nodeValue1 = line.substring(0, ind1);
                String nodeValue2 = line.substring(ind1 + 1, ind2);
                int weight;

                try {
                    weight = Integer.parseInt(line.substring(ind2 + 1, line.length()));
                } catch (Exception e) {
                    throw new NumberFormatException("Invalid data format!");
                }

                graph.addEdge(nodeValue1, nodeValue2, weight);
                graph.addEdge(nodeValue2, nodeValue1, weight);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Could not load specified file!", e);
        } catch (IOException e) {
            throw new RuntimeException("Opening or closing of Buffered Reader failed!", e);
        }

        if (graph.getNodesQuantity() == 0) {
            throw new IllegalArgumentException("No min spanning tree can be found in an empty graph!");
        }

        if (!graph.checkConnectivity()) {
            throw new IllegalArgumentException("Provided graph is not connected!");
        }

        Node tmpNode = new Node(graph.getNode(0).getValue());
        mst.addEdge(tmpNode.getValue(), tmpNode.getValue(), 0);

        while (mst.getNodesQuantity() != graph.getNodesQuantity()) {
            visited.add(new Node(tmpNode.getValue()));
            tmpNode = graph.getNode(tmpNode.getValue());

            Node iter = tmpNode.getConnection();
            while (iter != null) {
                if (visited.indexOf(iter) == -1) {
                    edgesPQ.add(new Edge(tmpNode.getValue(), iter.getValue(), iter.getWeight()));
                }
                iter = iter.getConnection();
            }

            Collections.sort(edgesPQ);

            Edge lEdge = edgesPQ.remove(0);
            mst.addEdge(lEdge.getFromNode(), lEdge.getToNode(), lEdge.getWeight());
            tmpNode = new Node(lEdge.getToNode());

            int i = 0;
            while (i < edgesPQ.size()) {
                if (edgesPQ.get(i).getToNode().equals(tmpNode.getValue())) {
                    edgesPQ.remove(i);
                    continue;
                }
                i++;
            }
        }

        return mst.getOutputString();
    }
}
