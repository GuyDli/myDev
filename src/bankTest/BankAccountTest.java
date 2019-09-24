package bankTest;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Test;

import bank.BankAccount;
import bank.OperationType;
import junit.framework.TestCase;

public class BankAccountTest extends TestCase{
	
	private BankAccount bankAccount;
	BigDecimal amount1000 = new BigDecimal(1000), amount500 = new BigDecimal(500), amount250 = new BigDecimal(250), expectedAmount;

	@Override
	protected void setUp() throws Exception {
		bankAccount = new BankAccount("example");
		amount1000 = new BigDecimal(1000); 
		amount500 = new BigDecimal(500); 
		amount250 = new BigDecimal(250);

	}
	
	@Override
	protected void tearDown() throws Exception {
		bankAccount = null;
		amount1000 = null; 
		amount500 = null; 
		amount250 = null;
		assertNull(bankAccount);
		assertNull(amount1000);
		assertNull(amount500);
		assertNull(amount250);
	}
	
	@Test
	public void testBankAccountName() {
		BankAccount bankAccount1 = new BankAccount("X");
		assertEquals("X", bankAccount1.getAccountName());
	}
	
	@Test
	public void testDeposit() {
		expectedAmount = amount1000;
		assertEquals(expectedAmount,bankAccount.deposit(amount1000));
	}
	
	@Test
	public void testDepositNull() {
		assertNull(bankAccount.deposit(null));
	}
	
	@Test
	public void testDepositAfterWithdrawal () {
		expectedAmount = amount500.subtract(amount500).add(amount1000);
		bankAccount.deposit(amount500);
		bankAccount.withdrawal(amount500);
		assertEquals(expectedAmount,bankAccount.deposit(amount1000));
	}
	
	@Test
	public void testDepositAfterDeposit () {
		expectedAmount = amount500.add(amount1000);
		bankAccount.deposit(amount500);
		assertEquals(expectedAmount,bankAccount.deposit(amount1000));
	}
	
	
	@Test
	public void testWithdrawalWithoutInitialDeposit () {
		assertNull(bankAccount.withdrawal(amount1000));
	}
	
	@Test
	public void testWithdrawalNull() {
		assertNull(bankAccount.withdrawal(null));
	}
	
	@Test
	public void testWithdrawalAfterDeposit () {
		expectedAmount = amount1000.add(amount500).subtract(amount1000);
		bankAccount.deposit(amount1000);
		bankAccount.deposit(amount500);
		assertEquals(expectedAmount,bankAccount.withdrawal(amount1000));
	}
	
	@Test
	public void testWithdrawalWithNotEnoughFilledAccount () {
		bankAccount.deposit(amount500);
		assertNull(bankAccount.withdrawal(amount1000));
	}
	
	@Test
	public void testWithdrawalAllMoney () {
		bankAccount.deposit(amount500);
		assertEquals(BigDecimal.ZERO, bankAccount.withdrawalAllMoney());
	}
	
	@Test
	public void testGetAccountHistoryString() {
		Date date;
		String expectedOutput;
		BigDecimal currentBalance;
		/* 
		 * As the operation date is set automatically when we are processing a deposit or withdrawal,
		 * we're trying to process all operations at the same Date. 
		 * That's why we're looping while all the operation are not process at the same time
		 * 
		*/
		do {
			this.bankAccount = new BankAccount("example");
			date = new Date();
			expectedOutput = new String();
				
			expectedOutput = expectedOutput.concat("\nOperation Date : "+ date +"\t\tOperation Type : "+ OperationType.DEPOSIT +"\t\tAmount : "+ amount1000.toString()) ;
			bankAccount.deposit(amount1000);

			expectedOutput = expectedOutput.concat("\nOperation Date : "+ date +"\t\tOperation Type : "+ OperationType.DEPOSIT +"\t\tAmount : "+ amount500.toString()) ;
			bankAccount.deposit(amount500);
	
			expectedOutput = expectedOutput.concat("\nOperation Date : "+ date +"\t\tOperation Type : "+ OperationType.WITHDRAWAL +"\t\tAmount : "+ amount250.negate().toString()) ;
			currentBalance = bankAccount.withdrawal(amount250);
			
			expectedOutput = expectedOutput.concat("\n\nCurrent Balance Amount : " + currentBalance) ;
			currentBalance = null;
		}
		while (!new Date().toString().equals(date.toString()));

		assertEquals(expectedOutput,bankAccount.getAccountHistoryString());
	}
}
