package me.krotn.ServerSave;

import java.util.logging.Logger;

public class SSLogManager {
	private Logger log;
	private String prefix = "[ServerSave] ";
	
	/**
	 * Constructs a {@code RentLogManager} that uses the specified {@code Logger}
	 * @param log The {@code Logger} to use.
	 */
	public SSLogManager(Logger log){
		this.log=log;
	}
	
	/**
	 * Constructs a {@code RentLogManager} that uses the specified {@code Logger} and applies
	 * the specified prefix.
	 * @param log The logger to use.
	 * @param prefix The prefix to add to the beginning of each log message.
	 */
	public SSLogManager(Logger log, String prefix){
		this(log);
		this.prefix = prefix;
	}
	
	public String formatMessage(String message){
		return prefix+message;
	}
	
	public void info(String message){
		log.info(formatMessage(message));
	}
	
	public void warning(String message){
		log.warning(formatMessage(message));
	}
	
	public void severe(String message){
		log.severe(formatMessage(message));
	}
	
	public Logger getLogger(){
		return log;
	}
	
	public String getPrefix(){
		return prefix;
	}
}
