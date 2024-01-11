package hr.javafx.miletic7.model;

import java.io.Serializable;
import java.math.BigDecimal;

public record Discount(BigDecimal discountAmount) implements Serializable {
    @Override
    public boolean equals(Object obj) {
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
