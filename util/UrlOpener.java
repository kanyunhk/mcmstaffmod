package mcmstaffmod.util;

import java.awt.Desktop;
import java.io.IOException;

public class UrlOpener
{
	/**
	 * Opens a URL.
	 * 
	 * @param URL the URL to open
	 */
	public static void openURL(String URL)
	{
		try
		{
			Desktop.getDesktop().browse(java.net.URI.create(URL));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
