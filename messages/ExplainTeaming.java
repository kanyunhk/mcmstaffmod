package net.playmcm.qwertysam.messages;

import java.util.ArrayList;
import java.util.List;

import net.playmcm.qwertysam.messages.api.Message;
import net.playmcm.qwertysam.messages.api.MessageType;

public class ExplainTeaming extends Message
{
	@Override
	public MessageType type()
	{
		return MessageType.EXPLAIN_TEAMING;
	}

	@Override
	public List<List<String>> messages()
	{
		List<List<String>> messages = new ArrayList<List<String>>();

		List<String> var1 = new ArrayList<String>();

		var1.add("Teaming is when the gunner knows who the murderer is and purposefully shoots an innocent.");
		var1.add("It is against the rules to team.");

		messages.add(var1);

		return messages;
	}

	@Override
	public String url()
	{
		return null;
	}
}
