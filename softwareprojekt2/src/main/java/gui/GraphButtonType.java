package gui;

/**
 * The visualization viewer needs to know, which button is pressed in the JavaFX GUI.
 */
public enum GraphButtonType {
    /**
     * In case the add-vertex-button has been pressed.
     */
    ADD_VERTEX,
    /**
     * In case the remove-vertex-button has been pressed.
     */
    REMOVE_VERTEX,
    /**
     * In case the add-sphere-button has been pressed.
     */
    ADD_SPHERE,
    /**
     * In case the remove-sphere-button has been pressed.
     */
    REMOVE_SPHERE,
    /**
     * In case the add-edge-button has been pressed.
     */
    REMOVE_EDGE,
    /**
     * In case none of the buttons has been pressed.
     */
    NONE
}
