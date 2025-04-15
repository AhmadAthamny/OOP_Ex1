/**
 * Implements a CleverPlayer, an automatic Player with middle strategy.
 * CleverPlayer can be used as a Player in a single game or tournament.
 * @see Tournament
 * @see Game
 * @see PlayerFactory
 * @author Ahmad Athamny
 */
public class CleverPlayer implements Player {

    // the clever player strategy is to make the mark if it results in having a streak with at least
    // MINIMUM_GOOD_SCORE, it doesn't need to be the longest streak, just bigger or equal to the minimum.
    private static final int MINIMUM_GOOD_SCORE = 2;

    /**
     * Default constructor, creates the CleverPlayer and it's ready to use.
     */
    public CleverPlayer() {}

    /**
     * CleverPlayer plays a turn using his middle strategy.
     * @param board The board that CleverPlayer will mark his move on.
     * @param mark The mark that CleverPlayer will use to mark his new move.
     */
    @Override
    public void playTurn(final Board board, final Mark mark) {
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                if (board.getMark(i, j) != Mark.BLANK) continue;

                board.putMark(mark, i, j);
                int score = Utilities.getLongestMarkStreak(board, mark);

                // if score is good enough, keep the move
                if (score >= MINIMUM_GOOD_SCORE) {
                    return;
                }

                // otherwise, undo the move
                board.putMark(Mark.BLANK, i, j);
            }
        }

        // if no good move found, play random
        int[] randomEmptyCoords = Utilities.pickRandomBoardEmptyCoords(board);
        board.putMark(mark, randomEmptyCoords[Utilities.INDEX_ROW], randomEmptyCoords[Utilities.INDEX_COL]);
    }
}
