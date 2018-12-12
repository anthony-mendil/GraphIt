package log_management.tables;

import javax.persistence.*;

@Entity
@Table(name = "GRAPHS", schema = "PUBLIC", catalog = "TEST")
public class Graph {

    private int id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        throw new UnsupportedOperationException();
    }
}
