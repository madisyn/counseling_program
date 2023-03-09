
public class Employee {
	
	String name;
	int MonthlySalary;
	boolean paid[] = new boolean[12];
	

	public Employee(String name, int Salary) {
		this.name = name;
		this.MonthlySalary = Salary/12;
	}
	
	public void receiveIncome(int month) {
		//try/catch if the number is a month
		paid[month] = true;
	}

}
