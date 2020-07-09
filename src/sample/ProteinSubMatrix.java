package sample;


import java.io.BufferedReader;

import java.io.FileReader;


public class ProteinSubMatrix {

    private int[][] subMatrix;


    public ProteinSubMatrix() {
        this.subMatrix = new int[24][24];
    }

    public int[][] setSubMatrix(int[][] subMatrix) {
        return this.subMatrix = subMatrix;
    }


    public void blosum62Matrix() {
        this.subMatrix = readFile("/Users/alexng/BIO_CW_FINAL/BLOSUM62");
    }

    public void pam250Matrix() { this.subMatrix = readFile("/Users/alexng/BIO_CW_FINAL/PAM250");}

    public void blosum100Matrix() {this.subMatrix = readFile("/Users/alexng/BIO_CW_FINAL/BLOSUM100");}

    public void dayhoffMatrix() {this.subMatrix = readFile("/Users/alexng/BIO_CW_FINAL/DAYHOFF");}

    public void pam120Matrix(){this.subMatrix = readFile("/Users/alexng/BIO_CW_FINAL/PAM120");}

    public static void printIntMatrix(int[][] matrix) {

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }

    }


    public static int[][] readFile(String path) {
        int[][] matrix = new int[24][24];

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            int iCount = 0;
            while ((line = br.readLine()) != null) {

                if (!line.startsWith("#")) {
                    //System.out.println(line);
                    int jCount = 0;
                    String[] split = line.split(" ");
                    boolean allNum = false;
                    //System.out.println(Arrays.toString(split));
                    for (int i = 0; i < split.length; i++) {
                        if (!split[i].isEmpty()) {
                            try {
                                int num = Integer.parseInt(split[i]);
                                matrix[iCount][jCount] = num;
                                jCount++;
                                allNum = true;
                            } catch (NumberFormatException e) {
                                allNum = false;
                            }

                        }
                    }
                    if (allNum) {
                        iCount++;
                    }
                }
            }
            //printIntMatrix(matrix);
        } catch (Exception e) {
            System.out.println(e);
        }


        return matrix;
    }


    public static int convertBaseToIndex(String base) {
        String[] bases = new String[]{"A", "R", "N", "D", "C", "Q", "E", "G", "H", "I", "L", "K", "M", "F", "P", "S",
                "T", "W", "Y", "V", "B", "Z", "X", "*"};
        int output = 0;
        for (int i = 0; i < bases.length; i++) {
            if (base.equals(bases[i])) {
                output = i;
            }
        }
        return output;
    }

    public int getNumFromMatrix(String firstBase, String secondBase) {
        int firstIndex = convertBaseToIndex(firstBase);
        int secondIndex = convertBaseToIndex(secondBase);

        return this.subMatrix[firstIndex][secondIndex];
    }

    public static void main(String[] args) {
        ProteinSubMatrix p = new ProteinSubMatrix();
        p.blosum100Matrix();
        //printIntMatrix(p.subMatrix);
        //System.out.println(convertBaseToIndex("V"));
        System.out.println(p.getNumFromMatrix("A", "V"));
    }


}

