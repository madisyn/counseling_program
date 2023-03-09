
public class Bank {
	
	static int total;
	
	public Bank (int num){
		this.total = num;
	}
	
	public void payEmployees(Employee name) {
		this.total -= name.salary;
		name.paid = true;
	}
	
	public void receivePayment(Client name) {
		this.total += name.paymentAmount;
		name.paid = true;
	}
	
	public void receivePayment(Intern name) {
		this.total += name.paymentAmount;
		name.paid = true;
	}
	
	/*public <T> void receivePayment(T name) {
		this.total += name.paymentAmount;
		name.paid = true;
	}*/
	
	public void voidPayment(Type name) {
		this.total -= name.paymentAmount;
		name.paid = false;
	}

}
