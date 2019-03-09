package gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ComboBox;

/**
 * Listens to the focus change of the font and size comboboxes for the spheres and symptoms and evaluates accordingly to
 * each combobox if the input is valid after losing the focus.
 */
public class ComboBoxFocusListener implements ChangeListener<Boolean> {
    /**
     * The string to compare the id of the combobox with the id of the combobox, which is for the font sizes of spheres.
     */
    private static final String SIZE_SPHERE_COMBO_BOX = "sizeSphereComboBox";
    /**
     * The string to compare the id of the combobox with the id of the combobox, which is for the font sizes of symptoms
     */
    private static final String SIZE_SYMPTOM_COMBO_BOX = "sizeSymptomComboBox";
    /**
     * The string to compare the id of the combobox with the id of the combobox, which is for the fonts of spheres.
     */
    private static final String FONT_SYMPTOM_COMBO_BOX = "fontSymptomComboBox";
    /**
     * The string to compare the id of the combobox with the id of the combobox, which is for the fonts of symptoms.
     */
    private static final String FONT_SPHERE_COMBO_BOX = "fontSphereComboBox";
    /**
     * The combobox that the listener is assigned to.
     */
    private final ComboBox<String> comboBox;
    /**
     * The values object that contains all default attributes and current attributes of spheres, symptoms and edges.
     */
    private final Values values;

    /**
     * Creates a new ComboBoxFocusListener and sets this ComboBox and value instance.
     * @param pComboBox The ComboBox which is listened to.
     */
    ComboBoxFocusListener(ComboBox<String> pComboBox) {
        this.comboBox = pComboBox;
        values = Values.getInstance();
    }

    /**
     * Gets called when the textfield gets or loses the focus.
     * When the textfield is on focus, it shows the menu of the combobox.
     * When the textfield loses the focus, it validates the input of the textfield.
     * If the input is valid the combobox will call the action accordingly to the combobox and input.
     * If the input is invalid the combobox will be resetted to the last valid input.
     *
     * @param observable Is the textfield is focused or not.
     * @param oldValue   Was it focused before or not.
     * @param newValue   Is it focused now or not.
     */
    @Override
    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
        if (newValue) {
            comboBox.show();
        } else {
            switch (comboBox.getId()) {
                case SIZE_SPHERE_COMBO_BOX:
                    comboBox.getEditor().setText("" + values.getFontSizeSphere());
                    break;
                case SIZE_SYMPTOM_COMBO_BOX:
                    comboBox.getEditor().setText("" + values.getFontSizeVertex());
                    break;
                case FONT_SPHERE_COMBO_BOX:
                    comboBox.getEditor().setText("" + values.getFontSphere());
                    break;
                case FONT_SYMPTOM_COMBO_BOX:
                    comboBox.getEditor().setText(values.getFontVertex());
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        }
    }
}