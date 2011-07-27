package me.krotn.ServerSave;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

public class ServerSave extends JavaPlugin{
	private SSLogManager logMan = new SSLogManager(Logger.getLogger("Minecraft"));
	private SSPropertiesManager propMan = new SSPropertiesManager();
	private SSTaskManager taskMan = null;
	private ServerSaveTask saveTask = null;
	private boolean useWarnings = false;
	
	public void onEnable(){
		if(!SSDirectoryManager.directoryExists()){
			SSDirectoryManager.createDirectory();
		}
		taskMan = new SSTaskManager(this,this.getServer().getScheduler());;
		logMan.info("Save interval is: "+propMan.getProperty("saveFrequency")+" seconds.");
		long saveTime = new Long(propMan.getProperty("saveFrequency")).longValue();
		saveTask = new ServerSaveTask(this,this.getServer());
		useWarnings = new Boolean(propMan.getProperty("warnBeforeSave"));
		if(!useWarnings){
			taskMan.scheduleSyncRepeatingTask(saveTask, saveTime, saveTime);
		}
		else{
			//Add warning code here!
		}
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
