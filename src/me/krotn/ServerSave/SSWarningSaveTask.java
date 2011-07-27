package me.krotn.ServerSave;

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
		
	}
	
	public void run(){
		
	}
}
