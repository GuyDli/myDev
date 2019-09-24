package bankTest;
import java.math.BigDecimal;
import java.util.Date;

import org.junit.Test;

import bank.Operation;
import bank.OperationType;
import junit.framework.TestCase;

public class OperationTest extends TestCase{

	
	private Operation operationDeposit, operationWithdrawal;
	private BigDecimal expectedAmount100, expectedAmountNeg50;
	private Date expectedDate1,expectedDate2;
	
	/*
	 * We do not test null values for constructor arguments as they are managed to be not null by calling methods 
	 */
	
	@Override
	protected void setUp() throws Exception {
		this.operationDeposit = new Operation(new BigDecimal(100), new Date (1000000));
		this.operationWithdrawal = new Operation(new BigDecimal(-50), new Date (2000000));
	}
	
	@Override
	protected void tearDown() throws Exception {
		this.operationDeposit = null;
		this.operationWithdrawal= null;
		assertNull(this.operationDeposit);
		assertNull(this.operationWithdrawal);
	}
	
	@Test
	public void testGetAmount() {
		this.expectedAmount100 = new BigDecimal(100);
		this.expectedAmountNeg50 = new BigDecimal(-50);
		assertEquals(expectedAmount100,operationDeposit.getAmount());
		assertEquals(expectedAmountNeg50,operationWithdrawal.getAmount());
	}

	@Test
	public void testGetOperationDate() {
		this.expectedDate1 = new Date(1000000);
		this.expectedDate2 = new Date(2000000);
		assertEquals(expectedDate1,operationDeposit.getOperationDate());
		assertEquals(expectedDate2,operationWithdrawal.getOperationDate());
	}

	@Test
	public void testGetOperationTypeDeposit() {
		assertEquals(OperationType.DEPOSIT.toString(),operationDeposit.getOperationType());
	}
	
	@Test
	public void testGetOperationTypeWithdrawal() {
		assertEquals(OperationType.WITHDRAWAL.toString(),operationWithdrawal.getOperationType());
	}
}
