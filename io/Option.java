package net.playmcm.qwertysam.io;

import java.util.ArrayList;
import java.util.List;

import net.playmcm.qwertysam.messages.api.MessageType;
import net.playmcm.qwertysam.messages.api.URL;

public enum Option
{
	BECOMING_STAFF1("bcmStf1", "If you'd like to learn how you can become a staff member, check out this page.", MessageType.BECOME_STAFF),
	BECOMING_STAFF2("bcmStf2", "", MessageType.BECOME_STAFF),
	BECOMING_STAFF3("bcmStf3", "", MessageType.BECOME_STAFF),
	BECOMING_STAFF_AUTO_LINK("bcmStfAu", "true"),

	RULES_PAGE1("rlPg1", "If you'd like to read the server rules, type '/rules' or go here.", MessageType.RULES),
	RULES_PAGE2("rlPg2", "", MessageType.RULES),
	RULES_PAGE3("rlPg3", "", MessageType.RULES),
	RULES_PAGE_AUTO_LINK("rlPgAu", "true"),

	VOTE_PAGE1("vtPg1", "If you'd like to support us by voting for the server, please go here.", MessageType.VOTE),
	VOTE_PAGE2("vtPg2", "", MessageType.VOTE),
	VOTE_PAGE3("vtPg3", "", MessageType.VOTE),
	VOTE_PAGE_AUTO_LINK("vtPgAu", "true"),

	DONATE_PAGE1("dntPg1", "If you'd like to purchase a rank, you can do so here.", MessageType.DONATE),
	DONATE_PAGE2("dntPg2", "", MessageType.DONATE),
	DONATE_PAGE3("dntPg3", "", MessageType.DONATE),
	DONATE_PAGE_AUTO_LINK("dntPgAu", "true"),
	
	BAN_APPEAL_PAGE1("bnaPg1", "If you'd like to create a ban appeal, you can do so on this page.", MessageType.BAN_APPEALS),
	BAN_APPEAL_PAGE2("bnaPg2", "", MessageType.BAN_APPEALS),
	BAN_APPEAL_PAGE3("bnaPg3", "", MessageType.BAN_APPEALS),
	BAN_APPEAL_PAGE_AUTO_LINK("bnaPgAu", "true"),

	REPORT_PAGE1("rptPg1", "If you'd like to create a report, you can do so on this page.", MessageType.REPORTS),
	REPORT_PAGE2("rptPg2", "", MessageType.REPORTS),
	REPORT_PAGE3("rptPg3", "", MessageType.REPORTS),
	REPORT_PAGE_AUTO_LINK("rptPgAu", "true"),

	FORUM_PAGE1("frmPg1", "Our website can be found here.", MessageType.FORUM),
	FORUM_PAGE2("frmPg2", "", MessageType.FORUM),
	FORUM_PAGE3("frmPg3", "", MessageType.FORUM),
	FORUM_PAGE_AUTO_LINK("frmPgAu", "true"),

	EXPLAIN_TEAMING1("exTm1", "Teaming is when the gunner knows who the murderer is and purposefully shoots an innocent,", MessageType.EXPLAIN_TEAMING),
	EXPLAIN_TEAMING2("exTm2", "or refuses to shoot the murderer. It is against the rules to team.", MessageType.EXPLAIN_TEAMING),
	EXPLAIN_TEAMING3("exTm3", "", MessageType.EXPLAIN_TEAMING),

	EXPLAIN_ARGUE1("exArg1", "If you disagree with the punishment that was received, you may go to the forum to discuss the issue.", MessageType.EXPLAIN_ARGUE),
	EXPLAIN_ARGUE2("exArg2", URL.BAN_APPEALS, MessageType.EXPLAIN_ARGUE),
	EXPLAIN_ARGUE3("exArg3", "If you continue to debate over this punishment in-game, you will also receive a punishment.", MessageType.EXPLAIN_ARGUE),

	EXPLAIN_RDM1("exRDM1", "RDM, or random death match, is when a player has a gun and shoots a random person not knowing", MessageType.EXPLAIN_RDM),
	EXPLAIN_RDM2("exRDM2", "whether they're innocent or not. RDM is not against the rules, and is not to be confused", MessageType.EXPLAIN_RDM),
	EXPLAIN_RDM3("exRDM3", "with Teaming.", MessageType.EXPLAIN_RDM),

	CUSTOM_ONE_TITLE("cus1Ti", "Custom 1"),
	CUSTOM_ONE1("cus11", "", MessageType.CUSTOM1),
	CUSTOM_ONE2("cus12", "", MessageType.CUSTOM1),
	CUSTOM_ONE3("cus13", "", MessageType.CUSTOM1),

	CUSTOM_TWO_TITLE("cus2Ti", "Custom 2"),
	CUSTOM_TWO1("cus21", "", MessageType.CUSTOM2),
	CUSTOM_TWO2("cus22", "", MessageType.CUSTOM2),
	CUSTOM_TWO3("cus23", "", MessageType.CUSTOM2),

	CUSTOM_THREE_TITLE("cus3Ti", "Custom 3"),
	CUSTOM_THREE1("cus31", "", MessageType.CUSTOM3),
	CUSTOM_THREE2("cus32", "", MessageType.CUSTOM3),
	CUSTOM_THREE3("cus33", "", MessageType.CUSTOM3),

	CUSTOM_FOUR_TITLE("cus4Ti", "Custom 4"),
	CUSTOM_FOUR1("cus41", "", MessageType.CUSTOM4),
	CUSTOM_FOUR2("cus42", "", MessageType.CUSTOM4),
	CUSTOM_FOUR3("cus43", "", MessageType.CUSTOM4),
	
	HEAVY_CUSTOMIZATION("hvyCst", "false");
	
	private String key;
	private String value;
	private String originalValue;
	private MessageType type;

	/**
	 * An value with a key.
	 * 
	 * @param key the identifier of this
	 * @param value the value of this
	 */
	Option(String key, String value)
	{
		this(key, value, null);
	}
	
	/**
	 * An value with a key.
	 * 
	 * @param key the identifier of this
	 * @param value the value of this
	 */
	Option(String key, String value, MessageType type)
	{
		this.key = key;
		this.type = type;
		originalValue = "" + value;
		setString(value);
	}

	/**
	 * @return the identifier for this
	 */
	public String key()
	{
		return key;
	}

	/**
	 * @return this value as a String.
	 */
	public String value()
	{
		return value;
	}
	
	/**
	 * @return this value as a boolean.
	 */
	public boolean asBoolean()
	{
		return value.equals("true") ? true : false;
	}

	/**
	 * Sets this value as a String.
	 * 
	 * @param value the new value
	 */
	public void setString(String value)
	{
		this.value = value;
	}
	
	/**
	 * Sets this value as a Boolean.
	 * 
	 * @param value the new value
	 */
	public void setBoolean(boolean value)
	{
		this.value = (value ? "true" : "false");
	}
	
	/**
	 * @return the default value of 
	 */
	public String getDefault()
	{
		return originalValue;
	}
	
	public static List<String> getValues(MessageType type)
	{
		List<String> messages = new ArrayList<String>();
		
		for (Option message : Option.values())
		{
			if (message.type == type) messages.add(message.value());
		}
		
		return messages;
	}
}
