public class Board {

    private int boardSize;
    private Mark[][] boardMatrix;

    public Board() {}

    public Board(int boardSize) {
        this.boardSize = boardSize;
        this.boardMatrix = new Mark[boardSize][boardSize];
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                this.boardMatrix[i][j] = Mark.BLANK;
            }
        }
    }

    public int getSize() {
        return this.boardSize;
    }

    public boolean putMark(Mark mark, int row, int col) {
        if (!isValidCoordination(row, col) || getMark(row, col) != Mark.BLANK) {
            return false;
        }
        this.boardMatrix[row][col] = mark;
        return true;
    }

    public Mark getMark(int row, int col) {

        // make sure coordinate is legal
        if (!isValidCoordination(row, col)) {
            return Mark.BLANK;
        }

        return this.boardMatrix[row][col];
    }

    private boolean isValidCoordination(int row, int col) {
        if (row < 0 || col < 0 || row >= boardSize || col >= boardSize) {
            return false;
        }
        return true;
    }
}
