package com.idealo.impl.factory.agent;

import com.idealo.impl.factory.agent.common.DiscountCommonAgent;
import com.idealo.model.Config;
import com.idealo.model.ItemRecord;
import com.idealo.model.OfferTypeAgent;
import com.idealo.utils.Constants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class TimerOfferAgent implements OfferTypeAgent {

  @Override
  public ItemRecord applyOffer(ItemRecord itemRecord, Config config) {
    validateMandatoryConfigs(config);
    Date today;
    Date offerDate;
    try {
      SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT);
      today = sdf.parse(sdf.format(new Date()));
      offerDate = sdf.parse(sdf.format(config.getOfferDate()));
    } catch (ParseException e) {
      today = new Date();
      offerDate = config.getOfferDate();
    }

    if(Objects.equals(today, offerDate)) {
      itemRecord.setPrice(DiscountCommonAgent.getDiscountedPrice(itemRecord, config));
    } else {
      itemRecord.setPrice(itemRecord.getPrice() + config.getUnitPrice());
    }
    itemRecord.setCount(itemRecord.getCount() + 1);
    return itemRecord;
  }

  private void validateMandatoryConfigs(Config config) {
    if(config.getOfferDate() == null || config.getUnitPrice() == null) {
      throw new IllegalArgumentException("Pricing rules are incomplete");
    }
  }
}
