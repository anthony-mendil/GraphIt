package gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

public class OnlyNumberTextFieldListener implements ChangeListener<String> {
    private TextField textField;

    public OnlyNumberTextFieldListener(TextField pTextField) {
        textField = pTextField;
    }

    @Override
    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        if (!newValue.matches("\\d*"))
            textField.setText(oldValue);
    }
}
