import java.util.*;
import java.io.*;

/** Bank Class 
 * Contains a hash table of all bank accounts
 * Implements all required functionalities
 * @author JosephKalash
 *
 */

public class Bank implements Serializable{
	
	/**Serialization */
	private static final long serialVersionUID = 5996474295623716947L;
	
	/** Hash Table containing the bank accounts */
	public Hashtable<Account, Account> accounts;
	
	/** Default Constructor */
	Bank() {
		accounts = new Hashtable<Account, Account>();
	}
	
	/** Custom Serialization 
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @param aInputStream
	 * @return void
	 * */
	private void readObject(ObjectInputStream aInputStream) throws ClassNotFoundException, IOException {
		     //Perform the default de-serialization first
		     aInputStream.defaultReadObject();
	}
	
	private void writeObject(ObjectOutputStream aOutputStream) throws IOException {
	      //Perform the default serialization for all non-transient, non-static fields
	      aOutputStream.defaultWriteObject();
	} 

	/** Compare two banks by checking if their corresponding Accounts hash are identical
	 * @param obj
	 * @return boolean
	 */
	@Override 
	public boolean equals(Object obj) {
		Bank otherBank = (Bank)obj;
		
		Collection<Account> accs = this.accounts.values();
		Collection<Account> otherAccs = otherBank.accounts.values();
		return accs.equals(otherAccs);
	}
	
	/**Helper functions needed for the design of our banking system:
	 * 		1. Read expression from the console
	 * 		2. Request account ID from user
	 * 		3. Request a list of numbers from user
	 * 		4. Print different balances of a given account
	 */
	
	/**Read an entire line from the console and returns it to caller
	 * @return String
	 * */
	public static String readExpression() {
		
		//  open up standard input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String expression = null;
		
		//  read the expression from the command-line
		try {
			expression = br.readLine();
		} catch (IOException ioe) {
			System.out.println("IO error trying to read expression!");
			        System.exit(1);
		}
				
		return expression;
	}
	
	/**Returns an Account for a given ID entered by user
	 * @return Account
	 * */
	private Account requestAccount() {
		Account _acc = new Account();
		
		boolean found = false;
		do {
			System.out.print("Enter an account ID: ");
			
			_acc.accountID = readExpression();
			//Check ID validity
			try {
				_acc.checkID();
			}
			catch(IllegalIDException e) {
				System.out.println("Invalid Account ID!");
				continue;
			}
			
			//Make sure the Account ID is unique
			Collection<Account> accs = this.accounts.values();
			for(Account acc : accs) {
				if (acc.accountID.equals(_acc.accountID)) {
					_acc = acc;
					found = true;
					continue;
				}
			}
			
			if(!found)
				System.out.println("Account not found.\n");
			
		}while(!found);
		
		return _acc;
	}
	
	/**Returns a list of integers entered by the user through the console
	 * @return List<Integer>
	 * */
	private List<Integer> requestNumbers() {

		List<Integer> list = new ArrayList<Integer>();
		String in = readExpression();

		String[] nums = in.split(" ");
		for(int i=0; i< nums.length; i++)
			list.add(new Integer(nums[i]));
		return list;
	}
	
	/**Prints all the balances available for a given account 
	 * @param acc
	 * @return void
	 * */
	private void printAvailableBalances(Account acc) {
		System.out.println("Your accounts are:");
		for(Balance b : acc.balances)
			System.out.print(b.currencyString()+"\t\t");
	}
	
	/** Custom implementation: 
	 * 1. Creating new account
	 * 2. Deleting account balance
	 * 3. Deleting account ID
	 * 4. Balance inquiry
	 * 5. Withdrawal
	 * 6. Deposit
	 * 7. Transfer
	 * 8. Quit
	 */
	
