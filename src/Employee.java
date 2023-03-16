
public class Employee {
	
	String name;
	String workType;
	int MonthlySalary;
	int hoursPerWeek;
	boolean paid[] = new boolean[11];
	

	public Employee(String name, int Salary) {
		this.name = name;
		this.MonthlySalary = Salary/12;
		this.workType = "Hourly";
		this.hoursPerWeek = 0;
		for(int i=0; i<paid.length; i++) {
			paid[i] = false;
		}
	}
	
	public Employee(String name, String workingType, int Salary) {
		this.name = name;
		this.MonthlySalary = Salary/12;
		this.workType = workingType;
		this.hoursPerWeek = 40;
		for(int i=0; i<paid.length; i++) {
			paid[i] = false;
		}
	}
	
	public void receiveIncome(int month) {
		//try/catch if the number is a month
		paid[month] = true;
		
	}

}
