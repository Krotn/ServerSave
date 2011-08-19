package me.krotn.ServerSave;

import org.bukkit.Server;

public class SSBasicBroadcastTask implements Runnable{
	String text;
	String logText = null;
	Server server;
	
	public SSBasicBroadcastTask(String broadcastText,Server server){
		this.text = broadcastText;
		this.server = server;
		this.logText = null;
	}
	
	public SSBasicBroadcastTask(String broadcastText,String logText,Server server){
		this.text = broadcastText;
		this.server = server;
		this.logText = logText;
	}
	
	public void run(){
		try{
			this.server.broadcastMessage(this.text);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
