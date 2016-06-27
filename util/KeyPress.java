package qwertysam.util;

import org.lwjgl.input.Keyboard;

public class KeyPress
{
	private int keyID;

	/**
	 * Filters out spam when the key is being held down.
	 */
	private boolean filterSpam;
	private boolean justEntered;
	private boolean pressTrigger;

	/**
	 * Filters the detection of a key being pressed.
	 * 
	 * @param keyID the number ID of the key
	 */
	public KeyPress(int keyID, boolean onPressTrigger)
	{
		this.keyID = keyID;
		filterSpam = false;
		justEntered = true;
		pressTrigger = onPressTrigger;
	}

	private boolean previouslyDown = false;

	/**
	 * @return if the key has been released.
	 */
	public boolean onRelease()
	{
		boolean isKeyDown = isKeyDown();

		if (previouslyDown && !isKeyDown && !justEntered)
		{
			previouslyDown = isKeyDown;
			return true;
		}
		else if (!isKeyDown)
		{
			justEntered = false;
		}
		previouslyDown = isKeyDown;

		return false;
	}

	private boolean isKeyDown()
	{
		return Keyboard.isKeyDown(keyID);
	}

	/**
	 * @return if the key has been triggered.
	 */
	public boolean isPressed()
	{
		boolean isKeyDown = isKeyDown();

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

	public boolean isTriggered()
	{
		return (pressTrigger ? isPressed() : onRelease());
	}
}
