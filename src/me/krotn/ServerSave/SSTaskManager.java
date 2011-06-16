package me.krotn.ServerSave;

import java.util.ArrayList;

import org.bukkit.scheduler.BukkitScheduler;

public class SSTaskManager {
	private int ticksPerSecond = 20;
	private BukkitScheduler scheduler;
	private ServerSave plugin;
	
	private ArrayList<Integer> tasks = new ArrayList<Integer>();
	
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
	
	public int scheduleRepeatingTask(Runnable task,long delayToStart,long period){
		int taskID = scheduler.scheduleAsyncRepeatingTask(this.plugin, task, secsInTicks(delayToStart), secsInTicks(period));
		if(taskID!=-1){
			tasks.add(taskID);
		}
		return taskID;
	}
	
	public void removeAllTasks(){
		for(Integer taskID:tasks){
			scheduler.cancelTask(taskID);
		}
		tasks.clear();
	}
}
