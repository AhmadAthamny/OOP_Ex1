/**
 * Factory class used to build board renderer instances based on the desired type.
 */
public class RendererFactory {

    private static final String TYPE_CONSOLE = "console";
    private static final String TYPE_VOID = "void";

    /**
     * Default constructor, initializes the factory and is ready to create renderers.
     */
    public RendererFactory() {}

    /**
     * Returns a built renderer from the given type and size.
     * @param type Type of board renderer desired.
     * @param size Size of the board for the desired renderer. Required to build certain renderer types.
     * @return An instance of a Renderer with the desired implementation, or null if the type is unknown.
     */
    public Renderer buildRenderer(String type, int size) {
        switch (type) {
            case TYPE_CONSOLE:
                return new ConsoleRenderer(size);
            case TYPE_VOID:
                return new VoidRenderer();
            default:
                return null;
        }
    }
}
