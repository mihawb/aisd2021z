package pl.edu.pw.ee;

public class NodeOld implements Comparable<NodeOld> {
    private String value;
    private Integer weight;
    private NodeOld con;

    public NodeOld(String value) {
        this.value = value;
        this.weight = null;
        this.con = null;
    }

    public NodeOld(String value, int weight) {
        this.value = value;
        this.weight = weight;
        this.con = null;
    }

    public void setConnection(NodeOld con) {
        this.con = con;
    }

    public String getValue() {
        return value;
    }

    public int getWeight() {
        return weight;
    }

    public NodeOld getConnection() {
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
        NodeOld otherNode = (NodeOld) other;

        return this.value.equals(otherNode.value);
    }

    @Override
    public String toString() {
        return "Node " + value + " with cost " + weight;
    }

    @Override
    public int compareTo(NodeOld other) {
        return this.value.compareTo(other.value);
    }

    public void addConnectedNode(NodeOld to) {
        // edges repeating in source file will not be handled, idc
        if (this.getConnection() == null) {
            this.setConnection(to);
        } else if (to.compareTo(this.getConnection()) < 0) {
            to.setConnection(this.getConnection());
            this.setConnection(to);
        } else {
            NodeOld iter = this.getConnection();
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
