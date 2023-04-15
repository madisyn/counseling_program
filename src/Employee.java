
public class Employee {
	String name;
	String workType;
	int MonthlySalary;
	int hoursPerWeek;
	int MonthlyHours;
	int Salary =-1;
	int HourlyWage=-1;
	boolean paid[] = new boolean[11];
	

	public Employee(String name, int HourlyRate) {
		this.name = name;
		this.HourlyWage = HourlyRate;
		this.MonthlySalary = HourlyRate*40*4;
		this.workType = "Hourly";
		this.hoursPerWeek = 0;
		this.MonthlyHours = 0;
		for(int i=0; i<paid.length; i++) {
			paid[i] = false;
		}
	}
	
	public Employee(String name, String workingType, int Salary) {
		this.name = name;
		this.Salary = Salary;
		this.MonthlySalary = Salary/12;
		this.workType = workingType;
		this.hoursPerWeek = 40;
		for(int i=0; i<paid.length; i++) {
			paid[i] = false;
		}
	}
	
	public boolean receiveIncome(int month) {
		//try/catch if the number is a month
		if(paid[month]!=false) {
			System.out.println("Employee has already been paid.  Please check records");
			return false;
		}
			
			paid[month] = true;
			return true;
		
		
		
		
	}
	
	public void addHours(int hours) {
		if(this.workType!="Hourly") {
			System.out.println("Employee is not paid hourly.  Please check their file");
		}
		else {
			this.hoursPerWeek+=hours;
			this.checkHours();
		}
	}
	
	public void deleteHours(int hours) {
		if(this.workType!="Hourly") {
			System.out.println("Employee is not paid hourly.  Please check their file");
		}
		else {
			this.hoursPerWeek-=hours;
		}
	}
	
	public void checkHours() {
		if(this.hoursPerWeek>40) {
			System.out.println("Hours exceeded 40 for the week!  Please check with supervisor");
		}
		if(this.hoursPerWeek>35 && this.hoursPerWeek<40) {
			System.out.println("Hours close to 40.  Target hours per week is 40");
		}
	}
	
	public void restartHours() {
		this.MonthlyHours+= this.hoursPerWeek;
		this.hoursPerWeek = 0;
	}
	
	public void restartMonthlyHours() {
		this.MonthlyHours = 0;
	}
	
	public void changeHourlyRate(int hourlyRate) {
		this.HourlyWage = hourlyRate;
		this.MonthlySalary = hourlyRate*40*4;
	}
	
	public boolean checkCompletedPaymentMonth(int month) {
		if(this.paid[month]==true  && month>0 && month <=12) {
			return true;
		}
		return false;
	}

}
