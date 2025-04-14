import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeniusPlayer implements Player {

    private static final Random rand = new Random();

    public GeniusPlayer() {}

    @Override
    public void playTurn(Board board, Mark mark) {
        Mark opp = (mark == Mark.X) ? Mark.O : Mark.X;
        int bestScore = Integer.MIN_VALUE, bestRow = -1, bestCol = -1;

        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                if (board.getMark(i, j) != Mark.BLANK) continue;

                // add to our longest streak
                board.putMark(mark, i, j);
                int myStreak = longestStreak(board, mark);
                board.putMark(Mark.BLANK, i, j);

                if (myStreak > bestScore) {
                    bestScore = myStreak;
                    bestRow = i;
                    bestCol = j;
                }
            }
        }

        // best coordination must be found, otherwise board is full and game ended already
        if (bestRow != -1 && bestCol != -1)
            board.putMark(mark, bestRow, bestCol);
    }


    private static int longestStreak(Board board, Mark mark) {
        int n = board.getSize();
        int max = 0;

        // Rows
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = 0; j < n; j++) {
                count = (board.getMark(i, j) == mark) ? count + 1 : 0;
                max = Math.max(max, count);
            }
        }

        // Columns
        for (int j = 0; j < n; j++) {
            int count = 0;
            for (int i = 0; i < n; i++) {
                count = (board.getMark(i, j) == mark) ? count + 1 : 0;
                max = Math.max(max, count);
            }
        }

        // Diagonals (↘)
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                int count = 0;
                for (int d = 0; r + d < n && c + d < n; d++) {
                    count = (board.getMark(r + d, c + d) == mark) ? count + 1 : 0;
                    max = Math.max(max, count);
                }
            }
        }

        // Diagonals (↙)
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                int count = 0;
                for (int d = 0; r + d < n && c - d >= 0; d++) {
                    count = (board.getMark(r + d, c - d) == mark) ? count + 1 : 0;
                    max = Math.max(max, count);
                }
            }
        }

        return max;
    }

}
