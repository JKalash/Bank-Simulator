import java.util.*;
import java.io.*;

/** Simulator that shows
 * the multiple functionalities provided with the Bank class
 * 
 * Consists of a simple menu 
 * @author JosephKalash
 *
 */

public class Simulator {

	/** Bank object that we will be testing */ 
	private static Bank bank;
	
	/** Default constructor */
	Simulator() {
		bank = new Bank();
	}
	
	/** Methods that displays menu.
	 * Recursive until user explicitly Quits
	 * @throws Exception
	 */
	public static void displayMenu() throws Exception {
		System.out.println("Select a menu option:");
		System.out.println("\t\t1. Creating a new account");
		System.out.println("\t\t2. Delete account balances");
		System.out.println("\t\t3. Delete an account id");
		System.out.println("\t\t4. Balance inquiry");
		System.out.println("\t\t5. Withdrawal");
		System.out.println("\t\t6. Deposit");
		System.out.println("\t\t7. Transfer");
		System.out.println("\t\t8. Quit");
		
		
		Scanner sc = new Scanner (System.in);
		Integer choice = sc.nextInt();
		
		switch(choice) {
			case 1: bank.createAccount(); break;
			case 2: bank.deleteAccountBalance(); break;
			case 3: bank.deleteAccountID(); break;
			case 4: bank.balanceInquiry(); break;
			case 5: bank.withdraw(); break;
			case 6: bank.deposit(); break;
			case 7: bank.transfer(); break;
			case 8: bank.quit(); break;
			default:break;
		}
		
		//Save Data
		try {
			saveToDisk();
		}
		catch(Exception e) {
			System.out.println("Unable to save changes to disk.");
			System.out.println("Reason: " + e.getLocalizedMessage());
		}
		
		System.out.println("\n\n");
		
		if(choice != 8)
			displayMenu();
		
		sc.close();
	}
	
	/** Method that saves the bank object
	 * to disk
	 * @throws Exception
	 */
	public static void saveToDisk() throws Exception {
		
		//If no object, return
		if(bank == null)
			return;
		
		//Write object to disk
		// Write to disk with FileOutputStream
		FileOutputStream f_out = new FileOutputStream("bank.data");

		// Write object with ObjectOutputStream
		ObjectOutputStream obj_out = new ObjectOutputStream (f_out);

		// Write object out to disk
		obj_out.writeObject ( bank );
		obj_out.close();
	}
	
	/** Method that reads the bank object from disk
	 * 
	 * @throws Exception
	 */
	public static void readFromDisk() throws Exception {
		
		// Read from disk using FileInputStream
		FileInputStream f_in = new FileInputStream("bank.data");

		// Read object using ObjectInputStream
		ObjectInputStream obj_in = new ObjectInputStream (f_in);

		// Read an object
		Object obj = obj_in.readObject();

		// Cast object to a Bank
		if (obj instanceof Bank)
			bank = (Bank) obj;
			
		obj_in.close();
	}
	
	public static void main(String[] s) {
		try {
			
			//Try reading bank from file on disk
			try{
				readFromDisk();
			}
			catch(Exception e) {
				//No Data file was found for the bank, proceed normally 
				bank = new Bank();
			}
			
			if(bank == null)
				bank = new Bank();
			
			System.out.println("Bank Simulator:");
			displayMenu();
		}
		
		catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
	
	}
}
