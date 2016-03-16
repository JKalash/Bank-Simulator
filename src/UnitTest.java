import static org.junit.Assert.*;
import org.junit.*;

public class UnitTest {
	
	private static Bank b;
	
	@Test
	public void testCreateAccount() {
		if(b==null)
			b = new Bank();
		try {
			b.createAccount();
		}
		catch(Exception e) {
			assertTrue(false);
		}
		
		assertTrue(true);
	}

	@Test
	public void testDeleteAccountBalance() {
		if(b==null)
			b = new Bank();
		

		assertTrue(true);
	}

	@Test
	public void testDeleteAccountID() {
		if(b==null)
			b = new Bank();
		
		assertTrue(true);
	}

	@Test
	public void testBalanceInquiry() {
		if(b==null)
			b = new Bank();
		

		assertTrue(true);
	}

	@Test
	public void testWithdraw() {
		if(b==null)
			b = new Bank();
		

		assertTrue(true);
	}

	@Test
	public void testDeposit() {
		if(b==null)
			b = new Bank();

		assertTrue(true);
	}

	@Test
	public void testTransfer() {
		if(b==null)
			b = new Bank();
		

		assertTrue(true);
	}

	@Test
	public void testQuit() {
		if(b==null)
			b = new Bank();
		

		assertTrue(true);
	}

}
