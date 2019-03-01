package gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ComboBox;

public class OnlyLettersSpacesComboBoxListener implements ChangeListener<String> {
    private final ComboBox<String> comboBox;

    public OnlyLettersSpacesComboBoxListener(ComboBox<String> pComboBox) {
        this.comboBox = pComboBox;
    }

    @Override
    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        if (!newValue.matches("[a-zA-Z ]*"))
            comboBox.getEditor().setText(oldValue);
    }
}