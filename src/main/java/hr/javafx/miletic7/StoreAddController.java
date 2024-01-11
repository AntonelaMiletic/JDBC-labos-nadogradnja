package hr.javafx.miletic7;

import hr.javafx.miletic7.model.Store;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;

public class StoreAddController implements Initializable {
    @FXML private TextField nameTextField;
    @FXML private TextField webAddressTextField;
    @FXML private ListView itemsListView;

    public void addNewStore() {
        if (!nameTextField.getText().isEmpty() && !webAddressTextField.getText().isEmpty() && !itemsListView.getSelectionModel().isEmpty()) {
            HelloApplication.stores.add(new Store(nameTextField.getText(),
                    webAddressTextField.getText(), new HashSet(itemsListView.getSelectionModel().getSelectedItems())));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        itemsListView.setItems(FXCollections.observableArrayList(HelloApplication.items));
        itemsListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }
}
