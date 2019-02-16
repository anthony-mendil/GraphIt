package log_management.parameters;

import log_management.parameters.add_remove.AddRemoveSphereParam;
import lombok.Data;

import java.io.Serializable;

/**
 * The abstract param class.
 */
@Data
public class Param implements Serializable {

    public Param() {}
    /**
     * A to-string method for pretty printing.
     * @return A string.
     */
    public String prettyPrint() {
        //if (this instanceof AddRemoveSphereParam) {
            //AddRemoveSphereParam param = (AddRemoveSphereParam) this;
            //return param.prettyPrint();
        //}
        //else {
         //   return "";
        //}
        return "";
    }
}
