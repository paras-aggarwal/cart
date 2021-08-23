package com.idealo;

import com.google.gson.Gson;
import com.idealo.impl.CheckOut;
import com.idealo.model.Config;
import com.idealo.model.PricingAttribute;
import com.idealo.model.PricingRule;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.idealo.utils.Constants.PRICING_RULES_FILE_PATH;

public class Application {

  public static void main(String[] args) throws FileNotFoundException {
    Map<String, Config> pricingRules = readPricingRules();
    CheckOut checkOut = new CheckOut(pricingRules);
    checkOut.scan("A");
    checkOut.scan("A");
    System.out.println(checkOut.total());
  }

  private static Map<String, Config> readPricingRules() throws FileNotFoundException {
    BufferedReader bufferedReader = new BufferedReader(new FileReader(PRICING_RULES_FILE_PATH));
    PricingRule pricingRule = new Gson().fromJson(bufferedReader, PricingRule.class);
    return constructPricingRulesMap(pricingRule.getPricingRules());
  }

  private static Map<String, Config> constructPricingRulesMap(List<PricingAttribute> pricingRules) {
    Map<String, Config> configMap = new HashMap<>();
    for(PricingAttribute attribute : pricingRules) {
      Config config = new Config(attribute.getOfferType(), attribute.getDiscountType(), attribute.getOfferPrice(),
          attribute.getOfferPercentage(), attribute.getUnitPrice(), attribute.getOfferQuantity(), attribute.getOfferDate());
      config.setOfferDate(new Date());
      configMap.put(attribute.getItem(), config);
    }
    return configMap;
  }
}
