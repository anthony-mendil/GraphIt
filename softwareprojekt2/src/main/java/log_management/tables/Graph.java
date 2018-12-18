package log_management.tables;

import javax.persistence.*;

/**
 *
 */
@Entity
@Table(name = "GRAPHS", schema = "PUBLIC", catalog = "TEST")
public class Graph {

    /**
     *
     */
    private int id;

    /**
     *
     */
    private String gxl;

    /**
     *
     * @return
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(int id) {
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @return
     */
    @Column(name = "GXL")
    public String getGxl() {
        return gxl;
    }

    /**
     *
     * @param gxl
     */
    public void setGxl(String gxl) {
        this.gxl = gxl;
    }
}
