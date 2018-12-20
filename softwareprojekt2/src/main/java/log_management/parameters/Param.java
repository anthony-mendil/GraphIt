package log_management.parameters;

import lombok.Data;

/**
 * the abstract param class
 */
@Data
public abstract class Param {
    /**
     * a to string method for pretty printing
     * @return a string
     */
    public abstract String toString();
}
