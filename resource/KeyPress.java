package net.playmcm.qwertysam.resource;

import org.lwjgl.input.Keyboard;

public class KeyPress
{
	private int keyID;

	/**
	 * Filters out spam when the key is being held down.
	 */
	private boolean filterSpam;

	/**
	 * Filters the detection of a key being pressed.
	 * 
	 * @param keyID the number ID of the key
	 */
	public KeyPress(int keyID)
	{
		this.keyID = keyID;
		this.filterSpam = false;
	}

	/**
	 * @return if the key has been triggered.
	 */
	public boolean isPressed()
	{
		boolean isKeyDown = Keyboard.isKeyDown(keyID);

		if (isKeyDown && !filterSpam)
		{
			filterSpam = true;
			return true;
		}
		else if (!isKeyDown)
		{
			filterSpam = false;
		}
		return false;
	}
}
