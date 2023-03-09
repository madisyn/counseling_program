/*
 * Intern class will handle Interns name, their clients, if they paid their monthly dues,
 * and 
 */
public class Intern {
	
	String name;
	int paymentAmount =150;
	boolean paid[] = new boolean[12];
	Staff supervisor;
	
	
	public Intern(String name, Staff staffName) {
		this.name = name;
		this.supervisor = staffName;
	}
	
	public Intern(String name, Staff staffName,int modifiedExpense) {
		this.name = name;
		this.paymentAmount = modifiedExpense;
		this.supervisor = staffName;
	}
	
	public void payMonthlyExpense(int month) {
		//try/catch if the number is a month
		paid[month] = true;
	}
	
	public void voidMonthlyExpense(int month) {
		paid[month] = false;
	}
}
