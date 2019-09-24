package bank;
import java.math.BigDecimal;
import java.util.Date;

public class Operation {

	private final BigDecimal amount;
	private final Date operationDate;
	private String operationType;
		
	public Operation (BigDecimal amount, Date operationDate) {
		this.operationType = amount.signum() < 0 ? OperationType.WITHDRAWAL.toString() : OperationType.DEPOSIT.toString(); 
		this.amount = amount;
		this.operationDate = operationDate;
	}
	
	
	/**
	 * @return the amount of the operation
	 */
	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * @return the date of the operation
	 */
	public Date getOperationDate() {
		return operationDate;
	}


	/**
	 * @return the type of the operation
	 */
	public String getOperationType() {
		return operationType;
	}
	
}
