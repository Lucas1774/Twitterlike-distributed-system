package common;

import java.rmi.*;

public interface ServicioGestorInterface extends Remote {
	
	public ServicioDatosInterface getdatabase() throws RemoteException;
	
	public String Follow (String follower, String followed) throws RemoteException;
	
	public String Unfollow (String follower, String followed) throws RemoteException;
	
	public String Twit (String message, String user) throws RemoteException;
	
	public String Ban (String user) throws RemoteException;
	
	public String UnBan (String user) throws RemoteException;
	
	public void clearQueuedTwits(String user) throws RemoteException;
	
	public void callbackRegister(CallbackUsuarioInterface object, String user) throws RemoteException;

	public void deleteCallbackRegister (CallbackUsuarioInterface object, String user) throws RemoteException;
	
	public void notifyLoggedFollowers(Trino twit) throws RemoteException;
} 
