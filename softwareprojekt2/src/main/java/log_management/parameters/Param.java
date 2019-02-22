package log_management.parameters;

import lombok.Data;

import java.io.Serializable;

/**
 * The abstract param class.
 */
@Data
public class Param {

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
