package net.playmcm.qwertysam.messages.api;

import java.util.List;

public interface IMessage
{
	public MessageType type();

	public List<String> messages();

	public String url();
}
