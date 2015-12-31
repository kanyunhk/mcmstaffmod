package net.playmcm.qwertysam.util;

import java.util.HashMap;

import net.minecraft.client.Minecraft;
import net.playmcm.qwertysam.log.LogType;
import net.playmcm.qwertysam.log.QLogger;

public class AuthUtil
{
	private static HashMap<String, String> uuids = new HashMap<String, String>();
	static
	{
		uuids.put("55058335a49a44ef920cfb0864225bcf", "Sammeh"); // Sam
		uuids.put("fa9b5735afc0432785f38e6f58277520", "Seppeh"); // Lauren
		uuids.put("797f44ea30664868adb9a64272927f24", "Ere"); // Ere
		uuids.put("95a8d801900d45ec812b0c4c099e96cc", "Benneh bOI"); // Ben
		uuids.put("4bebde164e7c49a0b00f732bd56f840f", "Linu"); // Linus
		uuids.put("485759fc52e34d31a3202a54cc709c82", "Aleenuu"); // Alena
		uuids.put("bd550bb734524f579df1abb4bb12f076", "Telurr"); // Tyler
		uuids.put("7f44ed5ed7f74d54939f5e5b5dd1c2e4", "Alecks"); // Alex
		uuids.put("f17f30cd60ae402c87e1645be92577ab", "Brenden"); // Brandon
		uuids.put("b9f0500fc3c944dbbdb6f44f6b38bcfe", "Emelee"); // Emily
		uuids.put("e38dac7884d9469c9cb7287bcf88a690", "Etien"); // Etian
		uuids.put("0d3ea2215d52459a92b3d0186b851aed", "Fetheh"); // Faithhh
		
	}

	private static boolean hasInit = false;
	private static boolean canUse;

	public static boolean canUse()
	{
		if (!hasInit)
		{
			String uuid = Minecraft.getMinecraft().getSession().getPlayerID();

			QLogger.log(LogType.AUTHUTIL, "UUID = " + uuid);

			canUse = false;
			for (String authedUUID : uuids.keySet())
			{
				if (authedUUID.equals(uuid)) canUse = true;
			}

			QLogger.log(LogType.AUTHUTIL, canUse ? "Authentication successful!... oHonh " + uuids.get(uuid) + "... c;|" : "Authentication failed.");
			hasInit = true;
		}
		return canUse;
	}
}
