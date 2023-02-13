package servidor;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

import common.CallbackUsuarioInterface;
import common.Cliente;
import common.ServicioDatosInterface;
import common.ServicioGestorInterface;
import common.Trino;

public class ServicioGestorImpl extends UnicastRemoteObject implements ServicioGestorInterface {
	private static final long serialVersionUID = 1L;

	private HashMap<String, CallbackUsuarioInterface> list;
	
	private ServicioDatosInterface h;
	
	protected ServicioGestorImpl(ServicioDatosInterface h) throws RemoteException {
		super();
		this.h = h;
		this.list = new HashMap<String, CallbackUsuarioInterface>();
	}
	
	public ServicioDatosInterface getdatabase() {
		return this.h;
	}
	
	//@nopre
	public String Follow(String follower, String followed) throws RemoteException {
		String callback;
		if (!h.Contains(followed) || h.getClient(follower).follows(followed)){
			callback = "No existe ese usuario o ya esta en tu lista de seguidos.";
		} else {
			for (int i = 0; i < h.getClients().size(); i++) {
				if(h.getClients().get(i).getName().equals(follower)) {
					Cliente c = h.getClients().get(i);
					c.addContact(followed);
					h.set(i, c);
					break;
				}
			}
			callback = "Ahora sigues a " + followed;
		}
		return callback;
	}
	
	//@nopre
	public String Unfollow(String follower, String followed) throws RemoteException {
		String callback;
		if (!h.getClient(follower).follows(followed)){
			callback = "No sigues a ese usuario.";
		} else {
			for (int i = 0; i < h.getClients().size(); i++) {
				if(h.getClients().get(i).getName().equals(follower)) {
					Cliente c = h.getClients().get(i);
					c.deleteContact(followed);
					h.set(i, c);
					break;
				}
			}
			callback = "Ya no sigues a " + followed;
		}
		return callback;
	}
	
	//@nopre
	public String Twit(String message, String user) throws RemoteException {
		Trino t = new Trino (user, message);
		for (int i = 0; i < h.getClients().size(); i++) {
			if(h.getClients().get(i).getName().equals(user)) {
				Cliente c = h.getClients().get(i);
				c.addTwit(t);
				h.set(i, c);
				break;
			}
		}
		String callback = "Trino enviado con exito";
		return callback;
	}
	
	//@nopre
	public String Ban(String user) throws RemoteException {
		String callback;
		if (!h.Contains(user)){
			System.out.println("Usuario " + user + " no encontrado.");
			callback = "No existe un usuario con ese nombre";
		} else {
			for (int i = 0; i < h.getClients().size(); i++) {
				if(h.getClients().get(i).getName().equals(user)) {
					Cliente c = h.getClients().get(i);
					c.setIsbanned(true);
					h.set(i, c);
					break;
				}
			}
			callback = "usuario baneado correctamente";
		}
		return callback;
	}

	//@nopre
	public String UnBan(String user) throws RemoteException {
		String callback;
		if (!h.Contains(user)){
			callback = "No existe un usuario con ese nombre";
		} else {
			for (int i = 0; i < h.getClients().size(); i++) {
				if(h.getClients().get(i).getName().equals(user)) {
					Cliente c = h.getClients().get(i);
					c.setIsbanned(false);
					h.set(i, c);
					break;
				}
			}
			callback = "usuario desbloqueado correctamente";
		}
		return callback;
	}
	
	public void clearQueuedTwits(String user) throws RemoteException {
		for (int i = 0; i < h.getClients().size(); i++) {
			if(h.getClients().get(i).getName().equals(user)) {
				Cliente c = h.getClients().get(i);
				ArrayList <Trino> aux = new ArrayList<Trino>();
				for (int j = 0; j < c.getQueuedTwits().size(); j++) {
					if (h.getClient(c.getQueuedTwits().get(j).GetNickPropietario()).isbanned()) {
						aux.add(h.getClients().get(i).getQueuedTwits().get(j));
					}
					c.setQueuedTwits(aux);
				}
				h.set(i, c);
				break;
			}
		}
	}
	
	public void callbackRegister(CallbackUsuarioInterface object, String user) throws RemoteException{
		if (!this.list.containsKey(user)){
			this.list.put (user, object);
		}
	}

	public synchronized void deleteCallbackRegister(CallbackUsuarioInterface object, String user) throws RemoteException {
		this.list.remove(user);
	}
	
	public synchronized void notifyLoggedFollowers(Trino twit) throws RemoteException {
		for (int i = 0; i < h.getClients().size(); i++) {
			if (h.getClients().get(i).follows(twit.GetNickPropietario())){
				if(h.getClients().get(i).isonline() && !h.getClient(twit.GetNickPropietario()).isbanned()) {
					list.get(h.getClients().get(i).getName()).notifyMe(twit);
				} else {
					Cliente c = h.getClients().get(i);
					c.getQueuedTwits().add(twit);
					h.set(i, c);
				}
			}
		}
	}
}
