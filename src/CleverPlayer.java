import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CleverPlayer implements Player {

    private static final Random rand = new Random();

    public CleverPlayer() {}

    @Override
    public void playTurn(Board board, Mark mark) {
        Mark opp = (mark == Mark.X) ? Mark.O : Mark.X;

        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                if (board.getMark(i, j) != Mark.BLANK) continue;

                board.putMark(mark, i, j);
                int score = longestStreak(board, mark);
                board.putMark(Mark.BLANK, i, j);

                // if score is at least 2, then it's good enough
                if (score >= 2) {
                    board.putMark(mark, i, j);
                    return;
                }
            }
        }

        // if we couldn't make a 2-length streak, we'll just go random
        int random_row, random_col;
        random_row = rand.nextInt(board.getSize());
        random_col = rand.nextInt(board.getSize());
        board.putMark(Mark.BLANK, random_row, random_col);
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
