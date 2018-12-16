package log_management.tables;

import javax.persistence.*;

@Entity
@Table(name = "GRAPHS", schema = "PUBLIC", catalog = "TEST")
public class Graph {

    private int id;

    private String gxl;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        throw new UnsupportedOperationException();
    }

    @Column(name = "GXL")
    public String getGxl() {
        return gxl;
    }

    public void setGxl(String gxl) {
        this.gxl = gxl;
    }
}
