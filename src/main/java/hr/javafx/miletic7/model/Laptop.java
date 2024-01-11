package hr.javafx.miletic7.model;

import java.io.Serializable;
import java.math.BigDecimal;

public non-sealed class Laptop extends Item implements Technical, Serializable {
    private Integer warrantyMonths;

    public Laptop(String name, Category category, BigDecimal width, BigDecimal height, BigDecimal length, BigDecimal productionCost, BigDecimal sellingPrice, Integer warrantyMonths, Discount discount) {
        super(name, category, width, height, length, productionCost, sellingPrice, discount);
        this.warrantyMonths = warrantyMonths;
    }

    public Laptop(Integer warrantyMonths) {
        this.warrantyMonths = warrantyMonths;
    }

    @Override
    public Integer getWarrantyMonths() {
        return warrantyMonths;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
