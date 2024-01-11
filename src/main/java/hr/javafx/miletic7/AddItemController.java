package hr.javafx.miletic7;

import hr.javafx.miletic7.model.Category;
import hr.javafx.miletic7.model.Discount;
import hr.javafx.miletic7.model.Item;
import hr.javafx.miletic7.utils.DatabaseUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class AddItemController implements Initializable{
    @FXML private TextField itemNameTextField;
    @FXML private ComboBox<Category> itemsCategoryComboBox;
    @FXML private TextField itemWidthTextField;
    @FXML private TextField itemHeightTextField;
    @FXML private TextField itemLengthTextField;
    @FXML private TextField itemProductionCostTextField;
    @FXML private TextField itemSellingPriceTextField;
    @FXML private TextField itemDiscountTextField;

    public void addNewItem() {
        if(!itemNameTextField.getText().isEmpty() && !itemsCategoryComboBox.getSelectionModel().isEmpty() &&
                !itemWidthTextField.getText().isEmpty() &&
                !itemHeightTextField.getText().isEmpty() && !itemLengthTextField.getText().isEmpty() &&
                !itemProductionCostTextField.getText().isEmpty() && !itemSellingPriceTextField.getText().isEmpty()
                && !itemDiscountTextField.getText().isEmpty()){
            try {
                DatabaseUtils.insertNewItemToDatabase(new Item(itemNameTextField.getText(),
                        itemsCategoryComboBox.getValue(), new BigDecimal(itemWidthTextField.getText()),
                        new BigDecimal(itemHeightTextField.getText()), new BigDecimal(itemLengthTextField.getText()),
                        new BigDecimal(itemProductionCostTextField.getText()), new BigDecimal(itemSellingPriceTextField.getText()),
                        new Discount(new BigDecimal(itemDiscountTextField.getText()))));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Spremanje uspješno");
        alert.setHeaderText("Spremanje novog artikla je bilo uspješno!");
        alert.setContentText("Artikl " + itemNameTextField + " " + itemsCategoryComboBox + " uspješno se spremio!");
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            itemsCategoryComboBox.setItems(FXCollections.observableArrayList(DatabaseUtils.getAllCategoriesFromDatabase()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}