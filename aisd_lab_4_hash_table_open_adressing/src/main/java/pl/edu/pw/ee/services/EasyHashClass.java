package pl.edu.pw.ee.services;

public class EasyHashClass implements Comparable<EasyHashClass> {
    
    String name;

    public EasyHashClass(String str) {
        this.name = str;
    }

    @Override
    public int hashCode() {
        return this.name.length() * 3;
    }

    @Override
    public int compareTo(EasyHashClass other) {
        if (other == null)
            throw new NullPointerException();
        else
            return this.name.length() - other.name.length();
    }
    
    @Override
    public boolean equals(Object other) {
        if (other == null)
            return false;
        
        if (other.getClass() != this.getClass())
            return false;
        
        final EasyHashClass otherObj = (EasyHashClass) other;
        
        if (!otherObj.name.equals(this.name))
            return false;

        return true;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