	/**1. Creating new account 
	 * @throws Exception
	 * */
	public void createAccount() throws Exception {
		
		Account _acc = new Account();
		
		System.out.println("Process: Creating a new account");
		
		boolean unique = false;
		
		do {
			
			System.out.print("Enter an account ID: ");
			_acc.accountID = readExpression();
			
			//Check ID validity
			try {
				_acc.checkID();
			}
			catch(IllegalIDException e) {
				System.out.println("Invalid Account ID!");
				continue;
			}
			
			//Make sure the Account ID is unique
			boolean uniqueID = true;
			Collection<Account> accs = this.accounts.values();
			for(Account acc : accs) {
				if (acc.accountID.equals(_acc.accountID)) {
					System.out.println("Sorry, Account ID " + _acc.accountID + " already exists!");
					uniqueID = false;
				}
			}
			
			if(uniqueID)
				unique = true;
			
		} while(!unique);
		
		System.out.print("Enter your first name: ");
		String fName = readExpression();
		System.out.print("Enter your last name: ");
		String lName = readExpression();
		_acc.firstName = fName;
		_acc.lastName = lName;
		
		System.out.println("\nChoose from the following menu the different currencies you want for your accounts:");
		System.out.println("1. Lebanese Lira (LBP)\t\t​2. Euro (EUR)​​\t\t3. United States Dollar (USD)");
		System.out.println("4. Canadian Dollar (CAD)​\t\t5.Japanese Yen (JPY)\t\t6. Chinese Yuan (CNY)");
		
		List<Integer> balancesToCreate = this.requestNumbers();
		
		for(Integer b: balancesToCreate) {
			Balance bal = new Balance(b);
			System.out.print("Enter the balance number for " + bal.currencyForInt(b) + ": ");
			String input = readExpression();
			bal.value = (new Double(input)).doubleValue();
			_acc.balances.add(bal);
		}
		accounts.put(_acc, _acc);
		System.out.println("Account ID " + _acc.accountID + " is created!");
	}
	
	/**2. Deleting account balance 
	 * */
	public void deleteAccountBalance() {
		
		System.out.println("Process: Delete account balances");
		Account _acc = this.requestAccount();
		
		this.printAvailableBalances(_acc);
		
		System.out.println("\nEnter the accounts you want to delete:");
		List<Integer> balancesToDelete = this.requestNumbers();
		
		//Delete requested balances
		for(Integer del: balancesToDelete)
			for(Balance b :_acc.balances)
				if (b.currency.ordinal() == del-1)
					_acc.balances.remove(b);
		

		Balance b = new Balance(1);
		//Print out deleted balances
		for (Integer del : balancesToDelete)
			System.out.print(b.currencyForInt(del) + " ");
		System.out.print("were deleted from Account ID " + _acc.accountID);
		
	}
	
	/**3. Deleting account ID 
	 * */
	public void deleteAccountID() {
		
		//Remove the account
		System.out.println("Process: Delete Account ID");
		Account _acc = this.requestAccount();
		accounts.remove(_acc);
		
		System.out.println("Account "+ _acc.accountID + " was deleted.");
	}

	/**4. Balance inquiry 
	 * */
	public void balanceInquiry() {
		
		System.out.println("Process: Balance Inquiry");
		Account _acc = this.requestAccount();
		
		this.printAvailableBalances(_acc);
		
		System.out.println("\nEnter the accounts you want to view:");
		List<Integer> balancesToView = this.requestNumbers();
		
		System.out.print("Your balance: ");
		List<Balance> balances = _acc.balances;
		String printInquiry = "";
		for(Integer i : balancesToView) {
			for(Balance b: balances) {
				if (b.currency.ordinal() == i-1) {
					printInquiry+= b.currencyForInt(i)+"="+b.value+" ";
					break;
				}
			}
		}
		System.out.print(printInquiry+"\n");
		
		//Ask for a receipt
		System.out.println("Do you want a receipt?");
		String desire = readExpression();
		if (!desire.toLowerCase().equals("yes") && !desire.toLowerCase().equals("y"))
			return;
		
		String fileName="Account_"+_acc.accountID+"_Info.txt";
		try {
			PrintWriter writer = new PrintWriter(fileName, "UTF-8");
			writer.println(fileName);
			writer.println("Account ID: "+_acc.accountID);
			writer.println("First Name: "+_acc.firstName+"\t\tLast Name: "+_acc.lastName);
			writer.println("Your balance:");
			writer.println(printInquiry);
			System.out.println(fileName +" saved!");
			writer.close();
		}
		catch(Exception e) {
			System.out.println("Something went wrong. Please try again later.");
		}
		
	}

