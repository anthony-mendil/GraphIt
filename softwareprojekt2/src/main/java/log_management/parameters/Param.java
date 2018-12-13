package log_management.parameters;

import lombok.Data;

/**
 * the abstract param class
 */
@Data
public abstract class Param {
    public abstract String convertToJson();
}
