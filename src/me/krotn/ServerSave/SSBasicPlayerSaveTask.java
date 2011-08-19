package me.krotn.ServerSave;

import org.bukkit.Server;

public class SSBasicPlayerSaveTask implements Runnable{
	Server server;
	
	public SSBasicPlayerSaveTask(Server server){
		this.server = server;
	}
	
	public void run(){
		try{
			this.server.savePlayers();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
