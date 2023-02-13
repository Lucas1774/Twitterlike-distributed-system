package usuario;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import common.*;
public class CallbackUsuarioImpl extends UnicastRemoteObject implements CallbackUsuarioInterface {
	private static final long serialVersionUID = 1L;

	protected CallbackUsuarioImpl() throws RemoteException {
		super();
	}

	public void notifyMe(Trino twit) throws RemoteException {
		System.out.println("> " + twit.GetNickPropietario() + "# " + twit.GetTrino());
	}
}
