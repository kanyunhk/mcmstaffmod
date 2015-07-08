package net.playmcm.qwertysam.gui;

import java.io.IOException;

import org.lwjgl.input.Keyboard;

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
import net.playmcm.qwertysam.ModMain;
import net.playmcm.qwertysam.io.SaveHandling;
import net.playmcm.qwertysam.util.MessageSender;

public class ModGuiWithDropdownBun extends GuiScreen
{
	/** The parent GUI **/
	private final GuiScreen parentScreen;
	
	/** The pixels between the GUI and the top of the screen **/
	private static int spaceFromTop = 20;
	
	/** The horizontal spacing between the buttons **/
	private static int xSpacing = 3;
	
	/** The width of the buttons **/
	private static int smallButtonWidth = 120;
	
	/** The text field where the user can input a name and TAB it in (hopefully later) **/
    protected GuiTextField nameField;
	
    private String nameFieldText = "";
    
    /** Tells which option of reason for bunning is selected **/
	private int selectedBunishment = 0;
	
	/** Tells which severity is selected **/
	private int selectedSeverity = 1;
	
    public ModGuiWithDropdownBun(GuiScreen parentScreenParam, int selectedBunishmentParam, String nameFieldTextParam, int selectedSeverityParam)
    {
    	this.parentScreen = parentScreenParam;
    	this.selectedBunishment = selectedBunishmentParam;
    	this.nameFieldText = nameFieldTextParam;
    	this.selectedSeverity = selectedSeverityParam;
    }
    
