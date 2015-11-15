package net.playmcm.qwertysam.messages;

import java.util.ArrayList;
import java.util.List;

import net.playmcm.qwertysam.io.OptionKey;
import net.playmcm.qwertysam.io.OptionManager;
import net.playmcm.qwertysam.messages.api.LoadableMessage;
import net.playmcm.qwertysam.messages.api.Message;
import net.playmcm.qwertysam.messages.api.MessageType;

public class MessageManager
{
	public MessageManager(OptionManager options)
	{
		registerMessage(new BecomingStaff());
		registerMessage(new RulesPage());
		registerMessage(new VotePage());
		registerMessage(new DonatePage());
		registerMessage(new BanAppealsPage());
		registerMessage(new ReportPage());
		registerMessage(new ForumPage());
		registerMessage(new ExplainTeaming());
		registerMessage(new ExplainRDM());
		registerMessage(new ExplainArgue());
		registerMessage(new LoadableMessage(MessageType.CUSTOM1, options, OptionKey.customOne1, OptionKey.customOne2, OptionKey.customOne3));
		registerMessage(new LoadableMessage(MessageType.CUSTOM2, options, OptionKey.customTwo1, OptionKey.customTwo2, OptionKey.customTwo3));
		registerMessage(new LoadableMessage(MessageType.CUSTOM3, options, OptionKey.customThree1, OptionKey.customThree2, OptionKey.customThree3));
		registerMessage(new LoadableMessage(MessageType.CUSTOM4, options, OptionKey.customFour1, OptionKey.customFour2, OptionKey.customFour3));
	}

	private List<Message> messages = new ArrayList<Message>();

	public List<String> getRandomMessage(MessageType type)
	{
		return getMessage(type).getRandomMessages();
	}

	public String getURL(MessageType type)
	{
		return getMessage(type).url();
	}

	public Message getMessage(MessageType type)
	{
		for (Message message : messages)
		{
			if (message.type() == type)
			{
				return message;
			}
		}
		return null;
	}

	private void registerMessage(Message message)
	{
		messages.add(message);
	}
}
