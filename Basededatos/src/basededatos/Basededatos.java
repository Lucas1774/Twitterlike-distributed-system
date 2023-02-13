package basededatos;

import java.rmi.Naming;

import common.*;

public class Basededatos {
	public static void main(String[] args) {
		String URLRegistry;
		try {
			Util.StartRegistry(1);
			ServicioDatosImpl exportedobject = new ServicioDatosImpl();
			URLRegistry = "rmi://localhost:"+ "1" + "/basededatos";
			Naming.rebind(URLRegistry, exportedobject);
			System.out.println ("Servidor registrado.");
			Util.RegistryList(URLRegistry);
			System.out.println("Base de datos preparada.");
		
		
			System.out.println("Base de datos.");
			int option = 0;
			while (option != 3) {
				option = UI.askforInt("Introduzca 1, 2 o 3.\r\n"
						+ "1.- Informacion de la Base de Datos.\r\n"
						+ "2.- Listar Trinos (solo nick del propietario y el timestamp).\r\n"
						+ "3.- Salir.");
				switch (option) {
				case 1:
					System.out.println("Informacion de la base de datos");
					Util.RegistryList(URLRegistry);
					break;
				case 2:
					System.out.println("Lista de trinos");
					for (int i = 0; i < exportedobject.getClients().size(); i++) {
						for (int j = 0; j < exportedobject.getClients().get(i).getTwits().size(); j++) {
							System.out.println( exportedobject.getClients().get(i).getTwits().get(j).toString());
						}
					}
					break;
				case 3:
					System.out.println("Cerrando base de datos");
					break;
				}
			}
		}  catch (Exception exception) {
			System.out.println("Excepción en Basededatos.main:" + exception);
		}
	}
}