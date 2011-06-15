package me.krotn.ServerSave;

import org.bukkit.plugin.java.JavaPlugin;

public class ServerSave extends JavaPlugin{
	
	public void onEnable(){
		if(!SSDirectoryManager.directoryExists()){
			SSDirectoryManager.createDirectory();
		}
	}
	
	public void onDisable(){
		
	}
}
