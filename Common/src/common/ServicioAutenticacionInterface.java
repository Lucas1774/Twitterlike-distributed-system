package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServicioAutenticacionInterface extends Remote{

	public String Register(String name) throws RemoteException;
		
	public String Login(String name) throws RemoteException;

	public String Logout(String name) throws RemoteException;
}
