package pl.edu.pw.ee;

class Node implements Comparable<Node> { 
    private int freq;
    private Integer character;
    private String code;
    public Node left = null;
    public Node right = null;

    public Node(int character, int freq) {
        this.freq = freq;
        this.character = character;
        this.code = "";
    }

    public Node(int freq) {
        this.freq = freq;
        this.character = null;
        this.code = "";
    }

    public Node(int character, String code) {
        this.character = character;
        this.code = code;
    }

    public Node(int character, int freq, String code) {
        this.freq = freq;
        this.character = character;
        this.code = code;
    }

    public Node() {
        this.character = null;
    }

    public int getFreq() {
        return freq;
    }

    public void setFreq(int freq) {
        this.freq = freq;
    }

    public void incrementFreq() {
        this.freq++;
    }

    public int getCharacter() {
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

    public String findCodeOfNode(int c) {
        String result = null;
        
        if (this.getLeft() == null && this.getRight() == null) {
            if (character == c) {
                result = this.code;
            } 
        } else {
            result = this.getLeft().findCodeOfNode(c);
            if (result == null) {
                result = this.getRight().findCodeOfNode(c);
            }
        }

        return result;
    }
}