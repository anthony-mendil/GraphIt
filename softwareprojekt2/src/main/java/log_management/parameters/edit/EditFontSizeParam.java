package log_management.parameters.edit;

import log_management.parameters.Param;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.Serializable;

public class EditFontSizeParam extends Param implements Serializable {

    private int oldFontSize;
    private int newFontSize;
    // does the font size change everywhere or is for example a list of vertex id's needed?

    public EditFontSizeParam(int oldFontSize, int newFontSize) {
        this.oldFontSize = oldFontSize;
        this.newFontSize = newFontSize;
    }

    @Override
    public String convertToJson() {
        throw new NotImplementedException();
    }

}
