package me.krotn.ServerSave;

import java.io.File;

public class SSDirectoryManager {

	private static final String mainDirectory = "plugins"+File.separator+"ServerSave";
	
	
	/**
	 * This method creates the ServerSave plugin directory.<br/>
	 * It does nothing if the directory already exists.
	 */
	public static void createDirectory(){
		new File(mainDirectory).mkdir();
	}
	
	public static boolean directoryExists(){
		return new File(mainDirectory).exists();
	}
	
	/**
	 * This function returns the main plugin directory.<br/>
	 * It omits the final separator (eg. "plugins/Rent").
	 * @return The main plugin directory.
	 */
	public static String getMainDirectory(){
		return mainDirectory;
	}
	
	/**
	 * Returns a suitable path that would place the file in the main plugin directory.
	 * @param fileName The name of the file to place in the directory.
	 * @return A String path placing the file in the main plugin directory (eg. "plugins/Rent/filename").
	 */
	public static String getPathInDir(String fileName){
		return mainDirectory+File.separator+fileName;
	}
}
