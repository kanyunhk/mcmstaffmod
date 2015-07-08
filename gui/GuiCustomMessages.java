package net.playmcm.qwertysam.gui;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.lwjgl.input.Keyboard;

import com.google.common.collect.Lists;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.resources.I18n;
import net.minecraft.network.play.client.C14PacketTabComplete;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MovingObjectPosition;
import net.playmcm.qwertysam.ModMain;
import net.playmcm.qwertysam.io.SaveHandling;
import net.playmcm.qwertysam.util.MessageSender;

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
    public void updateScreen()
    {
    	this.customTitle.updateCursorCounter();
		this.customLine1.updateCursorCounter();
		this.customLine2.updateCursorCounter();
    }

    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    public void initGui()
    {
    	this.buttonList.add(new GuiButton(200, this.width / 2 - 100, 204, I18n.format("gui.done", new Object[0])));
    }
    
	/**
     * Returns true if this GUI should pause the game when it is displayed in single-player
     */
    public boolean doesGuiPauseGame()
    {
        return false;
    }
    
    /**
     * Fired when a key is typed. This is the equivalent of KeyListener.keyTyped(KeyEvent e).
     */
    protected void keyTyped(char characterTyped, int keyCode)
    {
    	this.customTitle.textboxKeyTyped(characterTyped, keyCode);
        this.customLine1.textboxKeyTyped(characterTyped, keyCode);
        this.customLine2.textboxKeyTyped(characterTyped, keyCode);

        if (keyCode == 15)
        {
        	if (this.customTitle.isFocused() || this.customLine1.isFocused() || this.customLine2.isFocused())
        	{
        		this.autocompletePlayerNames();
        	}
        }

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
    
    private boolean playerNamesFound;
    private boolean waitingOnAutocomplete;
    private int autocompleteIndex;
    private List foundPlayerNames = Lists.newArrayList();

    public void autocompletePlayerNames()
    {
        String var3;

        GuiTextField theField = null;
        
        if (this.customTitle.isFocused())
        {
        	theField = this.customTitle;
        }
        else if (this.customLine1.isFocused())
        {
        	theField = this.customLine1;
        }
        else if (this.customLine2.isFocused())
        {
        	theField = this.customLine2;
        }
        
        if (this.playerNamesFound)
        {
            theField.deleteFromCursor(theField.func_146197_a(-1, theField.getCursorPosition(), false) - theField.getCursorPosition());

            if (this.autocompleteIndex >= this.foundPlayerNames.size())
            {
                this.autocompleteIndex = 0;
            }
        }
        else
        {
            int var1 = theField.func_146197_a(-1, theField.getCursorPosition(), false);
            this.foundPlayerNames.clear();
            this.autocompleteIndex = 0;
            String var2 = theField.getText().substring(var1).toLowerCase();
            var3 = theField.getText().substring(0, theField.getCursorPosition());
            this.sendAutocompleteRequest(var3, var2);

            if (this.foundPlayerNames.isEmpty())
            {
                return;
            }

            this.playerNamesFound = true;
            theField.deleteFromCursor(var1 - theField.getCursorPosition());
        }

        if (this.foundPlayerNames.size() > 1)
        {
            StringBuilder var4 = new StringBuilder();

            for (Iterator var5 = this.foundPlayerNames.iterator(); var5.hasNext(); var4.append(var3))
            {
                var3 = (String)var5.next();

                if (var4.length() > 0)
                {
                    var4.append(", ");
                }
            }

            this.mc.ingameGUI.getChatGUI().printChatMessageWithOptionalDeletion(new ChatComponentText(var4.toString()), 1);
        }

        theField.writeText((String)this.foundPlayerNames.get(this.autocompleteIndex++));
    }

    private void sendAutocompleteRequest(String p_146405_1_, String p_146405_2_)
    {
        if (p_146405_1_.length() >= 1)
        {
            BlockPos var3 = null;

            if (this.mc.objectMouseOver != null && this.mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
            {
                var3 = this.mc.objectMouseOver.func_178782_a();
            }

            this.mc.thePlayer.sendQueue.addToSendQueue(new C14PacketTabComplete(p_146405_1_, var3));
            this.waitingOnAutocomplete = true;
        }
    }
    
    public void onAutocompleteResponse(String[] p_146406_1_)
    {
    	GuiTextField theField = null;
        
        if (this.customTitle.isFocused())
        {
        	theField = this.customTitle;
        }
        else if (this.customLine1.isFocused())
        {
        	theField = this.customLine1;
        }
        else if (this.customLine2.isFocused())
        {
        	theField = this.customLine2;
        }
    	
        if (this.waitingOnAutocomplete)
        {
            this.playerNamesFound = false;
            this.foundPlayerNames.clear();
            String[] var2 = p_146406_1_;
            int var3 = p_146406_1_.length;

            for (int var4 = 0; var4 < var3; ++var4)
            {
                String var5 = var2[var4];

                if (var5.length() > 0)
                {
                    this.foundPlayerNames.add(var5);
                }
            }

            String var6 = theField.getText().substring(theField.func_146197_a(-1, theField.getCursorPosition(), false));
            String var7 = StringUtils.getCommonPrefix(p_146406_1_);

            if (var7.length() > 0 && !var6.equalsIgnoreCase(var7))
            {
            	theField.deleteFromCursor(theField.func_146197_a(-1, theField.getCursorPosition(), false) - theField.getCursorPosition());
            	theField.writeText(var7);
            }
            else if (this.foundPlayerNames.size() > 0)
            {
                this.playerNamesFound = true;
                this.autocompletePlayerNames();
            }
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
        
        this.customLine1.mouseClicked(p_73864_1_, p_73864_2_, p_73864_3_);
        this.customTitle.mouseClicked(p_73864_1_, p_73864_2_, p_73864_3_);
        this.customLine2.mouseClicked(p_73864_1_, p_73864_2_, p_73864_3_);
    }
    
    /**
     * Draws the screen and all the components in it.
     */
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
    	
        super.drawScreen(par1, par2, par3);
    }
}
