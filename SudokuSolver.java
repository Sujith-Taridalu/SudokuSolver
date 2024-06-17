public class SudokuSolver {
    public static void main(String[] args) {
        char[][] board = {
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
        };
        solveSudoku(board);
        for(int i=0;i<9;i++){
            for (int j=0;j<9;j++) System.out.print(board[i][j] + " ");
            System.out.println();
        }
    }
    static void solveSudoku(char[][] board) {
        boolean[][] table = new boolean[9][9];
        boolean f=false;
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if(board[i][j] != '.') table[i][j] = true;
            }
        }
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if(table[i][j]) continue;
                f = place(board,i,j,table);
                if(f) break;
            }
            if(f) break;
        }
    }
    static boolean place(char[][] board, int i, int j, boolean[][] table){
        boolean f=false;
        if(i == 9) return true;
        if(table[i][j]){
            if(j<8) f = place(board,i,j+1,table);
            else f = place(board,i+1,0,table);
            return f;
        }
        for(int k=1;k<10;k++){
            if(check(board,i,j,k)){
                board[i][j] = (char)(k+'0');
                if(j<8) f = place(board,i,j+1,table);
                else f = place(board,i+1,0,table);
            }
        }
        if(!f) board[i][j] = '.';
        return f;
    }
    static boolean check(char[][] board, int i, int j, int k){
        for(int x=0;x<9;x++){
            if(board[i][x] == (char)(k+'0')) return false;
        }
        for(int x=0;x<9;x++){
            if(board[x][j] == (char)(k+'0')) return false;
        }
        for(int a=(i-i%3);a<(i-i%3)+3;a++){
            for(int b=(j - j%3);b<(j - j%3)+3;b++){
                if(board[a][b] == (char)(k+'0')) return false;
            }
        }
        return true;
    }
}