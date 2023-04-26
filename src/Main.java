
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
	
	static ArrayList<Employee> employeeList = new ArrayList<Employee>();
	static ArrayList<Staff> staffList = new ArrayList<Staff>();
	static ArrayList<Intern> internList = new ArrayList<Intern>();
	static ArrayList<Client> clientList = new ArrayList<Client>();
	static Bank bank = new Bank("Name", 250000);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
				
				
				

	}

		
	//EMPLOYEE FUNCTIONS
	
	public static void addEmployee(String name, int hourlyRate) {
		Employee newEmployee = new Employee(name, hourlyRate);
		employeeList.add(newEmployee);
	}
	

	public static void deleteEmployee(String EmployeeName) {
		
		int index = returnEmployeeIndex(EmployeeName);
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
	public static void payEmployee(String name, int Month) {
		
		int index = returnEmployeeIndex(name);
		
		if(index != -1) {
			if(bank.BankVerifySufficientFunds(employeeList.get(index).MonthlySalary) && (!employeeList.get(index).checkCompletedPaymentMonth(Month))){
			bank.payEmployees(employeeList.get(index), Month);
			System.out.println(employeeList.get(index).name + " was paid $" + employeeList.get(index).MonthlySalary);
			}
			else if(!bank.BankVerifySufficientFunds(employeeList.get(index).MonthlySalary)) {
				System.out.println("Bank does not have enough funds.  Please talk to manager");
			}
			else if(employeeList.get(index).paid[Month] == true) {
				System.out.println("Employee was already paid for this month.  Please talk to a mangaer");
			}
		}
		else {
			System.out.println("Employee does not exist.  Please enter name of an existing employee");
		}
		
		
	}
	
	public static void changeEmployeeHourlyRate(String EmployeeName, int newRate) {
		
		int index = returnEmployeeIndex(EmployeeName);
		
		if(index != -1) {
			employeeList.get(index).changeHourlyRate(newRate);
		}
		else {
			System.out.println("Employee does not exist.  Please Enter a valid employee");
		}
		
	}
	
	public static void recordEmployeeHours(String EmployeeName, int hours) {
		int index = returnEmployeeIndex(EmployeeName);
		
		if(index != -1) {
			employeeList.get(index).addHours(hours);
		}
		else {
			System.out.println("Employee does not exist.  Please Enter a valid employee");
		}
	}
	
	
	public static void restartEmployeeWeeklyHours(String EmployeeName) {
		int index = returnEmployeeIndex(EmployeeName);
		
		if(index != -1) {
			employeeList.get(index).restartHours();
		}
		else {
			System.out.println("Employee does not exist.  Please Enter a valid employee");
		}
	}
	
	public static void restartEmployeeMonthlyHours(String EmployeeName) {
		int index = returnEmployeeIndex(EmployeeName);
		
		if(index != -1) {
			employeeList.get(index).restartMonthlyHours();
		}
		else {
			System.out.println("Employee does not exist.  Please Enter a valid employee");
		}
	}

	
	public static void printEmployeeList() {
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
	 * checks if employee is in the arrayList as well as returns its index-1
	 */
	public static int returnEmployeeIndex(String EmployeeName) {
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
	public static void deleteStaff(ArrayList<Staff> staffList, String staffName) {
		
		int index = returnStaffIndex(staffList, staffName);
		if(index == -1) {return;}
		
		//delete the staff that are assigned to any interns
		ArrayList<Intern> internList = new ArrayList<>();
		internList = staffList.get(index).getInternList();
		
		for(int i =0; i <internList.size(); i++) {
			internList.get(i).deleteSupervisor();
		}
			
			
		//deletes the staff from every client the staff has
		ArrayList<Client> clientList = new ArrayList<>();
		clientList = staffList.get(index).getClientList();
		
		for(int i=0; i<clientList.size(); i++) {
			DeleteClientsCounselor(clientList, clientList.get(i).name);
		}
			
			staffList.remove(index);
		
		
	}
	
	public static void removeClientConnectedToStaff(ArrayList<Staff> staffList, String staffName, ArrayList<Client> clientList, String clientName) {
		int staffIndex = returnStaffIndex(staffList, staffName);
		if(staffIndex==-1) {return;	}
		int clientIndex = returnClientIndex(clientList, clientName);
		if(clientIndex==-1) {return;}
		staffList.get(staffIndex).deleteClient(clientList.get(clientIndex));
		DeleteClientsCounselor(clientList, clientName);
	}
	
	public static void addClientToStaff(ArrayList<Staff> staffList, String staffName, ArrayList<Client> clientList, String clientName) {
		int staffIndex = returnStaffIndex(staffList, staffName);
		if(staffIndex==-1) {return;}
		int clientIndex = returnClientIndex(clientList, clientName);
		if(clientIndex==-1) {return;}
		staffList.get(staffIndex).addClient(clientList.get(clientIndex));
	}
	
	public static void addInternToStaff(ArrayList<Staff> staffList, String staffName, ArrayList<Intern> internList, String internName) {
		int staffIndex = returnStaffIndex(staffList, staffName);
		if(staffIndex==-1) {return;}
		int internIndex = returnInternIndex(internList, internName);
		if(internIndex==-1) {return;}
		staffList.get(staffIndex).addIntern(internList.get(internIndex));
		
	}
	
	public static void deleteStaffsIntern(ArrayList<Staff> staffList, String staffName, ArrayList<Intern> internList, String internName) {
		int staffIndex = returnStaffIndex(staffList, staffName);
		if(staffIndex==-1) {return;}
		int internIndex = returnInternIndex(internList, internName);
		if(internIndex==-1) {return;}
		staffList.get(staffIndex).deleteIntern(internList.get(internIndex));
		
		InternDeleteSupervisor(internList, internName);
	}
	
	public static void changeStaffSalary(ArrayList<Staff> staffList, String staffName, int salaryChange) {
		int staffIndex = returnStaffIndex(staffList, staffName);
		if(staffIndex==-1) {return;}
		staffList.get(staffIndex).changeSalary(salaryChange);
	}
	

	public static void printStaffList(ArrayList<Staff> staffList) {
		for(int i=0; i<staffList.size(); i++) {
			System.out.println("name: " + staffList.get(i).name);
			System.out.println("work type: " + staffList.get(i).workType);
			System.out.println("Monthly Salary: " + staffList.get(i).Salary+ "\n");
		}
	}
		
	public static void printStaffClients(ArrayList<Staff> staffList, String staffName) {
		int staffIndex = returnStaffIndex(staffList, staffName);
		if(staffIndex==-1) {
			System.out.println("Staff Does not exist.");
			return;}
		
		ArrayList<Client> clientList = new ArrayList<>();
		clientList = staffList.get(staffIndex).getClientList();
		for(int i=0; i<clientList.size(); i++) {
			System.out.println(staffName + "'s client: " + clientList.get(i).name);
		}
	}
	
	public static void printStaffInterns(ArrayList<Staff> staffList, String staffName) {
		int staffIndex = returnStaffIndex(staffList, staffName);
		if(staffIndex==-1) {
			System.out.println("Staff Does not exist.");
			return;}
		ArrayList<Intern> internList = new ArrayList<>();
		internList = staffList.get(staffIndex).getInternList();
		System.out.println(staffName + "'s interns: ");
		for(int i=0; i<internList.size(); i++) {
			System.out.println(internList.get(i).name);
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
		System.out.println("Staff does not exist.  Please enter a valid staff member");
		return -1;
	}
	
	//INTERN FUNCTIONS
	
	public static void addIntern(ArrayList<Intern> internList, ArrayList<Staff> staffList, String internName, String StaffName) {
		
		int staffIndex = returnStaffIndex(staffList, StaffName);
		if(staffIndex == -1) {
			System.out.println("Staff does not exist.  Please enter valid staff");
			return;
			}		
		
		Intern newIntern = new Intern(internName, returnStaffMember(staffList, StaffName));

		internList.add(newIntern);
		
		addInternToStaff(staffList, StaffName, internList, internName);
		
	}
	
	public static void addIntern(ArrayList<Intern> internList, ArrayList<Staff> staffList, String internName, String StaffName, int modification) {
		
		int staffIndex = returnStaffIndex(staffList, StaffName);
		if(staffIndex == -1) {return;}	
		
		Intern newIntern = new Intern(internName, returnStaffMember(staffList, StaffName), modification);

		internList.add(newIntern);
		addInternToStaff(staffList, StaffName, internList, internName);
		
	}
	
	/*
	 * Delete intern from Staff and Clients that they are assigned to
	 */
	public static void deleteIntern(ArrayList<Staff> staffList, ArrayList<Intern> internList, String internName) {
				
		//deletes the intern from every client the intern has
		int internIndex = returnInternIndex(internList, internName);
		ArrayList<Client> clientList = new ArrayList<>();
		clientList = internList.get(internIndex).getClientList();
		
		for(int i=0; i<clientList.size(); i++) {
			DeleteClientsCounselor(clientList, clientList.get(i).name);
		}
		//deletes the intern from the staffs Intern List
		deleteStaffsIntern(staffList, internList.get(internIndex).returnSupervisor().name, internList, internName);
		
		internList.remove(internIndex);

	}
	
	
	public static void addClientToIntern(ArrayList<Intern> internList, String internName, ArrayList<Client> clientList, String clientName) {
		int internIndex = returnInternIndex(internList, internName);
		int clientIndex = returnClientIndex(clientList, clientName);
		internList.get(internIndex).addClient(clientList.get(clientIndex));
	}
	
	public static void removeInternsClient(ArrayList<Intern> internList, String internName, ArrayList<Client> clientList, String clientName) {
		int internIndex = returnInternIndex(internList, internName);
		if(internIndex == -1) {
			System.out.println("Intern does not exist");
			return;}	
		int clientIndex = returnClientIndex(clientList, clientName);
		if(clientIndex == -1) {
			System.out.println("Client does not exist");
			return;}	
		internList.get(internIndex).deleteClient(clientList.get(clientIndex));
	}
	
	public static void InternChangeSupervisor(ArrayList<Staff> staffList, String staffName, ArrayList<Intern> internList, String internName) {
		int internIndex = returnInternIndex(internList, internName);
		if(internIndex == -1) {return;}	
		int staffIndex = returnStaffIndex(staffList, staffName);
		if(staffIndex == -1) {return;}	
		deleteStaffsIntern(staffList, internList.get(internIndex).returnSupervisor().name, internList, internName);
		internList.get(internIndex).changeSupervisor(staffList.get(staffIndex));
		staffList.get(staffIndex).addIntern(internList.get(internIndex));
		
	}
	
	public static void InternDeleteSupervisor(ArrayList<Intern> internList, String internName) {
		int internIndex = returnInternIndex(internList, internName);
		if(internIndex == -1) {return;}	
		
		internList.get(internIndex).returnSupervisor().deleteIntern(internList.get(internIndex));
		internList.get(internIndex).deleteSupervisor();
	}
	
	public static void receivePaymenFromIntern(ArrayList<Intern> internList, String internName, Bank bank, int month){
		int internIndex = returnInternIndex(internList, internName);
		if(internIndex == -1) {return;}	
		
		if(!internList.get(internIndex).checkCompletedPaymentMonth(month)) {
			bank.receivePayment(internList.get(internIndex), month);
			System.out.println("Intern " + internList.get(internIndex).name + " payment received");
		}
		else {
			System.out.println("Intern " +internList.get(internIndex).name +" has already made a payment for this month.  Please select a correct month");
		}
	}
	
	public static void voidInternMonthlyPayment(ArrayList<Intern> internList, String internName, Bank bank, int month) {
		int internIndex = returnInternIndex(internList, internName);
		if(internIndex == -1) {return;}	
		
		if(internList.get(internIndex).checkCompletedPaymentMonth(month) == true) {
			internList.get(internIndex).voidMonthlyExpense(month);
			System.out.println("Payment for client " + internList.get(internIndex).name + " on month: " + month + " voided.");
		}
		else {
			System.out.println("Payment was never made for month: "+ month +  ". Please select a correct month");
		}
	}	
	
	public static void changeInternFee(ArrayList<Intern> internList, String internName, int feeChange) {
		int internIndex = returnInternIndex(internList, internName);
		if(internIndex == -1) {return;}	
		internList.get(internIndex).changePaymentAmount(feeChange);
	}
	
	
	public static void printInternList(ArrayList<Intern> internList) {
		for(int i=0; i<internList.size(); i++) {
			System.out.println("Name: " + internList.get(i).name);
			if(internList.get(i).supervisor == null) {
				System.out.println("Supervisor: Not Assigned");
			}
			else {
				System.out.println("Supervisor: " + internList.get(i).supervisor.name);
			}
			
			System.out.println("Monthly Expense: " + internList.get(i).paymentAmount);
		}
	}
	
	public static void printInternClients(ArrayList<Intern> internList, String internName) {
		int internIndex = returnInternIndex(internList, internName);
		if(internIndex == -1) {return;}	
		ArrayList<Client> clientList = new ArrayList<>();
		clientList = internList.get(internIndex).getClientList();
		System.out.println(internName + "'s Clients:");
		for(int i=0; i<clientList.size(); i++) {
			System.out.println(clientList.get(i).name);
		}
	}
	
	public static int returnInternIndex(ArrayList<Intern> internList, String name) {
		for(int i=0; i<internList.size(); i++) {
			if(internList.get(i).name.equals(name)) {
				return i;
			}
		}
		System.out.println("Intern does not exist.  Please enter a valid intern name");
		return -1;
	}
	

	//CLIENT FUNCTIONS
	
	public static void addClientAssignedToStaff(ArrayList<Client> clientList, String clientName, ArrayList<Staff> staffList, String staffName, int expense) {
		
		int clientIndex = returnClientIndex(clientList, clientName);
		if(clientIndex != -1) {
			System.out.println("Client already exists");
			return;}	
		int staffIndex = returnStaffIndex(staffList, staffName);
		if(staffIndex == -1) {
			System.out.println("Staff does not exist");
			return;}	

		Client newClient = new Client(clientName, expense, staffList.get(staffIndex));
		clientList.add(newClient);
		addClientToStaff(staffList, staffName, clientList, clientName);
			
	}
	
	public static void addAndAssignClientToIntern(ArrayList<Client> clientList, String clientName, ArrayList<Intern> internList, String internName, int expense) {
		System.out.println("maybe1");
		int clientIndex = returnClientIndex(clientList, clientName);
		if(clientIndex != -1) {
			System.out.println("Client already exists");
			return;}	
		int internIndex = returnInternIndex(internList, internName);
		if(internIndex == -1) {
			System.out.println("intern does not exist");
			return;}	
		Client newClient = new Client(clientName, expense, internList.get(internIndex));
		clientList.add(newClient);
		addClientToIntern(internList, internName, clientList, clientName);
		
	}
	
	public static void deleteClient(ArrayList<Client> clientList, String clientName, ArrayList<Intern> internList, ArrayList<Staff> staffList) {
		int clientIndex = returnClientIndex(clientList, clientName);
		if(clientIndex == -1) {return;}	
		
		
		//need to delete client from staff or interns as well as the arraylist
		String counselorName = clientList.get(clientIndex).getClientsCounselor();
		int index;
		//if the counselor is an intern
		if(clientList.get(clientIndex).getCounselorType()) {
			//find the intern and call delete interns client
			index = returnInternIndex(internList, counselorName);
			
			if(index == -1) {
				System.out.println("Counselor not in List.  Please enter a valid counselor");
				return;
			}
			internList.get(index).deleteClient(clientList.get(clientIndex));
		}
		else {
			//find the staff and call delete staffs client
			index = returnStaffIndex(staffList, counselorName);
			if(index == -1) {
				System.out.println("Counselor not in List.  Please enter a valid counselor");
				return;
			}
			staffList.get(index).deleteClient(clientList.get(clientIndex));
		}
				
		clientList.remove(returnClientIndex(clientList, clientList.get(clientIndex).name));
		
	}
	
	public static void DeleteClientsCounselor(ArrayList<Client> clientList, String clientName) {
		int clientIndex = returnClientIndex(clientList, clientName);
		if(clientIndex == -1) {return;}	
		clientList.get(clientIndex).deleteCounselor();
	}
	
	public static void ClientChangeInternCounselor(ArrayList<Client> clientList, String clientName, ArrayList<Intern> internList, String internName) {
		int clientIndex = returnClientIndex(clientList, clientName);
		if(clientIndex == -1) {return;}	
		int internIndex = returnInternIndex(internList, clientName);
		if(internIndex == -1) {return;}	


		clientList.get(clientIndex).changeCounselor(internList.get(internIndex));
	}
	
	public static void ClientChangeStaffCounselor(ArrayList<Client> clientList, String clientName, ArrayList<Staff> staffList, String staffName) {
		int clientIndex = returnClientIndex(clientList, clientName);
		if(clientIndex == -1) {return;}	
		int staffIndex = returnStaffIndex(staffList, staffName);
		if(staffIndex == -1) {return;}	
		
		
		clientList.get(clientIndex).changeCounselor(staffList.get(staffIndex));
	}
	
	
	public static void ClientMakesPayment(ArrayList<Client> clientList, String clientName, Bank bank, int month, int week){
		int clientIndex = returnClientIndex(clientList, clientName);
		if(clientIndex == -1) {return;}	
		
		if(!clientList.get(clientIndex).checkCompletedPaymentWeek(month, week)) {
			bank.receivePayment(clientList.get(clientIndex), month, week);
			System.out.println("Client " + clientList.get(clientIndex).name + " payment received");
		}
		else {
			System.out.println("Client " +clientList.get(clientIndex).name +" has already made a payment for month: " + month + ", week: " + week +  ". Please select a correct month and week");
		}
	}
	
	public static void voidClientPayment(ArrayList<Client> clientList, String clientName, Bank bank, int month, int week) {
		int clientIndex = returnClientIndex(clientList, clientName);
		if(clientIndex == -1) {return;}	
		
		if(clientList.get(clientIndex).checkCompletedPaymentWeek(month, week) == true) {
			clientList.get(clientIndex).voidExpense(month, week);
			System.out.println("Payment for client " + clientList.get(clientIndex).name + " on month: " + month + " week: " + week + " voided.");
		}
		else {
			System.out.println("Payment was never made for month: "+ month + ", week: " + week + ". Please select a correct month and week");
		}
	}
	
	public static void clientChangeFee(ArrayList<Client> clientList, String clientName, int chengeFeeAmount) {
		int clientIndex = returnClientIndex(clientList, clientName);
		if(clientIndex == -1) {return;}	
		
		clientList.get(clientIndex).changePaymentFee(chengeFeeAmount);
	}
	
	public static void printClientList(ArrayList <Client> clientList) {
		for(int i=0; i<clientList.size(); i++) {
			System.out.println("Name: " + clientList.get(i).name);
			System.out.println("Fee Amount: " + clientList.get(i).paymentAmount);
			System.out.println("Counselor Name: " + clientList.get(i).CounselorName + "\n");
		}
	}
	
	public static int returnClientIndex(ArrayList<Client> clientList, String name) {
		for(int i=0; i<clientList.size(); i++) {
			if(clientList.get(i).name.equals(name)) {
				return i;
			}
		}
		System.out.println("Client does not exist.  Please enter a valid Client name");
		return -1;
	}
	
	public static void printClientCounselorName(ArrayList<Client> clientList, String clientName) {
		int clientIndex = returnClientIndex(clientList, clientName);
		if(clientIndex == -1) {return;}	
		
		System.out.println("Client " + clientList.get(clientIndex).name + " counselor:  " + clientList.get(clientIndex).CounselorName);
	}
	
	//BANK FUNCTIONS
	
	public static int getBankTotal(Bank bank) {
		return bank.returnBankBalance();
	}
	
	public static void printBankTotal(Bank bank) {
		System.out.println("Bank Total: " + getBankTotal(bank));
	}


}
