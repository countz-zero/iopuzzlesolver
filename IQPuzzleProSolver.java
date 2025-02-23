import java.util.Scanner;
import java.util.Arrays;;
public class IQPuzzleProSolver {
    public static void main (String[] args) {
        Scanner input = new Scanner(System.in);

        int M = Integer.parseInt(input.next());
        int N = Integer.parseInt(input.next());
        int P = Integer.parseInt(input.next());
        input.nextLine();

        Board board = new Board(M, N);
        board.showBoardCheck();

        Piece[] piece_arr = new Piece[P];

        String keyword = input.nextLine();
        if (keyword.equals("DEFAULT")) {
            char letter = '0';
            int numOfPieces = 0;
            int i = 0;
            String block_row = input.nextLine();
            do {
                if (letter != getLetter(block_row)) {
                    numOfPieces++;
                    letter = getLetter(block_row);
                    piece_arr[i] = new Piece(letter);
                    i++;
                } 

                piece_arr[numOfPieces-1].updateRowShape(block_row);

                block_row = input.nextLine();

            } while (numOfPieces <= P && !block_row.equals(""));
        } 

        input.close();

        //Algoritma Brute Force

        
    }
    
    public static boolean checkPieceOfAConfig(Piece[] piece_arr, int k, Board board) {
        boolean fitted = false;

        if (k >= piece_arr.length) {
            return false;
        }
        
        for(int i = 0; i < board.getHeight(); i++) {
            for(int j = 0; j < board.getWidth(); j++) {
                if(board.checkFitPieceAtPlace(piece_arr[k], i, j)) {
                    board.addPiece(piece_arr[k], i, j);
                    fitted = true;
                    checkPieceOfAConfig(piece_arr, k+1, board);
                }
            }
        }

        return fitted;
    }

    public static char getLetter(String block_row) {
        for (int i = 0; i < block_row.length(); i++) {
            char ch = block_row.charAt(i);
            if (Character.isLetter(ch)) {
                return ch; // Return the first letter found
            }
        }

        return '\0'; // Return null character if no letter is found
    }

    
}