	/**
     * Adds the buttons (and other controls) to the screen in question.
     */
	public void initGui()
	{
		this.buttonList.clear();
		
		Keyboard.enableRepeatEvents(true);
    	this.nameField = new GuiTextField(0, this.fontRendererObj, this.width / 2 - smallButtonWidth - xSpacing, spaceFromTop + 20, smallButtonWidth, 20);
    	this.nameField.setText(this.nameFieldText);
    	this.nameField.setMaxStringLength(32);
		
    	//Swear, Spam, Harass, Hack, Cheat, Argue
    	
    	switch (selectedBunishment)
    	{
    		case 0:
    			this.buttonList.add(new GuiButton(100, this.width / 2 + xSpacing, spaceFromTop + 20, smallButtonWidth - xSpacing - 20, 20, "Swear"));
    			this.buttonList.add(new GuiButton(101, this.width / 2 + xSpacing, spaceFromTop + 38, smallButtonWidth - xSpacing - 20, 20, "Spam"));
    			this.buttonList.add(new GuiButton(102, this.width / 2 + xSpacing, spaceFromTop + 56, smallButtonWidth - xSpacing - 20, 20, "Harass"));
    			this.buttonList.add(new GuiButton(103, this.width / 2 + xSpacing, spaceFromTop + 74, smallButtonWidth - xSpacing - 20, 20, "Hack"));
    			this.buttonList.add(new GuiButton(104, this.width / 2 + xSpacing, spaceFromTop + 92, smallButtonWidth - xSpacing - 20, 20, "Cheat"));
    			this.buttonList.add(new GuiButton(105, this.width / 2 + xSpacing, spaceFromTop + 110, smallButtonWidth - xSpacing - 20, 20, "Argue"));
    			break;
    		case 1:
    			this.buttonList.add(new GuiButton(101, this.width / 2 + xSpacing, spaceFromTop + 20, smallButtonWidth - xSpacing - 20, 20, "Spam"));
    			this.buttonList.add(new GuiButton(100, this.width / 2 + xSpacing, spaceFromTop + 38, smallButtonWidth - xSpacing - 20, 20, "Swear"));
    			this.buttonList.add(new GuiButton(102, this.width / 2 + xSpacing, spaceFromTop + 56, smallButtonWidth - xSpacing - 20, 20, "Harass"));
    			this.buttonList.add(new GuiButton(103, this.width / 2 + xSpacing, spaceFromTop + 74, smallButtonWidth - xSpacing - 20, 20, "Hack"));
    			this.buttonList.add(new GuiButton(104, this.width / 2 + xSpacing, spaceFromTop + 92, smallButtonWidth - xSpacing - 20, 20, "Cheat"));
    			this.buttonList.add(new GuiButton(105, this.width / 2 + xSpacing, spaceFromTop + 110, smallButtonWidth - xSpacing - 20, 20, "Argue"));
    			break;
    		case 2:
    			this.buttonList.add(new GuiButton(102, this.width / 2 + xSpacing, spaceFromTop + 20, smallButtonWidth - xSpacing - 20, 20, "Harass"));
    			this.buttonList.add(new GuiButton(101, this.width / 2 + xSpacing, spaceFromTop + 38, smallButtonWidth - xSpacing - 20, 20, "Spam"));
    			this.buttonList.add(new GuiButton(100, this.width / 2 + xSpacing, spaceFromTop + 56, smallButtonWidth - xSpacing - 20, 20, "Swear"));
    			this.buttonList.add(new GuiButton(103, this.width / 2 + xSpacing, spaceFromTop + 74, smallButtonWidth - xSpacing - 20, 20, "Hack"));
    			this.buttonList.add(new GuiButton(104, this.width / 2 + xSpacing, spaceFromTop + 92, smallButtonWidth - xSpacing - 20, 20, "Cheat"));
    			this.buttonList.add(new GuiButton(105, this.width / 2 + xSpacing, spaceFromTop + 110, smallButtonWidth - xSpacing - 20, 20, "Argue"));
    			break;
    		case 3:
    			this.buttonList.add(new GuiButton(103, this.width / 2 + xSpacing, spaceFromTop + 20, smallButtonWidth - xSpacing - 20, 20, "Hack"));
    			this.buttonList.add(new GuiButton(101, this.width / 2 + xSpacing, spaceFromTop + 38, smallButtonWidth - xSpacing - 20, 20, "Spam"));
    			this.buttonList.add(new GuiButton(102, this.width / 2 + xSpacing, spaceFromTop + 56, smallButtonWidth - xSpacing - 20, 20, "Harass"));
    			this.buttonList.add(new GuiButton(100, this.width / 2 + xSpacing, spaceFromTop + 74, smallButtonWidth - xSpacing - 20, 20, "Swear"));
    			this.buttonList.add(new GuiButton(104, this.width / 2 + xSpacing, spaceFromTop + 92, smallButtonWidth - xSpacing - 20, 20, "Cheat"));
    			this.buttonList.add(new GuiButton(105, this.width / 2 + xSpacing, spaceFromTop + 110, smallButtonWidth - xSpacing - 20, 20, "Argue"));
    			break;
    		case 4:
    			this.buttonList.add(new GuiButton(104, this.width / 2 + xSpacing, spaceFromTop + 20, smallButtonWidth - xSpacing - 20, 20, "Cheat"));
    			this.buttonList.add(new GuiButton(101, this.width / 2 + xSpacing, spaceFromTop + 38, smallButtonWidth - xSpacing - 20, 20, "Spam"));
    			this.buttonList.add(new GuiButton(102, this.width / 2 + xSpacing, spaceFromTop + 56, smallButtonWidth - xSpacing - 20, 20, "Harass"));
    			this.buttonList.add(new GuiButton(103, this.width / 2 + xSpacing, spaceFromTop + 74, smallButtonWidth - xSpacing - 20, 20, "Hack"));
    			this.buttonList.add(new GuiButton(100, this.width / 2 + xSpacing, spaceFromTop + 92, smallButtonWidth - xSpacing - 20, 20, "Swear"));
    			this.buttonList.add(new GuiButton(105, this.width / 2 + xSpacing, spaceFromTop + 110, smallButtonWidth - xSpacing - 20, 20, "Argue"));
    			break;
    		case 5:
    			this.buttonList.add(new GuiButton(105, this.width / 2 + xSpacing, spaceFromTop + 20, smallButtonWidth - xSpacing - 20, 20, "Argue"));
    			this.buttonList.add(new GuiButton(101, this.width / 2 + xSpacing, spaceFromTop + 38, smallButtonWidth - xSpacing - 20, 20, "Spam"));
    			this.buttonList.add(new GuiButton(102, this.width / 2 + xSpacing, spaceFromTop + 56, smallButtonWidth - xSpacing - 20, 20, "Harass"));
    			this.buttonList.add(new GuiButton(103, this.width / 2 + xSpacing, spaceFromTop + 74, smallButtonWidth - xSpacing - 20, 20, "Hack"));
    			this.buttonList.add(new GuiButton(104, this.width / 2 + xSpacing, spaceFromTop + 92, smallButtonWidth - xSpacing - 20, 20, "Cheat"));
    			this.buttonList.add(new GuiButton(100, this.width / 2 + xSpacing, spaceFromTop + 110, smallButtonWidth - xSpacing - 20, 20, "Swear"));
    			break;
    	}
    	
    	//Adds the selected severity to the UI
    	this.buttonList.add(new GuiButton(201, this.width / 2 + xSpacing + (smallButtonWidth - 20), spaceFromTop + 20, 20, 20, this.selectedSeverity + ""));
    	
    	this.buttonList.add(new GuiIconButton(47, this.width / 2 + smallButtonWidth + (xSpacing * 2), spaceFromTop + 20, 20, 0, 20, 20));
    	
		this.buttonList.add(new GuiButton(0, this.width / 2 - smallButtonWidth - xSpacing, spaceFromTop + 50, smallButtonWidth, 20, "How To Become Mod"));
		//this.buttonList.add(new GuiButton(1, this.width / 2 + xSpacing, spaceFromTop + 50, smallButtonWidth, 20, "(Opens MCM Forum)"));

		this.buttonList.add(new GuiButton(2, this.width / 2 - smallButtonWidth - xSpacing, spaceFromTop + 80, smallButtonWidth, 20, "Rules Link"));
		//this.buttonList.add(new GuiButton(4, this.width / 2 + xSpacing, spaceFromTop + 80, smallButtonWidth, 20, "Donation Link"));
		
		this.buttonList.add(new GuiButton(6, this.width / 2 - smallButtonWidth - xSpacing, spaceFromTop + 110, smallButtonWidth, 20, "Reports Link"));
		//this.buttonList.add(new GuiButton(7, this.width / 2 + xSpacing, spaceFromTop + 110, smallButtonWidth, 20, "Ban Appeals Link"));
		
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
	}
	
