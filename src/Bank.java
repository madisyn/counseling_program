
public class Bank {
	
	int total;
	String name;
	
	public Bank (String name, int num){
		this.name = name;
		this.total = num;
	}
	
	public void payEmployees(Employee name, int month) {
		this.total -= name.MonthlySalary;
		name.receiveIncome(month);
	}
	
	/*
	 * This is to receive payments from clients before their appointment
	 */
	public void receivePayment(Client name, int month, int week) {
		//receive money from Client
		this.total += name.paymentAmount;
		
		//mark the Client did pay fee
		name.payExpense(month, week);
	}
	
	/*
	 * This is to receive payments from interns for the month
	 */
	public void receivePayment(Intern name, int month) {
		//receive money from Intern
		this.total += name.paymentAmount;
		
		//mark that the intern did pay fee for that month
		name.payMonthlyExpense(month);
	}
	
	
	/*
	 * This is to void a payment due to any errors for Interns
	 */
	public void voidInternPayment(Intern name, int month) {
		this.total -= name.paymentAmount;
		name.voidMonthlyExpense(month);
	}
	
	/*
	 * This is to void a payment due to any errors for Clients
	 */
	public void voidReceivePayment(Client name, int month, int week) {
		//receive money from Client
		this.total -= name.paymentAmount;
		
		//mark the Client did pay fee
		name.voidExpense(month, week);
	}
	
	public boolean BankPaymentCheck(int number) {
		if(number<this.total) {
			return true;
		}
		return false;
	}
	
	public int returnBankBalance() {
		return this.total;
	}

}
