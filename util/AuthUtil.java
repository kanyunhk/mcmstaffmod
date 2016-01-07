package net.playmcm.qwertysam.util;

import net.minecraft.client.Minecraft;
import net.playmcm.qwertysam.log.LogType;
import net.playmcm.qwertysam.log.QLogger;

public class AuthUtil
{
	private static Staff user;
	private static boolean hasInit = false;
	private static boolean canUse;

	private static void init()
	{
		String uuid = Minecraft.getMinecraft().getSession().getPlayerID();

		QLogger.log(LogType.AUTHUTIL, "UUID = " + uuid);

		user = Staff.valueByUUID(uuid);
		canUse = Staff.isValid(user);
		
		QLogger.log(LogType.AUTHUTIL, canUse ? "Authentication successful!... oHonh " + user.getName() + "... c;|" : "Authentication failed.");
		hasInit = true;
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
