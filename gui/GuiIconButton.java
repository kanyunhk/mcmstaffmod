package net.playmcm.qwertysam.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

/**
 * A button that has a specific image, based off of GuiButtonLanguage.
 * @author Samson
 */
public class GuiIconButton extends GuiButton
{
	/** An ID used by Minecraft. Not quite sure what its' usage is. **/
    private static final String __OBFID = "CL_00000672";
    /** Directory of the resource (the texture file). **/
    protected static final ResourceLocation textureDir = new ResourceLocation("qwertysam/textures/buttons.png");
    /** The horizontal position of the button in the image file. **/
    private int xTextureOffset;
    /** The vertical position of the button in the image file. **/
    private int yTextureOffset;
    /** The height of the button. **/
    private int xTextureSize;
    /** The width of the button. **/
    private int yTextureSize;
    /** Tells if the button is currently enabled. **/
    private boolean isEnabled;
    
    /**
     * Creates a new button with an image on it.
     * @param buttonID = The ID of the button.
     * @param xPosition = The horizontal position to draw the button at.
     * @param yPosition = The vertical position to draw the button at.
     * @param xTextureOffset = The horizontal position of the button in the image file.
     * @param yTextureOffset = The vertical position of the button in the image file.
     */
    public GuiIconButton(int buttonID, int xPosition, int yPosition, int xTextureOffset, int yTextureOffset)
    {
    	this(buttonID, xPosition, yPosition, xTextureOffset, yTextureOffset, 20, 20);
    }
    /**
     * Creates a new button with an image on it.
     * @param buttonID = The ID of the button.
     * @param xPosition = The horizontal position to draw the button at.
     * @param yPosition = The vertical position to draw the button at.
     * @param xTextureOffset = The horizontal position of the button in the image file.
     * @param yTextureOffset = The vertical position of the button in the image file.
     * @param xTextureSize = The height of the button.
     * @param yTextureSize = The width of the button.
     */
    public GuiIconButton(int buttonID, int xPosition, int yPosition, int xTextureOffset, int yTextureOffset, int xTextureSize, int yTextureSize)
    {
        super(buttonID, xPosition, yPosition, xTextureSize, yTextureSize, "");
        this.xTextureOffset = xTextureOffset;
    	this.yTextureOffset = yTextureOffset;
    	this.xTextureSize = xTextureSize;
    	this.yTextureSize = yTextureSize;
    	this.isEnabled = true;
    }

    /**
     * Draws this button to the screen.
     */
    @Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY)
    {
        if (this.visible)
        {
        	//Draws the Regular or texture pack'd minecraft button behind the decal.
        	FontRenderer var4 = mc.fontRendererObj;
            mc.getTextureManager().bindTexture(buttonTextures);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            this.hovered = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
            int var5 = this.getHoverState(this.hovered);
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            GlStateManager.blendFunc(770, 771);
            this.drawTexturedModalRect(this.xPosition, this.yPosition, 0, 46 + var5 * 20, this.width / 2, this.height);
            this.drawTexturedModalRect(this.xPosition + this.width / 2, this.yPosition, 200 - this.width / 2, 46 + var5 * 20, this.width / 2, this.height);
            this.mouseDragged(mc, mouseX, mouseY);
        	
        	mc.getTextureManager().bindTexture(this.textureDir);
        	//GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            boolean isMouseOverButton = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.yTextureSize;

            int tempYTextureOffset = this.yTextureOffset; //Used to bump the texture being drawn down in the image file to display it being hovered over or disabled.
            
            if(!this.isEnabled)
            {
            	tempYTextureOffset = this.yTextureOffset + (this.yTextureSize * 2);
            }
            else if (isMouseOverButton)
            {
            	tempYTextureOffset = this.yTextureOffset + this.yTextureSize;
            }
            
            this.drawTexturedModalRect(this.xPosition, this.yPosition, this.xTextureOffset, tempYTextureOffset, this.width, this.height);
        }
    }
    
    /**
     * Sets if the button is enabled.
     * @param newValue = The new button state.
     */
    public void setIsEnabled(boolean newValue)
    {
    	this.enabled = newValue;
    	this.isEnabled = newValue;
    }
    
    /**
     * @return If the button is enabled or not.
     */
    public boolean getIsEnabled()
    {
    	return this.isEnabled;
    }
}
