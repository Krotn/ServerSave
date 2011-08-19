package me.krotn.ServerSave;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.entity.Player;

/**
 * This class handles the main saving procedure for the ServerSave plugin.
 *
 */
public class ServerSaveTask implements Runnable{
	private ServerSave plugin;
	private Server server;
	private SSPropertiesManager propMan;
	
	/**
	 * Constructs a new ServerSaveTask.
	 * @param plugin The {@code ServerSave} plugin to use for getting resources. 
	 * @param server The {@code Server} to save.
	 */
	public ServerSaveTask(ServerSave plugin,Server server){
		this.plugin = plugin;
		this.server = server;
		this.propMan = plugin.getPropertiesManager();
	}
	
	/**
	 * Performs the save task.
	 * <br/>
	 * This task spreads out the world saves over several ticks.
	 * One per world and then saves players in another tick.
	 * This method catches all exceptions.
	 */
	public void run() {
		try{
			//Check if we should output to console.
			boolean outputToConsole = new Boolean(propMan.getProperty("printToConsole")).booleanValue();
			//Prepare the save start notification.
			String chatColorString = ChatColor.valueOf(propMan.getProperty("startColor").toUpperCase()).toString();
			String startNotification = propMan.getProperty("startNotification");
			//Get players to count them before save.
			Player[] players = server.getOnlinePlayers();
			boolean saveOnNoPlayers = new Boolean(propMan.getProperty("saveIfNoPlayers")).booleanValue();
			if(players.length<=0&&!(saveOnNoPlayers)){
				if(outputToConsole){
					//No players online and we are told not to save in that case so exit.
					plugin.getLogManager().info("No players, not saving.");
				}
				return;
			}
			//Broadcast start notification.
			if(!startNotification.isEmpty()){
				server.broadcastMessage(chatColorString+startNotification);
			}
			if(outputToConsole){
				plugin.getLogManager().info("Starting Save!");
			}
			//Schedule all the save components in other ticks.
			long ticks = 1; //World tick counter
			for(World world:server.getWorlds()){
				//Schedule worlds, one tick per world.
				this.plugin.getTaskManager().scheduleSyncTaskDirect(new SSBasicSaveTask(world),ticks);
				ticks++;
			}
			//Schedule player save in its own tick.
			this.plugin.getTaskManager().scheduleSyncTaskDirect(new SSBasicPlayerSaveTask(this.plugin.getServer()),ticks);
			ticks++;
			//Scheduling of save end notification.
			//Prepare the end notification string.
			chatColorString = ChatColor.valueOf(propMan.getProperty("endColor").toUpperCase()).toString();
			String endNotification = propMan.getProperty("endNotification");
			if(!endNotification.isEmpty()){
				String endText = chatColorString+endNotification; //Final notification string
				if(outputToConsole){
					//Notification should print a log message.
					this.plugin.getTaskManager().scheduleAsyncTaskDirect(new SSBasicBroadcastTask(endText,"Save complete!",this.plugin.getLogManager(),this.plugin.getServer()),ticks);
				}
				else{
					//Notification should not print a log message.
					this.plugin.getTaskManager().scheduleAsyncTaskDirect(new SSBasicBroadcastTask(endText,this.plugin.getServer()), ticks);
				}
				ticks++;
			}
		}catch(Exception e){
			//Catch all exceptions.
			plugin.getLogManager().warning("Problem performing world save!");
			e.printStackTrace();
		}
	}

}
