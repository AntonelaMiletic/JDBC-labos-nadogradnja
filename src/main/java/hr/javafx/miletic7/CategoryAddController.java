package hr.javafx.miletic7;

import hr.javafx.miletic7.model.Category;

import hr.javafx.miletic7.utils.DatabaseUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CategoryAddController{
    @FXML private TextField nameTextField;
    @FXML private TextField descTextField;

    public void addNewCategory(){
        if (!nameTextField.getText().isEmpty() && !descTextField.getText().isEmpty()) {
            try {
                DatabaseUtils.insertNewCategoryToDatabase(new Category(nameTextField.getText(), descTextField.getText()));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Spremanje uspješno");
        alert.setHeaderText("Spremanje novog artikla je bilo uspješno!");
        alert.setContentText("Kategorija " + nameTextField + " uspješno se spremila!");
        alert.showAndWait();
    }
}
