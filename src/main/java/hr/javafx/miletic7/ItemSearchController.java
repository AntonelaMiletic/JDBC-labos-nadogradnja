package hr.javafx.miletic7;

import hr.javafx.miletic7.model.Category;
import hr.javafx.miletic7.model.Item;
import hr.javafx.miletic7.utils.DatabaseUtils;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ItemSearchController implements Initializable {
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

    @FXML
    private TextField itemNameTextField;
    @FXML
    private ComboBox<String> itemsCategoryComboBox;
    @FXML
    private TableView<Item> itemsTableView;
    @FXML
    private TableColumn<Item, String> itemNameTableColumn;
    @FXML
    private TableColumn<Item, String> itemCategoryTableColumn;
    @FXML
    private TableColumn<Item, String> itemWidthTableColumn;
    @FXML
    private TableColumn<Item, String> itemHeightTableColumn;
    @FXML
    private TableColumn<Item, String> itemLengthTableColumn;
    @FXML
    private TableColumn<Item, String> itemProductionCostsTableColumn;
    @FXML
    private TableColumn<Item, String> itemSellingPriceTableColumn;
    @FXML
    private TableColumn<Item, String> itemDiscountTableColumn;

    @FXML
    public void initializeComboBox(URL url, ResourceBundle resourceBundle){
        List<String> categoryItems=categories.stream()
                .map(Category::getName)
                .sorted()
                .collect(Collectors.toList());

        ObservableList<String> observableCategoryList=FXCollections.observableArrayList(categoryItems);
        itemsCategoryComboBox.setItems(observableCategoryList);
    }

    public void itemSearch(){
        ObservableList<Item> observableItemList= FXCollections.observableArrayList(DatabaseUtils.getAllItemsFromDatabase());
        itemsTableView.setItems(observableItemList);
    }

    public void itemSearchByName(){
        List<Item> filteredList=new ArrayList<>();
        try {
            filteredList=(DatabaseUtils.filterItemsByCriteria(new Item(itemNameTextField.getText())));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ObservableList<Item> observableItemList= FXCollections.observableArrayList(filteredList);
        itemsTableView.setItems(observableItemList);
    }

    public void itemSearchByCategory(){
        try {
            List<Category> categories = DatabaseUtils.getAllCategoriesFromDatabase();
            Category category = new Category();
            for (Category cat : categories) {
                if(cat.getName().compareTo(itemsCategoryComboBox.getValue())==0){
                    category=cat;
                }
            }
            List<Item> filteredList = DatabaseUtils.filterItemsByCriteria(new Item(category));
            ObservableList<Item> observableItemList= FXCollections.observableArrayList(filteredList);
            itemsTableView.setItems(observableItemList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeComboBox(url, resourceBundle);
        itemSearch();
        itemNameTableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Item, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Item, String> param) {
                return new ReadOnlyStringWrapper(param.getValue().getName());
            }
        });

        itemCategoryTableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Item, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Item, String> param) {
                return new ReadOnlyStringWrapper(param.getValue().getCategory().getName());
            }
        });

        itemWidthTableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Item, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Item, String> param) {
                return new ReadOnlyStringWrapper(param.getValue().getWidth().toString());
            }
        });

        itemHeightTableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Item, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Item, String> param) {
                return new ReadOnlyStringWrapper(param.getValue().getHeight().toString());
            }
        });

        itemLengthTableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Item, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Item, String> param) {
                return new ReadOnlyStringWrapper(param.getValue().getLength().toString());
            }
        });

        itemProductionCostsTableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Item, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Item, String> param) {
                return new ReadOnlyStringWrapper(param.getValue().getProductionCost().toString());
            }
        });

        itemSellingPriceTableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Item, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Item, String> param) {
                return new ReadOnlyStringWrapper(param.getValue().getSellingPrice().toString());
            }
        });

        itemDiscountTableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Item, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Item, String> param) {
                return new ReadOnlyStringWrapper(param.getValue().getDiscount().discountAmount().toString());
            }
        });
    }
}