import java.util.Random;

public class WhateverPlayer implements Player {
    private static final Random rand = new Random();

    public WhateverPlayer() {}

    @Override
    public void playTurn(Board board, Mark mark) {
        while (true) {
            // randomize row and col
            int randomRow = rand.nextInt(board.getSize());
            int randomCol = rand.nextInt(board.getSize());

            // try to put mark and break if succeeds
            if (board.putMark(mark, randomRow, randomCol)) {
                break;
            }
        }
    }
}
