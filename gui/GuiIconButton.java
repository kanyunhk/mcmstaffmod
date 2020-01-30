package mcmstaffmod.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

/**
 * A button that has a specific image, based off of GuiButtonLanguage.
 * 
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
	private String tooltip;

	/**
	 * Creates a new button with an image on it.
	 * 
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
	 * 
	 * @param buttonID = The ID of the button.
	 * @param xPosition = The horizontal position to draw the button at.
	 * @param yPosition = The vertical position to draw the button at.
	 * @param xTextureOffset = The horizontal position of the button in the image file.
	 * @param yTextureOffset = The vertical position of the button in the image file.
	 */
	public GuiIconButton(int buttonID, String tooltip, int xPosition, int yPosition, int xTextureOffset, int yTextureOffset)
	{
		this(buttonID, tooltip, xPosition, yPosition, xTextureOffset, yTextureOffset, 20, 20);
	}

	/**
	 * Creates a new button with an image on it.
	 * 
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
		this(buttonID, null, xPosition, yPosition, xTextureOffset, yTextureOffset, xTextureSize, yTextureSize);
	}

	/**
	 * Creates a new button with an image on it.
	 * 
	 * @param buttonID = The ID of the button.
	 * @param xPosition = The horizontal position to draw the button at.
	 * @param yPosition = The vertical position to draw the button at.
	 * @param xTextureOffset = The horizontal position of the button in the image file.
	 * @param yTextureOffset = The vertical position of the button in the image file.
	 * @param xTextureSize = The height of the button.
	 * @param yTextureSize = The width of the button.
	 */
	public GuiIconButton(int buttonID, String tooltip, int xPosition, int yPosition, int xTextureOffset, int yTextureOffset, int xTextureSize, int yTextureSize)
	{
		super(buttonID, xPosition, yPosition, xTextureSize, yTextureSize, "");
		this.xTextureOffset = xTextureOffset;
		this.yTextureOffset = yTextureOffset;
		this.xTextureSize = xTextureSize;
		this.yTextureSize = yTextureSize;
		isEnabled = true;
		this.tooltip = tooltip;
	}

	/**
	 * Draws this button to the screen.
	 */
	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks)
	{
		super.drawButton(mc, mouseX, mouseY, partialTicks);
		if (visible) {
			mc.getTextureManager().bindTexture(textureDir);
            // GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            
			int tempYTextureOffset = yTextureOffset; // Used to bump the texture being drawn down in the image file to display it being hovered over or disabled.

			if (!isEnabled)
			{
				tempYTextureOffset = yTextureOffset + (yTextureSize * 2);
			}
			else if (isMouseOver())
			{
				tempYTextureOffset = yTextureOffset + yTextureSize;
			}

			drawTexturedModalRect(x, y, xTextureOffset, tempYTextureOffset, width, height);
		}
	}

	public boolean hasTooltip()
	{
		return tooltip != null;
	}

	public String tooltip()
	{
		return tooltip;
	}

	/**
	 * Sets if the button is enabled.
	 * 
	 * @param newValue = The new button state.
	 */
	public void setIsEnabled(boolean newValue)
	{
		enabled = newValue;
		isEnabled = newValue;
	}

	/**
	 * @return If the button is enabled or not.
	 */
	public boolean getIsEnabled()
	{
		return isEnabled;
	}
}
