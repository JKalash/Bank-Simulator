import java.io.Serializable;
import java.util.*;

/** Account class defines 
 * an account's unique ID, user's first and last name 
 * as well as a list of all balances.
 * 
 * Helper methods provided to make bank implementation easier
 *  
 * @author JosephKalash
 *
 */

public class Account implements Serializable {
	/**
	 * 
	 */
	
	/**Serialization */
	private static final long serialVersionUID = 384756348728L;
	
	/**Unique ID that defines an account */
	public String accountID;
	
	/** First name of the account holder */
	public String firstName;
	
	/** Last name for the account holder */
	public String lastName;
	
	/** A List containing Balance objects associated to that account*/
	public List<Balance> balances;
	
	/** Default Constructor */
	Account() {
		accountID = "0";
		firstName = lastName = "";
		balances = new ArrayList<Balance>();
	}
	
	/** Checks if the AccountID property is valid by making sure:
	 * 1. It only contains digits
	 * 2. It is greater than 99 
	 * @throws IllegalIDException
	 * */
	public void checkID() throws IllegalIDException {
		
		String regex = "\\d+";
		if(!accountID.matches(regex))
			throw new IllegalIDException("AccountID can only contain digits.");
		
		if(accountID.length() < 2)
			throw new IllegalIDException("AccountID must contain at least 2 digits.");
	}
	
	/** Method that returns true if a Balance object exists the balances property
	 * @param c
	 * @return boolean
	 * */
	public boolean containsBalanceWithCurrency(Currency c) {
		for(Balance b: balances)
			if (b.currency == c)
				return true;
		
		return false;
	}

	/** Returns true only if the two accounts possess the same Account ID
	 * @param obj
	 * @return boolean 
	 * */
	@Override
	 public boolean equals(Object obj) {
        if ( obj.getClass() != getClass())
        	return false;
        
        return ((Account)obj).accountID.equals(this.accountID);
    }
	 
	 /** DJB hash implementation
		 * @return int
		 */
		@Override
		public int hashCode() {
			 int hash = 5381;
		        for (int i = 0; i < accountID.length(); i++) {
		            hash = ((hash << 5) + hash) + accountID.toString().charAt(i);
		        }
		        return hash;
		}

	/** Prints all account Details 
	 * @return String
	 * */
	@Override
	public String toString() {
		String acc =  "Account ID: "+ accountID + "\n" + "First Name: " + firstName + "\n" + "Last Name: " + lastName + "\n";
		for(Balance b: balances) {
			acc += b.toString() + "\n";
		}
		return acc;
	}
	
}
