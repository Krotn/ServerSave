package me.krotn.ServerSave;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class SSWarningSaveTask implements Runnable{
	private ServerSave plugin = null;
	private SSPropertiesManager propMan = null;
	private SSTaskManager taskMan = null;
	private ServerSaveTask saveTask = null;
	private long saveTime = 600;
	private long warningTime = 30;
	
	public SSWarningSaveTask(ServerSave plugin,ServerSaveTask saveTask,long saveTime,long warningTime){
		this.plugin = plugin;
		this.propMan = plugin.getPropertiesManager();
		this.taskMan = plugin.getTaskManager();
		this.saveTask = saveTask;
		this.saveTime = saveTime;
		this.warningTime = warningTime;
	}
	
	public void start(){
		scheduleSelf();
	}
	
	public void scheduleSelf(){
		long delayToStart = this.saveTime-this.warningTime;
		this.taskMan.scheduleAsyncTask(this, delayToStart);
	}
	
	public void rescheduleSelf(){
		long delayToStart = this.saveTime;
		this.taskMan.scheduleAsyncTask(this, delayToStart);
	}
	
	public void scheduleSaveTask(){
		long delayToStart = this.warningTime;
		this.taskMan.scheduleSyncTask(this.saveTask,delayToStart);
	}
	
	public void run(){
		//Called when it's warning time!
		scheduleSaveTask();
		rescheduleSelf();
		String warningText = propMan.getProperty("warningNotification");
		String warningColorString = ChatColor.valueOf(propMan.getProperty("warningColor").toUpperCase()).toString();
		if(!warningText.isEmpty()){
		    this.plugin.getServer().broadcastMessage(warningColorString+warningText);
		}
		Player[] players = this.plugin.getServer().getOnlinePlayers();
		boolean outputToConsole = new Boolean(propMan.getProperty("printToConsole")).booleanValue();
		if(outputToConsole && (players.length > 0)){
			this.plugin.getLogManager().info("Save warning!");
		}
	}
}
