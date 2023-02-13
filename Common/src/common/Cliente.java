package common;

import java.io.Serializable;
import java.util.ArrayList;

public class Cliente implements Serializable{
	private static final long serialVersionUID = 1L;
	private String name;
	private boolean isonline;
	private boolean isbanned;
	private ArrayList<String> contacts;
	private ArrayList<Trino> twits;
	private ArrayList<Trino> queuedtwits;
	
	public Cliente(String name) {
		this.name = name;
		this.isonline = false;
		this.isbanned = false;
		this.contacts = new ArrayList<String>();
		this.twits = new ArrayList<Trino>();
		this.queuedtwits = new ArrayList<Trino>();
	}
	
	public String getName() {
		return this.name;
	}

	public boolean isonline() {
		return isonline;
	}

	public void setIsonline(boolean b) {
		this.isonline = b;
	}

	public boolean isbanned() {
		return isbanned;
	}

	public void setIsbanned(boolean isbanned) {
		this.isbanned = isbanned;
	}

	public ArrayList<String> getContacts() {
		return contacts;
	}

	public void addContact(String contact) {
		this.contacts.add(contact);
	}
	
	//@pre exists
	public void deleteContact(String contact) {
		for (int i = 0; i< this.contacts.size(); i++) {
			if (this.contacts.get(i).equals(contact)) {
				this.contacts.remove(i);
				break;
			}
		}
	}
	
	public ArrayList<Trino> getTwits() {
		return this.twits;
	}

	public void addTwit(Trino twit) {
		this.twits.add(twit);
	}
	
	public boolean follows(String name) {
		boolean b = false;
		if (this.contacts.contains(name)) {
			b = true;
		}
		return b;
	}

	public ArrayList<Trino> getQueuedTwits() {
		return this.queuedtwits;
	}
	
	public void setQueuedTwits(ArrayList<Trino> a) {
		this.queuedtwits = a;
	}
}
