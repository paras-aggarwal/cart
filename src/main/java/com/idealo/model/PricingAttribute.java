package com.idealo.model;

import java.util.Date;

public class PricingAttribute {
    String item;
    OfferType offerType;
    DiscountType discountType;
    Integer offerPrice;
    Integer offerPercentage;
    Integer unitPrice;
    Integer offerQuantity;
    Date offerDate;

    public String getItem() {
        return item;
    }

    public OfferType getOfferType() {
        return offerType;
    }

    public DiscountType getDiscountType() {
        return discountType;
    }

    public Integer getOfferPrice() {
        return offerPrice;
    }

    public Integer getOfferPercentage() {
        return offerPercentage;
    }

    public Integer getUnitPrice() {
        return unitPrice;
    }

    public Integer getOfferQuantity() {
        return offerQuantity;
    }

    public Date getOfferDate() {
        return offerDate;
    }

    public void setOfferDate(Date offerDate) {
        this.offerDate = offerDate;
    }
}
