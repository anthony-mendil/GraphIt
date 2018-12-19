package log_management.tables;

import javax.persistence.*;

/**
 * Represents the graph objects that are persisted into
 * the graphs table of the database.
 */
@Entity
@Table(name = "GRAPHS", schema = "PUBLIC", catalog = "TEST")
public class Graph {

    /**
     * The id of the graph.
     */
    private int id;

    /**
     * The GXL representation of the syndrom.
     */
    private String gxl;

    /**
     * Gets the graph id.
     *
     * @return The graph id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    /**
     * Sets the graph id.
     *
     * @param id The graph id.
     */
    public void setId(int id) {
        throw new UnsupportedOperationException();
    }

    /**
     * Gets the GXL representation of the graph.
     *
     * @return The GXL representation of the graph.
     */
    @Column(name = "GXL")
    public String getGxl() {
        return gxl;
    }

    /**
     * Sets the GXL representation of the graph.
     *
     * @param gxl The GXL representation of the graph.
     */
    public void setGxl(String gxl) {
        this.gxl = gxl;
    }
}
