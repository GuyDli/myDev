package bank;

public enum OperationType {

		  WITHDRAWAL ("Withdrawal"),
		  DEPOSIT ("Deposit");
		   
		  private String name;
		   
		  private OperationType(String name){
		    this.name = name;
		  }
		   
		  public String toString(){
		    return name;
		  }
}
