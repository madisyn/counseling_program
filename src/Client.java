/*
 * Client will have an Intern assigned to them and handle payments.
 */
public class Client {

	String name;
	int paymentAmount =150;
	boolean paid[][] = new boolean[12][4];
	String CounselorName;
	boolean isIntern = true;
	

	public Client(String name, int Expense, Intern in) {
		this.name = name;
		this.paymentAmount = Expense;
		this.CounselorName = in.name;
		
		for(int i=0; i<12; i++) {
			for(int j=0; i<4; j++) {
				this.paid[i][j] = false;
			}
		}
	}
	
	public Client(String name, int Expense, Staff staff) {
		this.name = name;
		this.paymentAmount = Expense;
		this.CounselorName = staff.name;
		this.isIntern = false;
		
		for(int i=0; i<12; i++) {
			for(int j=0; j<4; j++) {
				this.paid[i][j] = false;
			}
		}
	}
	
	public void payExpense(int month, int week) {
		//try/catch if the number is a month
		paid[month][week] = true;
	}
	
	public void voidExpense(int month, int week) {
		paid[month][week] = false;
	}
	public boolean checkCompletedPaymentWeek(int month, int week) {
		if(this.paid[month][week]==true) {
			return true;
		}
		return false;
	}
	
	public void changeCounselor(Staff staff) {
		this.CounselorName = staff.name;
		this.isIntern = false;
		
	}
	
	public void changeCounselor(Intern in) {
		this.CounselorName = in.name;
		this.isIntern = true;
		
	}
	
	public void changePaymentFee(int num) {
		this.paymentAmount = num;
	}
	
	public String getClientsCounselor() {
		return this.CounselorName;
	}
	
	public boolean getCounselorType() {
		return this.isIntern;
	}
	
	public void deleteCounselor() {
		this.CounselorName = null;
		this.isIntern = false;
	}
}
