/**
 * Implements a human player, where the player can manually choose coordinates to place his next
 * mark on his turn. HumanPlayer can be used as a Player in a tournament or a single game.
 * @see Tournament
 * @see Game
 * @see PlayerFactory
 * @author Ahmad Athamny
 */
public class HumanPlayer implements Player {
    /**
     * Default constructor, which also makes the player ready for playing.
     */
    HumanPlayer() {}

    /**
     * Asks the player for coordinates of his choice to place on the board for his turn.
     * @param board The player of which the player will place his mark on.
     * @param mark The mark that the player will place on the given board.
     */
    public void playTurn(Board board, Mark mark) {

        // ask player for mark
        System.out.printf(String.format("Player %s, type coordinates: ", mark.toString()));

        while (true) {

            int playerCoordinationInt = KeyboardInput.readInt();

            // get row/col from player input
            int row = playerCoordinationInt / 10;
            int col = playerCoordinationInt % 10;

            // validate coordination
            if (row < 0 || row > board.getSize() - 1 || col < 0 || col > board.getSize() - 1) {
                System.out.println("Invalid mark position. Please choose a valid position:");
            }
            else if(board.getMark(row, col) != Mark.BLANK) {
                System.out.println("Mark position is already occupied. Please choose a valid " +
                                "position: ");
            }
            else {
                board.putMark(mark, row, col);
                break;
            }
        }
    }
}
