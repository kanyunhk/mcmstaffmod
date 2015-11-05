package net.playmcm.qwertysam.messages;

import java.util.ArrayList;
import java.util.List;

import net.playmcm.qwertysam.io.SaveHandling;
import net.playmcm.qwertysam.messages.api.Message;
import net.playmcm.qwertysam.messages.api.MessageType;

public class CustomTwo extends Message
{
	@Override
	public MessageType type() 
	{
		return MessageType.CUSTOM2;
	}

	@Override
	public List<List<String>> messages() 
	{
		List<List<String>> messages = new ArrayList<List<String>>();
		
		List<String> var1 = new ArrayList<String>();
		
		if (SaveHandling.getCustomTwo1() != "") var1.add(SaveHandling.getCustomTwo1());
		if (SaveHandling.getCustomTwo2() != "") var1.add(SaveHandling.getCustomTwo2());
		
		if (var1.isEmpty()) var1.add(null);
		
		messages.add(var1);
		
		return messages;
	}

	@Override
	public String url() 
	{
		return null;
	}

}
