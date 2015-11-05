package net.playmcm.qwertysam.util;

import java.awt.Desktop;
import java.io.IOException;

public class UrlOpener 
{
	/**
	 *  Opens a URL.
	 * @param URL the URL to open
	 */
	public static void openURL(String URL)
	{
		// open the default web browser for the HTML page
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
