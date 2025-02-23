import java.util.ArrayList;
import java.util.Arrays;

public class Piece {
    private char symbol;
    private int w = 0;
    private int h = 0;
    //private ArrayList<ArrayList<String>> blockShape = new ArrayList<>();
    private ArrayList<ArrayList<String>> blockShape = new ArrayList<ArrayList<String>>();
    private int[][] blockShapeMatrix;

    public Piece(char x) {
        symbol = x;
    }

    public void updateRowShape(String blockRow) {
        ArrayList<String> blockRowArray = new ArrayList<>(Arrays.asList(blockRow.split("")));
        blockShape.add(blockRowArray);
        checkResize(blockRowArray);
        makeBlockShapeMatrix(blockShape);
    }

    public void makeBlockShapeMatrix(ArrayList<ArrayList<String>> blockShape) {
        blockShapeMatrix = new int[h][w];
        for (int i=0; i < h; i++) {
            for(int j = 0; j < w; j++) {
                if (j >= blockShape.get(i).size()) {
                    blockShapeMatrix[i][j] = 0;
                } else if(blockShape.get(i).get(j).equals(" ")) {
                    blockShapeMatrix[i][j] = 0;
                } else{
                    blockShapeMatrix[i][j] = 1;
                }
            }
        }
    }

    public void checkResize(ArrayList<String> blockRowArray) {
        if (blockRowArray.size() > w) {
            w = blockRowArray.size();
        }
        h++;
    }

    public void rotatePiece90Deg() {
        int[][] newBlockShapeMatrix = new int[w][h];
        for (int i = 0; i < w; i++) {
            for (int j=0; j< h; j++) {
                newBlockShapeMatrix[i][j] = blockShapeMatrix[h-1-j][i];
            }
        }

        blockShapeMatrix = newBlockShapeMatrix;
        
        w = h+w;
        h = w-h;
        w = w-h;
    }

    public void reflectPiece() {
        int[][] newBlockShapeMatrix = new int[h][w];
        for (int i = 0; i < h; i++) {
            for (int j=0; j< w; j++) {
                newBlockShapeMatrix[i][j] = blockShapeMatrix[i][w-1-j];
            }
        }

        blockShapeMatrix = newBlockShapeMatrix;
    }

    public char getSymbol() {
        return symbol;
    }

    public int[][] getBlockShapeMatrix() {
        return blockShapeMatrix;
    }

    public int getWidth() {
        return w;
    }

    public int getHeight() {
        return h;
    }

    public void printShape() {
        for(int i = 0; i < blockShape.size(); i++){
            System.out.println(blockShape.get(i));
        }
    }

}
