package com.idealo.impl.factory.agent;

import com.idealo.impl.factory.agent.common.DiscountCommonAgent;
import com.idealo.model.Config;
import com.idealo.model.ItemRecord;
import com.idealo.model.OfferTypeAgent;

public class QuantityOfferAgent implements OfferTypeAgent {

  @Override
  public ItemRecord applyOffer(ItemRecord itemRecord, Config config) {
    validateMandatoryConfigs(config);
    int newItemCount = itemRecord.getCount() + 1;
    if(newItemCount % config.getOfferQuantity() == 0) { // IMP (clearity) - state based
      itemRecord.setPrice(DiscountCommonAgent.getDiscountedPrice(itemRecord, config) * (newItemCount / config.getOfferQuantity()));
    } else {
      itemRecord.setPrice(itemRecord.getPrice() + config.getUnitPrice());
    }
    itemRecord.setCount(newItemCount);
    return itemRecord;
  }

  private void validateMandatoryConfigs(Config config) {
    if(config.getOfferQuantity() == null || config.getUnitPrice() == null) {
      throw new IllegalArgumentException("Pricing rules are incomplete");
    }
    if(config.getOfferQuantity() <= 0) {
      throw new IllegalArgumentException("Quantity should be greater than zero");
    }
  }
}
