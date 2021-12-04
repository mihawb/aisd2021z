package pl.edu.pw.ee;

public class HashQuadraticProbing<T extends Comparable<T>> extends HashOpenAdressing<T> {
    private double a;
    private double b;

    public HashQuadraticProbing() {
        super();
        this.a = 1.5;
        this.b = 5.1;
    }

    public HashQuadraticProbing(int size, double a, double b) {
        super(size);
        this.a = a;
        this.b = b;
    }

    @Override
    int hashFunc(int key, int i) {
        int m = getSize();

        int hash = (int) (key + a * i + b * i * i) % m;

        hash = hash < 0 ? -hash : hash;

        return hash;
    }
}
