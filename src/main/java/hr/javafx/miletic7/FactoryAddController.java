package hr.javafx.miletic7;

import hr.javafx.miletic7.model.Address;
import hr.javafx.miletic7.model.Factory;
import hr.javafx.miletic7.utils.DatabaseUtils;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.ResourceBundle;

public class FactoryAddController implements Initializable{
    @FXML private TextField nameTextField;
    @FXML private TextField streetTextField;
    @FXML private ListView itemsListView;

    public void addNewFactory() {
        if (!nameTextField.getText().isEmpty() && !streetTextField.getText().isEmpty() && !itemsListView.getSelectionModel().isEmpty()){
            try {
                DatabaseUtils.insertNewFactoryToDatabase(new Factory(nameTextField.getText(),
                        new Address.AddressBuilder(streetTextField.getText()).build(), new HashSet<>(itemsListView.getSelectionModel().getSelectedItems())));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        itemsListView.setItems(FXCollections.observableArrayList(DatabaseUtils.getAllFactoriesFromDatabase()));
        itemsListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }
}
