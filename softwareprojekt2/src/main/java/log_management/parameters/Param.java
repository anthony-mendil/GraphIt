package log_management.parameters;

import lombok.Data;

@Data
public abstract class Param {
    public abstract String convertToJson();
}
