public class Petunjuk1 extends Petunjuk{
    private int col;
    private int row;
    private int val;

    public Petunjuk1(int col, int row, int val){
        this.col = col;
        this.row = row;
        this.val = val;
    }

    public int getCol(){
        return col;
    }

    public int getRow(){
        return row;
    }

    public int getVal(){
        return val;
    }
}
