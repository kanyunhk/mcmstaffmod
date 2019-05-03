package mcmstaffmod.util;

import net.minecraft.client.Minecraft;

public class PathUtil
{
	public static String getMinecraftDir()
	{
		return Minecraft.getMinecraft().gameDir.getAbsolutePath();
	}
}
