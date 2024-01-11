package hr.javafx.miletic7.sort;

import hr.javafx.miletic7.model.Item;

import java.util.Comparator;

public class ItemSorter implements Comparator<Item> {

    @Override
    public int compare(Item i1, Item i2) {
        if (i1.getVolume().compareTo(i2.getVolume())>0){
            return 1;
        } else if (i1.getVolume().compareTo(i2.getVolume())==0) {
            return 0;
        }
        else {
            return -1;
        }
    }
}
