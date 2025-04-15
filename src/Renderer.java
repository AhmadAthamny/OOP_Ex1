/**
 * The Renderer interface is responsible for rendering the current state of a game board.
 * Implementations of this interface define how the board is displayed (console/void).
 */
public interface Renderer {
    /**
     * Renders the current state of the given board to the output.
     * @param board The board to render.
     */
    void renderBoard(Board board);
}
