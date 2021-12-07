package pl.edu.pw.ee;

class Node implements Comparable<Node> { 
    private int freq;
    private Character character = null;
    private String code;
    public Node left = null;
    public Node right = null;

    public Node(char c, int f) {
        freq = f;
        character = c;
        code = "";
    }

    public Node(int f) {
        freq = f;
        code = "";
    }

    public int getFreq() {
        return freq;
    }

    public Character getCharacter() {
        return character;
    }

    public boolean isLeaf() {
        return character != null;
    }

    public void setCode(String c) {
        this.code = c;
    }

    public String getCode() {
        return this.code;
    }

    public void setLeft(Node l) {
        this.left = l;
    }

    public void setRight(Node r) {
        this.right = r;
    }

    public Node getLeft() {
        return this.left;
    }

    public Node getRight() {
        return this.right;
    }

    @Override
    public String toString() {
        if (character == null) {
            return "Container node of frequency " + freq + " with code " + code;
        } else {
            return "Node " + character + " of frequency " + freq + " with code " + code;
        }
    }
    
    @Override
    public int compareTo(Node other) {
        if (this.freq > other.freq) {
            return 1;
        } else if (this.freq < other.freq) {
            return -1;
        } else if (!this.isLeaf() && other.isLeaf()) {
            return 1;
        } else if (this.isLeaf() && !other.isLeaf()) {
            return -1;
        } else {
            return 0;
        }
    }
}