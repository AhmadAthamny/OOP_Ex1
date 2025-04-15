import java.util.Random;

/**
 * CleverPlayer has a middle strategy, it beats WhateverPlayer almost always.
 * CleverPlayer implements Player interface.
 */
public class CleverPlayer implements Player {

    private static final Random rand = new Random();

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
    public void playTurn(Board board, Mark mark) {
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                if (board.getMark(i, j) != Mark.BLANK) continue;

                board.putMark(mark, i, j);
                int score = Utilites.longestStreak(board, mark);

                // if score is at least 2, then it's good enough
                if (score >= 2) {
                    return;
                }

                // reset mark
                board.putMark(Mark.BLANK, i, j);
            }
        }

        // if no result found, mark a random coords
        int[] randomEmptyCoords = Utilites.pickRandomBoardEmptyCoords(board);
        board.putMark(mark, randomEmptyCoords[0], randomEmptyCoords[1]);
    }
}
