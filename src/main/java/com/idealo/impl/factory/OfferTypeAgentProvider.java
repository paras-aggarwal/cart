package com.idealo.impl.factory;

import com.idealo.impl.factory.agent.NoOfferAgent;
import com.idealo.impl.factory.agent.QuantityOfferAgent;
import com.idealo.impl.factory.agent.TimerOfferAgent;
import com.idealo.model.OfferType;
import com.idealo.model.OfferTypeAgent;

import java.util.HashMap;
import java.util.Map;

public class OfferTypeAgentProvider {

  private Map<OfferType, OfferTypeAgent> factory;

  private void populateFactory() {
    factory = new HashMap<>();
    factory.put(OfferType.QUANTITY, new QuantityOfferAgent());
    factory.put(OfferType.TIMED, new TimerOfferAgent());
    factory.put(OfferType.NONE, new NoOfferAgent());
    factory.put(null, new NoOfferAgent());
  }

  public OfferTypeAgent getOfferAgent(OfferType offerType) {
    if(factory == null) {
      populateFactory();
    }
    return factory.get(offerType);
  }
}
