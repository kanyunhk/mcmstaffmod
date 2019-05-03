package mcmstaffmod.gui;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.gui.GuiScreen;

public class GuiFloatingTextAPI extends GuiScreen
{
	public void drawFloatingText(int mouseX, int mouseY)
	{
		for (Object button : this.buttonList)
		{
			if (button instanceof GuiIconButton)
			{
				GuiIconButton giButton = (GuiIconButton) button;

				if (giButton.isMouseOver() && giButton.hasTooltip())
				{
					List<String> hnng = new ArrayList<String>();
					hnng.add(giButton.tooltip());
					drawHoveringText(hnng, mouseX, mouseY);
				}
			}
		}
	}
}
