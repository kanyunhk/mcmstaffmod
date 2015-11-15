package net.playmcm.qwertysam;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.playmcm.qwertysam.gui.ModGui;
import net.playmcm.qwertysam.io.Option;
import net.playmcm.qwertysam.io.OptionKey;
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

	public ModMain()
	{
		triggerKey = new KeyPress(Keyboard.KEY_GRAVE);

		options = new OptionManager("mcm_options.txt");
		options.registerOption(new Option(OptionKey.customOneTitle, "Custom 1"));
		options.registerOption(new Option(OptionKey.customOne1, ""));
		options.registerOption(new Option(OptionKey.customOne2, ""));
		options.registerOption(new Option(OptionKey.customOne3, ""));
		options.registerOption(new Option(OptionKey.customTwoTitle, "Custom 2"));
		options.registerOption(new Option(OptionKey.customTwo1, ""));
		options.registerOption(new Option(OptionKey.customTwo2, ""));
		options.registerOption(new Option(OptionKey.customTwo3, ""));
		options.registerOption(new Option(OptionKey.customThreeTitle, "Custom 3"));
		options.registerOption(new Option(OptionKey.customThree1, ""));
		options.registerOption(new Option(OptionKey.customThree2, ""));
		options.registerOption(new Option(OptionKey.customThree3, ""));
		options.registerOption(new Option(OptionKey.customFourTitle, "Custom 4"));
		options.registerOption(new Option(OptionKey.customFour1, ""));
		options.registerOption(new Option(OptionKey.customFour2, ""));
		options.registerOption(new Option(OptionKey.customFour3, ""));
		options.loadOptions();

		messageSender = new MessageSender(this.options);
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

	public OptionManager getOptions()
	{
		return options;
	}

	public MessageSender message()
	{
		return messageSender;
	}
}
