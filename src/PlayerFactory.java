/**
 * PlayerFactory is used to create instances of Player of different implementations.
 */
public class PlayerFactory {

    private static final String TYPE_HUMAN = "human";
    private static final String TYPE_WHATEVER = "whatever";
    private static final String TYPE_CLEVER = "clever";
    private static final String TYPE_GENIUS = "genius";

    /**
     * Default constructor, initializes factory and is ready to create players.
     */
    public PlayerFactory() {}

    /**
     * Builds an instance of a Player according to the type provided, and returns it.
     * @param type string representing the player instance type desired, can be: human/whatever/clever/genius
     * @return Returns an instance of the desired Player type with the matching implementation.
     */
    public Player buildPlayer(String type) {
        switch (type) {
            case TYPE_HUMAN:
                return new HumanPlayer();
            case TYPE_WHATEVER:
                return new WhateverPlayer();
            case TYPE_CLEVER:
                return new CleverPlayer();
            case TYPE_GENIUS:
                return new GeniusPlayer();
            default:
                return null;
        }
    }
}
