package common;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Util {
	public static void StartRegistry (int portID) throws RemoteException {
		try {
			Registry registry = LocateRegistry.getRegistry(portID);
			registry.list();
		} catch(RemoteException exception) {
			System.out.println("El registro RMI no se puede localizar en " + portID);
			LocateRegistry.createRegistry(portID);
			System.out.println("Registro creado en puerto " + portID);
		}
	}
	
	public static void RegistryList (String URLRegistry) throws RemoteException, MalformedURLException{
		System.out.println("Registro " + URLRegistry + " contiene: ");
		String [] names = Naming.list(URLRegistry);
		for (int i=0; i<names.length; i++) {
			System.out.println(names[i]);
		}
	}
}
