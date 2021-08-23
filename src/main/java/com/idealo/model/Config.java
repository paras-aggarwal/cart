package com.idealo.model;

import java.util.Date;

public class Config {
  OfferType offerType;
  DiscountType discountType;

  Integer offerPrice;
  Integer offerPercentage;
  Integer unitPrice;

  Integer offerQuantity;
  Date offerDate;

  public Config(OfferType offerType, DiscountType discountType, Integer offerPrice, Integer offerPercentage, Integer unitPrice, Integer offerQuantity, Date offerDate) {
    this.offerType = offerType;
    this.discountType = discountType;
    this.offerPrice = offerPrice;
    this.offerPercentage = offerPercentage;
    this.unitPrice = unitPrice;
    this.offerQuantity = offerQuantity;
    this.offerDate = offerDate;
  }

  public OfferType getOfferType() {
    return offerType;
  }

  public void setOfferType(OfferType offerType) {
    this.offerType = offerType;
  }

  public DiscountType getDiscountType() {
    return discountType;
  }

  public void setDiscountType(DiscountType discountType) {
    this.discountType = discountType;
  }

  public Integer getOfferPrice() {
    return offerPrice;
  }

  public void setOfferPrice(Integer offerPrice) {
    this.offerPrice = offerPrice;
  }

  public Integer getOfferPercentage() {
    return offerPercentage;
  }

  public void setOfferPercentage(Integer offerPercentage) {
    this.offerPercentage = offerPercentage;
  }

  public Integer getUnitPrice() {
    return unitPrice;
  }

  public void setUnitPrice(Integer unitPrice) {
    this.unitPrice = unitPrice;
  }

  public Integer getOfferQuantity() {
    return offerQuantity;
  }

  public void setOfferQuantity(Integer offerQuantity) {
    this.offerQuantity = offerQuantity;
  }

  public Date getOfferDate() {
    return offerDate;
  }

  public void setOfferDate(Date offerDate) {
    this.offerDate = offerDate;
  }
}
