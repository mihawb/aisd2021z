package pl.edu.pw.ee;

import java.util.ArrayList;

public class WeightedGraph {
    private ArrayList<Node> nodesCons;

    public WeightedGraph() {
        this.nodesCons = new ArrayList<>();
    }

    public int getNodesQuantity() {
        return nodesCons.size();
    }

    public Node getNode(int index) {
        return nodesCons.get(index);
    }

    public Node getNode(String value) {
        int index = nodesCons.indexOf(new Node(value));
        if (index == -1) {
            return null;
        }
        return nodesCons.get(index);
    }

    public void addNode(Node node) {
        int index = nodesCons.indexOf(node);
        if (index == -1) {
            nodesCons.add(node);
            return;
        }
        String from = nodesCons.get(index).getValue();
        Node iter = node.getConnection();
        while(iter != null) {
            addEdge(from, iter.getValue(), iter.getWeight());
        }
    }

    public void addEdge(String from, String to, int weight) {
        Node fromNode = new Node(from);
        
        if (from.equals(to) && weight == 0) {
            nodesCons.add(fromNode);
            return;
        }

        Node toNode = new Node(to, weight);
        Node toNodeCopy = new Node(to);

        if (nodesCons.indexOf(fromNode) != -1) {
            int ind = nodesCons.indexOf(fromNode);
            fromNode = nodesCons.get(ind);

            fromNode.addConnectedNode(toNode);
        } else {
            fromNode.addConnectedNode(toNode);
            nodesCons.add(fromNode);
        }

        if (nodesCons.indexOf(toNodeCopy) == -1) {
            nodesCons.add(toNodeCopy);
        }
    }

    public boolean checkConnectivity() {
        ArrayList<Node> visited = new ArrayList<>();

        visited = checkConnectivity(visited, nodesCons.get(0));

        return visited.size() == nodesCons.size();
    }

    private ArrayList<Node> checkConnectivity(ArrayList<Node> visited, Node node) {
        if (visited.indexOf(node) != -1) {
            return visited;
        }
        visited.add(node);

        Node iter = node.getConnection();
        while (iter != null) {
            int indOfNodeToVisit = nodesCons.indexOf(iter);
            visited = checkConnectivity(visited, nodesCons.get(indOfNodeToVisit));
            iter = iter.getConnection();
        }

        return visited;
    }

    public String getOutputString() {
        String result = "";

        for (Node node : nodesCons) {
            String n1 = node.getValue();
            Node iter = node.getConnection();
            while (iter != null) {
                result += n1 + "_" + iter.getWeight() + "_" + iter.getValue() + "|";
                iter = iter.getConnection();
            }
        }

        return result.substring(0, result.length() - 1);
    }

    public void printGraph() {
        for (int i = 0; i < nodesCons.size(); i++) {
            Node iter = nodesCons.get(i);
            System.out.printf("Nodes visible from %s:\n", iter.getValue());
            iter = iter.getConnection();

            while(iter != null) {
                System.out.println(iter);
                iter = iter.getConnection();
            }
        }
    }
}
