package net.playmcm.qwertysam.gui;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.lwjgl.input.Keyboard;

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
import net.minecraft.network.play.client.C14PacketTabComplete;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MovingObjectPosition;
import net.playmcm.qwertysam.ModMain;
import net.playmcm.qwertysam.io.SaveHandling;
import net.playmcm.qwertysam.util.MessageSender;
import net.playmcm.qwertysam.util.NameAutocomplete;
import net.playmcm.qwertysam.util.UrlOpener;

public class ModGui extends GuiScreen
{	
	/** The pixels between the GUI and the top of the screen **/
	protected final int spaceFromTop = 20;
	
	/** The horizontal spacing between the buttons **/
	protected final int xSpacing = 3;
	
	/** The width of the buttons **/
	protected final int smallButtonWidth = 120;
	
	/** The text field where the user can input a name and TAB it in (hopefully later) **/
    protected static GuiTextField nameField;
	
    protected static String nameFieldText = "";
    
    /** Tells which option of reason for bunning is selected **/
    protected static int selectedBunishment;
	
	/** Tells which severity is selected **/
    protected static int selectedSeverity;
    
	private boolean justEntered = true;
	
	public ModGui(int selectedBunishmentParam, String nameFieldTextParam, int selectedSeverityParam)
	{
		this.selectedBunishment = selectedBunishmentParam;
		this.nameFieldText = nameFieldTextParam;
		this.selectedSeverity = selectedSeverityParam;
	}
	
	/**
     * Adds the buttons (and other controls) to the screen in question.
     */
	public void initGui()
	{
		this.justEntered = true;
		this.firstLetter = true;
		
		this.buttonList.clear();
		
		Keyboard.enableRepeatEvents(true);
    	nameField = new GuiTextField(0, this.fontRendererObj, this.width / 2 - smallButtonWidth - xSpacing, spaceFromTop + 20, smallButtonWidth, 20);
    	nameField.setMaxStringLength(32);
    	nameField.setText(this.nameFieldText);
    	
    	this.buttonList.add(new GuiIconButton(47, this.width / 2 + smallButtonWidth + (xSpacing * 2), spaceFromTop + 20, 20, 0, 20, 20));
    	
		this.buttonList.add(new GuiButton(0, this.width / 2 - smallButtonWidth - xSpacing, spaceFromTop + 50, smallButtonWidth, 20, "How To Become Mod"));
		//Voting link button

		this.buttonList.add(new GuiButton(2, this.width / 2 - smallButtonWidth - xSpacing, spaceFromTop + 80, smallButtonWidth, 20, "Rules Link"));
		//Donation link button
		
		this.buttonList.add(new GuiButton(6, this.width / 2 - smallButtonWidth - xSpacing, spaceFromTop + 110, smallButtonWidth, 20, "Reports Link"));
		//Ban appeals link button
		
		this.buttonList.add(new GuiButton(3, this.width / 2 - smallButtonWidth - xSpacing, spaceFromTop + 140, 80, 20, "Expl. Teaming"));
		this.buttonList.add(new GuiButton(13, this.width / 2 - 40, spaceFromTop + 140, 80, 20, "Expl. Argue"));
		this.buttonList.add(new GuiButton(5, this.width / 2 + 40 + xSpacing, spaceFromTop + 140, 80, 20, "Expl. RDM"));
		
		this.buttonList.add(new GuiButton(8, this.width / 2 - smallButtonWidth - xSpacing, spaceFromTop + 170, smallButtonWidth, 20, SaveHandling.getCustomOneTitle()));
		this.buttonList.add(new GuiButton(9, this.width / 2 + xSpacing, spaceFromTop + 170, smallButtonWidth, 20, SaveHandling.getCustomTwoTitle()));
		
		this.buttonList.add(new GuiIconButton(10, this.width / 2 - smallButtonWidth - (xSpacing * 2) - 20, spaceFromTop + 170, 0, 0, 20, 20));
		this.buttonList.add(new GuiIconButton(11, this.width / 2 + smallButtonWidth + (xSpacing * 2) , spaceFromTop + 170, 0, 0, 20, 20));
		
		this.buttonList.add(new GuiIconButton(12, this.width / 2 + 148, spaceFromTop + 64, 80, 0, 20, 20));
		this.buttonList.add(new GuiIconButton(20, this.width / 2 + 148, spaceFromTop + 84 + xSpacing, 60, 0, 20, 20));
		this.buttonList.add(new GuiIconButton(21, this.width / 2 + 148, spaceFromTop + 104 + (xSpacing * 2), 40, 0, 20, 20));
		this.buttonList.add(new GuiIconButton(22, this.width / 2 + 148, spaceFromTop + 124 + (xSpacing * 3), 100, 0, 20, 20));
		
		this.loadProprietaryButtons();
	}
	
