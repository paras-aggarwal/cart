package com.idealo.model;

public class ItemRecord {
  int count;
  int price;

  public ItemRecord(int count, int price) {
    this.count = count;
    this.price = price;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }
}
