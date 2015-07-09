package net.playmcm.qwertysam.gui;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.lwjgl.input.Keyboard;
import org.lwjgl.util.Color;

import com.google.common.collect.Lists;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiShareToLan;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.achievement.GuiAchievements;
import net.minecraft.client.gui.achievement.GuiStats;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.playmcm.qwertysam.ModMain;
import net.playmcm.qwertysam.io.SaveHandling;
import net.playmcm.qwertysam.util.MessageSender;
import net.playmcm.qwertysam.util.NameAutocomplete;

public class ModGuiWithDropdowns extends ModGui
{	
	/** The parent screen **/
	private ModGui parentScreen;
	
	public ModGuiWithDropdowns(int selectedBunishmentParam, String nameFieldTextParam, int selectedSeverityParam, ModGui parentScreenParam)
	{
		super(selectedBunishmentParam, nameFieldTextParam, selectedSeverityParam);
		this.selectedBunishment = selectedBunishmentParam;
		this.nameFieldText = nameFieldTextParam;
		this.selectedSeverity = selectedSeverityParam;
		this.parentScreen = parentScreenParam;
	}
	
	private String selectedShow1 = "[";
	private String selectedShow2 = "]";
	
	private String swear;
	private String spam;
	private String harass;
	private String hack;
	private String cheat;
	private String argue;
	
	private String one;
	private String two;
	private String three;
	
	private final String swearOriginal = "Swear";
	private final String spamOriginal = "Spam";
	private final String harassOriginal = "Harass";
	private final String hackOriginal = "Hack";
	private final String cheatOriginal = "Cheat";
	private final String argueOriginal = "Argue";
	
	private final String oneOriginal = "1";
	private final String twoOriginal = "2";
	private final String threeOriginal = "3";
	
	/**
	 * Loads the buttons that will not be there when the dropdown menus are down 
	 */
	public void loadProprietaryButtons()
	{
		this.buttonList.add(new GuiButton(110, this.width / 2 + xSpacing, spaceFromTop + 20, smallButtonWidth - xSpacing - 20, 20, ""));
		this.buttonList.add(new GuiButton(111, this.width / 2 + xSpacing, spaceFromTop + 38, smallButtonWidth - xSpacing - 20, 20, ""));
		this.buttonList.add(new GuiButton(112, this.width / 2 + xSpacing, spaceFromTop + 56, smallButtonWidth - xSpacing - 20, 20, ""));
		this.buttonList.add(new GuiButton(113, this.width / 2 + xSpacing, spaceFromTop + 74, smallButtonWidth - xSpacing - 20, 20, ""));
		this.buttonList.add(new GuiButton(114, this.width / 2 + xSpacing, spaceFromTop + 92, smallButtonWidth - xSpacing - 20, 20, ""));
		this.buttonList.add(new GuiButton(115, this.width / 2 + xSpacing, spaceFromTop + 110, smallButtonWidth - xSpacing - 20, 20, ""));
	
		this.buttonList.add(new GuiButton(211, this.width / 2 + xSpacing + (smallButtonWidth - 20), spaceFromTop + 20, 20, 20, ""));
		this.buttonList.add(new GuiButton(212, this.width / 2 + xSpacing + (smallButtonWidth - 20), spaceFromTop + 38, 20, 20, ""));
		this.buttonList.add(new GuiButton(213, this.width / 2 + xSpacing + (smallButtonWidth - 20), spaceFromTop + 56, 20, 20, ""));
	}
	
	/**
	 * Updates the variables for the selected bunishment.
	 * @param newSelection = The new selected option.
	 */
	private void updateSelectedBunishment(int newSelection)
	{
		if(this.selectedBunishment == newSelection)
		{
			this.parentScreen.selectedBunishment = newSelection;
			this.mc.displayGuiScreen(this.parentScreen);
		}
		else
		{
			this.selectedBunishment = newSelection;
		}
	}
	
