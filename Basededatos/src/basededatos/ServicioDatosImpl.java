package basededatos;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import common.Cliente;
import common.ServicioDatosInterface;

public class ServicioDatosImpl extends UnicastRemoteObject implements ServicioDatosInterface {
	private static final long serialVersionUID = 1L;
	private ArrayList<Cliente> clients= new ArrayList<Cliente>();
		
	protected ServicioDatosImpl() throws RemoteException {
		super();
	}
	
	public boolean Contains(String name) throws RemoteException {
		boolean b = false;
		for (int i = 0; i < this.clients.size(); i++) {
			if ( this.clients.get(i).getName().equals(name)) {
				b = true;
			}
		}
		return b;
	}
	
	public ArrayList<Cliente> getClients() throws RemoteException{
		return this.clients;
	}
	
	public void add(String name) throws RemoteException {
		Cliente client = new Cliente (name);
		this.clients.add(client);
	}

// 	@pre exists
	public Cliente getClient(String name) throws RemoteException {
		Cliente c = null;
		for (int i = 0; i < this.clients.size(); i++) {
			if (this.clients.get(i).getName().equals(name)){
				c = this.clients.get(i);
				break;
			}
		}
		return c;
	}

	public void set(int i, Cliente c) throws RemoteException {
		this.clients.set(i, c);
	}
}