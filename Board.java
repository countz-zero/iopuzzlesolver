import java.util.Arrays;

public class Board {
    private int boardMatrix[][];
    private char boardConfig[][];
    private int height;
    private int width;

    public Board(int h, int w) {
        boardMatrix = new int[h][w];
        boardConfig = new char[h][w];
        width = w;
        height = h;
    }

    public void addPiece(Piece piece, int row, int col) {
        if(checkFitPieceAtPlace(piece, row, col)) {
            int[][] piece_matrix = piece.getBlockShapeMatrix();
            int h_piece = piece_matrix.length;
            int w_piece = piece_matrix[0].length;

            for(int i = 0; i < h_piece; i++) {
                for(int j = 0; j < w_piece; j++) {
                    boardMatrix[row + i][col + j] += piece_matrix[i][j];
            }
        }
        }
    }

    public void removePiece(Piece piece, int row, int col) {
        int[][] piece_matrix = piece.getBlockShapeMatrix();
        int h_piece = piece_matrix.length;
        int w_piece = piece_matrix[0].length;

        for(int i = 0; i < h_piece; i++) {
            for(int j = 0; j < w_piece; j++) {
                boardMatrix[row + i][col + j] -= piece_matrix[i][j];
            }
        }
    }


    public boolean checkFitPieceAtPlace(Piece piece, int row, int col) {
        boolean isFit = true;
        int[][] piece_matrix = piece.getBlockShapeMatrix();
        int h_piece = piece_matrix.length;
        int w_piece = piece_matrix[0].length;

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


    public void showBoardCheck() {
        System.out.println(Arrays.deepToString(boardMatrix));
    }

    public void showBoardConfig() {
        System.out.println(Arrays.deepToString(boardConfig));
    }
}
