/**
 * This enum provides different Mark types (blank, X, and O), which are used by players to place on the board.
 */
public enum Mark {
    BLANK("blank"), X("x"), O("o");

    private static final String VALUE_FOR_BLANK_MARK = null;

    private final String markString;

    /**
     * Used to construct the enum constants with a string.
     * @param markString The string value to attach to the enum constant.
     */
    Mark(String markString) {
        this.markString = markString;
    }

    /**
     * @return string that represents the enum constant mark string.
     */
    public String toString() {
        if (this == BLANK) {
            return VALUE_FOR_BLANK_MARK;
        }
        return this.markString;
    }
}
