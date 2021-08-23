package com.idealo.impl.factory.agent;

import com.idealo.model.Config;
import com.idealo.model.ItemRecord;
import com.idealo.model.OfferTypeAgent;

public class NoOfferAgent implements OfferTypeAgent {

  @Override
  public ItemRecord applyOffer(ItemRecord itemRecord, Config config) {
    validateMandatoryConfigs(config);
    itemRecord.setPrice(itemRecord.getPrice() + config.getUnitPrice());
    itemRecord.setCount(itemRecord.getCount() + 1);
    return itemRecord;
  }

  private void validateMandatoryConfigs(Config config) {
    if(config.getUnitPrice() == null) {
      throw new IllegalArgumentException("Pricing rules are incomplete");
    }
  }
}
