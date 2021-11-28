package pl.edu.pw.ee;

public class HashDoubleHashing<T extends Comparable<T>> extends HashOpenAdressing<T> {
    public HashDoubleHashing() {
        super();
    }

    public HashDoubleHashing(int size) {
        super(size);
    }

    @Override
    int hashFunc(int key, int i) {
        int m = getSize();

        int hash = (key % m + i * hashGunc(key)) % m;

        hash = hash < 0 ? -hash : hash;

        return hash;
    }

    private int hashGunc(int key) {
        int m = getSize();

        int hash = key % (m - 3);

        if (hash == 0)
            return 1; ////////////????????????????????????

        hash = hash < 0 ? -hash : hash;

        return hash;
    }
}