	/**5. Withdrawal 
	 * */
	public void withdraw() {
		System.out.println("Process: Withdrawal");
		boolean success = false;
		while(!success) {
			//Request an account ID
			Account _acc = this.requestAccount();
			
			//Print balances of the given account
			this.printAvailableBalances(_acc);
			
			//Request accounts to withdraw from
			System.out.println("\nEnter the accounts you want to withdraw from:");
			List<Integer> balancesToWithdraw = this.requestNumbers();
			List<Balance> balances = _acc.balances;
			
			for(Integer i: balancesToWithdraw) {
				for(Balance b: balances) {
					if(b.currency.ordinal()==i-1) {
						System.out.println("Enter the amount of money to withdraw from " + b.currencyForInt(i)+ ":");
						Integer amount = this.requestNumbers().get(0);
						if(amount > b.value) {
							System.out.println("Not enough money to withdraw for Account ID "+_acc.accountID + " from the "+ b.currencyForInt(i) + " account!");
						}
						else {
							b.value-=amount;
							success = true;
							System.out.println(amount.toString() + " was withdrawn from "+b.currencyForInt(i)+" account of Account ID "+_acc.accountID+"!");
						}
					}
				}
			}
		}
	}

	/**6. Deposit 
	 * */
	public void deposit() {
		System.out.println("Process: Deposit");
		Account _acc = this.requestAccount();
		this.printAvailableBalances(_acc);
		
		System.out.println("\nEnter the accounts you want to deposit to: ");
		List<Integer> balancesToDeposit = this.requestNumbers();
		List<Balance> balances = _acc.balances;
		
		for(Integer i: balancesToDeposit) {
			for(Balance b: balances) {
				if(b.currency.ordinal()==i-1) {
					System.out.println("Enter the amount of money to deposit to "+b.currencyForInt(i));
					Integer amount = this.requestNumbers().get(0);
					b.value += amount;
					System.out.println(amount.toString() + " was deposited into "+b.currencyForInt(i) + " account of Account ID "+ _acc.accountID);
				}
			}
		}
	}

	/**7. Transfer 
	 * */
	public void transfer() {
		
		boolean success = false;
		while(!success) {
			System.out.println("Process: Transfer");
			Account _acc = this.requestAccount();
			
			this.printAvailableBalances(_acc);
			System.out.println("\nEnter the account you want to transfer from: ");
			Integer balanceFrom = this.requestNumbers().get(0);
			
			System.out.println("Enter the amount of money to transfer: ");
			Integer amount = this.requestNumbers().get(0);
			
			System.out.println("Enter the Account ID to transfer to: ");
			Integer accountTo = this.requestNumbers().get(0);
			
			//Check for all possible issues
			
			//Issue 1: not enough money to withdraw
			Balance bFrom = new Balance(0);
			for(Balance b: _acc.balances)
				if(b.currency.ordinal() == balanceFrom-1) {
					bFrom = b;
					break;
				}
			if(bFrom.value < amount) {
				System.out.println("Not enough money to withdraw " + amount.toString() + " from " + bFrom.currencyForInt(balanceFrom) + " account of Account ID " + _acc.accountID+"!");
				continue;
			}
			
			//Issue 2: Receiver account ID non existent
			//Make sure the Account ID is unique
			Collection<Account> accs = this.accounts.values();
			boolean found = false;
			for(Account acc : accs) {
				if (acc.accountID.equals(accountTo.toString())) {
					found = true;
					continue;
				}
			}
			if(!found) {
				System.out.println("Account " + accountTo + " does not exist!");
				continue;
			}
			
			
			//Issue 3 : Receiver does not have a balance with corresponding currency
			//Make sure the Account ID is unique
			Account aTo = new Account();
			for(Account acc : accs) {
				if (acc.accountID.equals(accountTo.toString())) {
					aTo = acc;
					break;
				}
			}
			Currency currency = Currency.LBP;
			switch(balanceFrom) {
				case 1: currency = Currency.LBP; break;
				case 2: currency = Currency.EUR; break;
				case 3: currency = Currency.USD; break;
				case 4: currency = Currency.CAD; break;
				case 5: currency = Currency.CNY; break;
				case 6: currency = Currency.JPY; break;
			}
			if (!aTo.containsBalanceWithCurrency(currency)) {
				System.out.println("Sorry, Account ID "+ aTo.accountID+ " does not have a " + bFrom.currencyForInt(balanceFrom) + " account.");
				continue;
			}
			
			//No issues, initiate transfer
			bFrom.value -= amount;
			for(Balance b : aTo.balances)
				if(b.currency == currency) {
					b.value += amount;
					success = true;
				}
			System.out.println(amount.toString() +" "+ bFrom.currencyForInt(balanceFrom) +  " were successfully transferred from Account " + _acc.accountID + " to Account " + accountTo);
			
		}
	}

	/** 8. Quit 
	 * */
	public void quit() {
		System.out.println("Thank you for banking with us!");
	}

}
