package net.playmcm.qwertysam.util;

import net.minecraft.client.Minecraft;
import net.playmcm.qwertysam.log.LogType;
import net.playmcm.qwertysam.log.QLogger;

public class AuthUtil
{
	private static String[] authenticatedUUIDs = { 
			"55058335a49a44ef920cfb0864225bcf", // Sam
			"fa9b5735afc0432785f38e6f58277520", // Lauren
			"797f44ea30664868adb9a64272927f24", // Ere
			"95a8d801900d45ec812b0c4c099e96cc", // Ben
			"4bebde164e7c49a0b00f732bd56f840f", // Linus
			"485759fc52e34d31a3202a54cc709c82", // Alena
			"bd550bb734524f579df1abb4bb12f076", // Tyler
			"b9f0500fc3c944dbbdb6f44f6b38bcfe", // Emelee
			"", // Etian
			"", // Faithhh
	};

	private static boolean hasInit = false;
	private static boolean canUse;

	public static boolean canUse()
	{
		if (!hasInit)
		{
			String uuid = Minecraft.getMinecraft().getSession().getPlayerID();

			QLogger.log(LogType.AUTHUTIL, "UUID = " + uuid);

			canUse = false;
			for (String authedUUID : authenticatedUUIDs)
			{
				if (authedUUID.equals(uuid)) canUse = true;
			}

			QLogger.log(LogType.AUTHUTIL, canUse ? "Authentication successful!" : "Authentication failed.");
			hasInit = true;
		}
		return canUse;
	}
}
