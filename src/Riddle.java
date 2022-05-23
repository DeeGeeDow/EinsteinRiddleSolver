import java.util.*;

public class Riddle {
    private int[][] RiddleMatrix;
    private int row;
    private int col;
    private List<Petunjuk> clues;

    public Riddle(int row, int col){
        this.row = row;
        this.col = col;
        this.clues = new ArrayList<>();
        //initiate RiddleMatrix to 0s
        this.RiddleMatrix = new int[row][col];
    }

    public void addClues(Petunjuk clue){
        this.clues.add(clue);
    }

    //return is riddle solvable
    public boolean heuristic(){
        for(Petunjuk c: clues){
            if(c instanceof Petunjuk1){
                int ro = ((Petunjuk1) c).getRow();
                int co = ((Petunjuk1) c).getCol();
                int v = ((Petunjuk1) c).getVal();
                if(RiddleMatrix[ro][co] != 0) return false;
                RiddleMatrix[ro][co] = v;
            }
        }
        return true;
    }

    public List<Integer> T(int[][] matrix, int k){
        int ro = k/col;
        List<Integer> availables = new ArrayList<>();
        for(int i=1; i<=col; i++){
            availables.add(i);
        }
        for(int i=0; i<col; i++){
            if(matrix[ro][i]>0){
                availables.remove(Integer.valueOf(matrix[ro][i]));
            }
        }
        return availables;
    }

    public boolean B(int[][] matrix){
        for(Petunjuk c : clues){
            if(c instanceof  Petunjuk2){
                int ro1 = ((Petunjuk2) c).getRow1();
                int ro2 = ((Petunjuk2) c).getRow2();
                int v1 = ((Petunjuk2) c).getVal1();
                int v2 = ((Petunjuk2) c).getVal2();
                for(int i=0; i<col; i++){
                    if(matrix[ro1][i] == v1){
                        if(matrix[ro2][i] != v2 && matrix[ro2][i] != 0){
                            return false;
                        }
                    }
                    else if(matrix[ro2][i] == v2){
                        if(matrix[ro1][i] != v1 && matrix[ro1][i] != 0){
                            return false;
                        }
                    }
                }
            }else if(c instanceof Petunjuk3){
                int ro1 = ((Petunjuk3) c).getRow1();
                int ro2 = ((Petunjuk3) c).getRow2();
                int v1 = ((Petunjuk3) c).getVal1();
                int v2 = ((Petunjuk3) c).getVal2();
                Relation r = ((Petunjuk3) c).getRel();
                for(int i=0; i<col; i++){
                    if(matrix[ro1][i] == v1){
                        switch(r){
                            case NEIGHBOR:
                                boolean isPossible = false;
                                if(i>0 && (matrix[ro2][i-1] == v2 || matrix[ro2][i-1] == 0)) isPossible = true;
                                if(i<col-1 && (matrix[ro2][i+1] == v2 || matrix[ro2][i+1] == 0)) isPossible = true;
                                if(!isPossible) return false;
                                break;
                            case LEFT:
                                if(i==col-1) return false;
                                else if(matrix[ro2][i+1] != v2 && matrix[ro2][i+1] != 0) return false;
                                break;
                            case RIGHT:
                                if(i==0) return false;
                                else if(matrix[ro2][i-1] != v2 && matrix[ro2][i-1] != 0) return false;
                                break;
                            case NOT_NEIGHBOR:
                                if(i>0 && matrix[ro2][i-1] == v2 ) return false;
                                if(i<col-1 && matrix[ro2][i+1] == v2) return false;
                                break;
                            default:
                                return false;
                        }
                    }
                    else if(matrix[ro2][i] == v2){
                        switch(r){
                            case NEIGHBOR:
                                boolean isPossible = false;
                                if(i>0 && (matrix[ro1][i-1] == v1 || matrix[ro1][i-1] == 0)) isPossible = true;
                                if(i<col-1 && (matrix[ro1][i+1] == v1 || matrix[ro1][i+1] == 0)) isPossible = true;
                                if(!isPossible) return false;
                                break;
                            case RIGHT:
                                if(i==col-1) return false;
                                else if(matrix[ro1][i+1] != v1 && matrix[ro1][i+1] != 0) return false;
                                break;
                            case LEFT:
                                if(i==0) return false;
                                else if(matrix[ro1][i-1] != v1 && matrix[ro1][i-1] != 0) return false;
                                break;
                            case NOT_NEIGHBOR:
                                if(i>0 && matrix[ro1][i-1] == v1 ) return false;
                                if(i<col-1 && matrix[ro1][i+1] == v1) return false;
                                break;
                            default:
                                return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public boolean isSolved(int[][] matrix){
        for(int i=0; i<row*col; i++){
            if(matrix[i/col][i%col] == 0) return false;
        }
        return true;
    }
    public boolean backtrack(int k){
        if(RiddleMatrix[k/col][k%col] == 0){
            List<Integer> t = T(RiddleMatrix, k);
            for(Integer i: t){
                RiddleMatrix[k/col][k%col] = i;
                if(B(RiddleMatrix)){

                    if(isSolved(RiddleMatrix)){

                        for(int r=0; r<row; r++){
                            for(int c=0; c<col; c++){
                                System.out.print(RiddleMatrix[r][c]);
                                System.out.print(" ");
                            }
                            System.out.println();
                        }
                        return true;
                    }
                    if(k<row*col-1){
                        backtrack(k+1);
                    }
                }
            }
            RiddleMatrix[(k)/col][(k)%col] = 0;
        }else{
            backtrack(k+1);
        }
        return false;
    }
    public void solve(){
        if(heuristic()){
            backtrack(0);
        }else{
            System.out.println("Gagal solve pada tahap heuristik");
        }
    }
}
