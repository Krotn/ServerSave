package me.krotn.ServerSave;

import java.util.ArrayList;

import org.bukkit.scheduler.BukkitScheduler;

public class SSTaskManager {
	private int ticksPerSecond = 20;
	private BukkitScheduler scheduler;
	private ServerSave plugin;
	
	
	public SSTaskManager(ServerSave plugin, BukkitScheduler scheduler){
		this.plugin = plugin;
		this.scheduler = scheduler;
	}
	
	public SSTaskManager(ServerSave plugin){
		this(plugin,plugin.getServer().getScheduler());
	}
	
	public long secsInTicks(double seconds){
		return Math.round(seconds*ticksPerSecond);
	}
	
	public int scheduleSyncRepeatingTask(Runnable task,long delayToStart,long period){
		int taskID = scheduler.scheduleSyncRepeatingTask(this.plugin, task, secsInTicks(delayToStart), secsInTicks(period));
		return taskID;
	}
	
	public int scheduleAsyncTask(Runnable task,long delayToStart){
		int taskID = scheduler.scheduleAsyncDelayedTask(this.plugin, task, secsInTicks(delayToStart));
		return taskID;
	}
	
	public int scheduleSyncImmediateTask(Runnable task){
		int taskID = scheduler.scheduleSyncDelayedTask(this.plugin, task);
		return taskID;
	}
	
	public int scheduleSyncTask(Runnable task,long delayToStart){
		int taskID = scheduler.scheduleSyncDelayedTask(this.plugin, task, secsInTicks(delayToStart));
		return taskID;
	}
	
	public int scheduleSyncTaskDirect(Runnable task,long ticksDelayToStart){
		int taskID = scheduler.scheduleSyncDelayedTask(this.plugin, task, ticksDelayToStart);
		return taskID;
	}
	
	public int scheduleAsyncTaskDirect(Runnable task, long ticksDelayToStart){
		int taskID = scheduler.scheduleAsyncDelayedTask(this.plugin, task, ticksDelayToStart);
		return taskID;
	}
	
	public void removeAllTasks(){
		scheduler.cancelTasks(this.plugin);
	}
}
