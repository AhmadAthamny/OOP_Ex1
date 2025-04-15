/**
 * Game class is responsible for running a Game instance, including starting a game, managing player turns,
 * and announcing a winner/tie accordingly.
 */
public class Game {
    private static final int DEFAULT_REQUIRED_WIN_STREAK = 3;

    private Player playerX;
    private Player playerO;
    private Renderer boardRenderer;
    private int winStreak;
    private Board currentGameBoard;

    /**
     * Initializes a game instance, as well as initializes a board with default board size, and with
     * default win streak.
     * @param playerX Player instance of the first player.
     * @param playerO Player instance of the second player
     * @param renderer Renderer that will be used to render the board after each turn.
     * @see Player
     * @see Renderer
     */
    public Game(Player playerX, Player playerO, Renderer renderer) {
        this.playerX = playerX;
        this.playerO = playerO;
        this.boardRenderer = renderer;
        this.winStreak = DEFAULT_REQUIRED_WIN_STREAK;

        // construct game board
        this.currentGameBoard = new Board();
    }

    /**
     * Initializes a game instance, as well as initializes a board with the given board size and win streak.
     * @param playerX Player instance of the first player.
     * @param playerO Player instance of the second player
     * @param size Size of the board.
     * @param winStreak Win streak required for a player to win the game.
     * @param renderer Renderer that will be used to render the board after each turn.
     * @see Renderer
     * @see Player
     */
    public Game(Player playerX, Player playerO, int size, int winStreak, Renderer renderer) {
        this.playerX = playerX;
        this.playerO = playerO;
        this.boardRenderer = renderer;
        this.winStreak = winStreak;

        // construct game board
        this.currentGameBoard = new Board(size);
    }

    /**
     * A getter for the game's win streak
     * @return The game's required win streak (int)
     */
    public int getWinStreak() {
        return this.winStreak;
    }

    /**
     * A getter for the game's board size.
     * @return Size of the board used in the game (int).
     */
    public int getBoardSize() {
        return this.currentGameBoard.getSize();
    }

    /**
     * Runs the game's instance, from start to end, managing players' turns.
     * @return Return the mark of the winner player (Mark).
     */
    public Mark run() {

        // current player variable to easily switch between players
        Player currPlayer = this.playerX;
        Mark currMark = Mark.X;

        // we play as long as the board has empty cells
        while (!isBoardFull()) {
            // run 1 game round
            runGameRound(currPlayer, currMark);

            // check for winner
            if (isPlayerWinner(currMark)) {
                System.out.println(String.format("Player %s has won the game since he hit the winning " +
                        "streak of %d", currMark, this.winStreak));
                return currMark;
            }

            // setup for next round
            currPlayer = currPlayer == this.playerX ? this.playerO : this.playerX;
            currMark = currMark == Mark.X ? Mark.O : Mark.X;
        }

        return Mark.BLANK;
    }

    private void runGameRound(Player currPlayer, Mark currMark) {
        currPlayer.playTurn(this.currentGameBoard, currMark);

        // render board
        this.boardRenderer.renderBoard(this.currentGameBoard);
    }

    private boolean isPlayerWinner(Mark currMark) {
        if(getMarkLongestStreak(currMark) >= this.winStreak) {
            return true;
        }
        return false;
    }

    private boolean isBoardFull() {
        for(int i = 0; i < getBoardSize(); i++) {
            for(int j = 0; j < getBoardSize(); j++) {
                if (this.currentGameBoard.getMark(i, j) == Mark.BLANK) {
                    return false;
                }
            }
        }
        return true;
    }

    private int getMarkLongestStreak(Mark mark) {
        int boardSize = getBoardSize();
        int max = 0;

        // Rows
        for (int i = 0; i < boardSize; i++) {
            int count = 0;
            for (int j = 0; j < boardSize; j++) {
                count = (this.currentGameBoard.getMark(i, j) == mark) ? count + 1 : 0;
                max = Math.max(max, count);
            }
        }

        // Columns
        for (int j = 0; j < boardSize; j++) {
            int count = 0;
            for (int i = 0; i < boardSize; i++) {
                count = (this.currentGameBoard.getMark(i, j) == mark) ? count + 1 : 0;
                max = Math.max(max, count);
            }
        }

        // Diagonals (↘)
        for (int r = 0; r < boardSize; r++) {
            for (int c = 0; c < boardSize; c++) {
                int count = 0;
                for (int d = 0; r + d < boardSize && c + d < boardSize; d++) {
                    count = (this.currentGameBoard.getMark(r + d, c + d) == mark) ? count + 1 : 0;
                    max = Math.max(max, count);
                }
            }
        }

        // Diagonals (↙)
        for (int r = 0; r < boardSize; r++) {
            for (int c = 0; c < boardSize; c++) {
                int count = 0;
                for (int d = 0; r + d < boardSize && c - d >= 0; d++) {
                    count = (this.currentGameBoard.getMark(r + d, c - d) == mark) ? count + 1 : 0;
                    max = Math.max(max, count);
                }
            }
        }
        return max;
    }
}
