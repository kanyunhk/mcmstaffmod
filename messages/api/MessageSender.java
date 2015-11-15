package net.playmcm.qwertysam.messages.api;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.playmcm.qwertysam.io.OptionManager;
import net.playmcm.qwertysam.log.LogType;
import net.playmcm.qwertysam.log.QLogger;
import net.playmcm.qwertysam.messages.MessageManager;

public class MessageSender
{
	public static final char[] alphabet = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 
			'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
			'(', ')', '[', ']', '\'', '\"', '\\', '/', '<', '>', ',', '.', '!', '@', '#', '$', '%', '^', '&', '*', '+', '-', '_', '=', '|' };

	/** The delay in milliseconds for sending delayed messages. */
	public static final int delay = 200;

	private MessageManager messageManager;

	public MessageSender(OptionManager options)
	{
		this.messageManager = new MessageManager(options);
	}

	public void sendMessage(MessageType type)
	{
		sendMessage(type, false);
	}

	public void sendMessage(MessageType type, boolean justLink)
	{
		if (!justLink)
		{
			List<String> messageStrings = messageManager.getRandomMessage(type);

			if (messageStrings != null)
			{
				int i = 0;
				for (String string : messageManager.getRandomMessage(type))
				{
					boolean sendMessage = false;
					if (string != "" && string != null)
					{
						for (char character : alphabet)
						{
							if (string.toLowerCase().contains("" + character)) sendMessage = true;
						}
					}

					if (sendMessage) sendDelayedMessage(string, i);
					i++;
				}
			}
		}
		else if (messageManager.getURL(type) != null)
		{
			sendDelayedMessage(messageManager.getURL(type), 0);
		}
	}

	/**
	 * Sends a message from the player.
	 * 
	 * @param message the message to be sent
	 */
	public void sendMessage(String message)
	{
		sendDelayedMessage(message, 0);
	}

	/**
	 * Sends a message from the player.
	 * 
	 * @param message the message to be sent
	 */
	public void sendMessage(String message, int delayMultiplier)
	{
		sendDelayedMessage(message, delayMultiplier);
	}

	/**
	 * Sends a delayed message.
	 * 
	 * @param message the message to be sent
	 */
	public void sendDelayedMessage(final String message, final int delayMultiplier)
	{
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
					QLogger.log(LogType.EXCEPTION, "Failed to send delayed message on another thread.");
					e.printStackTrace();
				}
			}
		}).start();
	}
}
