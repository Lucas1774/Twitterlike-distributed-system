package servidor;

import java.rmi.RemoteException;

import java.rmi.server.UnicastRemoteObject;

import common.Cliente;
import common.ServicioAutenticacionInterface;
import common.ServicioDatosInterface;

public class ServicioAutenticacionImpl extends UnicastRemoteObject implements ServicioAutenticacionInterface{
	private static final long serialVersionUID = 1L;
	private ServicioDatosInterface h;
	
	protected ServicioAutenticacionImpl(ServicioDatosInterface h) throws RemoteException {
		super();
		this.h = h;
	}
	
	//@nopre
	public String Register(String name) throws RemoteException {
		String callback;
		if (h.Contains(name)){
			callback = "Ya existe un usuario con ese nombre";
		} else {
			h.add(name);
			callback = "usuario registrado correctamente";
		}
		return callback;
	}
	
	//nopre
	public String Login  (String name) throws RemoteException {
		String callback;
		if (h.Contains(name)){
			for (int i = 0; i < h.getClients().size(); i++) {
				if(h.getClients().get(i).getName().equals(name)) {
					Cliente c = h.getClients().get(i);
					c.setIsonline(true);
					h.set(i, c);
					break;
				}
			}
			callback = ("Bienvenido " + name);
		} else {
			callback = ("No existe usuario con ese nombre.");
		}
		return callback;
	}
	
	//@pre exists
	public String Logout (String name) throws RemoteException {
		String callback;
		for (int i = 0; i < h.getClients().size(); i++) {
			if(h.getClients().get(i).getName().equals(name)) {
				Cliente c = h.getClients().get(i);
				c.setIsonline(false);
				h.set(i, c);
				break;
			}
		}
		callback = ("Hasta pronto " + name);
		return callback;
	}
}