	/**
     * Called from the main game loop to update the screen.
     */
    public void updateScreen()
    {
    	this.nameField.updateCursorCounter();
    }
	
    /**
     * Fired when a key is typed. This is the equivalent of KeyListener.keyTyped(KeyEvent e).
     */
    protected void keyTyped(char characterTyped, int p_73869_2_)
    {
    	this.nameField.textboxKeyTyped(characterTyped, p_73869_2_);

        if (p_73869_2_ == 15)
        {
        	this.nameField.setFocused(true);
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
        
        this.nameField.mouseClicked(p_73864_1_, p_73864_2_, p_73864_3_);
    }
    
	/**
     * Fired when a button is clicked.
     * @param button = The ID of the button that was clicked.
     */
	protected void actionPerformed(GuiButton button) throws IOException
    {
        switch (button.id)
        {
            case 0:
            	MessageSender.sendInfo("become_mod");
            	exitGui();
                break;
            case 1:
            	ModMain.openURL("http://playmcm.net/");
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
            case 4:
            	MessageSender.sendInfo("donate_link");
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
            case 7:
            	MessageSender.sendInfo("appeal_link");
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
            	this.mc.displayGuiScreen(new GuiCustomOneEditor(this.parentScreen));
            	break;
            case 11:
            	this.mc.displayGuiScreen(new GuiCustomTwoEditor(this.parentScreen));
            	break;
            case 12:
            	MessageSender.sendMessage("/warn help"); //Sends the command that displays info on the /warn command usage
            	exitGui();
            	break;
            case 20:
            	ModMain.openURL("http://playmcm.net/"); //Forum Page
            	exitGui();
            	break;
            case 21:
            	ModMain.openURL("https://goo.gl/C6FZrg"); //Mod Guidelines Documentation page
            	exitGui();
            	break;
            case 22:
            	ModMain.openURL("https://goo.gl/WMrAUT"); //Bugs and Suggestions page
            	exitGui();
            	break;
            case 100:
            	this.mc.displayGuiScreen(new ModGui(0, this.nameField.getText(), this.selectedSeverity));
            	break;
            case 101:
            	this.mc.displayGuiScreen(new ModGui(1, this.nameField.getText(), this.selectedSeverity));
            	break;
            case 102:
            	this.mc.displayGuiScreen(new ModGui(2, this.nameField.getText(), this.selectedSeverity));
            	break;
            case 103:
            	this.mc.displayGuiScreen(new ModGui(3, this.nameField.getText(), this.selectedSeverity));
            	break;
            case 104:
            	this.mc.displayGuiScreen(new ModGui(4, this.nameField.getText(), this.selectedSeverity));
            	break;
            case 105:
            	this.mc.displayGuiScreen(new ModGui(5, this.nameField.getText(), this.selectedSeverity));
            	break;
            case 201:
            	this.mc.displayGuiScreen(new ModGuiWithDropdownSeverity(this.parentScreen, this.selectedBunishment, this.nameField.getText(), this.selectedSeverity));
            	break;
            case 47:
            	this.sendBun();
            	exitGui();
            	break;
        }
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
    	
    	MessageSender.sendMessage("/warn " + nameField.getText() + " " + reason + " " + this.selectedSeverity);
    }
	
	/**
     * Returns true if this GUI should pause the game when it is displayed in single-player
     */
    public boolean doesGuiPauseGame()
    {
        return false;
    }
	
    /**
     * Exits the GUI
     */
    public void exitGui()
    {
    	this.mc.displayGuiScreen((GuiScreen)null);
    }
    
    /** Tells Minecraft it's ready to exit the gui as soon as the player lets go of ESC **/
    private boolean waitForRelease = false;
    
	/**
     * Draws the screen and all the components in it. Args : mouseX, mouseY, renderPartialTicks
     */
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
		if ((!Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) && waitForRelease)
		{
			waitForRelease = false;
			this.exitGui();
		}
		
		if((Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)))
		{
			waitForRelease = true;
		}
		
        this.drawCenteredString(this.fontRendererObj, "Mod Tools", this.width / 2, spaceFromTop, 16777215);
        this.drawString(this.fontRendererObj, "/bun", this.width / 2 - 154, spaceFromTop + 26, 16777215);
        
        this.nameField.drawTextBox();
        
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
