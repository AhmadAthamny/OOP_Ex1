/**
 * Game class is responsible for running a Game instance, including starting a game, managing player turns,
 * and announcing a winner/tie accordingly.
 * @author Ahmad Athamny
 * @see Tournament
 */
public class Game {

    private static final int DEFAULT_REQUIRED_WIN_STREAK = 3;
    private static final String WINNER_ANNOUNCEMENT_TEMPLATE =
            "Player %s has won the game since he hit the winning streak of %d";
    private static final Mark DRAW_MARK = Mark.BLANK;

    private final Player playerX;
    private final Player playerO;
    private final Renderer boardRenderer;
    private final int winStreak;
    private final Board currentGameBoard;

    /**
     * Initializes a game instance, with default board size and default win streak.
     * @param playerX Player instance of the first player.
     * @param playerO Player instance of the second player.
     * @param renderer Renderer that will be used to render the board after each turn.
     */
    public Game(Player playerX, Player playerO, Renderer renderer) {
        this.playerX = playerX;
        this.playerO = playerO;
        this.boardRenderer = renderer;
        this.winStreak = DEFAULT_REQUIRED_WIN_STREAK;
        this.currentGameBoard = new Board();
    }

    /**
     * Initializes a game instance with custom board size and win streak.
     * @param playerX Player instance of the first player.
     * @param playerO Player instance of the second player.
     * @param size Size of the board.
     * @param winStreak Win streak required for a player to win the game.
     * @param renderer Renderer that will be used to render the board after each turn.
     */
    public Game(Player playerX, Player playerO, int size, int winStreak, Renderer renderer) {
        this.playerX = playerX;
        this.playerO = playerO;
        this.boardRenderer = renderer;
        this.winStreak = winStreak;
        this.currentGameBoard = new Board(size);
    }

    /**
     * A getter for the game's win streak.
     * @return The game's required win streak (int).
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
     * @return Returns the mark of the winner player (Mark), or BLANK if it's a tie.
     */
    public Mark run() {
        Player currPlayer = this.playerX;
        Mark currMark = Mark.X;

        while (!isBoardFull()) {
            runGameRound(currPlayer, currMark);

            if (isPlayerWinner(currMark)) {
                System.out.println(String.format(WINNER_ANNOUNCEMENT_TEMPLATE, currMark, this.winStreak));
                return currMark;
            }

            currPlayer = (currPlayer == this.playerX) ? this.playerO : this.playerX;
            currMark = (currMark == Mark.X) ? Mark.O : Mark.X;
        }

        return DRAW_MARK;
    }

    /**
     * Runs a single game round: player plays and board is rendered.
     * @param currPlayer The player to play this round.
     * @param currMark The mark that the player will use.
     */
    private void runGameRound(Player currPlayer, Mark currMark) {
        currPlayer.playTurn(this.currentGameBoard, currMark);
        this.boardRenderer.renderBoard(this.currentGameBoard);
    }

    /**
     * Checks whether the given mark has achieved the required win streak.
     * @param currMark The mark to check.
     * @return True if the mark has a winning streak, false otherwise.
     */
    private boolean isPlayerWinner(Mark currMark) {
        return Utilities.getLongestMarkStreak(this.currentGameBoard, currMark) >= this.winStreak;
    }

    /**
     * Checks whether the board has no empty cells left.
     * @return True if the board is full, false otherwise.
     */
    private boolean isBoardFull() {
        for (int i = 0; i < getBoardSize(); i++) {
            for (int j = 0; j < getBoardSize(); j++) {
                if (this.currentGameBoard.getMark(i, j) == Mark.BLANK) {
                    return false;
                }
            }
        }
        return true;
    }
}
