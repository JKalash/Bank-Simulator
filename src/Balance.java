import java.io.*;

/** Balance class defines 
 * the currency type and value associated to it 
 * @author JosephKalash
 *
 */

/**Currency enumerator */
enum Currency { 
	LBP,
	EUR,
	USD,
	CAD,
	JPY,
	CNY
};

public class Balance implements Serializable {
	
	/**Serialization */
	private static final long serialVersionUID = 32495874369L;
	
	/** Currency of the balance */
	public Currency currency;
	
	/** Value of the balance */
	public Double value;
   
	/**Constructor that takes a native currency enumerator and a value parameter 
	 * @param currency
	 * @param value
	 * */
	public Balance(Currency currency, Double value) { 
    this.currency = currency; 
    this.value = value; 
  } 
  
  
  /**Constructor that takes a currency ordinal
   * @param currency
   */
  public Balance(Integer currency) {
	  this.value = 0.0;
	  switch (currency) {
	  	case 1: this.currency = Currency.LBP; break;
	  	case 2: this.currency = Currency.EUR; break;
	  	case 3: this.currency = Currency.USD; break;
	  	case 4: this.currency = Currency.CAD; break;
	  	case 5: this.currency = Currency.JPY; break;
	  	case 6: this.currency = Currency.CNY; break;
	  }
  }
  
  
  /** Return this balance's currency name 
   * @return String
   * */
  public String currencyString() {
	  
	  switch(this.currency) {
	  case LBP:return "1. Lebanese Lira (LBP)";
	  case EUR:return "2. Euro (EUR)";
	  case USD:return "3. US Dollar (USD)"; 
	  case CAD:return "4. Canadian Dollar (CAD)";
	  case JPY:return "5. Japanese Yen (JPY)"; 
	  case CNY:return "6. Chinese Yuan (CNY)";
	  default:return "";
	  }
  }
  
  /** Returns name of currency of a given Currency's ordinal value 
   * @param i
   * @return String
   * */
  public String currencyForInt(Integer i) {
	  switch (i) {
		  case 1:return "LBP";
		  case 2:return "EUR";
		  case 3:return "USD"; 
		  case 4:return "CAD";
		  case 5:return "JPY"; 
		  case 6:return "CNY";
		  default:return "";
	  }
  }
  
  /** Prints the currency and balance value
   * @return String
   */
  @Override
  public String toString() {
	 
	  String currencyString;
	  
	  switch(this.currency) {
		  case LBP:currencyString = "LBP";break;
		  case EUR:currencyString = "EUR";break;
		  case USD:currencyString = "USD";break; 
		  case CAD:currencyString = "CAD";break;
		  case JPY:currencyString = "JPY";break; 
		  case CNY:currencyString = "CNY";break;
		  default:currencyString = "";break;
	  }
	  return currencyString+"="+value; 
  }

} 
