package me.krotn.ServerSave;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

public class ServerSave extends JavaPlugin{
	private SSLogManager logMan = new SSLogManager(Logger.getLogger("Minecraft"));
	private SSPropertiesManager propMan = new SSPropertiesManager();
	private SSTaskManager taskMan = null;
	
	public void onEnable(){
		if(!SSDirectoryManager.directoryExists()){
			SSDirectoryManager.createDirectory();
		}
		taskMan = new SSTaskManager(this,this.getServer().getScheduler());;
		logMan.info("Save interval is: "+propMan.getProperty("saveFrequency")+" seconds.");
		long time = new Long(propMan.getProperty("saveFrequency")).longValue();
		taskMan.scheduleRepeatingTask(new ServerSaveTask(this,this.getServer()), time, time);
		logMan.info("ServerSave enabled!");
	}
	
	public void onDisable(){
		taskMan.removeAllTasks();
		logMan.info("ServerSave disabled!");
	}
	
	public SSPropertiesManager getPropertiesManager(){
		return propMan;
	}
	
	public SSLogManager getLogManager(){
		return logMan;
	}
}
