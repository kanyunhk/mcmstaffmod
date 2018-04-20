package qwertysam;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import qwertysam.gui.ModGui;
import qwertysam.io.Option;
import qwertysam.io.OptionManager;
import qwertysam.messages.api.MessageSender;
import qwertysam.util.AuthUtil;
import qwertysam.util.KeyPress;

/**
 * Uses Timer.java from Minecraft src code.
 * 
 * @version 2.1
 */
public class ModMain
{
	public static final int KEYBOARD_SHORTCUT = AuthUtil.getUser().isEtian() ? Keyboard.KEY_G : Keyboard.KEY_GRAVE;
	private KeyPress triggerKey;
	private OptionManager options;
	private MessageSender messageSender;

	// TODO Always change this to false before exporting
	public static final boolean debug = false;

	public ModMain()
	{
		triggerKey = new KeyPress(KEYBOARD_SHORTCUT, true);

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
