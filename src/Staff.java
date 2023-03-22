import java.util.ArrayList;
public class Staff {
	private ArrayList<Intern> internList;
	private ArrayList<Client> clientList;
	
	public Staff() {
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
		internList.add(name);		
	}
	
	public void deleteIntern(Intern name) {
		for(int i=0; i<internList.size(); i++) {
			if(internList.get(i)== name) {
				internList.remove(name);
			}
		}
				
	}
	
	
	public void addClient(Client name) {
			clientList.add(name);
	
	}

	public void deleteClient(Client name) {
		for(int i=0; i<internList.size(); i++) {
			if(clientList.get(i)== name) {
				clientList.remove(name);
			}
		}
	}
	
}
