package net.playmcm.qwertysam.messages;

import java.util.ArrayList;
import java.util.List;

import net.playmcm.qwertysam.messages.api.Message;
import net.playmcm.qwertysam.messages.api.MessageType;
import net.playmcm.qwertysam.messages.api.URL;

public class ExplainArgue extends Message
{
	@Override
	public MessageType type() 
	{
		return MessageType.EXPLAIN_ARGUE;
	}
	
	@Override
	public List<List<String>> messages() 
	{
		List<List<String>> messages = new ArrayList<List<String>>();
		
		List<String> var1 = new ArrayList<String>();
		
		var1.add("If you disagree with the punishment that was received, you may go to the forum to discuss the issue.");
		var1.add(url());
		var1.add("If you continue to debate over this punishment in-game, you will also receive a punishment.");
		
		messages.add(var1);
		
		return messages;
	}

	@Override
	public String url() 
	{
		return URL.BAN_APPEALS;
	}

}
