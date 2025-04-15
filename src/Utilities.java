import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Utilities includes common code that is used in different and distinct classes.
 */
public class Utilities {
    private static final Random RANDOM = new Random();

    /**
     * Calculates the longest possible streak for the given mark in the given board.
     * @param board Board to search for streak inside.
     * @param mark The mark that the streak consists of.
     * @return Integer value of the longest streak currently in the board.
     */
    public static int getLongestMarkStreak(Board board, Mark mark) {
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

    /**
     * Picks 1 random empty coords from the given board.
     * @param board Board to search in.
     * @return int[] with 2 integer values, index 0 for row, and index 1 for column, of an empty cell that
     * was chosen randomly.
     */
    public static int[] pickRandomBoardEmptyCoords(Board board) {
        int boardSize = board.getSize();
        List<int[]> emptyCoordinates = new ArrayList<>();

        // collect all available cells
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (board.getMark(i, j) == Mark.BLANK) {
                    emptyCoordinates.add(new int[]{i, j});
                }
            }
        }

        // randomly pick one available
        int randomCoordsIndex = RANDOM.nextInt(emptyCoordinates.size());
        return emptyCoordinates.get(randomCoordsIndex);
    }
}
