package mcmstaffmod.log;

import org.apache.logging.log4j.Level;

public enum LogType
{
	AUTHUTIL("AUTH", Level.INFO), 
	WARNING("WARNING", Level.WARN), 
	OPTIONS_ERROR("OPTIONS", Level.FATAL), 
	EXCEPTION("EXCEPTION", Level.FATAL);

	private String prefix;
	private Level level;

	LogType(String prefix, Level level)
	{
		this.prefix = prefix;
		this.level = level;
	}

	/**
	 * @return gets the prefix for the logged message.
	 */
	public String getPrefix()
	{
		return "[" + prefix + "] ";
	}

	/**
	 * @return gets the level for the logger.
	 */
	public Level getLevel()
	{
		return level;
	}
}
