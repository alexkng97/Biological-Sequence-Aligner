package sample;


import java.util.ArrayList;
import java.util.Arrays;

public class NeedlemanWunsch {
    private String[] firstSequence;
    private String[] secondSequence;

    private int gapPenalty = -1;
    private int match = 0;
    private int mismatch = 0;
    private String scoringMatrix;

    private ArrayList<Cell> cellToPropagate;
    private ArrayList<String> directionToPropagate;
    private ArrayList<Cell> passedMultiMax;
    private ArrayList<ArrayList<String>> allPaths;
    private ArrayList<ArrayList<String>> allCopied;

    private Cell[][] matrix;


    public NeedlemanWunsch(String firstSeq, String secondSeq, String type, int gapPenalty, int match, int mismatch, String scoringMatrix) {
        if (type.equals("DNA")) {
            this.firstSequence = processDNASequence(firstSeq);
            this.secondSequence = processDNASequence(secondSeq);
        } else if (type.equals("Protein")) {
            this.firstSequence = processProteinSequence(firstSeq);
            this.secondSequence = processProteinSequence(secondSeq);
        }

        this.gapPenalty = gapPenalty;

        this.match = match;
        this.mismatch = mismatch;
        this.scoringMatrix = scoringMatrix;

        Cell[][] c = new Cell[secondSequence.length + 1][firstSequence.length + 1];
        for (int i = 0; i < c.length; i++) {
            for (int j = 0; j < c[i].length; j++) {
                //i is vertical (second sequence)
                //j is horizontal (first sequence)
                c[i][j] = new Cell(i, j);
            }

        }
        this.matrix = c;


        if (type.equals("DNA")) {
            fillDNAMatrix();
        } else if (type.equals("Protein")) {
            fillProteinMatrix();
        }
    }

    public Cell[][] getMatrix() {
        return matrix;
    }

    public String[] getFirstSequence(){return firstSequence;}

    public String[] getSecondSequence(){return secondSequence;}

    public int getValueIdentityMatrix(String firstBase, String secondBase) {
        if (firstBase.equals(secondBase)) {
            return match;
        } else {
            //change for mismatch
            return mismatch;
        }

    }

    public static String[] processDNASequence(String sequence) {
        String[] seq = sequence.split("");
        int count = 0;

        for (int i = 0; i < seq.length; i++) {
            if (seq[i].equals("A") || seq[i].equals("C") || seq[i].equals("G") || seq[i].equals("T")) {
                //do nothing
            } else {
                count++;
            }
        }
        if (count == 0) {
            return seq;
        } else {
            System.out.println("Invalid Base Entered");
            return null;
        }
    }


