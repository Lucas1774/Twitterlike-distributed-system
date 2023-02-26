package servidor;


import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import common.*;

public class Servidor {
	public static void main (String[] args){
		
		String URLDBRegistry = "rmi://localhost:1/basededatos";
		ServicioDatosInterface h;
		try {
			h = (ServicioDatosInterface)Naming.lookup(URLDBRegistry);
		
			String URLAutenticadorRegistry, URLGestorRegistry;
			
			Util.StartRegistry (1);
			ServicioAutenticacionImpl exportedobject1 = new ServicioAutenticacionImpl(h);
			ServicioGestorImpl exportedobject2 = new ServicioGestorImpl(h);
			URLGestorRegistry = "rmi://localhost:"+ "1" + "/servidor/gestor";
			URLAutenticadorRegistry = "rmi://localhost:"+ "1" + "/servidor/autenticador";
			Naming.rebind(URLGestorRegistry, exportedobject2);
			Naming.rebind(URLAutenticadorRegistry, exportedobject1);
			System.out.println ("Servidor registrado.");
			Util.RegistryList("rmi://localhost:"+ "1" + "/servidor");
			System.out.println("Servidor preparado.");
			
			
			System.out.println("Servidor.");
			int option = 0;
			while (option != 6) {
				option = UI.askforInt("Introduzca un numero del 1 al 6.\r\n"
						+ "1.- Informacion del Servidor.\r\n"
						+ "2.- Listar Usuarios Registrados.\r\n"
						+ "3.- Listar Usuarios Logueados.\r\n"
						+ "4.- Bloquear (banear) usuario.\r\n"
						+ "5.- Desbloquear usuario.\r\n"
						+ "6.- Salir.");
				switch (option) {
					case 1:
						System.out.println("Informacion del servidor.");
						Util.RegistryList("rmi://localhost:"+ "1" + "/servidor");
						break;
					case 2:
						System.out.println("Listar usuarios registrados.");
						for (int i = 0; i<h.getClients().size(); i++) {
							System.out.println(h.getClients().get(i).getName());
						}
						break;
					case 3:
						System.out.println("Listar usuarios logueados.");
						for (int i = 0; i < h.getClients().size(); i++) {
							if (h.getClients().get(i).isonline()) {
								System.out.println(h.getClients().get(i).getName());
							}
						}
						break;
					case 4:
						System.out.println("Bloquear (banear) usuario.");
						System.out.println(exportedobject2.Ban(UI.askforString("Introduce el nombre del usuario a banear.")));
						break;
					case 5:
						System.out.println("Desbloquear usuario.");
						System.out.println(exportedobject2.UnBan(UI.askforString("Introduce el nombre del usuario a desbloquear.")));						
						break;
					case 6:
						System.out.println("Cerrando servidor.");
						break;
				}
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}
}
