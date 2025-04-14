public enum Mark {
    BLANK("blank"), X("x"), O("o");

    private final String markString;

    Mark(String markValue) {
        this.markString = markValue;
    }

    public String toString()    {
        if(this.markString.equals("blank")) {
            return null;
        }
        return this.markString;
    }
}
