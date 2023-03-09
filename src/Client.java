/*
 * Client will have an Intern assigned to them and handle payments.
 */
public class Client {

	String name;
	int PaymentAmount =150;
	boolean paid[][] = new boolean[12][4];
	Intern InternName;
	

	public Client(String name, int Expense, Intern inName) {
		this.name = name;
		this.PaymentAmount = Expense;
		this.InternName = inName;
	}
	
	public void payExpense(int month, int week) {
		//try/catch if the number is a month
		paid[month][week] = true;
	}
	
}
