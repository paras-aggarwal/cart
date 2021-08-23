package com.idealo.impl;

import com.google.gson.Gson;
import com.idealo.model.Config;
import com.idealo.model.PricingAttribute;
import com.idealo.model.PricingRule;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class CheckOutTest {

  private static final String PRICING_RULES_FILE_PATH = "src/test/resources/rules.json";

  private PricingRule readPricingRuleFromJsonFile() throws FileNotFoundException {
    BufferedReader bufferedReader = new BufferedReader(new FileReader(PRICING_RULES_FILE_PATH));
    Gson gson = new Gson();
    return gson.fromJson(bufferedReader, PricingRule.class);
  }

  private Map<String, Config> constructPricingRuleMap(List<PricingAttribute> pricingRules) {
    Map<String, Config> pricingRulesMap = new HashMap<>();
    for(PricingAttribute attribute : pricingRules) {
      Config config = new Config(attribute.getOfferType(), attribute.getDiscountType(), attribute.getOfferPrice(),
          attribute.getOfferPercentage(), attribute.getUnitPrice(), attribute.getOfferQuantity(), attribute.getOfferDate());
      config.setOfferDate(new Date());
      pricingRulesMap.put(attribute.getItem(), config);
    }
    return pricingRulesMap;
  }

  private Map<String, Config> populateCorrectPricingRules() throws FileNotFoundException {
    PricingRule pricingRule = readPricingRuleFromJsonFile();
    return constructPricingRuleMap(pricingRule.getPricingRules());
  }

  public int calculatePrice(String goods) throws FileNotFoundException {
    CheckOut checkout = new CheckOut(populateCorrectPricingRules());
    for(int i = 0; i < goods.length(); i++) {
      checkout.scan(String.valueOf(goods.charAt(i)));
    }
    return checkout.total();
  }

  @Test
  public void totals() throws FileNotFoundException {
    assertEquals(0, calculatePrice(""));
    assertEquals(40, calculatePrice("A"));
    assertEquals(90, calculatePrice("AB"));
    assertEquals(135, calculatePrice("CDBA"));
    assertEquals(80, calculatePrice("AA"));
    assertEquals(100, calculatePrice("AAA"));
    assertEquals(140, calculatePrice("AAAA"));
    assertEquals(180, calculatePrice("AAAAA"));
    assertEquals(200, calculatePrice("AAAAAA"));
    assertEquals(150, calculatePrice("AAAB"));
    assertEquals(180, calculatePrice("AAABB"));
    assertEquals(200, calculatePrice("AAABBD"));
    assertEquals(200, calculatePrice("DABABA"));
    assertEquals(290, calculatePrice("DABABAE"));
    assertEquals(90, calculatePrice("E"));
    assertEquals(180, calculatePrice("EE"));
  }
}
