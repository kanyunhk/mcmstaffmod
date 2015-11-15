package net.playmcm.qwertysam.messages.api;

import java.util.ArrayList;
import java.util.List;

import net.playmcm.qwertysam.io.OptionManager;

public class LoadableMessage extends Message
{
	private OptionManager options;
	private String message1key;
	private String message2key;
	private String message3key;
	private MessageType type;

	public LoadableMessage(MessageType type, OptionManager options, String message1key, String message2key, String message3key)
	{
		this.options = options;
		this.message1key = message1key;
		this.message2key = message2key;
		this.message3key = message3key;
		this.type = type;
	}

	@Override
	public MessageType type()
	{
		return type;
	}

	@Override
	public List<List<String>> messages()
	{
		List<List<String>> messages = new ArrayList<List<String>>();

		List<String> var1 = new ArrayList<String>();

		String mes1 = options.getOption(message1key).asString();
		String mes2 = options.getOption(message2key).asString();
		String mes3 = options.getOption(message3key).asString();

		if (mes1 != "") var1.add(mes1);
		if (mes2 != "") var1.add(mes2);
		if (mes3 != "") var1.add(mes3);

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
