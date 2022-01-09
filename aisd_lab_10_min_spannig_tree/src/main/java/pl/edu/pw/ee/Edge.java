package pl.edu.pw.ee;

public class Edge implements Comparable<Edge> {
    private String from;
    private String to;
    private int weight;

    public Edge(String from, String to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public String getFromNode() {
        return from;
    }

    public String getToNode() {
        return to;
    }

    public int getWeight() {
        return weight;
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
        Edge otherEdge = (Edge) other;

        if ((this.from.equals(otherEdge.from) && this.to.equals(otherEdge.to))
                || (this.from.equals(otherEdge.to) && this.to.equals(otherEdge.from))) {
            return true;
        }

        return false;
    }

    @Override
    public int compareTo(Edge other) {
        return this.weight - other.weight;
    }

    @Override
    public String toString() {
        return "From " +  this.from + " to " + this.to + " with cost " + this.weight;
    }
}
