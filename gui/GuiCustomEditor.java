package net.playmcm.qwertysam.gui;

import java.io.IOException;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.resources.I18n;
import net.playmcm.qwertysam.io.OptionManager;

/**
 * The options menu for the backup mod.
 * 
 * @author Samson
 */
public class GuiCustomEditor extends GuiScreen
{
	/** The parent gui screen. **/
	protected final GuiScreen parentScreen;

	/** The custom text field where the user can input their own title **/
	protected GuiTextField customTitle;
	protected String customTitleKey;
	protected String originalTitle;

	/** The custom text field where the user can input their own first line of text **/
	protected GuiTextField customLine1;
	protected String customLine1Key;
	protected String originalLine1;

	/** The custom text field where the user can input their own second line of text **/
	protected GuiTextField customLine2;
	protected String customLine2Key;
	protected String originalLine2;

	/** The custom text field where the user can input their own second line of text **/
	protected GuiTextField customLine3;
	protected String customLine3Key;
	protected String originalLine3;

	protected OptionManager saveManager;

	/**
	 * The Gui that displays and allows you to change the options.
	 * 
	 * @param guiParentScreen = The parent gui screen.
	 */
	public GuiCustomEditor(GuiScreen guiParentScreen, OptionManager saveManager, String customTitleKey, String customLine1Key, String customLine2Key, String customLine3Key)
	{
		this.parentScreen = guiParentScreen;
		this.saveManager = saveManager;
		this.customTitleKey = customTitleKey;
		this.customLine1Key = customLine1Key;
		this.customLine2Key = customLine2Key;
		this.customLine3Key = customLine3Key;

		originalTitle = saveManager.getOption(customTitleKey).asString();
		originalLine1 = saveManager.getOption(customLine1Key).asString();
		originalLine2 = saveManager.getOption(customLine2Key).asString();
		originalLine3 = saveManager.getOption(customLine3Key).asString();
	}

	/**
	 * Saves all the current settings on the screen to the options file.
	 */
	private void saveSettings()
	{
		String newTitle = this.customTitle.getText();
		String newLine1 = this.customLine1.getText();
		String newLine2 = this.customLine2.getText();
		String newLine3 = this.customLine3.getText();

		saveManager.getOption(customTitleKey).setString(newTitle);
		saveManager.getOption(customLine1Key).setString(newLine1);
		saveManager.getOption(customLine2Key).setString(newLine2);
		saveManager.getOption(customLine3Key).setString(newLine3);
		// Only save options if something's been modified
		if (!(newTitle.equals(originalTitle) && newLine1.equals(originalLine1) && newLine2.equals(originalLine2) && newLine3.equals(originalLine3)))
		{
			saveManager.saveOptions();
		}
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
				this.saveSettings();
				this.mc.displayGuiScreen(this.parentScreen);
				break;
			case 3:
				this.customTitle.setText(saveManager.getOption(customTitleKey).getDefault());
				this.customLine1.setText(saveManager.getOption(customLine1Key).getDefault());
				this.customLine2.setText(saveManager.getOption(customLine2Key).getDefault());
				this.customLine3.setText(saveManager.getOption(customLine3Key).getDefault());
				break;
		}
	}

	/**
	 * Called from the main game loop to update the screen.
	 */
	@Override
	public void updateScreen()
	{
		this.customTitle.updateCursorCounter();
		this.customLine1.updateCursorCounter();
		this.customLine2.updateCursorCounter();
		this.customLine3.updateCursorCounter();
	}

	/**
	 * Adds the buttons (and other controls) to the screen in question.
	 */
	@Override
	public void initGui()
	{
		this.buttonList.add(new GuiButton(200, this.width / 2 - 100, 204, I18n.format("gui.done", new Object[0])));

		this.buttonList.add(new GuiButton(3, this.width / 2 + 4, 50, 100, 20, "Reset Fields"));

		Keyboard.enableRepeatEvents(true);
		this.customTitle = new GuiTextField(0, this.fontRendererObj, this.width / 2 - 104, 50, 100, 20);
		this.customTitle.setText(originalTitle);
		this.customTitle.setMaxStringLength(16);

		this.customLine1 = new GuiTextField(1, this.fontRendererObj, this.width / 2 - 200, 90, 400, 20);
		this.customLine1.setMaxStringLength(100);
		this.customLine1.setText(originalLine1);

		this.customLine2 = new GuiTextField(2, this.fontRendererObj, this.width / 2 - 200, 130, 400, 20);
		this.customLine2.setMaxStringLength(100);
		this.customLine2.setText(originalLine2);

		this.customLine3 = new GuiTextField(3, this.fontRendererObj, this.width / 2 - 200, 170, 400, 20);
		this.customLine3.setMaxStringLength(100);
		this.customLine3.setText(originalLine3);
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
		this.customTitle.textboxKeyTyped(characterTyped, keyCode);
		this.customLine1.textboxKeyTyped(characterTyped, keyCode);
		this.customLine2.textboxKeyTyped(characterTyped, keyCode);
		this.customLine3.textboxKeyTyped(characterTyped, keyCode);

		if (keyCode == 28 || keyCode == 156)
		{
			try
			{
				this.actionPerformed((GuiButton) this.buttonList.get(0));
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}

		((GuiButton) this.buttonList.get(0)).enabled = this.customTitle.getText().length() > 0;
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

		this.customTitle.mouseClicked(p_73864_1_, p_73864_2_, p_73864_3_);
		this.customLine1.mouseClicked(p_73864_1_, p_73864_2_, p_73864_3_);
		this.customLine2.mouseClicked(p_73864_1_, p_73864_2_, p_73864_3_);
		this.customLine3.mouseClicked(p_73864_1_, p_73864_2_, p_73864_3_);
	}

	/** Tells Minecraft it's ready to exit the gui as soon as the player lets go of ESC. */
	private boolean waitForRelease = false;

	/**
	 * Draws the screen and all the components in it.
	 */
	@Override
	public void drawScreen(int par1, int par2, float par3)
	{
		this.drawDefaultBackground();
		this.drawCenteredString(this.fontRendererObj, "Custom Preset Editor", this.width / 2, 18, 16777215);

		this.drawString(this.fontRendererObj, "Title", this.width / 2 - 84, 38, 16777215);

		this.drawString(this.fontRendererObj, "Line 1", this.width / 2 - 180, 78, 16777215);

		this.drawString(this.fontRendererObj, "Line 2", this.width / 2 - 180, 118, 16777215);

		this.drawString(this.fontRendererObj, "Line 3", this.width / 2 - 180, 158, 16777215);

		this.customTitle.drawTextBox();
		this.customLine1.drawTextBox();
		this.customLine2.drawTextBox();
		this.customLine3.drawTextBox();

		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
		{
			this.mc.displayGuiScreen(parentScreen);
		}

		super.drawScreen(par1, par2, par3);
	}
}
