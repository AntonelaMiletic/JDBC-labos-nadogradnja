package hr.javafx.miletic7;

import hr.javafx.miletic7.model.Category;
import hr.javafx.miletic7.model.Factory;
import hr.javafx.miletic7.model.Item;
import hr.javafx.miletic7.model.Store;
import hr.javafx.miletic7.utils.DatabaseUtils;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;

public class StoreScreenController implements Initializable {
    List<Category> categories;

    {
        try {
            categories = DatabaseUtils.getAllCategoriesFromDatabase();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    List<Item> items=DatabaseUtils.getAllItemsFromDatabase();

    @FXML
    private TextField storeIdTextField;
    @FXML
    private TextField storeNameTextField;
    @FXML
    private TextField storeWebAddressTextField;
    @FXML
    private TableView storeTableView;
    @FXML
    private TableColumn<Store,String> storeIdTableColumn;
    @FXML
    private TableColumn<Store,String> storeNameTableColumn;
    @FXML
    private TableColumn <Store,String> storeWebAdressTableColumn;
    @FXML
    private TableColumn <Store,String> storeItemsTableColumn;



    public void storeSearch(){
        ObservableList<Store> observableStoreList= FXCollections.observableArrayList(hr.javafx.miletic7.HelloApplication.stores);
        storeTableView.setItems(observableStoreList);
    }

    public void storeSearchById(){
        Long id= Long.valueOf(storeIdTextField.getText());
        List<Store> filteredList= hr.javafx.miletic7.HelloApplication.stores.stream()
                .filter(store->store.getId().equals(id))
                .collect(Collectors.toList());

        ObservableList<Store> observableFactoryList= FXCollections.observableArrayList(filteredList);
        storeTableView.setItems(observableFactoryList);
    }

    public void storeSearchByName(){
        String name=storeNameTextField.getText();
        List<Store> filteredList= hr.javafx.miletic7.HelloApplication.stores.stream()
                .filter(store->store.getName().contains(name))
                .collect(Collectors.toList());

        ObservableList<Store> observableStoreList= FXCollections.observableArrayList(filteredList);
        storeTableView.setItems(observableStoreList);
    }

    public void searchStoreByWebAdress(){
        String webAdress=storeWebAddressTextField.getText();
        List<Store> filteredList= hr.javafx.miletic7.HelloApplication.stores.stream()
                .filter(store->store.getWebAddress().contains(webAdress))
                .collect(Collectors.toList());

        ObservableList<Store> observableStoreList= FXCollections.observableArrayList(filteredList);
        storeTableView.setItems(observableStoreList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        storeIdTableColumn.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Store, String>, ObservableValue<String>>(){
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<Store,String> param){
                        return new ReadOnlyStringWrapper(param.getValue().getId().toString());
                    }
                }
        );

        storeNameTableColumn.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Store,String>, ObservableValue<String>>(){
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<Store,String> param){
                        return new ReadOnlyStringWrapper(param.getValue().getName());
                    }
                }
        );

        storeWebAdressTableColumn.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Store,String>, ObservableValue<String>>(){
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<Store,String> param){
                        return new ReadOnlyStringWrapper(param.getValue().getWebAddress());
                    }
                }
        );

        storeItemsTableColumn.setCellValueFactory(
                param->{
                    Set<Item> items=param.getValue().getItems();
                    String itemNames=items.stream()
                            .map(Item::getName)
                            .collect(Collectors.joining("\n"));

                    return new ReadOnlyStringWrapper(itemNames);
                }
        );
        storeTableView.getItems().setAll(hr.javafx.miletic7.HelloApplication.stores);
    }
}
