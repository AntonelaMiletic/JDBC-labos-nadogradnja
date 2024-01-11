/*package hr.javafx.miletic7.utils;

import hr.javafx.miletic7.enums.Gradovi;
import hr.javafx.miletic7.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

public class FileUtils {
    private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);
    private static final String CATEGORIES_TEXT_FILE_NAME = "dat/categories.txt";
    private static final String ITEMS_TEXT_FILE_NAME = "dat/items.txt";
    private static final String FACTORIES_TEXT_FILE_NAME = "dat/factories.txt";
    private static final String STORES_TEXT_FILE_NAME = "dat/stores.txt";

    public static List<Category> getCategoriesFromFile(){
        List<Category> categoriesList = new ArrayList<>();
        File categoriesFile = new File(CATEGORIES_TEXT_FILE_NAME);

        try(BufferedReader reader = new BufferedReader(new FileReader(categoriesFile))) {
            Optional<String> catIdOptionalString=Optional.empty();
            while((catIdOptionalString=Optional.ofNullable(reader.readLine())).isPresent()){
                Optional<Category> newCatOptional=Optional.empty();

                Long catId=Long.parseLong(catIdOptionalString.get());
                String catName=reader.readLine();
                String catDesc=reader.readLine();

                newCatOptional=Optional.of(new Category(catName, catDesc));

                if (newCatOptional.isPresent()){
                    categoriesList.add(newCatOptional.get());
                }
            }
        }
        catch (IOException ex){
            String message = "Dogodila se pogreška kod čitanja datoteke!";
            logger.error(message, ex);
            System.out.println(message);
        }

        for (Category category:categoriesList){
            System.out.println(category.getId() + " " + category.getName());
        }
        return categoriesList;
    }

    public static List<Item> getItemsFromFile(List<Category> categories){
        List<Item> itemsList = new ArrayList<>();
        File itemsFile = new File(ITEMS_TEXT_FILE_NAME);

        try(BufferedReader reader = new BufferedReader(new FileReader(itemsFile))) {
            Optional<String> itemIdOptionalString=Optional.empty();
            while((itemIdOptionalString=Optional.ofNullable(reader.readLine())).isPresent()){
                Optional<Item> newItemOptional=Optional.empty();

                Long itemId = Long.parseLong(itemIdOptionalString.get());
                String itemName=reader.readLine();
                String itemCatString=reader.readLine();
                Category itemCat=new Category();
                for(int j=0; j< categories.size(); j++){
                    if(categories.get(j).getName().compareTo(itemCatString)==0){
                        itemCat= categories.get(j);
                        break;
                    }
                }
                String itemWidthString=reader.readLine();
                BigDecimal itemWidth= new BigDecimal(itemWidthString);
                String itemHeightString=reader.readLine();
                BigDecimal itemHeight= new BigDecimal(itemHeightString);
                String itemLengthString=reader.readLine();
                BigDecimal itemLength= new BigDecimal(itemLengthString);
                String itemProductionCostString=reader.readLine();
                BigDecimal itemProductionCost= new BigDecimal(itemProductionCostString);
                String itemSellingPriceString=reader.readLine();
                BigDecimal itemSellingPrice= new BigDecimal(itemSellingPriceString);
                String itemDiscountString=reader.readLine();
                Discount itemDiscount=new Discount(new BigDecimal(itemDiscountString));


                if(itemCat.getId().compareTo(Item.ITEM_TYPE_EDIBLE)==0){
                   Integer itemTypeFood = Integer.parseInt(reader.readLine());
                   BigDecimal itemWeight = new BigDecimal(reader.readLine());
                   if(itemTypeFood == 1){
                       newItemOptional = Optional.of(new Jabuka(itemName, itemCat, itemWidth, itemHeight, itemLength, itemProductionCost, itemSellingPrice, itemWeight, itemDiscount));
                   }
                   else{
                       newItemOptional = Optional.of(new Kelj(itemName, itemCat, itemWidth, itemHeight, itemLength, itemProductionCost, itemSellingPrice, itemWeight, itemDiscount));
                   }
                }
                else {
                    String itemTypeTech=reader.readLine();
                    Integer itemWarrantyMonths = Integer.parseInt(reader.readLine());
                    newItemOptional=Optional.of(new Laptop(itemName, itemCat, itemWidth, itemHeight, itemLength, itemProductionCost, itemSellingPrice, itemWarrantyMonths, itemDiscount));
                }
                if (newItemOptional.isPresent()){
                    itemsList.add(newItemOptional.get());
                }
            }
        }
        catch (IOException ex){
            String message = "Dogodila se pogreška kod čitanja datoteke!";
            logger.error(message, ex);
            System.out.println(message);
        }

        return itemsList;
    }

    public static List<Factory> getFactoriesFromFile(List<Item> items){
        List<Factory> factoriesList = new ArrayList<>();
        File factoriesFile = new File(FACTORIES_TEXT_FILE_NAME);

        try(BufferedReader reader = new BufferedReader(new FileReader(factoriesFile))) {
            Optional<String> factoryIdOptionalString=Optional.empty();
            while((factoryIdOptionalString=Optional.ofNullable(reader.readLine())).isPresent()){
                Optional<Factory> newFactoryOptional=Optional.empty();

                Long factoryId=Long.parseLong(factoryIdOptionalString.get());
                String factoryName=reader.readLine();
                String street=reader.readLine();
                String houseNumber=reader.readLine();
                Integer cityNum = Integer.parseInt(reader.readLine());
                Optional<Gradovi> chosenCity = Optional.empty();
                switch (cityNum){
                    case 1:
                        chosenCity=Optional.of(Gradovi.ZAGREB);
                        break;
                    case 2:
                        chosenCity=Optional.of(Gradovi.RIJEKA);
                        break;
                    case 3:
                        chosenCity=Optional.of(Gradovi.OSIJEK);
                        break;
                    case 4:
                        chosenCity=Optional.of(Gradovi.VARAZDIN);
                        break;
                }

                Address address = new Address.AddressBuilder(street).setHouseNumber(houseNumber).setCity(chosenCity).build();

                List<String> itemChoice = List.of(reader.readLine().split(","));
                List<Integer> itemChoiceList = new ArrayList<>();
                for (String num : itemChoice) {
                    itemChoiceList.add(Integer.parseInt(num));
                }
                Set<Item> selectedItems = new HashSet<>();
                for (int item : itemChoiceList) {
                    selectedItems.add(items.get(item - 1));
                }

                newFactoryOptional=Optional.of(new Factory(factoryName, address, selectedItems));
                if (newFactoryOptional.isPresent()){
                    factoriesList.add(newFactoryOptional.get());
                }
            }
        }
        catch (IOException ex){
            String message = "Dogodila se pogreška kod čitanja datoteke!";
            logger.error(message, ex);
            System.out.println(message);
        }

        return factoriesList;
    }

    public static List<Store> getStoresFromFile(List<Item> items){
        List<Store> storesList = new ArrayList<>();
        File storesFile = new File(STORES_TEXT_FILE_NAME);

        try(BufferedReader reader = new BufferedReader(new FileReader(storesFile))) {
            Optional<String> storeIdOptionalString=Optional.empty();
            while((storeIdOptionalString=Optional.ofNullable(reader.readLine())).isPresent()){
                Optional<Store> newStoreOptional=Optional.empty();

                Long storeId=Long.parseLong(storeIdOptionalString.get());
                String storeName=reader.readLine();
                String webAddress=reader.readLine();

                List<String> itemChoice = List.of(reader.readLine().split(","));
                List<Integer> itemChoiceList = new ArrayList<>();
                for (String num : itemChoice) {
                    itemChoiceList.add(Integer.parseInt(num));
                }
                Set<Item> selectedItems = new HashSet<>();
                for (int item : itemChoiceList) {
                    selectedItems.add(items.get(item - 1));
                }

                Integer storeType = Integer.parseInt(reader.readLine());
                if(storeType==1){
                    newStoreOptional = Optional.of(new FoodStore(storeId, storeName, webAddress, selectedItems));
                }
                else{
                    newStoreOptional = Optional.of(new TechnicalStore(storeId, storeName, webAddress, selectedItems));
                }

                if (newStoreOptional.isPresent()){
                    storesList.add(newStoreOptional.get());
                }
            }
        }
        catch (IOException ex){
            String message = "Dogodila se pogreška kod čitanja datoteke!";
            logger.error(message, ex);
            System.out.println(message);
        }

        return storesList;
    }

    public static Long getNextItemId() {
        List<Item> items = getItemsFromFile(getCategoriesFromFile());
        Long lastItemId = items.stream().map(i -> i.getId()).max((i1, i2) -> i1.compareTo(i2)).get();
        return lastItemId + 1;
    }

    public static Long getNextCategoryId() {
        List<Category> categories = getCategoriesFromFile();
        Long lastCategoryId = categories.stream().map(i -> i.getId()).max((i1, i2) -> i1.compareTo(i2)).get();
        return lastCategoryId + 1;
    }

    public static Long getNextFactoryId() {
        List<Factory> factories = getFactoriesFromFile(getItemsFromFile(getCategoriesFromFile()));
        Long lastFactoryId = factories.stream().map(i -> i.getId()).max((i1, i2) -> i1.compareTo(i2)).get();
        return lastFactoryId + 1;
    }

    public static Long getNextStoreId() {
        List<Store> stores = getStoresFromFile(getItemsFromFile(getCategoriesFromFile()));
        Long lastStoreId = stores.stream().map(i -> i.getId()).max((i1, i2) -> i1.compareTo(i2)).get();
        return lastStoreId + 1;
    }*/

    /*public static void serializeCategories(List<Category> categoriesList){
        try(ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(CATEGORIES_SERIALIZATION_FILE_NAME))){
            oos.writeObject(categoriesList);
        }
        catch (IOException ex){
            System.out.println(ex);
            logger.error("Serialization error!", ex);
        }
    }

    public static void serializeItems(List<Item> itemList){
        try(ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(ITEMS_SERIALIZATION_FILE_NAME))){
            oos.writeObject(itemList);
        }
        catch (IOException ex){
            System.out.println(ex);
            logger.error("Serialization error!", ex);
        }
    }

    public static void serializeFactories(List<Factory> factoriesList){
        try(ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(FACTORIES_SERIALIZATION_FILE_NAME))){
            oos.writeObject(factoriesList);
        }
        catch (IOException ex){
            System.out.println(ex);
            logger.error("Serialization error!", ex);
        }
    }

    public static void serializeStores(List<Store> storesList){
        try(ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(STORES_SERIALIZATION_FILE_NAME))){
            oos.writeObject(storesList);
        }
        catch (IOException ex){
            System.out.println(ex);
            logger.error("Serialization error!", ex);
        }
    }
}*/
