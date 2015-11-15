package net.playmcm.qwertysam.gui;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.resources.I18n;
import net.playmcm.qwertysam.io.SaveHandling;

public class GuiCustomThreeEditor extends GuiCustomMessages
{
	public GuiCustomThreeEditor(GuiScreen guiParentScreen)
	{
		super(guiParentScreen);
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
		this.customTitle.setText(SaveHandling.getCustomThreeTitle());
		this.customTitle.setMaxStringLength(16);

		this.customLine1 = new GuiTextField(1, this.fontRendererObj, this.width / 2 - 200, 100, 400, 20);
		this.customLine1.setMaxStringLength(100);
		this.customLine1.setText(SaveHandling.getCustomThree1());

		this.customLine2 = new GuiTextField(2, this.fontRendererObj, this.width / 2 - 200, 150, 400, 20);
		this.customLine2.setMaxStringLength(100);
		this.customLine2.setText(SaveHandling.getCustomThree2());
	}

	/**
	 * Saves all the current settings on the screen to the options file.
	 */
	private void saveSettings()
	{
		SaveHandling.setCustomThreeTitle(this.customTitle.getText());
		SaveHandling.setCustomThree1(this.customLine1.getText());
		SaveHandling.setCustomThree2(this.customLine2.getText());
		SaveHandling.saveOptions();
	}
}
