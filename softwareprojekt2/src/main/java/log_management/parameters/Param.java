package log_management.parameters;

import lombok.Data;

import java.io.Serializable;

@Data
public abstract class Param {
    public abstract String convertToJson();
}
