/**
 * Board class is responsible for providing a board of a given or default size.
 * It offers API to get data from the board, or update board data.
 * This Board class may be used by Game class.
 * @author Ahmad Athamny
 * @see Game
 */
public class Board {
    private static final int DEFAULT_BOARD_SIZE = 4;

    private int boardSize;
    private Mark[][] boardMatrix;

    /**
     * Default constructor, initializes board with size of DEFAULT_BOARD_SIZE (macro).
     */
    public Board() {
        this.boardSize = DEFAULT_BOARD_SIZE;
        constructBoard();
    }

    /**
     * Class constructor that accepts the desired board size as an argument, and initializes a board with
     * the size provided.
     * @param boardSize
     */
    public Board(int boardSize) {
        this.boardSize = boardSize;
        constructBoard();
    }

    private void constructBoard() {
        this.boardMatrix = new Mark[this.boardSize][this.boardSize];
        for (int i = 0; i < this.boardSize; i++) {
            for (int j = 0; j < this.boardSize; j++) {
                this.boardMatrix[i][j] = Mark.BLANK;
            }
        }
    }

    /**
     * A method that returns the board's size.
     * @return the board's size (int)
     */
    public int getSize() {
        return this.boardSize;
    }

    /**
     * This method accepts mark and coordinates to update that board with.
     * @param mark of type Mark, which is the type of mark to place on board.
     * @param row row coordinate of the desired coordinates.
     * @param col column coordinate of the desired coordinates.
     * @return returns true if mark was put successfully, otherwise returns false.
     */
    public boolean putMark(Mark mark, int row, int col) {
        if (!isValidCoordination(row, col) || (getMark(row, col) != Mark.BLANK && mark != Mark.BLANK)) {
            return false;
        }
        this.boardMatrix[row][col] = mark;
        return true;
    }

    /**
     * Get mark info from coordinates on board.
     * @param row row coordinate of the desired coordinates.
     * @param col column coordinate of the desired coordinates.
     * @return Mark which is present at the given coordinates.
     */
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
