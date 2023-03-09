/*
 * Client will have an Intern assigned to them and handle payments.
 */
public class Client {

	String name;
	int paymentAmount =150;
	boolean paid[][] = new boolean[12][4];
	Intern InternName;
	

	public Client(String name, int Expense, Intern inName) {
		this.name = name;
		this.paymentAmount = Expense;
		this.InternName = inName;
	}
	
	public void payExpense(int month, int week) {
		//try/catch if the number is a month
		paid[month][week] = true;
	}
	
	public void voidExpense(int month, int week) {
		paid[month][week] = false;
	}
	
}
