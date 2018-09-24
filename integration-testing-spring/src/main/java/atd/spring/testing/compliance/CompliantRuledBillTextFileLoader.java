package atd.spring.testing.compliance;

import java.io.IOException;
import java.math.BigDecimal;

import atd.spring.testing.bills.Bill;
import atd.spring.testing.bills.LineItem;
import atd.spring.testing.bills.RuledBillTextFileLoader;
import atd.spring.testing.rules.LineItemRule;

public class CompliantRuledBillTextFileLoader  extends RuledBillTextFileLoader{
  private TrafficRegulator amountTrafficRegulator;
  
  public CompliantRuledBillTextFileLoader(LineItemRule rules, TrafficRegulator amountTrafficRegulator) {
    super(rules);
    this.amountTrafficRegulator = amountTrafficRegulator;
  }
  
  @Override
  public Bill load(String filename) throws IOException {
    Bill ret = super.load(filename);
    amountTrafficRegulator.registerBill(ret);
    return ret;
  }
  
  @Override
  protected LineItem createLineItem(String desc, BigDecimal amount, 
      BigDecimal price, String currency) {
    LineItem ret = super.createLineItem(desc, amount, price, currency);
    amountTrafficRegulator.registerLineItem(ret);
    return ret;
  }
}