/**
 * This is a factory for board renderers.
 */
public class RendererFactory {
    /**
     * Default constructor for RendererFactory
     */
    public RendererFactory() {}

    /**
     * Returns a built renderer from given type, and size.
     * @param type Type of board renderer desired
     * @param size Size of the board for desired renderer, this info is required to build certain type of
     *             renderer.
     * @return An instance of a Renderer with the desired implementation.
     */
    public Renderer buildRenderer(String type, int size) {
        switch (type) {
            case "console":
                return new ConsoleRenderer(size);
            case "void":
                return new VoidRenderer();
            default:
                return null;
        }
    }
}
