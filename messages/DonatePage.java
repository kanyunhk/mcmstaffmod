package net.playmcm.qwertysam.messages;

import java.util.ArrayList;
import java.util.List;

import net.playmcm.qwertysam.messages.api.Message;
import net.playmcm.qwertysam.messages.api.MessageType;
import net.playmcm.qwertysam.messages.api.URL;

public class DonatePage extends Message
{
	@Override
	public MessageType type() 
	{
		return MessageType.DONATE;
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
		
		var1.add("If you'd like to purchase ranks, you can donate here.");
		
		var2.add("If you're interested in donating to get a rank, you can purchase them here.");
		
		var3.add("If you'd like to donate to get a rank, please click on this link.");
		
		var4.add("If you would like to donate, you can purchase ranks on this page.");
		
		var5.add("If you're interested donating to get a rank, please take a look at this page.");
		
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
		return URL.DONATE;
	}

}
