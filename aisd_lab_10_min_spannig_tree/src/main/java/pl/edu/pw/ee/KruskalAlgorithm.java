package pl.edu.pw.ee;

import pl.edu.pw.ee.services.MinSpanningTree;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.util.Collections;

public class KruskalAlgorithm implements MinSpanningTree {
    public String findMST(String pathToFile) {
        if (pathToFile == null) {
            throw new IllegalArgumentException();
        }

        WeightedGraph graph = new WeightedGraph();
        ArrayList<WeightedGraph> forest = new ArrayList<>();
        ArrayList<Edge> edgesPQ = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(pathToFile))) {
            ArrayList<String> added = new ArrayList<>();

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

                // repeating edges not handled
                Edge newedge = new Edge(nodeValue1, nodeValue2, weight);
                if (edgesPQ.indexOf(newedge) == -1) {
                    edgesPQ.add(newedge);
                }

                if (added.indexOf(nodeValue1) == -1) {
                    added.add(nodeValue1);
                    WeightedGraph mst = new WeightedGraph();
                    mst.addEdge(nodeValue1, nodeValue1, 0);
                    forest.add(mst);
                }

                if (added.indexOf(nodeValue2) == -1) {
                    added.add(nodeValue2);
                    WeightedGraph mst = new WeightedGraph();
                    mst.addEdge(nodeValue2, nodeValue2, 0);
                    forest.add(mst);
                }
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

        Collections.sort(edgesPQ);

        while (edgesPQ.size() > 0) {
            Edge e = edgesPQ.remove(0);

            int indNode1, indNode2;

            for (indNode1 = 0; indNode1 < forest.size(); indNode1++) {
                if (forest.get(indNode1).getNode(e.getFromNode()) != null) {
                    break;
                }
            }

            for (indNode2 = 0; indNode2 < forest.size(); indNode2++) {
                if (forest.get(indNode2).getNode(e.getToNode()) != null) {
                    break;
                }
            }

            if (indNode1 != indNode2) {
                WeightedGraph newMST = forest.get(indNode1);
                for (int i = 0; i < forest.get(indNode2).getNodesQuantity(); i++) {
                    newMST.addNode(forest.get(indNode2).getNode(i));
                }
                newMST.addEdge(e.getFromNode(), e.getToNode(), e.getWeight());
                forest.set(indNode1, newMST);
                forest.remove(indNode2);
            }
        }

        return forest.get(0).getOutputString();
    }
}
