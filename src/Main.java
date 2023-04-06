import java.util.ArrayList;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
				ArrayList<Employee> employeeList = new ArrayList<Employee>();
				ArrayList<Staff> staffList = new ArrayList<Staff>();
				ArrayList<Intern> internList = new ArrayList<Intern>();
				ArrayList<Client> clientList = new ArrayList<Client>();
				Bank bank1 = new Bank("Name", 250000);
				
				addEmployee(employeeList, "Justin", 50);
				addEmployee(employeeList, "Chris", 50);
				addEmployee(employeeList, "Sandra", 50);
				

		
	}

		
	//EMPLOYEE FUNCTIONS
	
	public static void addEmployee(ArrayList<Employee> employeeList, String name, int rate) {
		Employee newEmployee = new Employee(name, rate);
		employeeList.add(newEmployee);
	}
	
	/*
	 * Deleting and employee removes them from the employeeList Array
	 */
	public static void deleteEmployee(ArrayList<Employee> employeeList, String EmployeeName) {
		
		int index = returnEmployeeIndex(employeeList, EmployeeName);
		if(index != -1) {
			employeeList.remove(index);
		}
		else {
			System.out.println("Employee does not exist.  Please Enter a valid employee");
		}
	}
	
	
	/*
	 * Will check if employee was already paid if not then will check if bank has enough money
	 * if the bank has enough money then money will be given to employee
	 */
	public static void payEmployee(Employee name, Bank bank, int Month) {
		
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
	
	public static void changeEmployeeHourlyRate(ArrayList<Employee> employeeList, String EmployeeName, int newRate) {
		
		int index = returnEmployeeIndex(employeeList, EmployeeName);
		
		if(index != -1) {
			employeeList.get(index).changeHourlyRate(newRate);
		}
		else {
			System.out.println("Employee does not exist.  Please Enter a valid employee");
		}
		
	}
	
	public static void recordEmployeeHours(ArrayList<Employee> employeeList, String EmployeeName, int hours) {
		int index = returnEmployeeIndex(employeeList, EmployeeName);
		
		if(index != -1) {
			employeeList.get(index).addHours(hours);
		}
		else {
			System.out.println("Employee does not exist.  Please Enter a valid employee");
		}
	}
	
	
	public static void restartEmployeeWeeklyHours(ArrayList<Employee> employeeList, String EmployeeName) {
		int index = returnEmployeeIndex(employeeList, EmployeeName);
		
		if(index != -1) {
			employeeList.get(index).restartHours();
		}
		else {
			System.out.println("Employee does not exist.  Please Enter a valid employee");
		}
	}
	
	public static void restartEmployeeMonthlyHours(ArrayList<Employee> employeeList, String EmployeeName) {
		int index = returnEmployeeIndex(employeeList, EmployeeName);
		
		if(index != -1) {
			employeeList.get(index).restartMonthlyHours();
		}
		else {
			System.out.println("Employee does not exist.  Please Enter a valid employee");
		}
	}

	
	public static void printEmployeeList(ArrayList<Employee> employeeList) {
		for(int i=0; i<employeeList.size(); i++) {
			System.out.println("name: " + employeeList.get(i).name);
			System.out.println("work type: " + employeeList.get(i).workType);
			System.out.println("Hourly Rate: " + employeeList.get(i).HourlyWage);
			System.out.println("Weekly Hours: " +  employeeList.get(i).hoursPerWeek);
			System.out.println("Monthly Hours: " +  employeeList.get(i).MonthlyHours);
			System.out.println("");
		}
	}
	
	/*
	 * checks if employee is in the arrayList as well as returns its index-=
	 */
	public static int returnEmployeeIndex(ArrayList<Employee> employeeList, String EmployeeName) {
		for(int i=0; i<employeeList.size(); i++) {
			if(employeeList.get(i).name.equals(EmployeeName)){
				return i;
			}
		}
		return -1;
	}
	
	//STAFF FUNCTIONS
	
	public static void addStaff(ArrayList<Staff> staffList, String name, int salary) {
		Staff newStaff = new Staff(name, salary);
		staffList.add(newStaff);
	}
	
	/*
	 * When we delete a staff, we need to delete that the staff is in charge of any interns and that they are assigned to clients
	 */
	public static void deleteStaff(Staff staff, Client client, Intern intern) {
		//*****************************
	}
	
	public static void deleteStaffClient(Staff staff, Client client) {
		staff.deleteClient(client);
	}
	
	public static void addStaffClient(Staff staff, Client client) {
		staff.addClient(client);
	}
	
	public static void changeStaffSalary(Staff staff, int salaryChange) {
		staff.changeSalary(salaryChange);
	}
	

		public static void printStaffList(ArrayList<Staff> staffList) {
		for(int i=0; i<staffList.size(); i++) {
			System.out.println("name: " + staffList.get(i).name);
			System.out.println("work type: " + staffList.get(i).workType);
			System.out.println("Monthly Salary: " + staffList.get(i).Salary);
		}
	}
	
	public static void printStaffClients(Staff name) {
		ArrayList<Client> clientList = new ArrayList<>();
		clientList = name.getClientList();
		for(int i=0; i<clientList.size(); i++) {
			System.out.println("check: " + clientList.get(i).name);
		}
	}
	
	public static Staff returnStaffMember(ArrayList<Staff> staffList, String name) {
		int index = returnStaffIndex(staffList, name);
		
		if(index != -1) {
			return staffList.get(index);
		}
		return null;
	}	
	
	
	public static int returnStaffIndex(ArrayList<Staff> staffList, String name) {
		for(int i=0; i<staffList.size(); i++) {
			if(staffList.get(i).name.equals(name)) {
				return i;
			}
		}
		return -1;
	}
	
	//INTERN FUNCTIONS
	
	public static void addIntern(ArrayList<Intern> internList, ArrayList<Staff> staffList, String internName, String StaffName) {
		Intern newIntern;
		
		//verify if StaffName is an actual staff member
		if(returnStaffIndex(staffList, StaffName) == -1) {
			System.out.println("Staff is not in directory.  Enter staff member");
		}		
		
		newIntern = new Intern(internName, returnStaffMember(staffList, StaffName));

		internList.add(newIntern);
		
	}
	
	public static void addIntern(ArrayList<Intern> internList, ArrayList<Staff> staffList, String internName, String StaffName, int modification) {
		Intern newIntern;
		
		//verify if StaffName is an actual staff member
		if(returnStaffIndex(staffList, StaffName)== -1) {
			System.out.println("Staff is not in directory.  Enter staff member");
		}		
		
		newIntern = new Intern(internName, returnStaffMember(staffList, StaffName), modification);

		internList.add(newIntern);
		
	}
	
	/*
	 * Delete intern from Staff and Clients that they are assigned to
	 */
	public static void deleteIntern() {
		
	}
	
	
	public static void AddInternClient(Intern intern, Client client) {
		intern.addClient(client);
	}
	
	public static void deleteInternClient(Intern intern, Client client) {
		intern.deleteClient(client);
	}
	
	public static void InternChangeSupervisor(Intern intern, Staff staff) {
		intern.changeSupervisor(staff);
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
	
	public static void voidIntern(Intern name, Bank bank, int month) {
		if(name.checkPaymentMonth(month) == true) {
			name.voidMonthlyExpense(month);
			System.out.println("Payment for client " + name.name + " on month: " + month + " voided.");
		}
		else {
			System.out.println("Payment was never made for month: "+ month +  ". Please select a correct month");
		}
	}	
	
	public static void changeInternFee(Intern intern, int feeChange) {
		intern.changePaymentAmount(feeChange);
	}
	
	
	public static void printInternList(ArrayList<Intern> internList) {
		for(int i=0; i<internList.size(); i++) {
			System.out.println("Name: " + internList.get(i).name);
			System.out.println("Supervisor: " + internList.get(i).supervisor.name);
			System.out.println("Monthly Expense: " + internList.get(i).paymentAmount);
		}
	}
	
	public static void printInternClients(Intern name) {
		ArrayList<Client> clientList = new ArrayList<>();
		clientList = name.getClientList();
		for(int i=0; i<clientList.size(); i++) {
			System.out.println("check: " + clientList.get(i).name);
		}
	}
	
	public static int returnInternIndex(ArrayList<Intern> internList, String name) {
		for(int i=0; i<internList.size(); i++) {
			if(internList.get(i).name.equals(name)) {
				return i;
			}
		}
		return -1;
	}
	

	
	
	
	//CLIENT FUNCTIONS
	
	public static void addClient() {
		
	}
	
	
	public static void deleteClient() {
		
		
	}
	
	public static void ClientChangeTherapist() {
		
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
	
	public static void clientChangeFee() {
		
	}
	
	public static void printClientList() {
		
	}
	
	public static void checkStaffList() {
		
	} 
	
	//BANK FUNCTIONS
	
	public static void getBankTotal() {
		
	}


}
