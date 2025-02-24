import java.util.Scanner;
import java.io.*;
import java.util.Arrays;
public class IQPuzzleProSolver {
    public static void main (String[] args) throws IOException {
        File file = new File("./input.txt");
        //File file_out = new File("output.txt");
        Scanner input = new Scanner(file);

        int M = Integer.parseInt(input.next());
        int N = Integer.parseInt(input.next());
        int P = Integer.parseInt(input.next());
        input.nextLine();

        Board board = new Board(M, N);

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
        bruteForceIt(piece_arr, 0, board);
        FileWriter file_out = new FileWriter("output.txt");
        
        if(board.checkBoardFull()) {
            board.showBoardMatrix();
            board.editBoardConfig(piece_arr);
            board.showBoardConfig(file_out);
            System.out.println(Arrays.deepToString(piece_arr[0].getBlockShapeMatrix()));
            System.out.println(piece_arr[0].getRowCoord());
            System.out.println(piece_arr[0].getColCoord());
            System.out.println(Arrays.deepToString(piece_arr[1].getBlockShapeMatrix()));
            System.out.println(piece_arr[1].getRowCoord());
            System.out.println(piece_arr[1].getColCoord());
            System.out.println(Arrays.deepToString(piece_arr[2].getBlockShapeMatrix()));
            System.out.println(piece_arr[2].getRowCoord());
            System.out.println(piece_arr[2].getColCoord());
        } else {
            System.out.println(Arrays.deepToString(piece_arr[0].getBlockShapeMatrix()));
            System.out.println(piece_arr[0].getRowCoord());
            System.out.println(piece_arr[0].getColCoord());
            System.out.println(Arrays.deepToString(piece_arr[1].getBlockShapeMatrix()));
            System.out.println(piece_arr[1].getRowCoord());
            System.out.println(piece_arr[1].getColCoord());
            System.out.println(Arrays.deepToString(piece_arr[2].getBlockShapeMatrix()));
            System.out.println(piece_arr[2].getRowCoord());
            System.out.println(piece_arr[2].getColCoord());
            System.out.println("Tidak ada solusi yg mungkin");
        }
    }
    
    public static void bruteForceIt(Piece[] piece_arr, int k, Board board) {
        if(k == piece_arr.length) {
            return;
        }

        int h = piece_arr[k].getHeight();
        int w = piece_arr[k].getWidth();

        for(int i = 0; i < board.getHeight() - h + 1; i++) {
            for(int j = 0; j < board.getWidth() - w + 1; j++) {
                if(board.checkFitPieceAtPlace(piece_arr[k], i, j)) {
                    board.addPiece(piece_arr[k], i, j);
                    bruteForceIt(piece_arr, k+1, board);
                    //board.removePiece(piece_arr[k]);
                }
            }
        }


        if(piece_arr[k].rotation_index < 4) {
            piece_arr[k].rotatePiece90Deg();
            bruteForceIt(piece_arr, k, board);
        } else if (piece_arr[k].reflection_index < 1){
            piece_arr[k].rotation_index = 0;
            piece_arr[k].reflectPiece();
            bruteForceIt(piece_arr, k, board);
        }

        if (k == 0 && piece_arr[k].reflection_index == 1 && piece_arr[k].rotation_index == 4) {
            return;
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
