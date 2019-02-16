package log_management.tables;

import log_management.parameters.add_remove.AddRemoveSphereParam;

import javax.persistence.*;

/**
 * Represents the Log objects that are persisted into
 * the logs table of the database.
 */
@Entity
public class AddRemoveSphereLog extends Log {
    /**
     * The parameters used in the action.
     */
    private AddRemoveSphereParam parameters;

    /**
     * Gets the parameters.
     *
     * @return The parameters in the JSON format.
     */
    @Column(name = "PARAMETERS", columnDefinition = "BLOB")
    public AddRemoveSphereParam getParameters() {
        return parameters;
    }

    /**
     * Sets the parameters.
     *
     * @param parameters The parameters in the JSON format.
     */
    public void setParameters(AddRemoveSphereParam parameters) {
        this.parameters = parameters;
    }
}
