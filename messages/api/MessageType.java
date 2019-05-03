package mcmstaffmod.messages.api;

public enum MessageType
{
	BECOME_STAFF(URL.HOW_TO_BECOME_MOD), 
	RULES(URL.RULES), 
	VOTE(URL.VOTE), 
	DONATE(URL.DONATE), 
	BAN_APPEALS(URL.BAN_APPEALS), 
	REPORTS(URL.REPORTS), 
	FORUM(URL.WEBSITE),
	EXPLAIN_TEAMING, 
	EXPLAIN_RDM, 
	EXPLAIN_ARGUE, 
	CUSTOM1, 
	CUSTOM2, 
	CUSTOM3,
	CUSTOM4;

	private String url;

	MessageType()
	{
		this(null);
	}

	MessageType(String url)
	{
		this.url = url;
	}

	public String getURL()
	{
		return url;
	}
}
