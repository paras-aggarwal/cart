package com.idealo.impl;

import com.idealo.impl.factory.OfferTypeAgentProvider;
import com.idealo.model.Config;
import com.idealo.model.ItemRecord;

import java.util.HashMap;
import java.util.Map;

public class CheckOut {
  private Map<String, ItemRecord> cartMap;
  private Map<String, Config> pricingRulesMap;

  public CheckOut(Map<String, Config> pricingRules) {
    this.cartMap = new HashMap<>();
    this.pricingRulesMap = new HashMap<>(pricingRules);
  }

  public void scan(String item) {
    Config config = pricingRulesMap.getOrDefault(item, null);
    if(config == null) {
      throw new IllegalArgumentException("No pricing rules available for such item");
    }
    ItemRecord itemRecord = cartMap.getOrDefault(item, new ItemRecord(0, 0));
    ItemRecord afterOfferRecord = new OfferTypeAgentProvider() // IMP
        .getOfferAgent(config.getOfferType())
        .applyOffer(itemRecord, config);
    cartMap.put(item, afterOfferRecord);
  }

  public Integer total() {
    int totalPrice = 0;
    for (Map.Entry<String, ItemRecord> entry : cartMap.entrySet()) {
      totalPrice += entry.getValue().getPrice();
    }
    return totalPrice;
  }

}
