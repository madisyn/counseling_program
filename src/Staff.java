import java.util.ArrayList;
public class Staff extends Employee{
	private ArrayList<Intern> internList;
	private ArrayList<Client> clientList;
	
	public Staff(String name, int Salary) {
		super(name,"Staff", Salary);
		internList = new ArrayList<Intern>();
		clientList = new ArrayList<Client>();
	}
	
	public ArrayList<Intern> getInternList(){
		return internList;
	}
	
	public ArrayList<Client> getClientList(){
		return clientList;
	}
	
	
	public void addIntern(Intern name) {
		if(internList.contains(name)) {
			System.out.println("Intern is already assigned to " + this.name);
		}
		else {
			internList.add(name);	
		}
			
	}
	
	public void deleteIntern(Intern name) {
		if(internList.contains(name)) {
			internList.remove(name);
		}
		else {
			System.out.println("Intern is not assigned to " + this.name);
		}
				
	}
	
	
	public void addClient(Client client) {
		if(clientList.contains(client)) {
			System.out.println("Client is already assigned to " + this.name);
		}
		else {
			clientList.add(client);	
		}
	
	}

	public void deleteClient(Client client) {
		if(clientList.contains(client)) {
			clientList.remove(client);
		}
		else {
			System.out.println("Client is not assigned to " + this.name);
		}

		
	}
	
	public void changeSalary(int newSalary) {
		this.Salary = newSalary;
		this.MonthlySalary = newSalary/12;
	}
	
}
