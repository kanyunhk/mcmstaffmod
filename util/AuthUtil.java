package net.playmcm.qwertysam.util;

import net.minecraft.client.Minecraft;

public class AuthUtil 
{
	private static String[] authenticatedUUIDs = {
			"",
			"",
			""};
	
	public static boolean canUse()
	{
		String uuid = Minecraft.getMinecraft().getSession().getPlayerID();
		
		System.out.println(uuid);
		
		for (String authedUUID : authenticatedUUIDs)
		{
			if (authedUUID.equals(uuid)) return true;
		}
		
		return false;
	}
}
