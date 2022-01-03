package pl.edu.pw.ee;

import java.util.ArrayList;

public class WeightedGraphOld {
    // ZMIENIC NA PRIVATE POTEM
    public ArrayList<NodeOld> nodesCons;

    public WeightedGraphOld() {
        this.nodesCons = new ArrayList<>();
    }

    public void addEdge(String from, String to, int weight) {
        NodeOld toNode = new NodeOld(to, weight);
        NodeOld fromNode = new NodeOld(from);

        if (nodeIsIn(nodesCons, fromNode)) {
            int ind = nodesCons.indexOf(fromNode);
            fromNode = nodesCons.get(ind);

            fromNode.addConnectedNode(toNode);
        } else {
            fromNode.addConnectedNode(toNode);
            nodesCons.add(fromNode);
        }
    }

    // zmienic na private
    private boolean nodeIsIn(ArrayList<NodeOld> list, NodeOld toCheck) {
        int n = list.size();
        boolean result = false;

        for (int i = 0; i < n; i++) {
            result |= list.get(i).equals(toCheck);
        }

        return result;
    }

    private int modIndexOf(ArrayList<NodeOld> list, NodeOld node) {
        int n = list.size();

        for (int i = 0; i < n; i++) {
            if (list.get(i).equals(node)) {
                return i;
            }
        }

        return -1;
    }

    public boolean checkConnectivity() {
        ArrayList<NodeOld> visited = new ArrayList<>();

        visited.add(new NodeOld(nodesCons.get(0).getValue()));
        visited = checkConnectivity(visited, nodesCons.get(0));

        return visited.size() == nodesCons.size();
    }

    private ArrayList<NodeOld> checkConnectivity(ArrayList<NodeOld> visited, NodeOld node) {
        if (node.getConnection() == null) {
            return visited;
        }

        NodeOld iter = node.getConnection();
        while (iter != null) {
            if (!nodeIsIn(visited, iter)) {
                visited.add(new NodeOld(iter.getValue()));
                visited = checkConnectivity(visited, iter);
            }
            iter = iter.getConnection();
        }

        visited = checkConnectivity(visited, node);

        return visited;
    }
}
