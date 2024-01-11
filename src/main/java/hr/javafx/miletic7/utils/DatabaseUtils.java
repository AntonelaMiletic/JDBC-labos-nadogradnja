package hr.javafx.miletic7.utils;

import hr.javafx.miletic7.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.transform.Source;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class DatabaseUtils {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseUtils.class);
    private static final String DATABASE_FILE = "conf/database.properties";
    private static Connection connectToDatabase() throws SQLException, IOException {
        Properties svojstva = new Properties();
        svojstva.load(new FileReader(DATABASE_FILE));
        String urlBazePodataka = svojstva.getProperty("databaseUrl");
        String korisnickoIme = svojstva.getProperty("username");
        String lozinka = svojstva.getProperty("password");
        Connection veza = DriverManager.getConnection(urlBazePodataka,
                korisnickoIme,lozinka);
        return veza;
    }
    public static void closeConnection(Connection connection) throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    public static void insertNewCategoryToDatabase(Category category) throws SQLException, IOException {
        Connection connection = connectToDatabase();
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO CATEGORY (NAME, DESCRIPTION) VALUES(?, ?)");
        stmt.setString(1, category.getName());
        stmt.setString(2, category.getDescription());
        stmt.executeUpdate();
        closeConnection(connection);
    }

    public static List<Category> getAllCategoriesFromDatabase() throws SQLException, IOException {
        Connection connection = connectToDatabase();
        List<Category> categoryList = new ArrayList<>();
        Statement sqlStatement = connection.createStatement();
        ResultSet categoriesResultSet = sqlStatement.executeQuery("SELECT * FROM CATEGORY");
        while(categoriesResultSet.next()) {
            Category newCategory = getCategoryFromResultSet(categoriesResultSet);
            categoryList.add(newCategory);
        }
        connection.close();
        return categoryList;
    }

    private static Category getCategoryFromResultSet(ResultSet categoriesResultSet) throws SQLException {
        Long categoryId = categoriesResultSet.getLong("ID");
        String categoryName = categoriesResultSet.getString("NAME");
        String categoryDescription = categoriesResultSet.getString("DESCRIPTION");
        return new Category(categoryId, categoryName, categoryDescription);
    }

    public static List<Category> filterCategoriesByCriteria(Category criteria) throws SQLException, IOException {
        Connection connection = connectToDatabase();
        List<Category> filteredCategoryList = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM CATEGORY WHERE 1=1");
        if(Optional.ofNullable(criteria.getId()).isPresent()) {
            sql.append(" AND ID = " + criteria.getId());
        }
        if(!criteria.getName().isEmpty()) {
            sql.append(" AND NAME LIKE '%" + criteria.getName() + "%'");
        }

        PreparedStatement statement = connection.prepareStatement(sql.toString());
        ResultSet categoriesResultSet = statement.executeQuery();
        while(categoriesResultSet.next()) {
            Category newCategory = getCategoryFromResultSet(categoriesResultSet);
            filteredCategoryList.add(newCategory);
        }
        connection.close();
        return filteredCategoryList;
    }

    public static void insertNewItemToDatabase(Item item) throws SQLException, IOException {
        Connection connection = connectToDatabase();
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO ITEM (CATEGORY_ID, NAME, WIDTH, HEIGHT, LENGTH," +
                "PRODUCTION_COST, SELLING_PRICE, DISCOUNT) VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
        stmt.setLong(1, item.getCategory().getId());
        stmt.setString(2, item.getName());
        stmt.setBigDecimal(3, item.getWidth());
        stmt.setBigDecimal(4, item.getHeight());
        stmt.setBigDecimal(5, item.getLength());
        stmt.setBigDecimal(6, item.getProductionCost());
        stmt.setBigDecimal(7, item.getSellingPrice());
        stmt.setBigDecimal(8, item.getDiscount().discountAmount());
        stmt.executeUpdate();
        connection.close();
    }

    public static List<Item> getAllItemsFromDatabase(){
        try {
            Connection connection = connectToDatabase();

            List<Item> itemList = new ArrayList<>();
            Statement sqlStatement = connection.createStatement();
            ResultSet itemsResultSet = sqlStatement.executeQuery("SELECT * FROM ITEM");
            while(itemsResultSet.next()) {
                Item newItem = getItemFromResultSet(itemsResultSet);
                itemList.add(newItem);
            }
            connection.close();
            return itemList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Item getItemFromResultSet(ResultSet itemsResultSet) {
        List<Category> categories=new ArrayList<>();
        try {
            categories = getAllCategoriesFromDatabase();

            Long id = itemsResultSet.getLong("ID");
            Long catId=itemsResultSet.getLong("CATEGORY_ID");
            Category category=new Category();
            for (Category cat : categories) {
                if(cat.getId().compareTo(catId)==0){
                    category=cat;
                }
            }
            String name=itemsResultSet.getString("NAME");
            BigDecimal width=itemsResultSet.getBigDecimal("WIDTH");
            BigDecimal height=itemsResultSet.getBigDecimal("HEIGHT");
            BigDecimal length=itemsResultSet.getBigDecimal("LENGTH");
            BigDecimal productionCost=itemsResultSet.getBigDecimal("PRODUCTION_COST");
            BigDecimal sellingPrice=itemsResultSet.getBigDecimal("SELLING_PRICE");
            Discount discount=new Discount(itemsResultSet.getBigDecimal("DISCOUNT"));

            return new Item(id, name, category, width, height, length, productionCost, sellingPrice, discount);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Item> filterItemsByCriteria(Item criteria) throws SQLException, IOException {
        Connection connection = connectToDatabase();
        List<Item> filteredItemList = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM ITEM WHERE 1=1");
        if(criteria.getName()!=null && !criteria.getName().isEmpty()) {
            sql.append(" AND NAME LIKE '%" + criteria.getName() + "%'");
        }
        if(Optional.ofNullable(criteria.getCategory()).isPresent()) {
            sql.append(" AND CATEGORY_ID = " + criteria.getCategory().getId());
        }

        PreparedStatement statement = connection.prepareStatement(sql.toString());
        ResultSet itemsResultSet = statement.executeQuery();
        while(itemsResultSet.next()) {
            Item newItem = getItemFromResultSet(itemsResultSet);
            filteredItemList.add(newItem);
        }
        connection.close();
        return filteredItemList;
    }

    public static void insertNewFactoryToDatabase(Factory factory) throws SQLException, IOException {
        Connection connection = connectToDatabase();
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO FACTORY (NAME, ADDRESS_ID) VALUES(?, ?, ?)");
        stmt.setString(1, factory.getName());
        stmt.setLong(2, factory.getAddress().getId());
        stmt.executeUpdate();
        connection.close();
    }

    public static List<Factory> getAllFactoriesFromDatabase(){
        try {
            Connection connection = connectToDatabase();

            List<Factory> factoryList = new ArrayList<>();
            Statement sqlStatement = connection.createStatement();
            ResultSet factoriesResultSet = sqlStatement.executeQuery("SELECT * FROM FACTORY");
            while(factoriesResultSet.next()) {
                Factory newFactory = getFactoryFromResultSet(factoriesResultSet);
                factoryList.add(newFactory);
            }
            connection.close();
            return factoryList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Factory getFactoryFromResultSet(ResultSet factoriesResultSet) {
        List<Factory> factories=new ArrayList<>();
        try {
            factories = getAllFactoriesFromDatabase();

            Long id = factoriesResultSet.getLong("ID");
            String name=factoriesResultSet.getString("NAME");
            Long addressId=factoriesResultSet.getLong("ADDRESS_ID");

            return new Factory(id, name, addressId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Factory> filterFactoriesByCriteria(Factory criteria) throws SQLException, IOException {
        Connection connection = connectToDatabase();
        List<Factory> filteredFactoryList = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM FACTORY WHERE 1=1");
        if(Optional.ofNullable(criteria.getId()).isPresent()) {
            sql.append(" AND ID = " + criteria.getId());
        }
        if(!criteria.getName().isEmpty()) {
            sql.append(" AND NAME LIKE '%" + criteria.getName() + "%'");
        }

        PreparedStatement statement = connection.prepareStatement(sql.toString());
        ResultSet factoriesResultSet = statement.executeQuery();
        while(factoriesResultSet.next()) {
            Factory newFactory = getFactoryFromResultSet(factoriesResultSet);
            filteredFactoryList.add(newFactory);
        }
        connection.close();
        return filteredFactoryList;
    }
}
