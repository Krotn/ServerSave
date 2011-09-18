package me.krotn.ServerSave;

import java.io.File;
import org.bukkit.plugin.Plugin;

public class SSDirectoryManager {
    private Plugin plugin;
    
    public SSDirectoryManager(Plugin plugin){
        this.plugin = plugin;
    }
	
	/**
	 * This method creates the ServerSave plugin directory.<br/>
	 * It does nothing if the directory already exists.
	 */
	public void createDirectory(){
		this.plugin.getDataFolder().mkdir();
	}
	
	public boolean directoryExists(){
		return this.plugin.getDataFolder().exists();
	}
	
	/**
	 * This function returns the main plugin directory.<br/>
	 * It omits the final separator (eg. "plugins/Rent").
	 * @return The main plugin directory.
	 */
	public String getMainDirectory(){
		return this.plugin.getDataFolder().getAbsolutePath();
	}
	
	/**
	 * Returns a suitable path that would place the file in the main plugin directory.
	 * @param fileName The name of the file to place in the directory.
	 * @return A String path placing the file in the main plugin directory (eg. "plugins/Rent/filename").
	 */
	public String getPathInDir(String fileName){
		return this.plugin.getDataFolder().getAbsolutePath()+File.separator+fileName;
	}
}
