package net.playmcm.qwertysam;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.playmcm.qwertysam.gui.ModGui;
import net.playmcm.qwertysam.io.SaveHandling;
import net.playmcm.qwertysam.resource.KeyPress;
import net.playmcm.qwertysam.util.MessageSender;
import net.playmcm.qwertysam.util.Messages;

/**
 * Uses S3APacketTabComplete.java and Timer.java from Minecraft src code.
 * @date_created July 7th 2015
 * @date_updated July 8th 2015
 * @version 1.0
 */
public class ModMain 
{
	/**
	 * Sends the messages.
	 */
	private static MessageSender messageSender;
	private ModGui modGui;
	private KeyPress triggerKey;
	private boolean firstLoop;
	
	public ModMain()
	{
		messageSender = new MessageSender();
		modGui = new ModGui();
		triggerKey = new KeyPress(Keyboard.KEY_GRAVE);
		firstLoop = true;
	}
	
	/**
	 * Has access to the main gameloop through Timer.updateTimer();
	 */
	public void gameLoop()
	{
		if (firstLoop)
		{
			Messages.init();
			SaveHandling.loadOptions();
			firstLoop = false;
		}
		
		if (Minecraft.getMinecraft().currentScreen == modGui || Minecraft.getMinecraft().currentScreen == null) //Stops the GUI from opening while not in-game
		{
			if (triggerKey.isPressed())
			{
				if (Minecraft.getMinecraft().currentScreen == modGui) //Exits if it's already on the menu
				{
					modGui.exitGui();
				}
				else //Goes to the menu if it's not there
				{
					Minecraft.getMinecraft().displayGuiScreen(modGui); 
				}
			}
		}
	}
}
