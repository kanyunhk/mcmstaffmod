package net.playmcm.qwertysam.messages;

import java.util.ArrayList;
import java.util.List;

import net.playmcm.qwertysam.messages.api.Message;
import net.playmcm.qwertysam.messages.api.MessageType;
import net.playmcm.qwertysam.messages.api.URL;

public class BanAppealsPage extends Message
{
	@Override
	public MessageType type()
	{
		return MessageType.BAN_APPEALS;
	}

	@Override
	public List<List<String>> messages()
	{
		List<List<String>> messages = new ArrayList<List<String>>();

		List<String> var1 = new ArrayList<String>();
		List<String> var2 = new ArrayList<String>();
		List<String> var3 = new ArrayList<String>();
		List<String> var4 = new ArrayList<String>();

		var1.add("If you'd like to create a ban appeal, you can do so on this page.");

		var2.add("If you want to create a ban appeal, please click on this link.");

		var3.add("If you'd like to make a ban appeal, please go to this page and create a new thread.");

		var4.add("If you want to make a ban appeal, please go here and make a new thread.");

		var1.add(url());
		var2.add(url());
		var3.add(url());
		var4.add(url());

		messages.add(var1);
		messages.add(var2);
		messages.add(var3);
		messages.add(var4);

		return messages;
	}

	@Override
	public String url()
	{
		return URL.BAN_APPEALS;
	}
}
