package pl.edu.pw.ee;

public class Elem implements Comparable<Elem> {
    private int value;
    private char path;
    private boolean drawable;

    Elem(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (getClass() != other.getClass()) {
            return false;
        }
        Elem otherElem = (Elem) other;

        return this.value == otherElem.value;
    }

    @Override
    public int compareTo(Elem other) {
        return this.value - other.value;
    }

    @Override
    public String toString() {
        if (drawable) {
            return path + " " + value;
        } else {
            return "" + value;
        }
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public void setPath(char path) {
        this.path = path;
    }

    public char getPath() {
        return this.path;
    }

    public void setDrawState(boolean drawable) {
        this.drawable = drawable;
    }

    public boolean isDrawable() {
        return this.drawable;
    }
}
