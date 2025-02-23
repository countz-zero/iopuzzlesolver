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

        for(int i = 0; i < piece_arr.length; i++) {

        }

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
