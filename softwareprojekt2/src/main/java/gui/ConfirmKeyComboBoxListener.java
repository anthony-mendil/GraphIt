package gui;

import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * Confirms and validates the input of the combobox for the sphere/symptom size option when the enter key was pressed.
 */
public class ConfirmKeyComboBoxListener implements EventHandler<KeyEvent> {
    /**
     * TODO
     */
    private static final String SIZE_SPHERE_COMBO_BOX = "sizeSphereComboBox";
    /**
     * TODO
     */
    private static final String SIZE_SYMPTOM_COMBO_BOX = "sizeSymptomComboBox";
    /**
     * TODO
     */
    private final Controller c;
    /**
     * TODO
     */
    private final ComboBox comboBox;

    /**
     * TODO
     * @param pC
     * @param pComboBox
     */
    ConfirmKeyComboBoxListener(Controller pC, ComboBox pComboBox) {
        c = pC;
        comboBox = pComboBox;
    }

    /**
     * Confirms and validates the input of the combobox for the sphere/symptom size option.
     * Only numbers between 4 and 96 is allowed.
     *
     * @param event The key event.
     */
    @Override
    public void handle(KeyEvent event) {
        comboBox.hide();
        if (event.getCode() == KeyCode.ENTER) {
            String tmpSize = comboBox.getEditor().getText();
            if (tmpSize.chars().allMatch(Character::isDigit)) {
                int size = Integer.parseInt(tmpSize);
                if (size > 3 && size <= 96) {
                    if (comboBox.getId().equals(SIZE_SPHERE_COMBO_BOX)) {
                        c.setCurrentSize(tmpSize);
                        c.editFontSizeSphere(size);
                    } else if (comboBox.getId().equals(SIZE_SYMPTOM_COMBO_BOX)) {
                        c.setCurrentSize(tmpSize);
                        c.editFontSizeVertices(size);
                    }
                }
            }
            c.getRoot().requestFocus();
        }
    }
}
