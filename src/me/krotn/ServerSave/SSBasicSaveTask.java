package me.krotn.ServerSave;

import org.bukkit.World;

public class SSBasicSaveTask implements Runnable{
	World saveWorld;
	
	public SSBasicSaveTask(World world){
		this.saveWorld = world;
	}
	
	public void run(){
		this.saveWorld.save();
	}
}
