package net.playmcm.qwertysam.gui;

import java.io.IOException;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.resources.I18n;
import net.playmcm.qwertysam.io.SaveHandling;

/**
 * The options menu for the backup mod.
 * @author Samson
 */
public class GuiCustomMessages extends GuiScreen
{
	/** The parent gui screen. **/
	protected final GuiScreen parentScreen;
    
	/** The custom text field where the user can input their own title **/
    protected GuiTextField customTitle;
    
    /** The custom text field where the user can input their own first line of text **/
    protected GuiTextField customLine1;
    
    /** The custom text field where the user can input their own second line of text **/
    protected GuiTextField customLine2;
    
    /**
     * The Gui that displays and allows you to change the options.
     * @param guiParentScreen = The parent gui screen.
     */
	public GuiCustomMessages(GuiScreen guiParentScreen) 
	{
		this.parentScreen = guiParentScreen;
	}
	
	/**
	 * Saves all the current settings on the screen to the options file.
	 */
	private void saveSettings()
	{
		SaveHandling.saveOptions();
	}
	
    /**
     * Fired when a button is clicked.
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
            	this.customTitle.setText("Custom");
            	this.customLine1.setText("");
            	this.customLine2.setText("");
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
    }

    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    @Override
	public void initGui()
    {
    	this.buttonList.add(new GuiButton(200, this.width / 2 - 100, 204, I18n.format("gui.done", new Object[0])));
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

        if (keyCode == 28 || keyCode == 156)
        {
            try 
            {
				this.actionPerformed((GuiButton)this.buttonList.get(0));
			} 
            catch (IOException e) 
            {
				e.printStackTrace();
			}
        }

        ((GuiButton)this.buttonList.get(0)).enabled =  this.customTitle.getText().length() > 0;
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
        
        this.customLine1.mouseClicked(p_73864_1_, p_73864_2_, p_73864_3_);
        this.customTitle.mouseClicked(p_73864_1_, p_73864_2_, p_73864_3_);
        this.customLine2.mouseClicked(p_73864_1_, p_73864_2_, p_73864_3_);
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
        
        this.drawString(this.fontRendererObj, "Line 1", this.width / 2 - 180, 88, 16777215);
        
        this.drawString(this.fontRendererObj, "Line 2", this.width / 2 - 180, 138, 16777215);
        
        this.customTitle.drawTextBox();
        this.customLine1.drawTextBox();
    	this.customLine2.drawTextBox();
    	
    	if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
		{
			waitForRelease = true;
		}
    	// Waits for ESC to be released before exiting GUI, prevents opening of the in-game pause menu upon exit of GUI
    	else if ((!Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) && waitForRelease)
    	{
    		waitForRelease = false;
    		this.mc.displayGuiScreen(parentScreen);
    	}
    	
        super.drawScreen(par1, par2, par3);
    }
}
