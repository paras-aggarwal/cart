package com.idealo.model;

public interface OfferTypeAgent {

  /**
   * This method apply any offer price that is applicable on a specific item
   * @param itemRecord
   * @param config
   * @return ItemRecord object with new count and new price after adding item to cart and apply offers
   */
  ItemRecord applyOffer(ItemRecord itemRecord, Config config);
}
