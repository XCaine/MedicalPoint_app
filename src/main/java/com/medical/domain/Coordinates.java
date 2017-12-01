package com.medical.domain;

import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Embeddable
public class Coordinates {
    private BigDecimal x;
    private BigDecimal y;

    public BigDecimal getX() {
        return x;
    }

    public void setX(BigDecimal x) {
        this.x = x;
    }

    public BigDecimal getY() {
        return y;
    }

    public void setY(BigDecimal y) {
        this.y = y;
    }
}
