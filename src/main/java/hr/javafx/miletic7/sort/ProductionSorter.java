package hr.javafx.miletic7.sort;

import hr.javafx.miletic7.model.Item;

import java.util.Comparator;

public class ProductionSorter implements Comparator<Item> {
    private boolean asc;
    public ProductionSorter(boolean asc){
        this.asc = asc;
    }

    @Override
    public int compare(Item i1, Item i2) {
        if (i1.getSellingPrice().compareTo(i2.getSellingPrice())>0){
            if (asc) return 1;
            else return -1;
        }
        else if(i1.getSellingPrice().compareTo(i2.getSellingPrice())<0){
            if (asc) return -1;
            else return 1;
        }
        else {
            return 0;
        }
    }
}
