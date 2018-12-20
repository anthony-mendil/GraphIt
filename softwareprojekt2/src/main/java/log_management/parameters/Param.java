package log_management.parameters;

import lombok.Data;

/**
 * The abstract param class.
 */
@Data
public abstract class Param {
    /**
     * A to-string method for pretty printing.
     * @return A string.
     */
    public abstract String toString();
}