    public void printMatrix(Cell[][] matrix) {
        for (int k = 0; k < this.firstSequence.length + 1; k++) {
            if (k == 0) {
                System.out.print("\t\t----\t\t");
            } else {
                System.out.print("\t\t " + this.firstSequence[k - 1] + "\t\t\t");
            }
        }

        System.out.println();
        for (int i = 0; i < matrix.length; i++) {
            if (i == 0) {
                System.out.print("--  ");
            } else {
                System.out.print(this.secondSequence[i - 1] + "\t");
            }

            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j].printCell();
                if (i == 0 && j == 0) {
                    System.out.print("\t");
                }
                System.out.print("\t");
            }
            System.out.println();
        }
    }

    private void printMaxMatrix() {
        for(int i = 0; i< matrix.length; i++){
            for(int j = 0; j< matrix[i].length; j++){
                matrix[i][j].printMaxOfCell();
                System.out.print("\t");
            }
            System.out.println();
        }
    }

    public void fillDNAMatrix() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (i == 0) {
                    matrix[i][j].setHorizontal(gapPenalty * j);
                } else if (j == 0) {
                    matrix[i][j].setVertical(gapPenalty * i);
                } else {
                    int prevDiagMax = matrix[i - 1][j - 1].getNumericMax();

                    int match = getValueIdentityMatrix(this.firstSequence[j - 1], this.secondSequence[i - 1]);
                    matrix[i][j].setDiagonal(prevDiagMax + match);

                    int prevHorizontalMax = matrix[i][j - 1].getNumericMax();
                    matrix[i][j].setHorizontal(prevHorizontalMax + gapPenalty);

                    int prevVerticalMax = matrix[i - 1][j].getNumericMax();
                    matrix[i][j].setVertical(prevVerticalMax + gapPenalty);

                }

            }

        }
    }

    public void startPropagation() {

        Cell currentCell = matrix[this.secondSequence.length][this.firstSequence.length];
        cellToPropagate = new ArrayList<>();
        directionToPropagate = new ArrayList<>();
        passedMultiMax = new ArrayList<>();
        allPaths = new ArrayList<>();
        allCopied = new ArrayList<>();

        singlePropagation(matrix, currentCell);
        System.out.println(allPaths.toString());

        printAlignment();

        //NOTE: paths are always backwards. from bottom right to top left
    }


    public void singlePropagation(Cell[][] matrix, Cell currentCell) {
        ArrayList<String> path = new ArrayList<>();

        if (currentCell.getI() != secondSequence.length && currentCell.getJ() != firstSequence.length) {
            //if cell is not bottom right, copies previous path (for the recursion)
            ArrayList<String> empty = new ArrayList<>(allCopied.get(0));
            path.addAll(empty);
        }

        //add null to arraylist if arraylists are empty, for the set methods below
        if (directionToPropagate.size() == 0) {
            directionToPropagate.add(null);
        }
        if (cellToPropagate.size() == 0) {
            cellToPropagate.add(null);
        }

        //while current cell is not the top left
        while (currentCell.getI() != 0 || currentCell.getJ() != 0) {
            //if multiple maxes in a a cell:
            int directionBefore = directionToPropagate.size();
            multiMax(currentCell);
            int directionAfter = directionToPropagate.size();
            if (directionAfter > directionBefore) {
                System.out.println("added");

                if (path.size() != 0) {
                    for (int i = 0; i < (directionAfter - directionBefore); i++) {
                        ArrayList<String> empty = new ArrayList<>(path);
                        allCopied.add(empty);
                        System.out.println(path.toString());
                        System.out.println(allCopied.toString());
                    }
                }

            }

            for (Cell c : cellToPropagate) {
                if (c != null) {
                    c.printCell();
                }
            }
            System.out.println(Arrays.toString((directionToPropagate.toArray())));

            int firstSeqPos = currentCell.getJ();
            int secondSeqPos = currentCell.getI();

            String string = directionToPropagate.get(0);

            //checking what direction is next, adjusts current cell accordingly
            if (string.equals("h")) {
                firstSeqPos--;
                System.out.println("HORIZONTAL");
                currentCell = matrix[secondSeqPos][firstSeqPos];
                cellToPropagate.set(0, currentCell);
                path.add("h");


            } else if (string.equals("d")) {
                secondSeqPos--;
                firstSeqPos--;
                System.out.println("DIAGONAL");
                currentCell = matrix[secondSeqPos][firstSeqPos];
                cellToPropagate.set(0, currentCell);
                path.add("d");

            } else if (string.equals("v")) {
                secondSeqPos--;
                System.out.println("VERTICAL");
                currentCell = matrix[secondSeqPos][firstSeqPos];
                cellToPropagate.set(0, currentCell);
                path.add("v");
            }
            //once a single path completed (top left corner)
            if (currentCell.getJ() == 0 && currentCell.getI() == 0) {

                System.out.println("COMPLETED");
                System.out.println(path.toString());
                //System.out.println(allCopied.toString());
                if (cellToPropagate.size() == allCopied.size()) {
                    allCopied.remove(0);
                }
                cellToPropagate.remove(0);
                directionToPropagate.remove(0);
                allPaths.add(path);

                //If more paths need to be called
                if (cellToPropagate.size() > 0) {
                    singlePropagation(matrix, cellToPropagate.get(0));
                }

            }
        }
        System.out.println(path.toString());
        for (Cell c : cellToPropagate) {
            c.printCell();
        }

    }


    //Checks if multiple maximums are in the current cell. If multiple maxes exists, it splits up the string and adds it
    //to directionToPropagate.
    private void multiMax(Cell current) {
        System.out.println(current.getJ() + "," + current.getI());
        String string = current.getMax();
        System.out.println(string);

        //To check if cell already been looked at, if it has, skip.
        int count = 0;
        for (Cell cell : passedMultiMax) {
            //cell.printCell();
            if (current == cell) {
                count++;
            }
        }

        if (count == 0) {
            if (string.length() > 1) {
                //split up string
                String[] split = string.split("");
                for (int i = 0; i < split.length; i++) {
                    //System.out.println(split[i]);
                    if (split[i].equals("h") && !split[i].equals(directionToPropagate.get(0))) {
                        if (directionToPropagate.get(0) == null) {
                            directionToPropagate.set(0, "h");
                            cellToPropagate.set(0, current);
                        } else {
                            cellToPropagate.add(current);
                            directionToPropagate.add("h");
                        }


                    } else if (split[i].equals("d") && !split[i].equals(directionToPropagate.get(0))) {
                        if (directionToPropagate.get(0) == null) {
                            directionToPropagate.set(0, "d");
                            cellToPropagate.set(0, current);
                        } else {
                            cellToPropagate.add(current);
                            directionToPropagate.add("d");
                        }


                    } else if (split[i].equals("v") && !split[i].equals(directionToPropagate.get(0))) {
                        if (directionToPropagate.get(0) == null) {
                            directionToPropagate.set(0, "v");
                            cellToPropagate.set(0, current);
                        } else {
                            cellToPropagate.add(current);
                            directionToPropagate.add("v");
                        }

                    }

                }
            } else {
                directionToPropagate.set(0, string);

            }
            passedMultiMax.add(current);

        } else {
            if (string.length() == 1) {
                directionToPropagate.set(0, string);

            }

        }

    }


    public String printAlignment() {
        printMatrix(matrix);
        StringBuilder sb = new StringBuilder();
        ArrayList<String> path;
        //System.out.println(Arrays.toString(firstSequence));
        //System.out.println(Arrays.toString(secondSequence));
        System.out.println("----------------------------");
        System.out.println("THERE ARE : " + allPaths.size() + " ALIGNMENTS");
        System.out.println();
        sb.append("There are ");
        sb.append(allPaths.size());
        sb.append(" alignment(s) with the score: ");
        Cell c = matrix[secondSequence.length][firstSequence.length];
        sb.append(c.getNumericMax());
        sb.append("\n\n");

        for (int i = 0; i < allPaths.size(); i++) {
            path = (ArrayList<String>) allPaths.get(i);

            String[] firstSeq = new String[path.size()];
            String[] secondSeq = new String[path.size()];

            int firstPos = this.firstSequence.length - 1;
            int secondPos = this.secondSequence.length - 1;

            for (int j = 0; j < path.size(); j++) {

                if (path.get(j).equals("h")) {

                    firstSeq[j] = this.firstSequence[firstPos];
                    secondSeq[j] = "-";
                    firstPos--;

                } else if (path.get(j).equals("d")) {

                    firstSeq[j] = this.firstSequence[firstPos];
                    secondSeq[j] = this.secondSequence[secondPos];
                    firstPos--;
                    secondPos--;

                } else {

                    firstSeq[j] = "-";
                    secondSeq[j] = this.secondSequence[secondPos];
                    secondPos--;

                }
            }
            for (int k = firstSeq.length - 1; k >= 0; k--) {
                System.out.print(firstSeq[k] + " ");
                sb.append(firstSeq[k]);
                sb.append(" ");
            }
            System.out.println();
            sb.append("\n");

            for (int k = secondSeq.length - 1; k >= 0; k--) {
                System.out.print(secondSeq[k] + " ");
                sb.append(secondSeq[k]);
                sb.append(" ");
            }
            sb.append("\n");
            sb.append("\n");
            System.out.println();
            System.out.println();

        }
        return sb.toString();


    }


    public void fillProteinMatrix() {
        ProteinSubMatrix p = new ProteinSubMatrix();
        switch (scoringMatrix) {
            case "BLOSUM62":
                p.blosum62Matrix();
                break;
            case "PAM250":
                p.pam250Matrix();
                break;
            case "BLOSUM100":
                p.blosum100Matrix();
                break;
            case "DAYOFF":
                p.dayhoffMatrix();
                break;
            case "PAM120":
                p.pam120Matrix();
                break;
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (i == 0) {
                    matrix[i][j].setHorizontal(gapPenalty * j);
                } else if (j == 0) {
                    matrix[i][j].setVertical(gapPenalty * i);
                } else {
                    int prevDiagMax = matrix[i - 1][j - 1].getNumericMax();

                    int match = p.getNumFromMatrix(this.firstSequence[j - 1], this.secondSequence[i - 1]);
                    matrix[i][j].setDiagonal(prevDiagMax + match);

                    int prevHorizontalMax = matrix[i][j - 1].getNumericMax();
                    matrix[i][j].setHorizontal(prevHorizontalMax + gapPenalty);

                    int prevVerticalMax = matrix[i - 1][j].getNumericMax();
                    matrix[i][j].setVertical(prevVerticalMax + gapPenalty);

                }

            }

        }
    }

    public static String[] processProteinSequence(String sequence) {
        String[] seq = sequence.split("");

        String[] bases = new String[]{"A", "R", "N", "D", "C", "Q", "E", "G", "H", "I", "L", "K", "M", "F", "P", "S",
                "T", "W", "Y", "V", "B", "Z", "X", "*"};
        int count = 0;
        for (String s : seq) {
            for (String base : bases) {
                if (s.equals(base)) {
                    count++;
                }
            }

        }

        if (count != seq.length) {
            System.out.println("Error: Wrong base entered");
            return null;
        } else {
            return sequence.split("");
        }
    }


    public static void main(String[] args) {
//        String testSeq = "CGACCTTTT";
//        String testSeq2 = "CTTCTTT";
//        String[] first = processDNASequence(testSeq);
//        String[] second = processDNASequence(testSeq2);
//
//        NeedlemanWunsch n = new NeedlemanWunsch(first, second, "type");
//        //Cell[][] test = createEmptyMatrix(first, second);
//
//        n.fillDNAMatrix();
//        n.printMatrix(n.matrix);
//
//        n.startPropagation();

        //n.smithWaterman(test);
//        String firstSeq = "MVHLTPEEKSAVTALWGKVNVDEVGGEALGRLLVVYPWTQRFLE" +
//                "SFGDLSTPDAVMGNPKVKAHGKKVLGAFSDGLAHLDNLKGTFATLSELHCDKLHVDPE" +
//                "NFRLLGNVLVCVLAHHFGKEFTPPVQAAYQKVVAGVANALAHKYH";
//        String secondSeq = "MVHXTPVEKSAVTALWGKVNVDEXGGEALGXLLVVYPWTQRFXX" +
//                "SFGDLXTPXAVMGNPKVKAHGKKVLGAFSDGLAHLDNLKGTFATLSELHCDKLHVDPE" +
//                "NFRLLGNVXVCVLAHHFGKEFTPPVXAAYQKVVAGVANALAHKYH";
//
//        NeedlemanWunsch ne = new NeedlemanWunsch(firstSeq, secondSeq, "Protein");
//
//        ne.fillProteinMatrix("blosum62");
//        ne.printMatrix(ne.matrix);
//
//        ne.startPropagation();


    }
}


