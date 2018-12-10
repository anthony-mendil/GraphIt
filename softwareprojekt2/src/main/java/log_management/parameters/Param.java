package log_management.parameters;

import lombok.Data;

import java.io.Serializable;

@Data
public abstract class Param implements Serializable {
    public abstract String convertToJson();
}
