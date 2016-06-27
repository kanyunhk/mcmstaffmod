package qwertysam.log;

import org.apache.logging.log4j.LogManager;

public class QLogger
{
	public static void log(LogType type, String message)
	{
		LogManager.getLogger().log(type.getLevel(), type.getPrefix() + message);
	}
}
