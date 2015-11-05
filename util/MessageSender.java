package net.playmcm.qwertysam.util;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.playmcm.qwertysam.messages.api.MessageType;

public class MessageSender 
{
	/** The delay in milliseconds for sending delayed messages. */
	public static final int delay = 200;
	
	public static void sendMessage(MessageType type)
	{
		sendMessage(type, false);
	}
	
	public static void sendMessage(MessageType type, boolean justLink)
	{
		if (!justLink)
		{
			List<String> messageStrings = Messages.getRandomMessage(type);
		
			if (messageStrings != null)
			{
				int i = 0;
				for (String string : Messages.getRandomMessage(type))
				{
					if (string != null && string != "")
					{
						sendDelayedMessage(string, i);
						i++;
					}
				}
			}
		}
		else if (Messages.getURL(type) != null)
		{
			sendDelayedMessage(Messages.getURL(type), 0);
		}
	}
	
	/**
	 * Sends a message from the player.
	 * @param message the message to be sent
	 */
	public void sendMessage(String message)
	{
		sendDelayedMessage(message, 0);
	}
	
	/**
	 * Sends a message from the player.
	 * @param message the message to be sent
	 */
	public void sendMessage(String message, int delayMultiplier)
	{
		sendDelayedMessage(message, delayMultiplier);
	}
	
	/**
	 * Sends a delayed message.
	 * @param message the message to be sent
	 */
	public static void sendDelayedMessage(final String message, final int delayMultiplier)
	{
		System.out.println(message);
		
		new Thread(new Runnable() 
		{
		    @Override
			public void run() 
		    {
		    	try 
		    	{
					Thread.sleep(delay * delayMultiplier);
					Minecraft.getMinecraft().thePlayer.sendChatMessage(message);
				} 
		    	catch (InterruptedException e) 
		    	{
					e.printStackTrace();
				}
		    }
		}).start();
	}
}
