package me.krotn.ServerSave;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class ServerSaveTask implements Runnable{
	private ServerSave plugin;
	private Server server;
	private SSPropertiesManager propMan;
	
	public ServerSaveTask(ServerSave plugin,Server server){
		this.plugin = plugin;
		this.server = server;
		this.propMan = plugin.getPropertiesManager();
	}
	public void run() {
		try{
			boolean outputToConsole = new Boolean(propMan.getProperty("printToConsole")).booleanValue();
			String chatColorString = ChatColor.valueOf(propMan.getProperty("startColor").toUpperCase()).toString();
			String startNotification = propMan.getProperty("startNotification");
			Player[] players = server.getOnlinePlayers();
			boolean saveOnNoPlayers = new Boolean(propMan.getProperty("saveIfNoPlayers")).booleanValue();
			if(players.length<=0&&!(saveOnNoPlayers)){
				if(outputToConsole){
					plugin.getLogManager().info("No players, not saving.");
				}
				return;
			}
			if(!startNotification.isEmpty()){
				server.broadcastMessage(chatColorString+startNotification);
			}
			if(outputToConsole){
				plugin.getLogManager().info("Starting Save!");
			}
			long ticks = 1;
			for(World world:server.getWorlds()){
				this.plugin.getTaskManager().scheduleSyncTaskDirect(new SSBasicSaveTask(world),ticks);
				ticks++;
			}
			plugin.getServer().savePlayers();
			chatColorString = ChatColor.valueOf(propMan.getProperty("endColor").toUpperCase()).toString();
			String endNotification = propMan.getProperty("endNotification");
			if(!endNotification.isEmpty()){
				server.broadcastMessage(chatColorString+endNotification);
			}
			if(outputToConsole){
				plugin.getLogManager().info("Save complete!");
			}
		}catch(Exception e){
			plugin.getLogManager().warning("Problem performing world save!");
			e.printStackTrace();
		}
	}

}
