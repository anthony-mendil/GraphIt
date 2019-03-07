package gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ComboBox;

/**
 * Blocks the input of the textfield of the combobox.
 * In this case everything except letters.
 */
public class OnlyLettersSpacesComboBoxListener implements ChangeListener<String> {
    /**
     * The combobox that the listener is assigned to.
     */
    private final ComboBox<String> comboBox;

    OnlyLettersSpacesComboBoxListener(ComboBox<String> pComboBox) {
        this.comboBox = pComboBox;
    }

    /**
     * Gets called when the user types something in the textfield and blocks every input that isn't
     * letters.
     *
     * @param observable The text of the textfield.
     * @param oldValue   The old text of the textfield.
     * @param newValue   The new text of the textfield.
     */
    @Override
    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        if (!newValue.matches("[a-zA-Z ]*"))
            comboBox.getEditor().setText(oldValue);
    }
}