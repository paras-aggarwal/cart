package com.idealo.impl;

import com.google.gson.Gson;
import com.idealo.model.Config;
import com.idealo.model.PricingAttribute;
import com.idealo.model.PricingRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckOutErrorTest {

  private static final String PRICING_RULES_FILE_PATH = "src/test/resources/incorrect-rules.json";

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
  
  @Rule
  public ExpectedException exceptionRule = ExpectedException.none();

  @Test
  public void totals_incompletePricingRules() throws FileNotFoundException {
    exceptionRule.expect(IllegalArgumentException.class);
    exceptionRule.expectMessage("Pricing rules are incomplete");
    calculatePrice("A");
  }

  @Test
  public void totals_invalidQuantity() throws FileNotFoundException {
    exceptionRule.expect(IllegalArgumentException.class);
    exceptionRule.expectMessage("Quantity should be greater than zero");
    calculatePrice("B");
  }

  @Test
  public void totals_noOfferDateForTimedOffer() throws FileNotFoundException {
    exceptionRule.expect(IllegalArgumentException.class);
    exceptionRule.expectMessage("Pricing rules are incomplete");
    calculatePrice("C");
  }

  @Test
  public void totals_noUnitPriceForNonOfferedItem() throws FileNotFoundException {
    exceptionRule.expect(IllegalArgumentException.class);
    exceptionRule.expectMessage("Pricing rules are incomplete");
    calculatePrice("D");
  }
}
