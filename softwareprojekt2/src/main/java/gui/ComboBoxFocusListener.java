package gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ComboBox;

public class ComboBoxFocusListener implements ChangeListener<Boolean> {
    private final ComboBox<String> comboBox;
    private static final String SIZE_SPHERE_COMBO_BOX = "sizeSphereComboBox";
    private static final String SIZE_SYMPTOM_COMBO_BOX = "sizeSymptomComboBox";
    private static final String FONT_SYMPTOM_COMBO_BOX = "fontSymptomComboBox";
    private static final String FONT_SPHERE_COMBO_BOX = "fontSphereComboBox";
    private final Values values;

    public ComboBoxFocusListener(ComboBox<String> pComboBox) {
        this.comboBox = pComboBox;
        values = Values.getInstance();
    }

    @Override
    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
        if (newPropertyValue) {
            comboBox.show();
        } else {
            if (comboBox.getId().equals(SIZE_SPHERE_COMBO_BOX)) {
                comboBox.getEditor().setText(""+values.getFontSizeSphere());
            }else if(comboBox.getId().equals(SIZE_SYMPTOM_COMBO_BOX)){
                comboBox.getEditor().setText(""+values.getFontSizeVertex());
            }else if(comboBox.getId().equals(FONT_SPHERE_COMBO_BOX)){
                comboBox.getEditor().setText(""+values.getFontSphere());
            }else if (comboBox.getId().equals(FONT_SYMPTOM_COMBO_BOX)) {
                comboBox.getEditor().setText(values.getFontVertex());
            }
        }
    }
}