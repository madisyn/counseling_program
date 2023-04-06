import java.util.ArrayList;

/*
 * Intern class will handle Interns name, their clients, if they paid their monthly dues,
 * and 
 */
public class Intern {
	
	String name;
	int paymentAmount =150;
	boolean paid[] = new boolean[12];
	Staff supervisor;
	private ArrayList<Client> clientList;
	
	
	public Intern(String name, Staff staffName) {
		this.name = name;
		this.supervisor = staffName;
		clientList = new ArrayList<Client>();
	}
	
	public Intern(String name, Staff staffName,int modifiedExpense) {
		this.name = name;
		this.paymentAmount = modifiedExpense;
		this.supervisor = staffName;
		clientList = new ArrayList<Client>();
	}
	
	public ArrayList<Client> getClientList(){
		return clientList;
	}
	
	public void payMonthlyExpense(int month) {
		//try/catch if the number is a month
		paid[month] = true;
	}
	
	public void voidMonthlyExpense(int month) {
		paid[month] = false;
	}
	
	public void changeSupervisor(Staff name) {
		this.supervisor = name;
	}
	
	public boolean checkPaymentMonth(int month) {
		if(this.paid[month]==true) {
			return true;
		}
		return false;
	}
	
	public void addClient(Client name) {
		if(clientList.contains(name)) {
			System.out.println("Client is already assigned to " + this.name);
		}
		else {
			clientList.add(name);	
		}
	
	}

	public void deleteClient(Client name) {
		if(clientList.contains(name)) {
			clientList.remove(name);
		}
		else {
			System.out.println("Client is not assigned to " + this.name);
		}
	}
	
	public void changePaymentAmount(int num) {
		this.paymentAmount= num;
	}
}
