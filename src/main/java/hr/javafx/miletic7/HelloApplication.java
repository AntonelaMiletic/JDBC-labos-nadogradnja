package hr.javafx.miletic7;

import hr.javafx.miletic7.model.Category;
import hr.javafx.miletic7.model.Factory;
import hr.javafx.miletic7.model.Item;
import hr.javafx.miletic7.model.Store;
import hr.javafx.miletic7.utils.DatabaseUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

public class HelloApplication extends Application {
    public static Stage mainStage;
    public static List<Category> categories;
    public static List<Item> items;
    public static List<Factory> factories;
    public static List<Store> stores;

    @Override
    public void start(Stage stage) throws IOException {
        mainStage = stage;
        try {
            categories= DatabaseUtils.getAllCategoriesFromDatabase();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        items= DatabaseUtils.getAllItemsFromDatabase();
        //factories=FileUtils.getFactoriesFromFile(items);
        //stores=FileUtils.getStoresFromFile(items);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
    public static Stage getMainStage() {
        return mainStage;
    }

    public static void main(String[] args) {
        launch();
    }
}