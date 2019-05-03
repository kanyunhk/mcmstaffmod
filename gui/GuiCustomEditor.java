package mcmstaffmod.gui;

import java.io.IOException;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.resources.I18n;
import mcmstaffmod.ModMain;
import mcmstaffmod.io.OptionManager;
import mcmstaffmod.util.KeyPress;

/**
 * The options menu for the backup mod.
 * 
 * @author Samson
 */
public class GuiCustomEditor extends GuiFloatingTextAPI
{
	/** The parent gui screen. **/
	protected final GuiScreen parentScreen;

	protected String autoLinkKey;

	/** The custom text field where the user can input their own title **/
	protected GuiTextField customTitle;
	protected String customTitleKey;

	/** The custom text field where the user can input their own first line of text **/
	protected GuiTextField customLine1;
	protected String customLine1Key;

	/** The custom text field where the user can input their own second line of text **/
	protected GuiTextField customLine2;
	protected String customLine2Key;

	/** The custom text field where the user can input their own second line of text **/
	protected GuiTextField customLine3;
	protected String customLine3Key;

	protected OptionManager saveManager;

	protected boolean titleEdit;

	private KeyPress escKey;
	private KeyPress gravKey;

	/**
	 * The Gui that displays and allows you to change the options.
	 * 
	 * @param guiParentScreen = The parent gui screen.
	 */
	public GuiCustomEditor(GuiScreen guiParentScreen, OptionManager saveManager, String customTitleKey, String customLine1Key, String customLine2Key, String customLine3Key, boolean titleEdit)
	{
		this(guiParentScreen, saveManager, null, customTitleKey, customLine1Key, customLine2Key, customLine3Key, titleEdit);
	}

	/**
	 * The Gui that displays and allows you to change the options.
	 * 
	 * @param guiParentScreen = The parent gui screen.
	 */
	public GuiCustomEditor(GuiScreen guiParentScreen, OptionManager saveManager, String autoLinkKey, String customTitleKey, String customLine1Key, String customLine2Key, String customLine3Key, boolean titleEdit)
	{
		parentScreen = guiParentScreen;
		this.saveManager = saveManager;
		this.autoLinkKey = autoLinkKey;
		this.customTitleKey = customTitleKey;
		this.customLine1Key = customLine1Key;
		this.customLine2Key = customLine2Key;
		this.customLine3Key = customLine3Key;

		this.titleEdit = titleEdit;

		escKey = new KeyPress(Keyboard.KEY_ESCAPE, false);
		gravKey = new KeyPress(ModMain.KEYBOARD_SHORTCUT, false);
	}

	public boolean hasLinkToggle()
	{
		return autoLinkKey != null;
	}

	/**
	 * Saves all the current settings on the screen to the options file.
	 */
	private void saveSettings()
	{
		if (titleEdit) saveManager.getOption(customTitleKey).setString(customTitle.getText());
		saveManager.getOption(customLine1Key).setString(customLine1.getText());
		saveManager.getOption(customLine2Key).setString(customLine2.getText());
		saveManager.getOption(customLine3Key).setString(customLine3.getText());

		saveManager.saveOptions();
	}

	/**
	 * Fired when a button is clicked.
	 * 
	 * @param button = The ID of the button that was clicked.
	 */
	@Override
	protected void actionPerformed(GuiButton button) throws IOException
	{
		switch (button.id)
		{
			case 200:
				saveSettings();
				mc.displayGuiScreen(parentScreen);
				break;
			case 3:
				if (titleEdit) customTitle.setText(saveManager.getOption(customTitleKey).getDefault());
				customLine1.setText(saveManager.getOption(customLine1Key).getDefault());
				customLine2.setText(saveManager.getOption(customLine2Key).getDefault());
				customLine3.setText(saveManager.getOption(customLine3Key).getDefault());

				if (hasLinkToggle()) saveManager.getOption(autoLinkKey).setString(saveManager.getOption(autoLinkKey).getDefault());
				saveSettings();
				mc.displayGuiScreen(new GuiCustomEditor(parentScreen, saveManager, autoLinkKey, customTitleKey, customLine1Key, customLine2Key, customLine3Key, titleEdit));
				break;
			case 27:
				saveManager.getOption(autoLinkKey).setBoolean(!saveManager.getOption(autoLinkKey).asBoolean());
				saveSettings();
				mc.displayGuiScreen(new GuiCustomEditor(parentScreen, saveManager, autoLinkKey, customTitleKey, customLine1Key, customLine2Key, customLine3Key, titleEdit));
				break;
		}
	}

	/**
	 * Called from the main game loop to update the screen.
	 */
	@Override
	public void updateScreen()
	{
		if (titleEdit) customTitle.updateCursorCounter();
		customLine1.updateCursorCounter();
		customLine2.updateCursorCounter();
		customLine3.updateCursorCounter();
	}

