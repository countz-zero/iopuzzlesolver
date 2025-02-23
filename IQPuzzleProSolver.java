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
    
    public static void bruteForceIt(Piece[] piece_arr, int k, Board board) {
        // if(k == piece_arr.length) {
        //     return true;
        // }

        int h = piece_arr[k].getHeight();
        int w = piece_arr[k].getWidth();

        for(int i = 0; i < board.getHeight() - h + 1; i++) {
            for(int j = 0; j < board.getWidth() - w + 1; j++) {
                if(board.checkFitPieceAtPlace(piece_arr[k], i, j)) {
                    board.addPiece(piece_arr[k], i, j);
                    bruteForceIt(piece_arr, k+1, board);
                }
            }
        }

        
        if(piece_arr[k].rotation_index < 4) {
            bruteForceIt(piece_arr, k, board);
        } else if (piece_arr[k].reflection_index < 1){
            piece_arr[k].rotation_index = 0;
            piece_arr[k].reflectPiece();
            bruteForceIt(piece_arr, k, board);
        }

        bruteForceIt(piece_arr, k-1, board);



        // if(k == 0) {
        //     return false;
        // }
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
