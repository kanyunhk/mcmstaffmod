package net.playmcm.qwertysam.util;

import java.util.ArrayList;
import java.util.List;

import net.playmcm.qwertysam.messages.BanAppealsPage;
import net.playmcm.qwertysam.messages.BecomingStaff;
import net.playmcm.qwertysam.messages.CustomOne;
import net.playmcm.qwertysam.messages.CustomThree;
import net.playmcm.qwertysam.messages.CustomTwo;
import net.playmcm.qwertysam.messages.DonatePage;
import net.playmcm.qwertysam.messages.ExplainArgue;
import net.playmcm.qwertysam.messages.ExplainRDM;
import net.playmcm.qwertysam.messages.ExplainTeaming;
import net.playmcm.qwertysam.messages.ReportPage;
import net.playmcm.qwertysam.messages.RulesPage;
import net.playmcm.qwertysam.messages.VotePage;
import net.playmcm.qwertysam.messages.api.Message;
import net.playmcm.qwertysam.messages.api.MessageType;

public class Messages
{
	private static List<Message> messages = new ArrayList<Message>();

	public static void init()
	{
		registerMessage(new BecomingStaff());
		registerMessage(new RulesPage());
		registerMessage(new VotePage());
		registerMessage(new DonatePage());
		registerMessage(new BanAppealsPage());
		registerMessage(new ReportPage());
		registerMessage(new ExplainTeaming());
		registerMessage(new ExplainRDM());
		registerMessage(new ExplainArgue());
		registerMessage(new CustomOne());
		registerMessage(new CustomTwo());
		registerMessage(new CustomThree());
	}

	public static List<String> getRandomMessage(MessageType type)
	{
		return getMessage(type).getRandomMessages();
	}

	public static String getURL(MessageType type)
	{
		return getMessage(type).url();
	}

	public static Message getMessage(MessageType type)
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

	private static void registerMessage(Message message)
	{
		messages.add(message);
	}
}
