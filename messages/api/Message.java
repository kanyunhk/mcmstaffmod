package net.playmcm.qwertysam.messages.api;

import java.util.List;
import java.util.Random;

public class Message implements IMessage
{
	@Override
	public MessageType type() 
	{
		return null;
	}

	@Override
	public List<List<String>> messages() 
	{
		return null;
	}

	@Override
	public String url() 
	{
		return null;
	}
	
	public List<String> getRandomMessages()
	{
		return messages().get(new Random().nextInt(messages().size()));
	}
}