	/**
	 * Updates the variables for the selected severity.
	 * @param newSelection = The new selected option.
	 */
	private void updateSelectedSeverity(int newSelection)
	{
		if(this.selectedSeverity == newSelection)
		{
			this.parentScreen.selectedSeverity = newSelection;
			this.mc.displayGuiScreen(this.parentScreen);
		}
		else
		{
			this.selectedSeverity = newSelection;
		}
	}
	
	/**
	 * Completes all the actions for the proprietary buttons that will not be there when the dropdown menus are out.
	 * @param buttonID = The ID of the button being pressed.
	 */
	public void proprietaryActionPerformed(int buttonID)
	{
		this.parentScreen.nameFieldText = this.nameField.getText();
		
		switch (buttonID)
        {
			case 110:
				this.updateSelectedBunishment(0);
				break;
			case 111:
				this.updateSelectedBunishment(1);
				break;
			case 112:
				this.updateSelectedBunishment(2);
				break;
			case 113:
				this.updateSelectedBunishment(3);
				break;
			case 114:
				this.updateSelectedBunishment(4);
				break;
			case 115:
				this.updateSelectedBunishment(5);
				break;
				
			case 211:
				this.updateSelectedSeverity(1);
				break;
			case 212:
				this.updateSelectedSeverity(2);
				break;
			case 213:
				this.updateSelectedSeverity(3);
				break;
        }
	}
	
	/**
	 * Draws all the proprietary screen features.
	 */
	public void proprietaryDrawScreen()
	{
		this.swear = this.swearOriginal;
		this.spam = this.spamOriginal;
		this.harass = this.harassOriginal;
		this.hack = this.hackOriginal;
		this.cheat = this.cheatOriginal;
		this.argue = this.argueOriginal;
		
		switch (this.selectedBunishment)
		{
			case 0:
				this.swear = this.selectedShow1 + this.swear + this.selectedShow2;
				break;
			case 1:
				this.spam = this.selectedShow1 + this.spam + this.selectedShow2;
				break;
			case 2:
				this.harass = this.selectedShow1 + this.harass + this.selectedShow2;
				break;
			case 3:
				this.hack = this.selectedShow1 + this.hack + this.selectedShow2;
				break;
			case 4:
				this.cheat = this.selectedShow1 + this.cheat + this.selectedShow2;
				break;
			case 5:
				this.argue = this.selectedShow1 + this.argue + this.selectedShow2;
				break;
		}
		
		this.one = this.oneOriginal;
		this.two = this.twoOriginal;
		this.three = this.threeOriginal;
		
		switch (this.selectedSeverity)
		{
			case 1:
				this.one = this.selectedShow1 + this.one + this.selectedShow2;
				break;
			case 2:
				this.two = this.selectedShow1 + this.two + this.selectedShow2;
				break;
			case 3:
				this.three = this.selectedShow1 + this.three + this.selectedShow2;
				break;
		}
		
		this.drawCenteredString(this.fontRendererObj, this.swear, this.width / 2 + 51, spaceFromTop + 26, 14737632);
		this.drawCenteredString(this.fontRendererObj, this.spam, this.width / 2 + 51, spaceFromTop + 44, 14737632);
		this.drawCenteredString(this.fontRendererObj, this.harass, this.width / 2 + 51, spaceFromTop + 62, 14737632);
		this.drawCenteredString(this.fontRendererObj, this.hack, this.width / 2 + 51, spaceFromTop + 80, 14737632);
		this.drawCenteredString(this.fontRendererObj, this.cheat, this.width / 2 + 51, spaceFromTop + 98, 14737632);
		this.drawCenteredString(this.fontRendererObj, this.argue, this.width / 2 + 51, spaceFromTop + 116, 14737632);
		
		this.drawCenteredString(this.fontRendererObj, this.one, this.width / 2 + 113, spaceFromTop + 26, 14737632);
		this.drawCenteredString(this.fontRendererObj, this.two, this.width / 2 + 113, spaceFromTop + 44, 14737632);
		this.drawCenteredString(this.fontRendererObj, this.three, this.width / 2 + 113, spaceFromTop + 62, 14737632);
	}
}
