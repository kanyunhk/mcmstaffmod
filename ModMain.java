package net.playmcm.qwertysam;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.playmcm.qwertysam.gui.ModGui;
import net.playmcm.qwertysam.io.Option;
import net.playmcm.qwertysam.io.OptionManager;
import net.playmcm.qwertysam.messages.api.MessageSender;
import net.playmcm.qwertysam.util.KeyPress;

/**
 * Uses Timer.java from Minecraft src code.
 * 
 * @version 2.0
 */
public class ModMain
{
	private KeyPress triggerKey;
	private OptionManager options;
	private MessageSender messageSender;

	public static final boolean debug = false;

	public ModMain()
	{
		triggerKey = new KeyPress(Keyboard.KEY_GRAVE, true);

		options = new OptionManager("mcm_options.txt");

		for (Option option : Option.values())
		{
			options.registerOption(option);
		}
		options.loadOptions();

		messageSender = new MessageSender();
	}

	/**
	 * Has access to the main gameloop through Timer.updateTimer();
	 */
	public void gameLoop()
	{
		if (Minecraft.getMinecraft().currentScreen instanceof ModGui || Minecraft.getMinecraft().currentScreen == null) // Stops the GUI from opening while not in-game
		{
			if (triggerKey.isPressed())
			{
				if (Minecraft.getMinecraft().currentScreen instanceof ModGui) // Exits if it's already on the menu
				{
					Minecraft.getMinecraft().displayGuiScreen((GuiScreen) null);
				}
				else // Goes to the menu if it's not there
				{
					Minecraft.getMinecraft().displayGuiScreen(new ModGui(this));
				}
			}
		}
	}

	/**
	 * @return the mod's OptionManager.
	 */
	public OptionManager getOptions()
	{
		return options;
	}
}
