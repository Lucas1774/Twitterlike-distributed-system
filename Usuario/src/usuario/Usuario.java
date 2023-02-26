package usuario;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import common.*;

public class Usuario {
	public static void main (String args[]) {
		String URLRegistryi = "rmi://192.168.1.134:1/servidor/gestor";
		String URLRegistryj = "rmi://192.168.1.134:1/servidor/autenticador";
		
		try {
			
			ServicioAutenticacionInterface autenticador = (ServicioAutenticacionInterface)Naming.lookup(URLRegistryj);
			ServicioGestorInterface gestor = (ServicioGestorInterface)Naming.lookup(URLRegistryi);

			Util.StartRegistry(1);
			
			System.out.println("Cliente.");
			int optiona = 0;
			while (optiona != 3) {
				optiona = UI.askforInt("Introduzca 1, 2 o 3.\r\n"
						+ "1.- Registrar nuevo usuario.\r\n"
						+ "2.- Hacer login.\r\n"
						+ "3.- Salir.");
				switch (optiona) {
					case 1:
						System.out.println("Registrar nuevo usuario.");
						System.out.println(autenticador.Register(UI.askforString("Introduzca nombre de usuario")));
						break;
					case 2:
						System.out.println("Hacer login");
						String user = UI.askforString("Introduzca nombre de usuario");
						String aux = autenticador.Login(user);
						System.out.println(aux);
						if (aux.equals (("Bienvenido " + user))) {
							CallbackUsuarioInterface callbackobject = new CallbackUsuarioImpl();
							String URLRegistry = "rmi://localhost:"+ "1" + "/callback/" + user;
							Naming.rebind(URLRegistry, callbackobject);
							Util.RegistryList("rmi://localhost:"+ "1");
							gestor.callbackRegister (callbackobject, user);
							
							if (gestor.getdatabase().getClient(user).getQueuedTwits().isEmpty()) {
								System.out.println("Niniguno de tus seguidos ha publicado mientras no estabas");
							} else {
								System.out.println("Trinos publicados mientras no estabas: ");
								for (int i = 0; i < gestor.getdatabase().getClient(user).getQueuedTwits().size(); i++) {
									if (!gestor.getdatabase().getClient(gestor.getdatabase().getClient(user).getQueuedTwits().get(i).GetNickPropietario()).isbanned()) {
										callbackobject.notifyMe(gestor.getdatabase().getClient(user).getQueuedTwits().get(i));
									}
								}
								gestor.clearQueuedTwits(user);
							}
							int optionb = 0;
							while (optionb != 7) {
								optionb = UI.askforInt("Introduzca un numero del 1 al 7.\r\n"
										+ "1.- Informacion del Usuario.\r\n"
										+ "2.- Enviar Trino.\r\n"
										+ "3.- Listar Usuarios del Sistema.\r\n"
										+ "4.- Seguir a\r\n"
										+ "5.- Dejar de seguir a.\r\n"
										+ "7.- Salir \"Logout\".");
								switch (optionb) {
								case 1:
									System.out.println("Informacion del Usuario.");
									Util.RegistryList("rmi://localhost:"+ "1" + "/callback/");
									break;
								case 2:
									System.out.println("Enviar trino.");
									String twitstring = UI.askforSentence("Introduce el mensaje del trino.");
									System.out.println(gestor.Twit(twitstring, user));
									Trino twit = new Trino (user, twitstring);
									gestor.notifyLoggedFollowers(twit);
									break;
								case 3:	
									System.out.println("Listar usuarios del sistema.");
									for (int i = 0; i<gestor.getdatabase().getClients().size(); i++) {
										System.out.println(gestor.getdatabase().getClients().get(i).getName());
									}
									break;
								case 4:	
									System.out.println("Seguir a.");
									System.out.println(gestor.Follow(user,  UI.askforString("Introduce el nombre del usuario al que quieres seguir.")));
									break;
								case 5:	
									System.out.println("Dejar de seguir a.");
									System.out.println(gestor.Unfollow(user,  UI.askforString("Introduce el nombre del usuario al que quieres dejar de seguir.")));
									break;
								case 7:	
									System.out.println("Salir \"logout\".");
									System.out.println(autenticador.Logout(user));
									gestor.deleteCallbackRegister(callbackobject, user);
									break;
								}
							}
						}	
						break;
					case 3:
						System.out.println("Cerrando programa.");
						break;
				}
			}
		}catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}	
	}
}
