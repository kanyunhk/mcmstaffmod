package net.playmcm.qwertysam.messages.api;

import java.util.ArrayList;
import java.util.List;

import net.playmcm.qwertysam.io.Option;
import net.playmcm.qwertysam.io.OptionManager;

public class LoadableMessage implements IMessage
{
	private OptionManager options;
	private List<String> messages;
	private MessageType type;

	public LoadableMessage(MessageType type, OptionManager options)
	{
		this.options = options;
		this.messages = Option.getValues(type);
		this.type = type;
	}

	@Override
	public MessageType type()
	{
		return type;
	}

	@Override
	public List<String> messages()
	{
		List<String> var1 = new ArrayList<String>();

		for (String message : messages)
		{
			if (message != "" && message != null) var1.add(message);
		}

		if (var1.isEmpty()) var1.add(null);

		return var1;
	}

	@Override
	public String url()
	{
		return null;
	}
}
