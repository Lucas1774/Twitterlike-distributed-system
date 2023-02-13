package common;

import java.io.Serializable;
import java.util.Date;

public class Trino implements Serializable{

	private static final long serialVersionUID = 2L;
	private String trino;
	private String nickPropietario;	//Ojo no pueden haber varios usuarios con el mismo nick
	private long timestamp; //momento en el que se produce el evento (tiempo en el servidor)
	
	public Trino(String nickPropietario, String trino)
	{
		this.trino=trino;
		this.nickPropietario=nickPropietario;
		Date date = new Date();
		this.timestamp=date.getTime();
	}
	public String GetTrino()
	{
		return (trino);
	}
	public String GetNickPropietario()
	{
		return(nickPropietario);
	}
	public long GetTimestamp()
	{
		return (timestamp);
	}
	public String toString(){
		return (getClass().getName()+"@"+trino+"|"+nickPropietario+"|"+timestamp+"|");
	}
}
