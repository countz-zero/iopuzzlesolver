package src;
import java.util.Scanner;
import java.io.*;
public class IQPuzzleProSolver {
    public static void main (String[] args) throws IOException {
        File file = new File("../test/input.txt");
        File outFile = new File("../test/output.txt");
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
        long start = System.currentTimeMillis();
        bruteForceIt(piece_arr, 0, board);
        long time = System.currentTimeMillis() - start;
        FileWriter fWriter = new FileWriter(outFile);
        PrintWriter pWriter = new PrintWriter(fWriter);
        
        if(board.checkBoardFull()) {
            board.showBoardMatrix();
            board.editBoardConfig(piece_arr);
            String output = board.showBoardConfig();
            System.out.println(output);

            System.out.println("Waktu yang dibutuhkan : ");
            System.out.println(time + " ms");
            System.out.println("Banyak kasus yang ditinjau :");
            System.out.println(board.cases);
            System.out.println("Apakah Anda ingin menyimpan solusi?");
            Scanner input2 = new Scanner(System.in);
            String toSave = input2.nextLine();
            if (toSave.equals("Y")) {
                System.out.println("Solusi tersimpan!");
                pWriter.println(output);
                pWriter.close();
                fWriter.close();
                input2.close();
            } 

            // System.out.println(Arrays.deepToString(piece_arr[0].getBlockShapeMatrix()));
            // System.out.println(piece_arr[0].getRowCoord());
            // System.out.println(piece_arr[0].getColCoord());
            // System.out.println(Arrays.deepToString(piece_arr[1].getBlockShapeMatrix()));
            // System.out.println(piece_arr[1].getRowCoord());
            // System.out.println(piece_arr[1].getColCoord());
            // System.out.println(Arrays.deepToString(piece_arr[2].getBlockShapeMatrix()));
            // System.out.println(piece_arr[2].getRowCoord());
            // System.out.println(piece_arr[2].getColCoord());
        } else {
            // System.out.println(Arrays.deepToString(piece_arr[0].getBlockShapeMatrix()));
            // System.out.println(piece_arr[0].getRowCoord());
            // System.out.println(piece_arr[0].getColCoord());
            // System.out.println(Arrays.deepToString(piece_arr[1].getBlockShapeMatrix()));
            // System.out.println(piece_arr[1].getRowCoord());
            // System.out.println(piece_arr[1].getColCoord());
            // System.out.println(Arrays.deepToString(piece_arr[2].getBlockShapeMatrix()));
            // System.out.println(piece_arr[2].getRowCoord());
            // System.out.println(piece_arr[2].getColCoord());
            System.out.println("Tidak ada solusi yg mungkin");
        }
    }
    
    public static boolean bruteForceIt(Piece[] piece_arr, int k, Board board) {
        if(k == piece_arr.length) {
            return true;
        }

        Piece piece = piece_arr[k];

        int h = piece.getHeight();
        int w = piece.getWidth();

        for(int row = 0; row < board.getHeight(); row++) {
            for(int col = 0; col < board.getWidth(); col++) {
                for(int r = 0; r < 4; r++) {
                    board.cases++;
                    piece.rotatePiece90Deg();
                    if(board.checkFitPieceAtPlace(piece, row, col)) {
                        board.addPiece(piece, row, col);
                        if(bruteForceIt(piece_arr, k+1, board)){
                            return true;
                        }
                        board.removePiece(piece);
                    }
                }

                piece.reflectPiece();
                for(int r = 0; r < 4; r++) {
                    board.cases++;
                    piece.rotatePiece90Deg();
                    if(board.checkFitPieceAtPlace(piece, row, col)) {
                        board.addPiece(piece, row, col);
                        if(bruteForceIt(piece_arr, k+1, board)){
                            return true;
                        }
                        board.removePiece(piece);
                    }
                }
            }
        }

        return false;
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
