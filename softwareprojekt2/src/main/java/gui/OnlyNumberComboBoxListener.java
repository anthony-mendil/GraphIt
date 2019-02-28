package gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ComboBox;

public class OnlyNumberComboBoxListener implements ChangeListener<String> {
    private final ComboBox<String> comboBox;

    public OnlyNumberComboBoxListener(ComboBox<String> pComboBox) {
        this.comboBox = pComboBox;
    }

    @Override
    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        if (!newValue.matches("\\d*"))
            comboBox.getEditor().setText(oldValue);

        if (comboBox.getEditor().getText().length() > 3)
            comboBox.getEditor().setText(comboBox.getEditor().getText(0, 3));
    }
}
