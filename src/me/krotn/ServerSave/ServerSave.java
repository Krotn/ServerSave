package me.krotn.ServerSave;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

public class ServerSave extends JavaPlugin{
    private SSLogManager logMan;
    private SSDirectoryManager dirMan;
    private SSPropertiesManager propMan;
    private SSTaskManager taskMan;
    private ServerSaveTask saveTask;
    private boolean useWarnings;
    private SSWarningSaveTask warnTask;
	
	public void onEnable(){
	    //Set variables
	    logMan = new SSLogManager(Logger.getLogger("Minecraft"));
	    dirMan = new SSDirectoryManager(this);
	    propMan = new SSPropertiesManager(this);
	    //Variables set! Let's go!
		if(!dirMan.directoryExists()){
			dirMan.createDirectory();
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
			long warnTime = new Long(propMan.getProperty("warningTime")).longValue();
			warnTask = new SSWarningSaveTask(this,saveTask,saveTime,warnTime);
			warnTask.start();
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
	
	public SSTaskManager getTaskManager(){
		return taskMan;
	}
}
