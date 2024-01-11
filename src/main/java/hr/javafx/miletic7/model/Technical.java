package hr.javafx.miletic7.model;

public sealed interface Technical permits Laptop{
    Integer getWarrantyMonths();
}
