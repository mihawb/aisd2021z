package pl.edu.pw.ee;

class LongestCommonSubsequence {
    String firstStr;
    String secondStr;
    Elem[][] letterPairs;
    boolean foundLCS;

    public LongestCommonSubsequence(String firstStr, String secondStr) {
        this.firstStr = firstStr;
        this.secondStr = secondStr;
        this.foundLCS = false;
    }

    public String findLCS() {
        int I = this.firstStr.length() + 1;
        int J = this.secondStr.length() + 1;
        letterPairs = new Elem[I][J];
        
        return null;
    }

    public void display() {
        if (!foundLCS) {
            findLCS();
        }
    }

    class Elem {
        int value;
        char path;
        boolean draw;

        Elem(int value) {
            this.value = value;
        }
    }
}
