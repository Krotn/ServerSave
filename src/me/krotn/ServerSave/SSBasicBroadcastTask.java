package me.krotn.ServerSave;

import org.bukkit.ChatColor;
import org.bukkit.Server;

public class SSBasicBroadcastTask implements Runnable{
	String text;
	String logText = null;
	Server server;
	SSLogManager logMan = null;
	
	public SSBasicBroadcastTask(String broadcastText,Server server){
		this.text = broadcastText;
		this.server = server;
		this.logText = null;
	}
	
	public SSBasicBroadcastTask(String broadcastText,String logText,SSLogManager logMan,Server server){
		this.text = broadcastText;
		this.server = server;
		this.logText = logText;
		this.logMan = logMan;
	}
	
	private boolean isChatColor(String toCheck){
	    for(ChatColor c:ChatColor.values()){
	        if(c.toString().equalsIgnoreCase(toCheck)){
	            return true;
	        }
	    }
	    return false;
	}
	
	public void run(){
		try{
			if(!(this.text.isEmpty())&&!(isChatColor(this.text))){
				this.server.broadcastMessage(this.text);
			}
			if(!(this.logText==null)){
				if(!this.logText.isEmpty()){
					this.logMan.info(this.logText);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
