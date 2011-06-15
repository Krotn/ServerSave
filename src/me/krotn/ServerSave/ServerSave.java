package me.krotn.ServerSave;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

public class ServerSave extends JavaPlugin{
	private SSLogManager logMan = new SSLogManager(Logger.getLogger("Minecraft"));
	public void onEnable(){
		if(!SSDirectoryManager.directoryExists()){
			SSDirectoryManager.createDirectory();
		}
		logMan.info("ServerSave enabled!");
	}
	
	public void onDisable(){
		
	}
}
