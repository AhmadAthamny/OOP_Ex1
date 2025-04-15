/**
 * Implements a genius player, an automatic Player with strategy that beats other automatic players almost
 * always.
 * GeniusPlayer can be used as a Player in a single game or tournament.
 * @see Tournament
 * @see Game
 * @see PlayerFactory
 * @author Ahmad Athamny
 */
public class GeniusPlayer implements Player {

    private static final int INVALID_COORD = -1;

    /**
     * Default constructor, creates the GeniusPlayer and it's ready to use.
     */
    public GeniusPlayer() {}

    /**
     * GeniusPlayer plays a turn using his best automatic strategy.
     * @param board The board that GeniusPlayer will mark his move on.
     * @param mark The mark that GeniusPlayer will use to mark his new move.
     */
    @Override
    public void playTurn(Board board, Mark mark) {
        int longestStreak = Integer.MIN_VALUE;
        int optimalChoiceRow = INVALID_COORD;
        int optimalChoiceCol = INVALID_COORD;

        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                // if cell isn't empty, skip.
                if (board.getMark(i, j) != Mark.BLANK) continue;

                // try mark cell and calculate streak length.
                board.putMark(mark, i, j);
                int currStreak = Utilities.getLongestMarkStreak(board, mark);

                // reset mark to search for more potentially longer streaks
                board.putMark(Mark.BLANK, i, j);

                // check if currently the longest streak, and update accordingly
                if (currStreak > longestStreak) {
                    longestStreak = currStreak;
                    optimalChoiceRow = i;
                    optimalChoiceCol = j;
                }
            }
        }

        // to avoid out of range error, in our use-case, we'll always find an optimal choice, since the
        // game is running as long as there are empty cells.
        if (optimalChoiceRow != INVALID_COORD) {
            board.putMark(mark, optimalChoiceRow, optimalChoiceCol);
        }
    }
}
