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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class CategoryScreenController implements Initializable {
    @FXML
    private TextField categoryIdTextField;
    @FXML
    private TextField categoryNameTextField;
    @FXML
    private TableView<Category> categoryTableView;
    @FXML
    private TableColumn<Category,String> categoryIdTableColumn;
    @FXML
    private TableColumn<Category, String> categoryNameTableColumn;
    @FXML
    private TableColumn<Category, String> categoryDescTableColumn;

    public void categorySearch(){
        try {
            ObservableList<Category> observableCategoryList = FXCollections.observableArrayList(DatabaseUtils.getAllCategoriesFromDatabase());

            categoryTableView.setItems(observableCategoryList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void searchCategoryById(){
        List<Category> filteredList= new ArrayList<>();
        try {
            filteredList.add(DatabaseUtils.filterCategoriesByCriteria(new Category(Long.valueOf(categoryIdTextField.getText()),
                    "", "")).get(0));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ObservableList<Category> observableCategoryList= FXCollections.observableArrayList(filteredList);
        categoryTableView.setItems(observableCategoryList);
    }

    public void searchCategoryByName(){
        String categoryName=categoryNameTextField.getText();

        List<Category> filteredList= new ArrayList<>();
        try {
            filteredList = DatabaseUtils.filterCategoriesByCriteria(new Category(categoryNameTextField.getText(), ""));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ObservableList<Category> observableCategoryList= FXCollections.observableArrayList(filteredList);
        categoryTableView.setItems(observableCategoryList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        categoryIdTableColumn.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Category,String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<Category,String> param){
                        return new ReadOnlyStringWrapper(param.getValue().getId().toString());
                    }
                }
        );

        categoryNameTableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Category, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Category, String> param) {
                return new ReadOnlyStringWrapper(param.getValue().getName());
            }
        });

        categoryDescTableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Category, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Category, String> param) {
                return new ReadOnlyStringWrapper(param.getValue().getDescription());
            }
        });
    }
}