	/**
	 * Adds the buttons (and other controls) to the screen in question.
	 */
	@Override
	public void initGui()
	{
		buttonList.add(new GuiButton(200, width / 2 - 100, 204, I18n.format("gui.done", new Object[0])));

		buttonList.add(new GuiButton(3, width / 2 + 4, 50, 100, 20, "Reset Fields"));

		Keyboard.enableRepeatEvents(true);

		if (titleEdit)
		{
			customTitle = new GuiTextField(0, fontRenderer, width / 2 - 104, 50, 100, 20);
			customTitle.setText(saveManager.getOption(customTitleKey).value());
			customTitle.setMaxStringLength(16);
		}

		if (hasLinkToggle())
		{
			buttonList.add(new GuiIconButton(27, "Auto-Send Link", width / 2 + 108, 50, (saveManager.getOption(autoLinkKey).asBoolean() ? 120 : 140), 0));
		}

		customLine1 = new GuiTextField(1, fontRenderer, width / 2 - 200, 90, 400, 20);
		customLine1.setMaxStringLength(100);
		customLine1.setText(saveManager.getOption(customLine1Key).value());

		customLine2 = new GuiTextField(2, fontRenderer, width / 2 - 200, 130, 400, 20);
		customLine2.setMaxStringLength(100);
		customLine2.setText(saveManager.getOption(customLine2Key).value());

		customLine3 = new GuiTextField(3, fontRenderer, width / 2 - 200, 170, 400, 20);
		customLine3.setMaxStringLength(100);
		customLine3.setText(saveManager.getOption(customLine3Key).value());
	}

	/**
	 * Returns true if this GUI should pause the game when it is displayed in single-player
	 */
	@Override
	public boolean doesGuiPauseGame()
	{
		return false;
	}

	/**
	 * Fired when a key is typed. This is the equivalent of KeyListener.keyTyped(KeyEvent e).
	 */
	@Override
	protected void keyTyped(char characterTyped, int keyCode)
	{
		if (titleEdit) customTitle.textboxKeyTyped(characterTyped, keyCode);
		customLine1.textboxKeyTyped(characterTyped, keyCode);
		customLine2.textboxKeyTyped(characterTyped, keyCode);
		customLine3.textboxKeyTyped(characterTyped, keyCode);

		switch (keyCode)
		{
			case 28: // Enter
			case 156: // ? (possibly num pad enter?)
				try
				{
					actionPerformed((GuiButton) buttonList.get(0));

				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
				break;
			case 15: // TAB (used to change focus)
				if (titleEdit && customTitle.isFocused())
				{
					customTitle.setFocused(false);
					customLine1.setFocused(true);
				}
				else if (customLine1.isFocused())
				{
					customLine1.setFocused(false);
					customLine2.setFocused(true);
				}
				else if (customLine2.isFocused())
				{
					customLine2.setFocused(false);
					customLine3.setFocused(true);
				}
				else if (customLine3.isFocused())
				{
					customLine3.setFocused(false);

					if (titleEdit)
					{
						customTitle.setFocused(true);
					}
					else
					{
						customLine1.setFocused(true);
					}
				}
				break;
		}

		if (titleEdit) ((GuiButton) buttonList.get(0)).enabled = customTitle.getText().length() > 0;
	}

	/**
	 * Called when the mouse is clicked.
	 */
	@Override
	protected void mouseClicked(int p_73864_1_, int p_73864_2_, int p_73864_3_)
	{
		try
		{
			super.mouseClicked(p_73864_1_, p_73864_2_, p_73864_3_);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		if (titleEdit) customTitle.mouseClicked(p_73864_1_, p_73864_2_, p_73864_3_);
		customLine1.mouseClicked(p_73864_1_, p_73864_2_, p_73864_3_);
		customLine2.mouseClicked(p_73864_1_, p_73864_2_, p_73864_3_);
		customLine3.mouseClicked(p_73864_1_, p_73864_2_, p_73864_3_);
	}
	
	public void exitGui()
	{
		saveSettings();
		mc.displayGuiScreen(parentScreen);
	}

	/**
	 * Draws the screen and all the components in it.
	 */
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);

		drawCenteredString(fontRenderer, "Custom Preset Editor", width / 2, 18, 16777215);

		if (titleEdit)
		{
			drawString(fontRenderer, "Title", width / 2 - 84, 38, 16777215);
		}
		else
		{
			// Renders the original title from the right
			drawString(fontRenderer, customTitleKey, width / 2 - fontRenderer.getStringWidth(customTitleKey) - 18, 56, 16777215);
		}

		drawString(fontRenderer, "Line 1", width / 2 - 180, 78, 16777215);

		drawString(fontRenderer, "Line 2", width / 2 - 180, 118, 16777215);

		drawString(fontRenderer, "Line 3", width / 2 - 180, 158, 16777215);

		if (titleEdit) customTitle.drawTextBox();
		customLine1.drawTextBox();
		customLine2.drawTextBox();
		customLine3.drawTextBox();

		// Doesn't let you hold grave while
		if (escKey.isTriggered() || (gravKey.isTriggered() && (titleEdit ? !customTitle.isFocused() : true) && !customLine1.isFocused() && !customLine2.isFocused() && !customLine3.isFocused()))
		{
			exitGui();
		}

		drawFloatingText(mouseX, mouseY);
	}
}
