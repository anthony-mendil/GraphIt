package actions;
/**
 * Enumerates all actions that have to be logged into the database.
 */
public enum LogEntryName {
    /**
     * The log entry will be of this type if vertices are added in the action.
     */
    ADD_VERTICES,
    /**
     * The log entry will be of this type if edges are added in the action.
     */
    ADD_EDGES,
    /**
     * The log entry will be of this type if a sphere is added in the action.
     */
    ADD_SPHERE,
    /**
     * The log entry will be of this type if anchor points are added in the action.
     */
    ADD_ANCHOR_POINTS,

    /**
     * The log entry will be of this type if vertices are removed in the action.
     */
    REMOVE_VERTICES,
    /**
     * The log entry will be of this type if edges are removed in the action.
     */
    REMOVE_EDGES,
    /**
     * The log entry will be of this type if a sphere is removed in the action.
     */
    REMOVE_SPHERE,
    /**
     * The log entry will be of this type if anchor points are removed in the action.
     */
    REMOVE_ANCHOR_POINTS,

    /**
     * The log entry will be of this type if vertices are moved in the action.
     */
    MOVE_VERTICES,
    /**
     * The log entry will be of this type if a sphere is moved in the action.
     */
    MOVE_SPHERE,
    /**
     * The log entry will be of this type if the color of a sphere is changed in the action.
     */
    EDIT_SPHERE_COLOR,
    /**
     * The log entry will be of this type if the draw color of vertices is changed in the action.
     */
    EDIT_VERTICES_DRAW_COLOR,
    /**
     * The log entry will be of this type if the paint color of vertices is changed in the action.
     */
    EDIT_VERTICES_FILL_COLOR,
    /**
     * The log entry will be of this type if color of edges is changed in the action.
     */
    EDIT_EDGES_COLOR,
    /**
     * The log entry will be of this type if the font size of a sphere is changed in the action.
     */
    EDIT_SPHERE_FONT_SIZE,
    /**
     * The log entry will be of this type if the font size of vertices is changed in the action.
     */
    EDIT_VERTICES_FONT_SIZE,
    /**
     * The log entry will be of this type if the size of a sphere is changed in the action.
     */
    EDIT_SPHERE_SIZE,
    /**
     * The log entry will be of this type if the size of vertices is changed in the action.
     */
    EDIT_VERTICES_SIZE,
    /**
     * The log entry will be of this type if the font of a sphere is changed in the action.
     */
    EDIT_FONT_SPHERE,
    /**
     * The log entry will be of this type if the font of vertices is changed in the action.
     */
    EDIT_FONT_VERTICES,
    /**
     * The log entry will be of this type if the shape of vertices is changed in the action.
     */
    EDIT_VERTICES_FORM,
    /**
     * The log entry will be of this type if the stroke of edges is changed in the action.
     */
    EDIT_EDGES_STROKE,
    /**
     * The log entry will be of this type if the edge type of edges is changed in the action.
     */
    EDIT_EDGES_TYPE,
    /**
     * The log entry will be of this type if the annotation of a sphere is changed in the action.
     */
    EDIT_SPHERE_ANNOTATION,
    /**
     * The log entry will be of this type if the annotation of a vertex is changed in the action.
     */
    EDIT_VERTEX_ANNOTATION,
    /**
     * The log entry will be of this type if the layout of the graph is changed in the action.
     */
    EDIT_LAYOUT,
    /**
     * The log entry will be of this type if activate-highlight-action is executed.
     */
    ACTIVATE_HIGHLIGHT,
    /**
     * The log entry will be of this type if activate-fadeout-action is executed.
     */
    ACTIVATE_FADEOUT,
    /**
     * The log entry will be of this type if activate-anchor-points-fadeout-action is executed.
     */
    ACTIVATE_ANCHOR_POINTS_FADEOUT,
    /**
     * The log entry will be of this type if deactivate-highlight-action is executed.
     */
    DEACTIVATE_HIGHLIGHT,
    /**
     * The log entry will be of this type if deactivate-fadeout-action is executed.
     */
    DEACTIVATE_FADEOUT,
    /**
     * The log entry will be of this type if deactivate-anchor-points-fadeout-action is executed.
     */
    DEACTIVATE_ANCHOR_POINTS_FADEOUT
}
