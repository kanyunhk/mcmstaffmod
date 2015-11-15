package net.playmcm.qwertysam.messages;

import java.util.ArrayList;
import java.util.List;

import net.playmcm.qwertysam.messages.api.Message;
import net.playmcm.qwertysam.messages.api.MessageType;
import net.playmcm.qwertysam.messages.api.URL;

public class ReportPage extends Message
{
	@Override
	public MessageType type()
	{
		return MessageType.REPORTS;
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

		var1.add("If you'd like to file a report against someone, you can do so here.");

		var2.add("If you want to make a report, you can do so on this page.");

		var3.add("If you want to make a report, please go to this page and make a new thread.");

		var4.add("If you'd like to report someone, please click on this link.");

		var5.add("If you want to report someone, please go here and create a new thread.");

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
		return URL.REPORTS;
	}
}
