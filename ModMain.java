package mcmstaffmod;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import mcmstaffmod.gui.ModGui;
import mcmstaffmod.io.Option;
import mcmstaffmod.io.OptionManager;
import mcmstaffmod.messages.api.MessageSender;
import mcmstaffmod.util.AuthUtil;
import mcmstaffmod.util.KeyPress;

/**
 * Uses Timer.java from Minecraft src code.
 */
public class ModMain
{
	// TODO Always change this to false before exporting
	public static final boolean debug = false;
	
	private static ModMain inst = null;
	public static ModMain instance() {
		if (inst == null) inst = new ModMain();
		return inst;
	}
	
	public static int KEYBOARD_SHORTCUT = Keyboard.KEY_G;
	private KeyPress triggerKey;
	private OptionManager options;
	private MessageSender messageSender;

	public ModMain()
	{
		KEYBOARD_SHORTCUT = AuthUtil.getUser() != null && AuthUtil.getUser().isEtian() ? Keyboard.KEY_G : Keyboard.KEY_GRAVE;
		
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
		if (triggerKey.isPressed())
		{
			if (Minecraft.getMinecraft().currentScreen instanceof ModGui) // Exits if it's already on the menu
			{
				Minecraft.getMinecraft().displayGuiScreen((GuiScreen) null);
			}
			else if (Minecraft.getMinecraft().currentScreen == null) // Stops the GUI from opening while not in-game
			{
				Minecraft.getMinecraft().displayGuiScreen(new ModGui(this));
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
