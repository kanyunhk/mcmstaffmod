package net.playmcm.qwertysam.messages;

import java.util.ArrayList;
import java.util.List;

import net.playmcm.qwertysam.messages.api.Message;
import net.playmcm.qwertysam.messages.api.MessageType;

public class ExplainRDM extends Message
{
	@Override
	public MessageType type() 
	{
		return MessageType.EXPLAIN_RDM;
	}

	@Override
	public List<List<String>> messages() 
	{
		List<List<String>> messages = new ArrayList<List<String>>();
		
		List<String> var1 = new ArrayList<String>();
		
		var1.add("RDM, or random death match, is when a player has a gun and shoots a random person not knowing");
		var1.add("whether they're innocent or not. RDM is not against the rules, and is not to be confused");
		var1.add("with Teaming.");
		
		messages.add(var1);
		
		return messages;
	}

	@Override
	public String url() 
	{
		return null;
	}

}
