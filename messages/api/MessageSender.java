package qwertysam.messages.api;

import java.util.List;

import net.minecraft.client.Minecraft;
import qwertysam.io.Option;
import qwertysam.log.LogType;
import qwertysam.log.QLogger;

public class MessageSender
{
	public static final char[] alphabet = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 
			'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
			'(', ')', '[', ']', '\'', '\"', '\\', '/', '<', '>', ',', '.', '!', '@', '#', '$', '%', '^', '&', '*', '+', '-', '_', '=', '|' };

	/** The delay in milliseconds for sending delayed messages. */
	public static final int delay = 200;

	public MessageSender()
	{
	}

	public static void sendMessage(MessageType type, boolean autoLink)
	{
		sendMessage(type, false, autoLink);
	}

	public static void sendMessage(MessageType type, boolean justLink, boolean autoLink)
	{
		if (!justLink)
		{
			if (Option.getValues(type) != null)
			{
				List<String> messages = Option.getValues(type);

				if (autoLink) messages.add(type.getURL());

				int i = 0;
				for (String string : messages)
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
		else if (type.getURL() != null)
		{
			sendMessage(type.getURL());
		}
	}

	/**
	 * Sends a message from the player.
	 * 
	 * @param message the message to be sent
	 */
	public static void sendMessage(String message)
	{
		sendDelayedMessage(message, 0);
	}

	/**
	 * Sends a message from the player.
	 * 
	 * @param message the message to be sent
	 */
	public static void sendMessage(String message, int delayMultiplier)
	{
		sendDelayedMessage(message, delayMultiplier);
	}

	/**
	 * Sends a delayed message.
	 * 
	 * @param message the message to be sent
	 */
	public static void sendDelayedMessage(final String message, final int delayMultiplier)
	{
		new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				try
				{
					Thread.sleep(delay * delayMultiplier);
					Minecraft.getMinecraft().player.sendChatMessage(message);
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
