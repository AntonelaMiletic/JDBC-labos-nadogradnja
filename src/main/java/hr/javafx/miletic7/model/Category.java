package hr.javafx.miletic7.model;

import java.io.Serializable;

public class Category extends NamedEntity implements Serializable {
    private String description;

    public Category(String name, String description) {
        super(name);
        this.description = description;
    }

    public Category() {
        super();
    }

    public Category(Long categoryId, String categoryName, String categoryDescription) {
        super(categoryId, categoryName);
        this.description=categoryDescription;
    }

    public Category(String name) {
        super(name);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.getId() + " " + super.getName();
    }
}