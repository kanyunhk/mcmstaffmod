package qwertysam.gui;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiYesNo;
import qwertysam.ModMain;
import qwertysam.io.Option;
import qwertysam.messages.api.MessageSender;
import qwertysam.messages.api.MessageType;
import qwertysam.messages.api.URL;
import qwertysam.util.UrlOpener;

public class ModGui extends GuiFloatingTextAPI
{
	/** The pixels between the GUI and the top of the screen **/
	private static final int spaceFromTop = 36;

	/** The horizontal spacing between the buttons **/
	public static final int xSpacing = 4;

	/** The vertical spacing between the buttons **/
	public static final int ySpacing = 20 + 6;

	/** The width of the buttons **/
	public static final int smallButtonWidth = 120;

	/** The width of the buttons **/
	public static final int linkButtonWidth = smallButtonWidth - 20 - xSpacing;

	private ModMain mod;

	public ModGui(ModMain mod)
	{
		super();
		this.mod = mod;
	}

	@Override
	public void initGui()
	{
		buttonList.clear();

		// buttonList.add(new GuiIconButton(47, width / 2 + smallButtonWidth + (xSpacing * 2), spaceFromTop + 20, 20, 0, 20, 20));

		boolean customize = mod.getOptions().getOption(Option.HEAVY_CUSTOMIZATION.key()).asBoolean();

		if (customize)
		{
			// How to become mod
			buttonList.add(new GuiButton(0, width / 2 - smallButtonWidth - (xSpacing * 2) - 20, spaceFromTop, linkButtonWidth, 20, "Becoming Staff"));
			buttonList.add(new GuiIconButton(1, width / 2 - smallButtonWidth + linkButtonWidth - (xSpacing + 20), spaceFromTop, smallButtonWidth, 0));
			buttonList.add(new GuiIconButton(33, width / 2 - smallButtonWidth + linkButtonWidth, spaceFromTop, 0, 0));

			// Rules Link
			buttonList.add(new GuiButton(2, width / 2 - smallButtonWidth - (xSpacing * 2) - 20, spaceFromTop + (ySpacing * 1), linkButtonWidth, 20, "Rules"));
			buttonList.add(new GuiIconButton(3, width / 2 - smallButtonWidth + linkButtonWidth - (xSpacing + 20), spaceFromTop + (ySpacing * 1), smallButtonWidth, 0));
			buttonList.add(new GuiIconButton(34, width / 2 - smallButtonWidth + linkButtonWidth, spaceFromTop + (ySpacing * 1), 0, 0));

			// Reports Link
			buttonList.add(new GuiButton(4, width / 2 - smallButtonWidth - (xSpacing * 2) - 20, spaceFromTop + (ySpacing * 2), linkButtonWidth, 20, "Reporting"));
			buttonList.add(new GuiIconButton(5, width / 2 - smallButtonWidth + linkButtonWidth - (xSpacing + 20), spaceFromTop + (ySpacing * 2), smallButtonWidth, 0));
			buttonList.add(new GuiIconButton(35, width / 2 - smallButtonWidth + linkButtonWidth, spaceFromTop + (ySpacing * 2), 0, 0));

			// Ban Appeals Link
			buttonList.add(new GuiButton(10, width / 2 - smallButtonWidth - (xSpacing * 2) - 20, spaceFromTop + (ySpacing * 3), linkButtonWidth, 20, "Ban Appeals"));
			buttonList.add(new GuiIconButton(11, width / 2 - smallButtonWidth + linkButtonWidth - (xSpacing + 20), spaceFromTop + (ySpacing * 3), smallButtonWidth, 0));
			buttonList.add(new GuiIconButton(36, width / 2 - smallButtonWidth + linkButtonWidth, spaceFromTop + (ySpacing * 3), 0, 0));

			// Vote Link
			buttonList.add(new GuiButton(6, width / 2 - smallButtonWidth - (xSpacing * 2) - 20, spaceFromTop + (ySpacing * 4), linkButtonWidth, 20, "Voting"));
			buttonList.add(new GuiIconButton(7, width / 2 - smallButtonWidth + linkButtonWidth - (xSpacing + 20), spaceFromTop + (ySpacing * 4), smallButtonWidth, 0));
			buttonList.add(new GuiIconButton(37, width / 2 - smallButtonWidth + linkButtonWidth, spaceFromTop + (ySpacing * 4), 0, 0));

			// Donation Link
			buttonList.add(new GuiButton(8, width / 2 - smallButtonWidth - (xSpacing * 2) - 20, spaceFromTop + (ySpacing * 5), linkButtonWidth, 20, "Donating"));
			buttonList.add(new GuiIconButton(9, width / 2 - smallButtonWidth + linkButtonWidth - (xSpacing + 20), spaceFromTop + (ySpacing * 5), smallButtonWidth, 0));
			buttonList.add(new GuiIconButton(38, width / 2 - smallButtonWidth + linkButtonWidth, spaceFromTop + (ySpacing * 5), 0, 0));

			// Forum Link
			buttonList.add(new GuiButton(25, width / 2 - smallButtonWidth - (xSpacing * 2) - 20, spaceFromTop + (ySpacing * 6), linkButtonWidth, 20, "Forum Link"));
			buttonList.add(new GuiIconButton(26, width / 2 - smallButtonWidth + linkButtonWidth - (xSpacing + 20), spaceFromTop + (ySpacing * 6), smallButtonWidth, 0));
			buttonList.add(new GuiIconButton(39, width / 2 - smallButtonWidth + linkButtonWidth, spaceFromTop + (ySpacing * 6), 0, 0));

			// Explain Teaming
			buttonList.add(new GuiButton(12, width / 2 + (xSpacing * 2) + 20, spaceFromTop, linkButtonWidth, 20, "Explain Teaming"));
			buttonList.add(new GuiIconButton(31, width / 2 + xSpacing, spaceFromTop, 0, 0, 20, 20));

			// Explain Arguing
			buttonList.add(new GuiButton(13, width / 2 + (xSpacing * 2) + 20, spaceFromTop + (ySpacing * 1), linkButtonWidth, 20, "Explain Arguing"));
			buttonList.add(new GuiIconButton(30, width / 2 + xSpacing, spaceFromTop + (ySpacing * 1), 0, 0, 20, 20));

			// Explain RDM
			buttonList.add(new GuiButton(14, width / 2 + (xSpacing * 2) + 20, spaceFromTop + (ySpacing * 2), linkButtonWidth, 20, "Explain RDM"));
			buttonList.add(new GuiIconButton(29, width / 2 + xSpacing, spaceFromTop + (ySpacing * 2), 0, 0, 20, 20));
		}
		else
		{
			// How to become mod
			buttonList.add(new GuiButton(0, width / 2 - smallButtonWidth - xSpacing, spaceFromTop, linkButtonWidth, 20, "Becoming Staff"));
			buttonList.add(new GuiIconButton(1, width / 2 - smallButtonWidth + linkButtonWidth, spaceFromTop, smallButtonWidth, 0));

			// Rules Link
			buttonList.add(new GuiButton(2, width / 2 - smallButtonWidth - xSpacing, spaceFromTop + (ySpacing * 1), linkButtonWidth, 20, "Rules"));
			buttonList.add(new GuiIconButton(3, width / 2 - smallButtonWidth + linkButtonWidth, spaceFromTop + (ySpacing * 1), smallButtonWidth, 0));

			// Reports Link
			buttonList.add(new GuiButton(4, width / 2 - smallButtonWidth - xSpacing, spaceFromTop + (ySpacing * 2), linkButtonWidth, 20, "Reporting"));
			buttonList.add(new GuiIconButton(5, width / 2 - smallButtonWidth + linkButtonWidth, spaceFromTop + (ySpacing * 2), smallButtonWidth, 0));

			// Ban Appeals Link
			buttonList.add(new GuiButton(10, width / 2 - smallButtonWidth - xSpacing, spaceFromTop + (ySpacing * 3), linkButtonWidth, 20, "Ban Appeals"));
			buttonList.add(new GuiIconButton(11, width / 2 - smallButtonWidth + linkButtonWidth, spaceFromTop + (ySpacing * 3), smallButtonWidth, 0));

			// Vote Link
			buttonList.add(new GuiButton(6, width / 2 - smallButtonWidth - xSpacing, spaceFromTop + (ySpacing * 4), linkButtonWidth, 20, "Voting"));
			buttonList.add(new GuiIconButton(7, width / 2 - smallButtonWidth + linkButtonWidth, spaceFromTop + (ySpacing * 4), smallButtonWidth, 0));

			// Donation Link
			buttonList.add(new GuiButton(8, width / 2 - smallButtonWidth - xSpacing, spaceFromTop + (ySpacing * 5), linkButtonWidth, 20, "Donating"));
			buttonList.add(new GuiIconButton(9, width / 2 - smallButtonWidth + linkButtonWidth, spaceFromTop + (ySpacing * 5), smallButtonWidth, 0));

			// Forum Link
			buttonList.add(new GuiButton(25, width / 2 - smallButtonWidth - xSpacing, spaceFromTop + (ySpacing * 6), linkButtonWidth, 20, "Forum Link"));
			buttonList.add(new GuiIconButton(26, width / 2 - smallButtonWidth + linkButtonWidth, spaceFromTop + (ySpacing * 6), smallButtonWidth, 0));

			// Explain Teaming
			buttonList.add(new GuiButton(12, width / 2 + xSpacing, spaceFromTop, smallButtonWidth, 20, "Explain Teaming"));

			// Explain Arguing
			buttonList.add(new GuiButton(13, width / 2 + xSpacing, spaceFromTop + (ySpacing * 1), smallButtonWidth, 20, "Explain Arguing"));

			// Explain RDM
			buttonList.add(new GuiButton(14, width / 2 + xSpacing, spaceFromTop + (ySpacing * 2), smallButtonWidth, 20, "Explain RDM"));
		}

		// Custom 1
		buttonList.add(new GuiButton(15, width / 2 + (xSpacing * 2) + 20, spaceFromTop + (ySpacing * 3), linkButtonWidth, 20, mod.getOptions().getOption(Option.CUSTOM_ONE_TITLE.key()).value()));
		buttonList.add(new GuiIconButton(16, width / 2 + xSpacing, spaceFromTop + (ySpacing * 3), 0, 0, 20, 20));

		// Custom 2
		buttonList.add(new GuiButton(17, width / 2 + (xSpacing * 2) + 20, spaceFromTop + (ySpacing * 4), linkButtonWidth, 20, mod.getOptions().getOption(Option.CUSTOM_TWO_TITLE.key()).value()));
		buttonList.add(new GuiIconButton(18, width / 2 + xSpacing, spaceFromTop + (ySpacing * 4), 0, 0, 20, 20));

		// Custom 3
		buttonList.add(new GuiButton(23, width / 2 + (xSpacing * 2) + 20, spaceFromTop + (ySpacing * 5), linkButtonWidth, 20, mod.getOptions().getOption(Option.CUSTOM_THREE_TITLE.key()).value()));
		buttonList.add(new GuiIconButton(24, width / 2 + xSpacing, spaceFromTop + (ySpacing * 5), 0, 0, 20, 20));

		// Custom 4
		buttonList.add(new GuiButton(27, width / 2 + (xSpacing * 2) + 20, spaceFromTop + (ySpacing * 6), linkButtonWidth, 20, mod.getOptions().getOption(Option.CUSTOM_FOUR_TITLE.key()).value()));
		buttonList.add(new GuiIconButton(28, width / 2 + xSpacing, spaceFromTop + (ySpacing * 6), 0, 0, 20, 20));

		int yAnchor = (customize ? 150 : 139);

		if (customize) buttonList.add(new GuiIconButton(40, "Reset All Settings", width / 2 + 148, yAnchor - 80 - (xSpacing * 4), 140, 0));
		buttonList.add(new GuiIconButton(32, "Toggle Customization", width / 2 + 148, yAnchor - 60 - (xSpacing * 3), 0, 0));
		buttonList.add(new GuiIconButton(19, "Command Help", width / 2 + 148, yAnchor - 40 - (xSpacing * 2), 80, 0, 20, 20));
		buttonList.add(new GuiIconButton(20, "MCM Forums", width / 2 + 148, yAnchor - 20 - xSpacing, 60, 0, 20, 20));
		buttonList.add(new GuiIconButton(21, "Staff Guidelines", width / 2 + 148, yAnchor, 40, 0, 20, 20));
		buttonList.add(new GuiIconButton(22, "Trello", width / 2 + 148, yAnchor + 20 + (xSpacing * 1), 100, 0, 20, 20));
	}

