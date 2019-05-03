package mcmstaffmod.util;

import net.minecraft.client.Minecraft;
import mcmstaffmod.ModMain;
import mcmstaffmod.log.LogType;
import mcmstaffmod.log.QLogger;

public class AuthUtil
{
	private static Staff user;
	private static boolean hasInit = false;
	private static boolean canUse;

	private static void init()
	{
		hasInit = true;
		if (ModMain.debug) {
			canUse = true;
			QLogger.log(LogType.AUTHUTIL, "Debug mode; allowing usage.");
			return;
		}
		
		String uuid = Minecraft.getMinecraft().getSession().getPlayerID();

		QLogger.log(LogType.AUTHUTIL, "UUID = " + uuid);

		user = Staff.valueByUUID(uuid);
		canUse = Staff.isValid(user);
		
		QLogger.log(LogType.AUTHUTIL, canUse ? "Authentication successful!... oHonh, " + user.getName() + "... c;|" : "Authentication failed.");
	}
	
	public static boolean canUse()
	{
		if (!hasInit) init();
		return canUse;
	}
	
	public static Staff getUser()
	{
		if (!hasInit) init();
		return user;
	}
}
