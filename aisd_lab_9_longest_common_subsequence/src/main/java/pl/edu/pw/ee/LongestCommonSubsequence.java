package pl.edu.pw.ee;

class LongestCommonSubsequence {
    private String firstStr;
    private String secondStr;
    private Elem[][] letterPairs;
    private boolean matrixInitiated;

    public LongestCommonSubsequence(String firstStr, String secondStr) {
        this.firstStr = firstStr;
        this.secondStr = secondStr;
        this.matrixInitiated = false;
    }

    public String findLCS() {
        validateStrings();
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

        this.matrixInitiated = true;
        return gatherLCSFromPath();
    }

    public void display() {
        if (!matrixInitiated) {
            findLCS();
        }

        int I = firstStr.length() + 1;
        int J = secondStr.length() + 1;

        System.out.printf((" ".repeat(8) + "|").repeat(2));
        for (int i = 0; i < secondStr.length(); i++) {
            String c = whitespaceHandler(secondStr.charAt(i), true);
            System.out.printf("%1$7s |", c);
        }
        System.out.println("\n" + ("-".repeat(8) + "+").repeat(J + 1));

        for (int i = 0; i < I; i++) {
            if (i > 0) {
                String c = whitespaceHandler(firstStr.charAt(i - 1), true);
                System.out.printf("%1$7s |", c);
            } else {
                System.out.printf(" ".repeat(8) + "|");
            }

            for (int j = 0; j < J; j++) {
                System.out.printf("%1$7s |", letterPairs[i][j]);
            }

            System.out.println("\n" + ("-".repeat(8) + "+").repeat(J + 1));
        }
    }

    private void validateStrings() {
        if (firstStr == null || secondStr == null) {
            throw new IllegalArgumentException("Input strings cannot be null!");
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
                String toAdd = whitespaceHandler(firstStr.charAt(i - 1), false);
                LCS = toAdd + LCS;
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

    private String whitespaceHandler(Character c, boolean draw) {
        if (c == '\b') {
            return "\\b";
        } else if (c == '\t') {
            return "\\t";
        } else if (c == '\n') {
            return "\\n";
        } else if (c == '\f') {
            return "\\f";
        } else if (c == '\r') {
            return "\\r";
        } else if (c == '\'') {
            return "\\'";
        } else if (c == '\"') {
            return "\\\"";
        } else if (c == '\\') {
            return "\\";
        } else if (c == ' ') {
            if (draw) {
                return "\\s";
            } else {
                return " ";
            }
        } else {
            return c.toString();
        }
    }
}