	/**
	 * Loads the buttons that will not be there when the dropdown menus are down 
	 */
	public void loadProprietaryButtons()
	{
		//Adds the selected severity to the UI
    	this.buttonList.add(new GuiButton(201, this.width / 2 + xSpacing + (smallButtonWidth - 20), spaceFromTop + 20, 20, 20, this.selectedSeverity + ""));
    	
		//Adds the selected punishment reason to the UI
    	String bunTitle = "";
    	switch (selectedBunishment)
    	{
    		case 0:
    			bunTitle = "Swear";
    			break;
    		case 1:
    			bunTitle= "Spam";
    			break;
    		case 2:
    			bunTitle = "Harass";
    			break;
    		case 3:
    			bunTitle= "Hack";
    			break;
    		case 4:
    			bunTitle= "Cheat";
    			break;
    		case 5:
    			bunTitle= "Argue";
    			break;
    	}
    	
    	this.buttonList.add(new GuiButton(100, this.width / 2 + xSpacing, spaceFromTop + 20, smallButtonWidth - xSpacing - 20, 20, bunTitle));
    	
		this.buttonList.add(new GuiButton(1, this.width / 2 + xSpacing, spaceFromTop + 50, smallButtonWidth, 20, "Voting Link"));
		this.buttonList.add(new GuiButton(4, this.width / 2 + xSpacing, spaceFromTop + 80, smallButtonWidth, 20, "Donation Link"));
		this.buttonList.add(new GuiButton(7, this.width / 2 + xSpacing, spaceFromTop + 110, smallButtonWidth, 20, "Ban Appeals Link"));
	}
	
	/**
     * Called from the main game loop to update the screen.
     */
    public void updateScreen()
    {
    	nameField.updateCursorCounter();
    }
	
    private boolean firstLetter = true;
    
    /**
     * Fired when a key is typed. This is the equivalent of KeyListener.keyTyped(KeyEvent e).
     */
    protected void keyTyped(char characterTyped, int keyCode)
    {
    	if(this.firstLetter && !this.justEntered && !Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))// When you first open the GUI it will clear any names already in the text field if you start typing
    	{
    		nameField.setText("");
    		this.firstLetter = false;
    	}
    	
    	if (keyCode == 15)
        {
    		if(nameField.isFocused())
    		{
    			NameAutocomplete.autocompletePlayerNames(nameField);
    		}
        }
    	
