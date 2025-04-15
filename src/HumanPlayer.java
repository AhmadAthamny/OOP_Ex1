/**
 * Implements a human player, where the player can manually choose coordinates to place his next
 * mark on his turn. HumanPlayer can be used as a Player in a tournament or a single game.
 * @see Tournament
 * @see Game
 * @see PlayerFactory
 * author Ahmad Athamny
 */
public class HumanPlayer implements Player {

    private static final int DECIMAL_DIVISOR = 10;
    private static final String INVALID_INPUT_MSG = "Invalid mark position. Please choose a valid position:";
    private static final String OCCUPIED_CELL_MSG = "Mark position is already occupied. Please choose a " +
            "valid position:";
    private static final String COORDINATE_PROMPT_TEMPLATE = "Player %s, type coordinates: ";

    /**
     * Default constructor, which also makes the player ready for playing.
     */
    public HumanPlayer() {}

    /**
     * Asks the player for coordinates of his choice to place on the board for his turn.
     * @param board The board on which the player will place his mark.
     * @param mark The mark that the player will place on the given board.
     */
    public void playTurn(Board board, Mark mark) {
        System.out.printf(String.format(COORDINATE_PROMPT_TEMPLATE, mark.toString()));

        while (true) {
            // ask player for his input
            int[] chosenCoordinates = readPlayerCoordinates();
            int row = chosenCoordinates[Utilities.INDEX_ROW];
            int col = chosenCoordinates[Utilities.INDEX_COL];

            // validate input, and mark the board if valid
            if (row < 0 || row >= board.getSize() || col < 0 || col >= board.getSize()) {
                System.out.println(INVALID_INPUT_MSG);
            } else if (board.getMark(row, col) != Mark.BLANK) {
                System.out.println(OCCUPIED_CELL_MSG);
            } else {
                board.putMark(mark, row, col);
                break;
            }
        }
    }

    private int[] readPlayerCoordinates() {
        int playerCoordinationInt = KeyboardInput.readInt();
        int row = playerCoordinationInt / DECIMAL_DIVISOR;
        int col = playerCoordinationInt % DECIMAL_DIVISOR;
        return new int[]{row, col};
    }
}