	/**
	 * Fires when a button is clicked.
	 * 
	 * @param button the button that was clicked
	 */
	@Override
	protected void actionPerformed(GuiButton button) throws IOException
	{
		switch (button.id)
		{
			case 0:
				MessageSender.sendMessage(MessageType.BECOME_STAFF, mod.getOptions().getOption(Option.BECOMING_STAFF_AUTO_LINK.key()).asBoolean());
				exitGui();
				break;
			case 1:
				MessageSender.sendMessage(MessageType.BECOME_STAFF, true, true);
				exitGui();
				break;
			case 2:
				MessageSender.sendMessage(MessageType.RULES, mod.getOptions().getOption(Option.RULES_PAGE_AUTO_LINK.key()).asBoolean());
				exitGui();
				break;
			case 3:
				MessageSender.sendMessage(MessageType.RULES, true, true);
				exitGui();
				break;
			case 4:
				MessageSender.sendMessage(MessageType.REPORTS, mod.getOptions().getOption(Option.REPORT_PAGE_AUTO_LINK.key()).asBoolean());
				exitGui();
				break;
			case 5:
				MessageSender.sendMessage(MessageType.REPORTS, true, true);
				exitGui();
				break;
			case 6:
				MessageSender.sendMessage(MessageType.VOTE, mod.getOptions().getOption(Option.VOTE_PAGE_AUTO_LINK.key()).asBoolean());
				exitGui();
				break;
			case 7:
				MessageSender.sendMessage(MessageType.VOTE, true, true);
				exitGui();
				break;
			case 8:
				MessageSender.sendMessage(MessageType.DONATE, mod.getOptions().getOption(Option.DONATE_PAGE_AUTO_LINK.key()).asBoolean());
				exitGui();
				break;
			case 9:
				MessageSender.sendMessage(MessageType.DONATE, true, true);
				exitGui();
				break;
			case 10:
				MessageSender.sendMessage(MessageType.BAN_APPEALS, mod.getOptions().getOption(Option.BAN_APPEAL_PAGE_AUTO_LINK.key()).asBoolean());
				exitGui();
				break;
			case 11:
				MessageSender.sendMessage(MessageType.BAN_APPEALS, true, true);
				exitGui();
				break;
			case 12:
				MessageSender.sendMessage(MessageType.EXPLAIN_TEAMING, false);
				exitGui();
				break;
			case 13:
				MessageSender.sendMessage(MessageType.EXPLAIN_ARGUE, false);
				exitGui();
				break;
			case 14:
				MessageSender.sendMessage(MessageType.EXPLAIN_RDM, false);
				exitGui();
				break;
			case 15:
				MessageSender.sendMessage(MessageType.CUSTOM1, false);
				exitGui();
				break;
			case 16:
				mc.displayGuiScreen(new GuiCustomEditor(this, mod.getOptions(), Option.CUSTOM_ONE_TITLE.key(), Option.CUSTOM_ONE1.key(), Option.CUSTOM_ONE2.key(), Option.CUSTOM_ONE3.key(), true));
				break;
			case 17:
				MessageSender.sendMessage(MessageType.CUSTOM2, false);
				exitGui();
				break;
			case 18:
				mc.displayGuiScreen(new GuiCustomEditor(this, mod.getOptions(), Option.CUSTOM_TWO_TITLE.key(), Option.CUSTOM_TWO1.key(), Option.CUSTOM_TWO2.key(), Option.CUSTOM_TWO3.key(), true));
				break;
			case 19:
				UrlOpener.openURL(URL.COMMAND_HELP);
				exitGui();
				break;
			case 20:
				UrlOpener.openURL(URL.WEBSITE); // Forum Page
				exitGui();
				break;
			case 21:
				UrlOpener.openURL(URL.GUIDELINES); // Mod Guidelines Documentation page
				exitGui();
				break;
			case 22:
				UrlOpener.openURL(URL.TRELLO); // Bugs and Suggestions page
				exitGui();
				break;
			case 23:
				MessageSender.sendMessage(MessageType.CUSTOM3, false);
				exitGui();
				break;
			case 24:
				mc.displayGuiScreen(new GuiCustomEditor(this, mod.getOptions(), Option.CUSTOM_THREE_TITLE.key(), Option.CUSTOM_THREE1.key(), Option.CUSTOM_THREE2.key(), Option.CUSTOM_THREE3.key(), true));
				break;
			case 25:
				MessageSender.sendMessage(MessageType.FORUM, mod.getOptions().getOption(Option.FORUM_PAGE_AUTO_LINK.key()).asBoolean());
				exitGui();
				break;
			case 26:
				MessageSender.sendMessage(MessageType.FORUM, true, true);
				exitGui();
				break;
			case 27:
				MessageSender.sendMessage(MessageType.CUSTOM4, false);
				exitGui();
				break;
			case 28:
				mc.displayGuiScreen(new GuiCustomEditor(this, mod.getOptions(), Option.CUSTOM_FOUR_TITLE.key(), Option.CUSTOM_FOUR1.key(), Option.CUSTOM_FOUR2.key(), Option.CUSTOM_FOUR3.key(), true));
				break;
			case 29:
				mc.displayGuiScreen(new GuiCustomEditor(this, mod.getOptions(), "Explain RDM", Option.EXPLAIN_RDM1.key(), Option.EXPLAIN_RDM2.key(), Option.EXPLAIN_RDM3.key(), false));
				break;
			case 30:
				mc.displayGuiScreen(new GuiCustomEditor(this, mod.getOptions(), "Explain Argue", Option.EXPLAIN_ARGUE1.key(), Option.EXPLAIN_ARGUE2.key(), Option.EXPLAIN_ARGUE3.key(), false));
				break;
			case 31:
				mc.displayGuiScreen(new GuiCustomEditor(this, mod.getOptions(), "Explain Teaming", Option.EXPLAIN_TEAMING1.key(), Option.EXPLAIN_TEAMING2.key(), Option.EXPLAIN_TEAMING3.key(), false));
				break;
			case 32:
				mod.getOptions().getOption(Option.HEAVY_CUSTOMIZATION.key()).setBoolean(!mod.getOptions().getOption(Option.HEAVY_CUSTOMIZATION.key()).asBoolean());
				mod.getOptions().saveOptions();
				mc.displayGuiScreen(new ModGui(mod));
				break;
			case 33:
				mc.displayGuiScreen(new GuiCustomEditor(this, mod.getOptions(), Option.BECOMING_STAFF_AUTO_LINK.key(), "How To Become Staff", Option.BECOMING_STAFF1.key(), Option.BECOMING_STAFF2.key(), Option.BECOMING_STAFF3.key(), false));
				break;
			case 34:
				mc.displayGuiScreen(new GuiCustomEditor(this, mod.getOptions(), Option.RULES_PAGE_AUTO_LINK.key(), "Rules", Option.RULES_PAGE1.key(), Option.RULES_PAGE2.key(), Option.RULES_PAGE3.key(), false));
				break;
			case 35:
				mc.displayGuiScreen(new GuiCustomEditor(this, mod.getOptions(), Option.REPORT_PAGE_AUTO_LINK.key(), "Reporting", Option.REPORT_PAGE1.key(), Option.REPORT_PAGE2.key(), Option.REPORT_PAGE3.key(), false));
				break;
			case 36:
				mc.displayGuiScreen(new GuiCustomEditor(this, mod.getOptions(), Option.BAN_APPEAL_PAGE_AUTO_LINK.key(), "Ban Appeals", Option.BAN_APPEAL_PAGE1.key(), Option.BAN_APPEAL_PAGE2.key(), Option.BAN_APPEAL_PAGE3.key(), false));
				break;
			case 37:
				mc.displayGuiScreen(new GuiCustomEditor(this, mod.getOptions(), Option.VOTE_PAGE_AUTO_LINK.key(), "Voting", Option.VOTE_PAGE1.key(), Option.VOTE_PAGE2.key(), Option.VOTE_PAGE3.key(), false));
				break;
			case 38:
				mc.displayGuiScreen(new GuiCustomEditor(this, mod.getOptions(), Option.DONATE_PAGE_AUTO_LINK.key(), "Donating", Option.DONATE_PAGE1.key(), Option.DONATE_PAGE2.key(), Option.DONATE_PAGE3.key(), false));
				break;
			case 39:
				mc.displayGuiScreen(new GuiCustomEditor(this, mod.getOptions(), Option.FORUM_PAGE_AUTO_LINK.key(), "Forum Link", Option.FORUM_PAGE1.key(), Option.FORUM_PAGE2.key(), Option.FORUM_PAGE3.key(), false));
				break;
			case 40:
				mc.displayGuiScreen(new GuiYesNo(this, "Are you sure that you want to reset all of your", "customization? (Cannot be undone)", 40));
				break;
		}
	}

	@Override
	public void confirmClicked(boolean clickedYes, int parentButtonClickedId)
	{
		if (clickedYes)
		{
			mod.getOptions().defaultResetOptions();
		}

		mc.displayGuiScreen(new ModGui(mod));
	}

	@Override
	public boolean doesGuiPauseGame()
	{
		return false;
	}

	/**
	 * Exits the GUI.
	 */
	public void exitGui()
	{
		mod.getOptions().saveOptions();
		mc.displayGuiScreen((GuiScreen) null);
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		super.drawScreen(mouseX, mouseY, partialTicks);

		drawCenteredString(fontRendererObj, "Staff Tools", width / 2, spaceFromTop - 16, 16777215);

		drawFloatingText(mouseX, mouseY);
	}
}
