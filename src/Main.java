
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
	
	static ArrayList<Employee> employeeList = new ArrayList<Employee>();
	static ArrayList<Staff> staffList = new ArrayList<Staff>();
	static ArrayList<Intern> internList = new ArrayList<Intern>();
	static ArrayList<Client> clientList = new ArrayList<Client>();
	static Bank bank = new Bank("Name", 250000);
	
	public static void main(String[] args) {


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
	
	public static void addStaff(String name, int salary) {
		Staff newStaff = new Staff(name, salary);
		staffList.add(newStaff);
	}
	
	/*
	 * When we delete a staff, we need to delete that the staff in charge of any interns and that they are assigned to clients
	 */
	public static void deleteStaff(String staffName) {
		
		int index = returnStaffIndex(staffName);
		if(index == -1) {return;}
		
		//delete the staff that are assigned to any interns
		ArrayList<Intern> tempInternList = new ArrayList<>();
		tempInternList = staffList.get(index).getInternList();
		
		for(int i =0; i <tempInternList.size(); i++) {
			tempInternList.get(i).deleteSupervisor();
		}
			
			
		//deletes the staff from every client the staff has
		ArrayList<Client> tempClientList = new ArrayList<>();
		tempClientList = staffList.get(index).getClientList();
		
		for(int i=0; i<tempClientList.size(); i++) {
			deleteClientsCounselor(tempClientList.get(i).name);
		}
			
			staffList.remove(index);
		
		
	}
	
	public static void removeClientConnectedToStaff(String staffName,String clientName) {
		int staffIndex = returnStaffIndex(staffName);
		if(staffIndex==-1) {return;	}
		int clientIndex = returnClientIndex(clientName);
		if(clientIndex==-1) {return;}
		staffList.get(staffIndex).deleteClient(clientList.get(clientIndex));
		deleteClientsCounselor(clientName);
	}
	
	
	//adds client to staffs clientList
	public static void addClientToStaff(String staffName,String clientName) {
		int staffIndex = returnStaffIndex(staffName);
		if(staffIndex==-1) {return;}
		int clientIndex = returnClientIndex(clientName);
		if(clientIndex==-1) {return;}
		staffList.get(staffIndex).addClient(clientList.get(clientIndex));
		clientList.get(clientIndex).changeCounselor(staffList.get(staffIndex));
	}
	
	public static void addInternToStaff(String staffName,String internName) {
		int staffIndex = returnStaffIndex(staffName);
		if(staffIndex==-1) {return;}
		int internIndex = returnInternIndex(internName);
		if(internIndex==-1) {return;}
		staffList.get(staffIndex).addIntern(internList.get(internIndex));
		
	}
	
	//deletes the staff as the Interns supervisor and removes the intern from staffs internList
	public static void deleteStaffsIntern(String staffName,String internName) {
		int staffIndex = returnStaffIndex(staffName);
		if(staffIndex==-1) {return;}
		int internIndex = returnInternIndex(internName);
		if(internIndex==-1) {return;}
		staffList.get(staffIndex).deleteIntern(internList.get(internIndex));
		
		internList.get(internIndex).deleteSupervisor();
	}
	
	public static void changeStaffSalary(String staffName, int salaryChange) {
		int staffIndex = returnStaffIndex(staffName);
		if(staffIndex==-1) {return;}
		staffList.get(staffIndex).changeSalary(salaryChange);
	}
	

	public static void printStaffList() {
		for(int i=0; i<staffList.size(); i++) {
			System.out.println("name: " + staffList.get(i).name);
			System.out.println("work type: " + staffList.get(i).workType);
			System.out.println("Monthly Salary: " + staffList.get(i).Salary+ "\n");
		}
	}
		
	public static void printStaffClients(String staffName) {
		int staffIndex = returnStaffIndex(staffName);
		if(staffIndex==-1) {
			System.out.println("Staff Does not exist.");
			return;}
		
		ArrayList<Client> tempClientList = new ArrayList<>();
		tempClientList = staffList.get(staffIndex).getClientList();
		for(int i=0; i<tempClientList.size(); i++) {
			System.out.println(staffName + "'s client: " + tempClientList.get(i).name);
		}
	}
	
	public static void printStaffInterns(String staffName) {
		int staffIndex = returnStaffIndex(staffName);
		if(staffIndex==-1) {
			System.out.println("Staff Does not exist.");
			return;}
		ArrayList<Intern> tempInternList = new ArrayList<>();
		tempInternList = staffList.get(staffIndex).getInternList();
		System.out.println(staffName + "'s interns: ");
		for(int i=0; i<tempInternList.size(); i++) {
			System.out.println(tempInternList.get(i).name);
		}
		
	}
	
	public static Staff returnStaffMember(String name) {
		int index = returnStaffIndex(name);
		
		if(index != -1) {
			return staffList.get(index);
		}
		return null;
	}	
	
	
	public static int returnStaffIndex(String name) {
		for(int i=0; i<staffList.size(); i++) {
			if(staffList.get(i).name.equals(name)) {
				return i;
			}
		}
		System.out.println("Staff does not exist.  Please enter a valid staff member");
		return -1;
	}
	
	//INTERN FUNCTIONS
	
	public static void addIntern(String internName, String StaffName) {
		
		int staffIndex = returnStaffIndex(StaffName);
		if(staffIndex == -1) {
			System.out.println("Staff does not exist.  Please enter valid staff");
			return;
			}		
		int internIndex = returnInternIndex(internName);
		if(internIndex != -1) {
			System.out.println("Intern already exists");
			return;
		}
		
		Intern newIntern = new Intern(internName, returnStaffMember(StaffName));

		internList.add(newIntern);
		
		addInternToStaff(StaffName,internName);
		
	}
	
	public static void addIntern(String internName, String StaffName, int modification) {
		
		int staffIndex = returnStaffIndex(StaffName);
		if(staffIndex == -1) {return;}	
		
		Intern newIntern = new Intern(internName, returnStaffMember(StaffName), modification);

		internList.add(newIntern);
		addInternToStaff(StaffName,internName);
		
	}
	
	/*
	 * Delete intern from Staff and Clients that they are assigned to
	 */
	public static void deleteIntern(String internName) {
				
		//deletes the intern from every client the intern has
		int internIndex = returnInternIndex(internName);
		ArrayList<Client> tempClientList = new ArrayList<>();
		tempClientList = internList.get(internIndex).getClientList();
		
		for(int i=0; i<tempClientList.size(); i++) {
			deleteClientsCounselor(tempClientList.get(i).name);
		}
		//deletes the intern from the staffs Intern List
		deleteStaffsIntern(internList.get(internIndex).returnSupervisor().name, internName);
		
		internList.remove(internIndex);

	}
	
	
	public static void addClientToIntern(String internName, String clientName) {
		int internIndex = returnInternIndex(internName);
		if(internIndex==-1) {
			System.out.println("Intern does not exist");
			return;
		}
		int clientIndex = returnClientIndex(clientName);
		if(clientIndex==-1) {
			System.out.println("Client does not exist");
			return;
		}
		internList.get(internIndex).addClient(clientList.get(clientIndex));
		clientList.get(clientIndex).changeCounselor(internList.get(internIndex));
	}
	
	public static void removeInternsClient(String internName, String clientName) {
		int internIndex = returnInternIndex(internName);
		if(internIndex == -1) {
			System.out.println("Intern does not exist");
			return;}	
		int clientIndex = returnClientIndex(clientName);
		if(clientIndex == -1) {
			System.out.println("Client does not exist");
			return;}	
		internList.get(internIndex).deleteClient(clientList.get(clientIndex));
		deleteClientsCounselor(clientName);
	}
	
	public static void InternChangeSupervisor(String staffName, String internName) {
		System.out.println("is it");
		int internIndex = returnInternIndex(internName);
		if(internIndex == -1) {return;}	
		int staffIndex = returnStaffIndex(staffName);
		if(staffIndex == -1) {return;}	
		System.out.println("checking");
		internList.get(internIndex).changeSupervisor(staffList.get(staffIndex));
		staffList.get(staffIndex).addIntern(internList.get(internIndex));
		System.out.println("check");
		
	}
	
	//removes supervisor name but does not delete Supervisor from database
	public static void InternDeleteSupervisor(String internName) {
		int internIndex = returnInternIndex(internName);
		if(internIndex == -1) {
			System.out.println("Intern does not exist");
			return;}	
		
		internList.get(internIndex).returnSupervisor().deleteIntern(internList.get(internIndex));
		internList.get(internIndex).deleteSupervisor();
	}
	
	public static void receivePaymentFromIntern(String internName, int month){
		int internIndex = returnInternIndex(internName);
		if(internIndex == -1) {
			System.out.println("Intern does not exist");
			return;
			}	
		
		if(!internList.get(internIndex).checkCompletedPaymentMonth(month)) {
			bank.receivePayment(internList.get(internIndex), month);
			System.out.println("Payment received from Intern: " + internList.get(internIndex).name );
		}
		else {
			System.out.println("Intern " +internList.get(internIndex).name +" has already made a payment for this month.  Please select a correct month");
		}
	}
	
	public static void voidInternMonthlyPayment(String internName,int month) {
		int internIndex = returnInternIndex(internName);
		if(internIndex == -1) {
			System.out.println("Intern does not exist");
			return;}	
		
		if(internList.get(internIndex).checkCompletedPaymentMonth(month) == true) {
			bank.voidInternPayment(internList.get(internIndex), month);
			System.out.println("Payment for client " + internList.get(internIndex).name + " on month: " + month + " voided.");
		}
		else {
			System.out.println("Payment was never made for month: "+ month +  ". Please select a correct month");
		}
	}	
	
	public static void changeInternFee(String internName, int feeChange) {
		int internIndex = returnInternIndex(internName);
		if(internIndex == -1) {
			System.out.println("Intern does not exist");
			return;}	
		internList.get(internIndex).changePaymentAmount(feeChange);
	}
	
	
	public static void printInternList() {
		for(int i=0; i<internList.size(); i++) {
			System.out.println("Name: " + internList.get(i).name);
			if(internList.get(i).supervisor == null) {
				System.out.println("Supervisor: Not Assigned");
			}
			else {
				System.out.println("Supervisor: " + internList.get(i).supervisor.name);
			}
			
			System.out.println("Monthly Intern Fee: " + internList.get(i).paymentAmount + "\n");
		}
	}
	
	public static void printInternClients(String internName) {
		int internIndex = returnInternIndex(internName);
		if(internIndex == -1) {return;}	
		ArrayList<Client> clientList = new ArrayList<>();
		clientList = internList.get(internIndex).getClientList();
		System.out.println(internName + "'s Clients:");
		for(int i=0; i<clientList.size(); i++) {
			System.out.println(clientList.get(i).name);
		}
	}
	
	public static int returnInternIndex(String name) {
		for(int i=0; i<internList.size(); i++) {
			if(internList.get(i).name.equals(name)) {
				return i;
			}
		}
		return -1;
	}
	

	//CLIENT FUNCTIONS
	
	public static void addClientAssignedToStaff(String clientName, String staffName, int expense) {
		
		int clientIndex = returnClientIndex(clientName);
		if(clientIndex != -1) {
			System.out.println("Client already exists");
			return;}	
		int staffIndex = returnStaffIndex(staffName);
		if(staffIndex == -1) {
			System.out.println("Staff does not exist");
			return;}	

		Client newClient = new Client(clientName, expense, staffList.get(staffIndex));
		clientList.add(newClient);
		addClientToStaff(staffName, clientName);
			
	}
	
	public static void addAndAssignClientToIntern( String clientName, String internName, int expense) {
		int clientIndex = returnClientIndex(clientName);
		if(clientIndex != -1) {
			System.out.println("Client already exists");
			return;}	
		int internIndex = returnInternIndex(internName);
		if(internIndex == -1) {
			System.out.println("intern does not exist");
			return;}	
		Client newClient = new Client(clientName, expense, internList.get(internIndex));
		clientList.add(newClient);
		addClientToIntern(internName, clientName);
		
	}
	
	public static void deleteClient(String clientName) {
		int clientIndex = returnClientIndex(clientName);
		if(clientIndex == -1) {
			System.out.println("Client does not exist");
			return;}	
		
		
		//need to delete client from staff or interns as well as the arraylist
		String counselorName = clientList.get(clientIndex).getClientsCounselor();
		int index;
		//if the counselor is an intern
		if(clientList.get(clientIndex).getCounselorType()) {
			//find the intern and call delete interns client
			index = returnInternIndex(counselorName);
			
			if(index == -1) {
				System.out.println("Counselor not in List.  Please enter a valid counselor");
				return;
			}
			internList.get(index).deleteClient(clientList.get(clientIndex));
		}
		else {
			//find the staff and call delete staffs client
			index = returnStaffIndex(counselorName);
			if(index == -1) {
				System.out.println("Counselor not in List.  Please enter a valid counselor");
				return;
			}
			staffList.get(index).deleteClient(clientList.get(clientIndex));
		}
				
		clientList.remove(returnClientIndex(clientList.get(clientIndex).name));
		
	}
	
	public static void deleteClientsCounselor(String clientName) {
		int clientIndex = returnClientIndex(clientName);
		if(clientIndex == -1) {return;}	
		clientList.get(clientIndex).deleteCounselor();
	}
	
	public static void clientChangeInternCounselor(String clientName, String internName) {
		addClientToIntern(internName, clientName);

	}
	
	public static void clientChangeStaffCounselor(String clientName, String staffName) {
		int clientIndex = returnClientIndex(clientName);
		if(clientIndex == -1) {return;}	
		int staffIndex = returnStaffIndex(staffName);
		if(staffIndex == -1) {return;}	
		
		
		clientList.get(clientIndex).changeCounselor(staffList.get(staffIndex));
		staffList.get(staffIndex).addClient(clientList.get(clientIndex));
	}
	
	
	public static void clientMakePayment(String clientName,int month, int week){
		int clientIndex = returnClientIndex(clientName);
		if(clientIndex == -1) {return;}	
		
		if(!clientList.get(clientIndex).checkCompletedPaymentWeek(month, week)) {
			bank.receivePayment(clientList.get(clientIndex), month, week);
			System.out.println("Client " + clientList.get(clientIndex).name + " payment received");
		}
		else {
			System.out.println("Client " +clientList.get(clientIndex).name +" has already made a payment for month: " + month + ", week: " + week +  ". Please select a correct month and week");
		}
	}
	
	public static void voidClientPayment(String clientName, int month, int week) {
		int clientIndex = returnClientIndex(clientName);
		if(clientIndex == -1) {
			System.out.println("Client Does not exist");
			return;}	
		
		if(clientList.get(clientIndex).checkCompletedPaymentWeek(month, week) == true) {
			bank.voidClientPayment(clientList.get(clientIndex), month, week);
			System.out.println("Payment for client " + clientList.get(clientIndex).name + " on month: " + month + " week: " + week + " voided.");
		}
		else {
			System.out.println("Payment was never made for month: "+ month + ", week: " + week + ". Please select a correct month and week");
		}
	}
	
	public static void clientChangeFee(String clientName, int chengeFeeAmount) {
		int clientIndex = returnClientIndex(clientName);
		if(clientIndex == -1) {
			System.out.println("Client does not exist");
			return;}	
		
		clientList.get(clientIndex).changePaymentFee(chengeFeeAmount);
	}
	
	public static void printClientList() {
		for(int i=0; i<clientList.size(); i++) {
			System.out.println("Name: " + clientList.get(i).name);
			System.out.println("Fee Amount: " + clientList.get(i).paymentAmount);
			System.out.println("Counselor Name: " + clientList.get(i).CounselorName + "\n");
		}
	}
	
	public static int returnClientIndex(String name) {
		for(int i=0; i<clientList.size(); i++) {
			if(clientList.get(i).name.equals(name)) {
				return i;
			}
		}
		return -1;
	}
	
	public static void printClientCounselorName(String clientName) {
		int clientIndex = returnClientIndex(clientName);
		if(clientIndex == -1) {
			System.out.println("Client does not exits");
			return;}	
		
		if(clientList.get(clientIndex).CounselorName == null) {
			System.out.println("Client " + clientList.get(clientIndex).name + " has not been assigned a counselor at this time");
		}
		else {
			System.out.println("Client " + clientList.get(clientIndex).name + " counselor:  " + clientList.get(clientIndex).CounselorName);
		}
		
	}
	
	//BANK FUNCTIONS
	
	public static int getBankTotal() {
		return bank.returnBankBalance();
	}
	
	public static void printBankTotal() {
		System.out.println("Bank Total: " + getBankTotal());
	}


}
