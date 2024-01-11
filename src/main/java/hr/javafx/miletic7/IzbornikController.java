package hr.javafx.miletic7;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class IzbornikController {
    public void showItemsScreen() {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("itemSearch.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            HelloApplication.getMainStage().setTitle("Items");
            HelloApplication.getMainStage().setScene(scene);
            HelloApplication.getMainStage().show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void showItemAdd() {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("itemAdd.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            HelloApplication.getMainStage().setTitle("Add item");
            HelloApplication.getMainStage().setScene(scene);
            HelloApplication.getMainStage().show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void showCategoryScreen() {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("categoryScreen.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            HelloApplication.getMainStage().setTitle("Category");
            HelloApplication.getMainStage().setScene(scene);
            HelloApplication.getMainStage().show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void showCategoryAdd() {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("categoryAdd.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            HelloApplication.getMainStage().setTitle("Add category");
            HelloApplication.getMainStage().setScene(scene);
            HelloApplication.getMainStage().show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void showFactoryScreen() {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("factoryScreen.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            HelloApplication.getMainStage().setTitle("Factory");
            HelloApplication.getMainStage().setScene(scene);
            HelloApplication.getMainStage().show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void showFactoryAdd() {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("factoryAdd.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            HelloApplication.getMainStage().setTitle("Add factory");
            HelloApplication.getMainStage().setScene(scene);
            HelloApplication.getMainStage().show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void showStoreScreen() {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("storeScreen.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            HelloApplication.getMainStage().setTitle("Store");
            HelloApplication.getMainStage().setScene(scene);
            HelloApplication.getMainStage().show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void showStoreAdd() {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("storeAdd.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            HelloApplication.getMainStage().setTitle("Add store");
            HelloApplication.getMainStage().setScene(scene);
            HelloApplication.getMainStage().show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
