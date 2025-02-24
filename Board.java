import java.util.Arrays;
import java.io.*;

public class Board {
    private int boardMatrix[][];
    private char boardConfig[][];
    private int height;
    private int width;
    public long cases = 0;

    public Board(int h, int w) {
        boardMatrix = new int[h][w];
        boardConfig = new char[h][w];
        width = w;
        height = h;
    }

    public void addPiece(Piece piece, int row, int col) { //Assumes position is valid
        int[][] piece_matrix = piece.getBlockShapeMatrix();
        int h_piece = piece_matrix.length;
        int w_piece = piece_matrix[0].length;

        for(int i = 0; i < h_piece; i++) {
            for(int j = 0; j < w_piece; j++) {
                boardMatrix[row + i][col + j] += piece_matrix[i][j];
            }
        }
        
        piece.setRowCoord(row);
        piece.setColCoord(col);
    }

    public void removePiece(Piece piece) {
        int[][] piece_matrix = piece.getBlockShapeMatrix();
        int row = piece.getRowCoord();
        int col = piece.getColCoord();
        int h_piece = piece_matrix.length;
        int w_piece = piece_matrix[0].length;

        for(int i = 0; i < h_piece; i++) {
            for(int j = 0; j < w_piece; j++) {
                boardMatrix[row + i][col + j] -= piece_matrix[i][j];
            }
        }
    }

    public boolean checkFitPieceOnSize(Piece piece) {
        if (piece.getHeight() > height || piece.getWidth() > width) {
            return false;
        } else {
            return true;
        }
    }

    public boolean checkFitPieceAtPlace(Piece piece, int row, int col) {
        boolean isFit = true;
        int[][] piece_matrix = piece.getBlockShapeMatrix();
        int h_piece = piece_matrix.length;
        int w_piece = piece_matrix[0].length;

        if (!checkFitPieceOnSize(piece)) {
            return false;
        }

        outerloop : for(int i = 0; i < h_piece; i++) {
            for(int j = 0; j < w_piece; j++) {
                if(boardMatrix[row + i][col + j] == 1 && piece_matrix[i][j] == 1) {
                    isFit = false;
                    break outerloop;
                }
            }
        }
        
        return isFit;
    }

    public boolean checkBoardFull() {
        boolean isFull = true;
        for(int i = 0; i < boardMatrix.length; i++) {
            for(int j = 0; j < boardMatrix[0].length; j++) {
                if(boardMatrix[i][j] != 1) {
                    isFull = false;
                }
            }
        }

        return isFull;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void showBoardMatrix() {
        System.out.println(Arrays.deepToString(boardMatrix));
    }

    public void editBoardConfig(Piece[] piece_arr) {
        for (int k = 0; k < piece_arr.length; k++) {
            int[][] matrix = piece_arr[k].getBlockShapeMatrix();
            int row = piece_arr[k].getRowCoord();
            int col = piece_arr[k].getColCoord();

            for(int i = 0; i < matrix.length; i++) {
                for(int j = 0; j < matrix[0].length; j++) {
                    if(matrix[i][j] == 1) {
                        boardConfig[row + i][col + j] = piece_arr[k].getSymbol();
                    }
                }
            }
        }
    }

    // public void showBoardConfig(FileWriter file_out) throws IOException{
    //     for(int i = 0; i < boardConfig.length; i++) {
    //         for(int j = 0; j < boardConfig[0].length; j++) {
    //             file_out.write(boardConfig[i][j]);
    //         }
    //             file_out.write("\n");
    //     }

    //     file_out.close();
    // }

    public String showBoardConfig() {
        String out = "";
        for(int i = 0; i < boardConfig.length; i++) {
            for(int j = 0; j < boardConfig[0].length; j++) {
                out += boardConfig[i][j];
            }
                out += "\n";
        }

        return out;
    }
}
