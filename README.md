# cart-checkout


### Things that can be improved

- Ability to have multiple rules for a single item e.g.,- Buy 2 unit at price X and Buy 3 unit at price Y and so on (Currently it only supports one rule per item in case of multiple rules it uses the later one)
- Limiting number of items to check out (In real scenarios, We have a limit on how many items a user can check out at once)
- Custom exceptions for a well-defined exception format

### Pricing rules format
```json
{
  "pricingRules": [
    {
      "item": "A",
      "offerType": "QUANTITY",
      "discountType": "FIXED_PRICE",
      "offerPrice": 100,
      "offerQuantity": 3,
      "offerPercentage": null,
      "offerDate": null,
      "unitPrice": 40
    },
    {
      "item": "B",
      "offerType": "QUANTITY",
      "discountType": "FIXED_PRICE",
      "offerPrice": 80,
      "offerQuantity": 2,
      "offerPercentage": null,
      "offerDate": null,
      "unitPrice": 50
    },
    {
      "item": "C",
      "offerType": "NONE",
      "discountType": "NONE",
      "offerPrice": null,
      "offerQuantity": null,
      "offerPercentage": null,
      "offerDate": null,
      "unitPrice": 25
    },
    {
      "item": "D",
      "offerType": "NONE",
      "discountType": "NONE",
      "offerPrice": null,
      "offerQuantity": null,
      "offerPercentage": null,
      "offerDate": null,
      "unitPrice": 20
    },
    {
      "item": "E",
      "offerType": "TIMED",
      "discountType": "PERCENTAGE",
      "offerPrice": null,
      "offerQuantity": null,
      "offerPercentage": 10,
      "offerDate": "2021-07-03T12:00:00+01:00", // ISO 8601 format
      "unitPrice": 100
    }
  ]
}
```

### Explanation for pricing rules format

- **offerType:** Possible values- NONE / QUANTITY / TIMED

> **NONE** means no offer is available on the specified item. Only unit price is used as part of this type.

> **QUANTITY** means the offer is available on buying of certain quantity. eg- On buying 3 quantity for A price will be 100

> **TIMED** means offer is available on a specific date. offerDate attribute is used in this case (ISO 8601 format).

- **discountType:** Possible values- NONE / PERCENTAGE / FLAT / FIXED_PRICE

> **PERCENTAGE** means the offer is a percentage based discount. eg- 10% discount. offerPercentage attribute will be used in this case.

> **FLAT** means the offer is a discount of specific amount. eg- 5 euro discount. offerPrice attribute will be used in this case.

> **FIXED_PRICE** means the offer is a fixed price value. eg- On buying 2 quantity of B the price will be 80. offerPrice attribute is used in this case.

- Pricing rules are read from `/src/main/resources/rules.json` in json format as mentioned above.
- Test class read rules from `/src/main/resources/rules.json` and `/src/main/resources/incorrect-rules.json` file.

### Project structure

- **Application:** This is the class with main method. This is where we are reading pricing rules from json file and initializing the CheckOut class.
- **resources:** contains json file from where we are reading pricing rules
- **utils:** contains the constants file where we have defined some constant variables like DATE_FORMAT, FILE_PATH
- **model:** contains all POJOs and interfaces
- **impl:** contains actual business logic
> - **CheckOut** is the class which is called for adding new item and calculation total amount.
> - **factory:** This contains code for implementation of factory method, OfferTypeAgentProvider: This class provides us an instance of class based on offerType.
> - **agent:** This contains the classes for agents for Quantity, Timed, NoOffer
> - **common:** This contains the DiscountCommonAgent, moved this to a common class because this will be used by multiple classes.

### Dependencies

- Junit 4.13.2 (for unit test)
- Gson 2.8.7 (For data mapping [Json -> Object])