    	nameField.textboxKeyTyped(characterTyped, keyCode);	
    }
    
    /**
     * The method that performs the autocomplete function (sent to from NameAutocomplete.java)
     * @param playerList = The list of online players.
     */
    public static void performAutocomplete(String[] playerList)
    {
    	if (nameField.isFocused())
    	{
    		NameAutocomplete.onAutocompleteResponse(playerList, nameField);
    	}
    }
    
    /**
     * Called when the mouse is clicked.
     */
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
        
        nameField.mouseClicked(p_73864_1_, p_73864_2_, p_73864_3_);
    }
    
	/**
     * Fired when a button is clicked.
     * @param button = The ID of the button that was clicked.
     */
	protected void actionPerformed(GuiButton button) throws IOException
    {
		this.mainActionPerformed(button.id);
		this.proprietaryActionPerformed(button.id);
    }
	
	/**
	 * Completes all the actions for the main buttons that will always be there.
	 * @param buttonID = The ID of the button being pressed.
	 */
	public void mainActionPerformed(int buttonID)
	{
		switch (buttonID)
        {
            case 0:
            	MessageSender.sendInfo("become_mod");
            	exitGui();
                break;
            case 2:
            	MessageSender.sendInfo("rules_link");
            	exitGui();
            	break;
            case 3:
            	MessageSender.sendInfo("explain_teaming");
            	exitGui();
            	break;
            case 5:
            	MessageSender.sendInfo("explain_rdm");
            	exitGui();
            	break;
            case 13:
            	MessageSender.sendInfo("explain_argue");
            	exitGui();
            	break;
            case 6:
            	MessageSender.sendInfo("report_link");
            	exitGui();
            	break;
            case 8:
            	MessageSender.sendInfo("custom_one");
            	exitGui();
            	break;
            case 9:
            	MessageSender.sendInfo("custom_two");
            	exitGui();
            	break;
            case 10:
            	this.mc.displayGuiScreen(new GuiCustomOneEditor(this));
            	break;
            case 11:
            	this.mc.displayGuiScreen(new GuiCustomTwoEditor(this));
            	break;
            case 12:
            	MessageSender.sendMessage("/warn help"); //Sends the command that displays info on the /warn command usage
            	exitGui();
            	break;
            case 20:
            	UrlOpener.openURL("http://playmcm.net/"); //Forum Page
            	exitGui();
            	break;
            case 21:
            	UrlOpener.openURL("https://goo.gl/C6FZrg"); //Mod Guidelines Documentation page
            	exitGui();
            	break;
            case 22:
            	UrlOpener.openURL("https://goo.gl/WMrAUT"); //Bugs and Suggestions page
            	exitGui();
            	break;
            case 47:
            	this.sendBun();
            	exitGui();
            	break;
        }
	}
	
	/**
	 * Completes all the actions for the proprietary buttons that will not be there when the dropdown menus are out.
	 * @param buttonID = The ID of the button being pressed.
	 */
	public void proprietaryActionPerformed(int buttonID)
	{
		switch (buttonID)
        {
			case 1:
				MessageSender.sendInfo("vote_link");
				exitGui();
				break;
			case 4:
	        	MessageSender.sendInfo("donate_link");
	        	exitGui();
	        	break;
			case 7:
	        	MessageSender.sendInfo("appeal_link");
	        	exitGui();
	        	break;
            case 100:
            case 201:
            	this.mc.displayGuiScreen(new ModGuiWithDropdowns(this.selectedBunishment, nameField.getText(), this.selectedSeverity, this));
            	break;
        }
	}
	
	/**
     * Returns true if this GUI should pause the game when it is displayed in single-player
     */
    public boolean doesGuiPauseGame()
    {
        return false;
    }
    
    /**
     * Sends the warn command
     */
    public void sendBun()
    {
    	String reason = "";
    	switch(this.selectedBunishment)
    	{
    		case 0:
    			reason = "SWEAR";
    			break;
    		case 1:
    			reason = "SPAM";
    			break;
    		case 2:
    			reason = "HARASS";
    			break;
    		case 3:
    			reason = "HACK";
    			break;
    		case 4:
    			reason = "CHEAT";
    			break;
    		case 5:
    			reason = "ARGUE";
    			break;
    	}
    	
    	nameFieldText = nameField.getText();
    	
    	MessageSender.sendMessage("/warn " + nameFieldText + " " + reason + " " + this.selectedSeverity);
    }
	
    /**
     * Exits the GUI
     */
    public void exitGui()
    {
    	nameFieldText = nameField.getText();
    	this.mc.displayGuiScreen((GuiScreen)null);
    }
    
    /** Tells Minecraft it's ready to exit the gui as soon as the player lets go of ESC **/
    private boolean waitForRelease = false;
    
	/**
     * Draws the screen and all the components in it. Args : mouseX, mouseY, renderPartialTicks
     */
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
		// Waits for ESC to be released before exiting GUI, prevents opening of the in-game pause menu upon exit of GUI
		if ((!Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) && waitForRelease)
		{
			waitForRelease = false;
			this.exitGui();
		}
		if((Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) && !this.justEntered)
		{
			waitForRelease = true;
		}
		else if (justEntered && !Keyboard.isKeyDown(Keyboard.KEY_GRAVE)) // Waits for the GRAVE key to be released before focusing on the TextField or allowing exiting through the GRAVE key being pressed.
		{
			justEntered = false;
			nameField.setFocused(true);
		}
			
        this.drawCenteredString(this.fontRendererObj, "Mod Tools", this.width / 2, spaceFromTop, 16777215);
        this.drawString(this.fontRendererObj, "/bun", this.width / 2 - 154, spaceFromTop + 26, 16777215);
        
        nameField.drawTextBox();
        
        super.drawScreen(mouseX, mouseY, partialTicks);
        
        this.proprietaryDrawScreen();
    }
	
	/**
	 * Draws all the proprietary screen features.
	 */
	public void proprietaryDrawScreen()
	{
		
	}
}
