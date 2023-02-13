package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CallbackUsuarioInterface extends Remote {
	
	public void notifyMe(Trino twit)throws RemoteException;
	
	

}