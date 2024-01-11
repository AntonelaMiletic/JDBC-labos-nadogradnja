/*package hr.javafx.miletic7.generics;

import hr.javafx.miletic7.model.Store;
import hr.javafx.miletic7.model.Technical;

import java.util.Set;

public class TechnicalStore <T extends Technical> extends Store {
    private Set<T> technicalItems;

    public TechnicalStore(Long id, String name, String webAddress, Set<T> technicalItems) {
        super(id, name, webAddress, technicalItems);
        this.technicalItems = technicalItems;
    }

    public TechnicalStore(Set<T> technicalItems) {
        this.technicalItems = technicalItems;
    }

    public TechnicalStore() {

    }

    @Override
    public Set<T> getItems() {
        return technicalItems;
    }

    public TechnicalStore(String technicalStore, String s, Set<T> technicalItems) {
    }

    public Set<T> getTechnicalItems() {
        return technicalItems;
    }

    public void setTechnicalItems(Set<T> technicalItems) {
        this.technicalItems = technicalItems;
    }
}*/