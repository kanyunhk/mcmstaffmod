package net.playmcm.qwertysam.util;

import org.lwjgl.input.Keyboard;

public class KeyPress
{
	private int keyID;

	/**
	 * Filters out spam when the key is being held down.
	 */
	private boolean filterSpam;
	private boolean justEntered;
	
	/**
	 * Filters the detection of a key being pressed.
	 * 
	 * @param keyID the number ID of the key
	 */
	public KeyPress(int keyID)
	{
		this.keyID = keyID;
		filterSpam = false;
		justEntered = true;
	}

	/**
	 * @return if the key has been triggered.
	 */
	public boolean isPressed()
	{
		boolean isKeyDown = Keyboard.isKeyDown(keyID);

		if (isKeyDown && !filterSpam && !justEntered)
		{
			filterSpam = true;
			return true;
		}
		else if (!isKeyDown)
		{
			justEntered = false;
			filterSpam = false;
		}
		return false;
	}
}
