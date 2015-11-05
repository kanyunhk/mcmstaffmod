package net.playmcm.qwertysam.messages;

import java.util.ArrayList;
import java.util.List;

import net.playmcm.qwertysam.messages.api.Message;
import net.playmcm.qwertysam.messages.api.MessageType;
import net.playmcm.qwertysam.messages.api.URL;

public class VotePage extends Message
{
	@Override
	public MessageType type() 
	{
		return MessageType.VOTE;
	}

	@Override
	public List<List<String>> messages() 
	{
		List<List<String>> messages = new ArrayList<List<String>>();
		
		List<String> var1 = new ArrayList<String>();
		List<String> var2 = new ArrayList<String>();
		List<String> var3 = new ArrayList<String>();
		List<String> var4 = new ArrayList<String>();
		List<String> var5 = new ArrayList<String>();
		
		var1.add("If you'd like to support us by voting for the server, please click here.");
		
		var2.add("If you're interested in voting for our server, please click here.");
		
		var3.add("If you're looking to vote for the server, please go to this page.");
		
		var4.add("If you'd like to vote for our server, please click on this link.");
		
		var5.add("If you are looking to vote for our server, please click here.");
		
		var1.add(url());
		var2.add(url());
		var3.add(url());
		var4.add(url());
		var5.add(url());
		
		messages.add(var1);
		messages.add(var2);
		messages.add(var3);
		messages.add(var4);
		messages.add(var5);
		
		return messages;
	}

	@Override
	public String url() 
	{
		return URL.VOTE;
	}

}
