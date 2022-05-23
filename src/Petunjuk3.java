public class Petunjuk3 extends Petunjuk{
    private int row1;
    private int val1;
    private int row2;
    private int val2;
    private Relation rel;

    public Petunjuk3(int row1, int val1, int row2, int val2, Relation rel){
        this.row1 = row1;
        this.val1 = val1;
        this.row2 = row2;
        this.val2 = val2;
        this.rel = rel;
    }

    public int getRow1(){
        return row1;
    }

    public int getVal1(){
        return val1;
    }

    public int getRow2(){
        return row2;
    }

    public int getVal2(){
        return val2;
    }

    public Relation getRel(){
        return rel;
    }
}