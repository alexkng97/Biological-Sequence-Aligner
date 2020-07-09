package sample;

public class Cell {
    private int diagonal;
    private int vertical;
    private int horizontal;

    private int i;
    private int j;

    public Cell(int i, int j){
        this.diagonal = 0;
        this.vertical = 0;
        this.horizontal = 0;

        this.i = i;
        this.j = j;
    }

    public int getDiagonal(){
        return this.diagonal;
    }

    public int getVertical(){
        return this.vertical;
    }

    public int getHorizontal(){
        return this.horizontal;
    }

    public int getI(){ return this.i;}

    public int getJ(){ return this.j;}

    public void setDiagonal(int diagonal){
        this.diagonal = diagonal;
    }

    public void setVertical(int vertical){
        this.vertical = vertical;
    }

    public void setHorizontal(int horizontal){
        this.horizontal = horizontal;
    }


    public int getNumericMax(){

        if(i ==0){
            return horizontal;
        }else if (j == 0) {
            return vertical;
        } else {
            return Math.max(Math.max(diagonal, horizontal), vertical);
        }


    }

    public String getMax(){
        int max = getNumericMax();
        if(i ==0){
            return "h";
        }else if (j == 0) {
            return "v";
        } else {
            if (horizontal == max){
                //case where horizontal is a max
                if(horizontal == diagonal){
                    //horizontal and diagonal both max
                    if(horizontal == vertical){
                        //all 3
                        return "hdv";
                    } else{
                        //just horizontal and diagonal
                        return "hd";
                    }

                } else if (horizontal == vertical){
                    //horizontal and vertical both max
                    return "hv";

                } else{
                    //just horizontal
                    return "h";
                }
            } else if(diagonal == max){
                //case where diagonal is max, horizontal is not
                if(diagonal == vertical){
                    //diagonal and vertical
                    return "dv";
                }else{
                    //just diagonal
                    return "d";
                }

            } else {
                return "v";
            }


        }
    }


    public void printCell(){
        System.out.print("|");
        System.out.print("H:" + this.horizontal+" ");
        System.out.print("D:" + this.diagonal +" ");
        System.out.print("V:" + this.vertical +" ");


        System.out.print("| ");

    }

    public void printMaxOfCell(){
        System.out.print("|");
        System.out.print(getMax() +":" + getNumericMax());
    }

    public String getMaxOfCell(){
        StringBuilder sb = new StringBuilder();
        sb.append(getMax());
        sb.append(":");
        sb.append(getNumericMax());

        return sb.toString();

    }


    public static void main(String[] args) {
        Cell c = new Cell(1,1);

        c.setHorizontal(0);
        c.setVertical(-3);
        c.setDiagonal(0);

        System.out.println(c.getNumericMax());

        System.out.println(c.getMax());

        System.out.println((c.getMax()).length());


    }
}