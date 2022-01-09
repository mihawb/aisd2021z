package pl.edu.pw.ee;

public class Node implements Comparable<Node> {
    private String value;
    private Integer weight;
    private Node con;

    public Node(String value) {
        this.value = value;
        this.weight = null;
        this.con = null;
    }

    public Node(String value, int weight) {
        this.value = value;
        this.weight = weight;
        this.con = null;
    }

    public void setConnection(Node con) {
        this.con = con;
    }

    public String getValue() {
        return value;
    }

    public int getWeight() {
        return weight;
    }

    public Node getConnection() {
        return con;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (this == other) {
            return true;
        }
        if (getClass() != other.getClass()) {
            return false;
        }
        Node otherNode = (Node) other;

        return this.value.equals(otherNode.value);
    }

    @Override
    public String toString() {
        return "Node " + value + " with cost " + weight;
    }

    @Override
    public int compareTo(Node other) {
        return this.value.compareTo(other.value);
    }

    public void addConnectedNode(Node to) {
        // edges repeating in source file will not be handled
        if (this.getConnection() == null) {
            this.setConnection(to);
        } else if (to.compareTo(this.getConnection()) < 0) {
            to.setConnection(this.getConnection());
            this.setConnection(to);
        } else {
            Node iter = this.getConnection();
            while(to.compareTo(iter) > 0) {
                if (iter.getConnection() == null) {
                    iter.setConnection(to);
                    return;
                } else if (to.compareTo(iter.getConnection()) < 0) {
                    to.setConnection(iter.getConnection());
                    iter.setConnection(to);
                    return;
                }
                iter = iter.getConnection();
            }
        }
    }
}
