public class Game {

    private static final int DEFAULT_BOARD_SIZE = 4;
    private static final int DEFAULT_REQUIRED_WIN_STREAK = 3;

    private Player playerX;
    private Player playerO;
    private Renderer boardRenderer;
    private int boardSize;
    private int winStreak;
    private Board currentGameBoard;

    public Game(Player playerX, Player playerO, Renderer renderer) {
        this.playerX = playerX;
        this.playerO = playerO;
        this.boardRenderer = renderer;
        this.boardSize = DEFAULT_BOARD_SIZE;
        this.winStreak = DEFAULT_REQUIRED_WIN_STREAK;
    }

    public Game(Player playerX, Player playerO, int size, int winStreak, Renderer renderer) {
        this.playerX = playerX;
        this.playerO = playerO;
        this.boardRenderer = renderer;
        this.boardSize = size;
        this.winStreak = winStreak;
    }

    public int getWinStreak() {
        return this.winStreak;
    }

    public int getBoardSize() {
        return this.boardSize;
    }

    public Mark run() {
        // construct new game board
        this.currentGameBoard = new Board(this.boardSize);

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
        for(int i = 0; i < this.boardSize; i++) {
            for(int j = 0; j < this.boardSize; j++) {
                if (this.currentGameBoard.getMark(i, j) == Mark.BLANK) {
                    return false;
                }
            }
        }
        return true;
    }

    private int getMarkLongestStreak(Mark mark) {
        int boardSize = this.currentGameBoard.getSize();
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
