package common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ServicioDatosInterface extends Remote {
	
	public boolean Contains(String name) throws RemoteException;
	
	public ArrayList<Cliente> getClients() throws RemoteException;
	
	public Cliente getClient(String name) throws RemoteException;
	
	public void add(String name) throws RemoteException;

	public void set(int i, Cliente c) throws RemoteException;

}

