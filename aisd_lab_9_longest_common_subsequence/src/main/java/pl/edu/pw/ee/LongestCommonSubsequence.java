package pl.edu.pw.ee;

class LongestCommonSubsequence {
    private String firstStr;
    private String secondStr;
    private Elem[][] letterPairs;
    private boolean foundLCS;

    public LongestCommonSubsequence(String firstStr, String secondStr) {
        this.firstStr = firstStr;
        this.secondStr = secondStr;
        this.foundLCS = false;
    }

    public String findLCS() {
        initialiseMatrix();
        int I = firstStr.length() + 1;
        int J = secondStr.length() + 1;

        for (int i = 1; i < I; i++) {
            for (int j = 1; j < J; j++) {
                if (firstStr.charAt(i - 1) == secondStr.charAt(j - 1)) {
                    int newV = letterPairs[i - 1][j - 1].getValue() + 1;
                    letterPairs[i][j].setValue(newV);
                    letterPairs[i][j].setPath('\\');
                } else if (letterPairs[i][j - 1].compareTo(letterPairs[i - 1][j]) > 0) {
                    int newV = letterPairs[i][j - 1].getValue();
                    letterPairs[i][j].setValue(newV);
                    letterPairs[i][j].setPath('<');
                } else {
                    int newV = letterPairs[i - 1][j].getValue();
                    letterPairs[i][j].setValue(newV);
                    letterPairs[i][j].setPath('^');
                }
            }
        }

        this.foundLCS = true;
        return gatherLCSFromPath();
    }

    public void display() {
        if (!foundLCS) {
            findLCS();
        }

        int I = firstStr.length() + 1;
        int J = secondStr.length() + 1;

        System.out.printf((" ".repeat(8) + "|").repeat(2));
        for (int i = 0; i < secondStr.length(); i++) {
            System.out.printf("%1$7s |", secondStr.charAt(i));
        }
        System.out.println("\n" + "-".repeat(9 * (J + 1)));

        for (int i = 0; i < I; i++) {
            if (i > 0) {
                System.out.printf("%1$7s |", firstStr.charAt(i - 1));
            } else {
                System.out.printf(" ".repeat(8) + "|");
            }

            for (int j = 0; j < J; j++) {
                System.out.printf("%1$7s |", letterPairs[i][j]);
            }

            System.out.println("\n" + "-".repeat(9 * (J + 1)));
        }
    }

    private void initialiseMatrix() {
        int I = firstStr.length() + 1;
        int J = secondStr.length() + 1;
        letterPairs = new Elem[I][J];

        for (int i = 0; i < I; i++) {
            for (int j = 0; j < J; j++) {
                letterPairs[i][j] = new Elem(0);
            }
        }
    }

    private String gatherLCSFromPath() {
        String LCS = "";

        int i = firstStr.length();
        int j = secondStr.length();

        while (letterPairs[i][j].getValue() != 0) {
            letterPairs[i][j].setDrawState(true);
            char whereTo = letterPairs[i][j].getPath();
            if (whereTo == '\\') {
                LCS = firstStr.charAt(i - 1) + LCS;
                i--;
                j--;
            } else if (whereTo == '<') {
                j--;
            } else if (whereTo == '^') {
                i--;
            }
        }

        return LCS.equals("") ? null : LCS;
    }
}
