package atd.spring.testing.bills;

import java.math.BigDecimal;

import atd.spring.testing.rules.LineItemRule;

public class RuledBillTextFileLoader extends BillTextFileLoader {
  LineItemRule rules; 
  
  public RuledBillTextFileLoader (LineItemRule rules) {
    this.rules = rules;
  }
  
  protected LineItem createLineItem(String desc, BigDecimal amount, BigDecimal price,
      String currency) {
    return new RuleBasedLineItem(
      desc,
      new Money(price,currency),
      amount,
      rules);
  }

}
