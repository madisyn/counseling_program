import java.util.ArrayList;
import java.util.Scanner;


public class Main {
	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		
		
	}
	/*
	 * Will check if employee was already paid if not then will check if bank has enough money
	 * if the bank has enough money then money will be given to employee
	 */
	public static void PayEmployee(Employee name, Bank bank, int Month) {
		
		if(bank.BankPaymentCheck(name.MonthlySalary) && (name.checkPaymentMonth(Month))){
			bank.payEmployees(name, Month);
			System.out.println(name.name + " was paid $" + name.MonthlySalary);
			System.out.println(name.paid[Month]);
		}
		else if(!bank.BankPaymentCheck(name.MonthlySalary)) {
			System.out.println("Bank does not have enough funds.  Please talk to manager");
		}
		else if(name.paid[Month] == true) {
			System.out.println("Employee was already paid for this month.  Please talk to a mangaer");
		}
		
	}
	
	public static void receiveFromIntern(Intern name, Bank bank, int month){
		if(!name.checkPaymentMonth(month)) {
			bank.receivePayment(name, month);
			System.out.println("Intern " + name.name + " payment received");
		}
		else {
			System.out.println("Intern " +name.name +" has already made a payment for this month.  Please select a correct month");
		}
	}
	
	public static void receiveFromClient(Client name, Bank bank, int month, int week){
		if(!name.checkPaymentMonthWeek(month, week)) {
			bank.receivePayment(name, month, week);
			System.out.println("Client " + name.name + " payment received");
		}
		else {
			System.out.println("Client " +name.name +" has already made a payment for month: " + month + ", week: " + week +  ". Please select a correct month and week");
		}
	}
	
	public static void voidClient(Client name, Bank bank, int month, int week) {
		if(name.checkPaymentMonthWeek(month, week) == true) {
			name.voidExpense(month, week);
			System.out.println("Payment for client " + name.name + " on month: " + month + " week: " + week + " voided.");
		}
		else {
			System.out.println("Payment was never made for month: "+ month + ", week: " + week + ". Please select a correct month and week");
		}
	}
	
	public static void voidIntern(Intern name, Bank bank, int month) {
		if(name.checkPaymentMonth(month) == true) {
			name.voidMonthlyExpense(month);
			System.out.println("Payment for client " + name.name + " on month: " + month + " voided.");
		}
		else {
			System.out.println("Payment was never made for month: "+ month +  ". Please select a correct month");
		}
	}
	
	public static void printStaffClients(Staff name) {
		ArrayList<Client> clientList = new ArrayList<>();
		clientList = name.getClientList();
		for(int i=0; i<clientList.size(); i++) {
			System.out.println("check: " + clientList.get(i).name);
		}
	}
	
	public static void printInternClients(Intern name) {
		ArrayList<Client> clientList = new ArrayList<>();
		clientList = name.getClientList();
		for(int i=0; i<clientList.size(); i++) {
			System.out.println("check: " + clientList.get(i).name);
		}
	}
	
	public static void addEmployee(ArrayList<Employee> employeeList) {
		//enter name and other stuff to create the employee
		System.out.print("name: ");
		String name = scan.nextLine();
		System.out.print("Hourly Rate: ");
		int rate = scan.nextInt();
		Employee newEmployee = new Employee(name, rate);
		employeeList.add(newEmployee);
	}
	
	public static void printEmployeeList(ArrayList<Employee> employeeList) {
		for(int i=0; i<employeeList.size(); i++) {
			System.out.println("name: " + employeeList.get(i).name);
			System.out.println("work type: " + employeeList.get(i).workType);
			System.out.println("Hourly Rate: " + employeeList.get(i).HourlyWage);
		}
	}

}
