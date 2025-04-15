/**
 * Player is an interface for a Player that implements playTurn.
 * Player instances are used to participate in tournaments or single games.
 * @see Tournament
 * @see Game
 * @author Ahmad Athamny
 */
public interface Player {

    /**
     * This runs the player's turn, according to its implementation.
     * @param board The board of which the player will place his mark on.
     * @param mark The type of mark the Player will use to set his choice.
     */
    void playTurn(Board board, Mark mark);
}
