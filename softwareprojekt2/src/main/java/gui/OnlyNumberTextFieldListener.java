package gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

/**
 * Blocks the input of the textfield.
 * In this case everything except numbers.
 */
public class OnlyNumberTextFieldListener implements ChangeListener<String> {
    /**
     * The textfield that the listener is assigned to.
     */
    private TextField textField;

    OnlyNumberTextFieldListener(TextField pTextField) {
        textField = pTextField;
    }

    /**
     * Gets called when the user types something in the textfield and blocks every input that isn't
     * numbers.
     *
     * @param observable The text of the textfield.
     * @param oldValue   The old text of the textfield.
     * @param newValue   The new text of the textfield.
     */
    @Override
    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        if (!newValue.matches("\\d*"))
            textField.setText(oldValue);
    }
}
