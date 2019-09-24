package bankTest;

import org.junit.Test;

import bank.OperationType;
import junit.framework.TestCase;

public class OperationTypeTest extends TestCase{
	
	
	 @Test  
	 public void testDepositToString(){
		 assertEquals("Deposit", OperationType.DEPOSIT.toString());
	 }
	 
	 @Test  
	 public void testWithdrawalToString(){
		 assertEquals("Withdrawal", OperationType.WITHDRAWAL.toString());
	 }
	 
}
