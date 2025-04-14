import java.util.Scanner;

public class HumanPlayer implements Player {
    HumanPlayer() {}

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
