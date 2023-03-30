import java.util.ArrayList;
import java.util.Scanner;


public class Main {
	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
				ArrayList<Employee> employeeList = new ArrayList<Employee>();
				ArrayList<Staff> staffList = new ArrayList<Staff>();
				ArrayList<Intern> internList = new ArrayList<Intern>();
				ArrayList<Client> clientList = new ArrayList<Client>();
				Bank bank1 = new Bank("Name", 250000);
				
			
		
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
		Scanner scanner = new Scanner(System.in);
		System.out.print("name: ");
		String name = scanner.nextLine();
		System.out.print("Hourly Rate: ");
		int rate = scanner.nextInt();
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
	
	public static void addStaff(ArrayList<Staff> staffList) {
		//enter name and other stuff to create the employee
		Scanner scanner = new Scanner(System.in);
		System.out.print("name: ");
		String name = scanner.nextLine();
		System.out.print("Salary: ");
		int salary = scanner.nextInt();
		Staff newStaff = new Staff(name, salary);
		staffList.add(newStaff);
	}
	
	public static void printStaffList(ArrayList<Staff> staffList) {
		for(int i=0; i<staffList.size(); i++) {
			System.out.println("name: " + staffList.get(i).name);
			System.out.println("work type: " + staffList.get(i).workType);
			System.out.println("Monthly Salary: " + staffList.get(i).Salary);
		}
	}
	
	public static boolean checkStaffList(ArrayList<Staff> staffList, String name) {
		for(int i=0; i<staffList.size(); i++) {
			if(staffList.get(i).name.equals(name)) {
				return true;
			}
		}
		return false;
	}
	
	public static Staff returnStaffMember(ArrayList<Staff> staffList, String name) {
		for(int i=0; i<staffList.size(); i++) {
			if(staffList.get(i).name.equals(name)) {
				return staffList.get(i);
			}
		}
		return null;
	}
	
	public static void addIntern(ArrayList<Intern> internList, ArrayList<Staff> staffList) {
		Intern newIntern;
		//enter name and other stuff to create the employee
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("New Intern name: ");
		String internName = scanner.nextLine();
		
		System.out.print("Staff Supervisor Name: ");
		String StaffName = scanner.nextLine();
		
		//verify if StaffName is an actual staff member
		while(!checkStaffList(staffList, StaffName)) {
			System.out.println("Staff is not in directory.  Enter staff member");
			StaffName = scanner.nextLine();
		}
		
		System.out.print("Modified Expense? (yes or no): ");
		String answer = scanner.nextLine();
		
		//verify if modified expense or not
		while(!(answer.contains("yes") || answer.contains("no"))) {
			System.out.println("Modified Expense? (please type \"yes\" or \"no\"): ");
			answer = scanner.nextLine();
		}
		
		
		if(answer.equals("yes")){
			System.out.print("Modified Expense amount: ");
			int modification = scanner.nextInt();
			newIntern = new Intern(internName, returnStaffMember(staffList, StaffName), modification);
		}
		else {
			newIntern = new Intern(internName, returnStaffMember(staffList, StaffName));
		}
		
		internList.add(newIntern);
		
	}
	public static void printInternList(ArrayList<Intern> internList) {
		for(int i=0; i<internList.size(); i++) {
			System.out.println("Name: " + internList.get(i).name);
			System.out.println("Supervisor: " + internList.get(i).supervisor.name);
			System.out.println("Monthly Expense: " + internList.get(i).paymentAmount);
		}
	}


}
