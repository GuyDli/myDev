package bank;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BankAccount {

	private String accountName;
	
	private BigDecimal currentBalance;
	
	
	/*
	 * A List has been choose to manage the operations due to the following reasons :
	 * 	- Operations will only be added
	 * 	- According to the previous point, the items will be automatically sorted from the oldest to the youngest operation
	 * 	- No search will be process
	 */
	private List<Operation> operations;
	
	public BankAccount(String accountName) {
		this.accountName = accountName;
		this.operations = new ArrayList<Operation>();
		this.currentBalance = new BigDecimal(0);
	}
	
	/**
	 * Method to deposit a provided amount on the bank account
	 * @param amount the amount to deposit
	 * @return return the account balance after the deposit or null if the amount to deposit is invalid
	 */
	public BigDecimal deposit (BigDecimal amount) throws NullPointerException {
		if (amount != null) {
			this.operations.add(new Operation (amount,new Date()));
			return currentBalance = currentBalance.add(amount); 
		}
		else
			return null;
	}
	
	/**
	 * Method to withdrawal a provided amount on the bank account
	 * @param amount the amount to withdrawal
	 * @return currentBalance return the account balance after the withdrawal 
	 * or null if the current balance could not be withdrawn 
	 */
	/*
	 * In this example, we expect that no overdraft is allowed
	 */
	public BigDecimal withdrawal (BigDecimal amount) {
		if (amount != null && currentBalance.compareTo(amount) >= 0) {
			this.operations.add(new Operation (amount.negate(),new Date()));
			return currentBalance = currentBalance.subtract(amount);
		}
		else 
			return null;
	}
	
	
	/**
	 * Method to withdrawal all the account money
	 * @return currentBalance return the account balance after the withdrawal 
	 */
	public BigDecimal withdrawalAllMoney () {
		return this.withdrawal(this.currentBalance);
	}
	
	/**
	 * Method display the account operations history
	 */
	public void getAccountHistory() {
		System.out.println(this.getAccountHistoryString());
	}
	
	/**
	 * Method to build the account history
	 * @return accountHistory return the account operations history as String
	 */
	public String getAccountHistoryString() {
		String accountHistory = new String();
		System.out.println(this.operations);
		for (Operation operation : this.operations) 
			accountHistory = accountHistory.concat(	"\nOperation Date : " + operation.getOperationDate() +
													"\t\tOperation Type : " + operation.getOperationType() +
													"\t\tAmount : " + operation.getAmount());
		return accountHistory.concat("\n\nCurrent Balance Amount : " + this.currentBalance);
	}
	
	/**
	 * Method to get the account name
	 * @return the name of the account as String
	 */
	public String getAccountName() {
		return accountName;
	}
	
}
