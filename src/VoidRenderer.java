/**
 * Implements a renderer that renders nothing, useful for tournaments with automatic players with high
 * games volume.
 */
public class VoidRenderer implements Renderer {
    /**
     * Implementing the renderBoard API method, which simply does nothing.
     * @param board
     */
    public void renderBoard(Board board) {}
}
