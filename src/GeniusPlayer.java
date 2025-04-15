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
    /**
     * Default constructor, creates the CleverPlayer and it's ready to use.
     */
    public GeniusPlayer() {}

    /**
     * GeniusPlayer plays a turn using his best automatic strategy.
     * @param board The board that GeniusPlayer will mark his move on.
     * @param mark The mark that GeniusPlayer will use to mark his new move.
     */
    @Override
    public void playTurn(Board board, Mark mark) {
        int longestStreak = Integer.MIN_VALUE, optimalChoiceRow = -1, optimalChoiceCol = -1;

        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                if (board.getMark(i, j) != Mark.BLANK) continue;

                // add to our longest streak
                board.putMark(mark, i, j);
                int currStreak = Utilities.getLongestMarkStreak(board, mark);
                board.putMark(Mark.BLANK, i, j);

                // check if it's currently the longest
                if (currStreak > longestStreak) {
                    longestStreak = currStreak;
                    optimalChoiceRow = i;
                    optimalChoiceCol = j;
                }
            }
        }

        // best coordination must be found, otherwise board is full and game ended already
        board.putMark(mark, optimalChoiceRow, optimalChoiceCol);
    }

}
