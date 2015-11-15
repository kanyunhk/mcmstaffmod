package net.playmcm.qwertysam.util;

import net.minecraft.client.Minecraft;

public class PathUtil
{
	public static String getMinecraftDir()
	{
		return Minecraft.getMinecraft().mcDataDir.getAbsolutePath();
	}
}
