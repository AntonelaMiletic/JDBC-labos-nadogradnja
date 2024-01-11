package hr.javafx.miletic7;

import hr.javafx.miletic7.model.Category;
import hr.javafx.miletic7.model.Factory;
import hr.javafx.miletic7.model.Item;
import hr.javafx.miletic7.utils.DatabaseUtils;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.Callback;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FactoryScreenController {
    List<Category> categories;
    List<Item> itemsList;
    {
        try {
            categories = DatabaseUtils.getAllCategoriesFromDatabase();
            itemsList= DatabaseUtils.getAllItemsFromDatabase();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private TextField factoryIdTextField;
    @FXML
    private TextField factoryNameTextField;
    @FXML
    private TableView<Factory> factoryTableView;
    @FXML
    private TableColumn<Factory,String> factoryIdTableColumn;
    @FXML
    private TableColumn<Factory,String> factoryNameTableColumn;
    @FXML
    private TableColumn<Factory,String> factoryStreetTableColumn;
    @FXML
    private TableColumn<Factory,String> factoryItemsTableColumn;

    public void initialize(){
        factoryIdTableColumn.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Factory, String>, ObservableValue<String>>(){
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<Factory,String> param){
                        return new ReadOnlyStringWrapper(param.getValue().getId().toString());
                    }
                }
        );

        factoryNameTableColumn.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Factory, String>, ObservableValue<String>>(){
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<Factory,String> param){
                        return new ReadOnlyStringWrapper(param.getValue().getName());
                    }
                }
        );

        factoryStreetTableColumn.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Factory, String>, ObservableValue<String>>(){
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<Factory,String> param){
                        return new ReadOnlyStringWrapper(param.getValue().getAddress().getStreet());
                    }
                }
        );

        factoryItemsTableColumn.setCellValueFactory(
                param->{
                    Set<Item> items=param.getValue().getItems();
                    String itemNames=items.stream()
                            .map(Item::getName)
                            .collect(Collectors.joining("\n"));

                    return new ReadOnlyStringWrapper(itemNames);
                }
        );
    }

    public void factorySearch(){
        ObservableList<Factory> observableFactoryList= FXCollections.observableArrayList(hr.javafx.miletic7.HelloApplication.factories);
        factoryTableView.setItems(observableFactoryList);
    }

    public void factorySearchByName(){

        String name=factoryNameTextField.getText();
        List<Factory> filteredList= hr.javafx.miletic7.HelloApplication.factories.stream()
                .filter(factory->factory.getName().contains(name))
                .collect(Collectors.toList());

        ObservableList<Factory> observableFactoryList= FXCollections.observableArrayList(filteredList);
        factoryTableView.setItems(observableFactoryList);
    }

    public void factorySearchById(){
        Long id= Long.valueOf(factoryIdTextField.getText());
        List<Factory> filteredList= hr.javafx.miletic7.HelloApplication.factories.stream()
                .filter(factory->factory.getId().equals(id))
                .collect(Collectors.toList());

        ObservableList<Factory> observableFactoryList= FXCollections.observableArrayList(filteredList);
        factoryTableView.setItems(observableFactoryList);
    }
}
