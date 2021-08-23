package com.idealo.impl.factory.agent.common;

import com.idealo.model.Config;
import com.idealo.model.ItemRecord;

public class DiscountCommonAgent {

  public static int getDiscountedPrice(ItemRecord itemRecord, Config config) {
    int priceSoFar;
    switch (config.getDiscountType()) {
      case PERCENTAGE:
        priceSoFar = itemRecord.getPrice() + config.getUnitPrice();
        priceSoFar -= (priceSoFar / 100) * config.getOfferPercentage();
        break;
      case FLAT:
        priceSoFar = (itemRecord.getPrice() + config.getUnitPrice()) - config.getOfferPrice();
        break;
      case FIXED_PRICE:
        priceSoFar = config.getOfferPrice();
        break;
      default:
        priceSoFar = itemRecord.getPrice() + config.getUnitPrice();
        break;
    }
    return priceSoFar;
  }
}
