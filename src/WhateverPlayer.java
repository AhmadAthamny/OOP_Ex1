/**
 * Implements whatever player, an automatic Player with a basic strategy: mark a random empty cell.
 * WhateverPlayer can be used as a Player in a single game or tournament.
 * @see Tournament
 * @see Game
 * @see PlayerFactory
 * @author Ahmad Athamny
 */
public class WhateverPlayer implements Player {
    /**
     * Default constructor, creates the WhateverPlayer and it's ready to use.
     */
    public WhateverPlayer() {}

    /**
     * WhateverPlayer plays a turn using his basic strategy.
     * @param board The board that WhateverPlayer will mark his move on.
     * @param mark The mark that WhateverPlayer will use to mark his new move.
     */
    @Override
    public void playTurn(Board board, Mark mark) {
        // get one random available coords
        int[] randomEmptyCoords = Utilities.pickRandomBoardEmptyCoords(board);

        // mark the board
        board.putMark(mark, randomEmptyCoords[0], randomEmptyCoords[1]);
    }
}
