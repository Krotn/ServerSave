package me.krotn.ServerSave;

import org.bukkit.Server;

public class SSBasicBroadcastTask implements Runnable{
	String text;
	Server server;
	
	public SSBasicBroadcastTask(String broadcastText,Server server){
		this.text = broadcastText;
		this.server = server;
	}
	
	public void run(){
		this.server.broadcastMessage(this.text);
	}
}
