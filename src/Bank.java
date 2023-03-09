
public class Bank {
	
	static int total;
	
	public Bank (int num){
		Bank.total = num;
	}
	
	public void payEmployees(Employee name, int month) {
		Bank.total -= name.MonthlySalary;
		name.receiveIncome(month);
	}
	
	public void receivePayment(Client name, int month, int week) {
		//receive money from Client
		Bank.total += name.paymentAmount;
		
		//mark the Client did pay fee
		name.payExpense(month, week);
	}
	
	public void receivePayment(Intern name, int month) {
		//receive money from Intern
		Bank.total += name.paymentAmount;
		
		//mark that the intern did pay fee
		name.payMonthlyExpense(month);
	}
	
	public void voidInternPayment(Intern name, int month) {
		Bank.total -= name.paymentAmount;
		name.voidMonthlyExpense(month);
	}
	
	public void voidReceivePayment(Client name, int month, int week) {
		//receive money from Client
		Bank.total -= name.paymentAmount;
		
		//mark the Client did pay fee
		name.voidExpense(month, week);
	}

}
