package gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ComboBox;

/**
 * Blocks the input of the textfield of the combobox.
 * In this case everything except numbers and every number with more than 2 digits.
 *
 * @author Jacky Philipp Mach
 */
public class OnlyNumberComboBoxListener implements ChangeListener<String> {
    /**
     * The combobox that the listener is assigned to.
     */
    private final ComboBox<String> comboBox;

    /**
     * Constructor for the comboBox only allowing numbers.
     * @param pComboBox The comboBox.
     */
    OnlyNumberComboBoxListener(ComboBox<String> pComboBox) {
        this.comboBox = pComboBox;
    }

    /**
     * Gets called when the user types something in the textfield and blocks every input that isn't
     * numbers and more than 2 digits.
     *
     * @param observable The text of the textfield.
     * @param oldValue   The old text of the textfield.
     * @param newValue   The new text of the textfield.
     */
    @Override
    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        if (!newValue.matches("\\d*"))
            comboBox.getEditor().setText(oldValue);

        if (comboBox.getEditor().getText().length() > 2)
            comboBox.getEditor().setText(comboBox.getEditor().getText(0, 2));
    }
}
