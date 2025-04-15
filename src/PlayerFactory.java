/**
 * PlayerFactory is used to create instances of Player of different implementations.
 */
public class PlayerFactory {
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
            case "human":
                return new HumanPlayer();
            case "whatever":
                return new WhateverPlayer();
            case "clever":
                return new CleverPlayer();
            case "genius":
                return new GeniusPlayer();
            default:
                return null;
        }
    }
}
