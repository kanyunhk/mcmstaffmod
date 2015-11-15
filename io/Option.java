package net.playmcm.qwertysam.io;

public class Option
{
	private String key;
	private String value;
	private String originalValue;
	
	/**
	 * An value with a key.
	 * 
	 * @param key the identifier of this
	 * @param value the value of this
	 */
	public Option(String key, float value)
	{
		this.key = key;
		this.originalValue = "" + value;
		this.setFloat(value);
	}

	/**
	 * An value with a key.
	 * 
	 * @param key the identifier of this
	 * @param value the value of this
	 */
	public Option(String key, int value)
	{
		this.key = key;
		this.originalValue = "" + value;
		this.setInteger(value);
	}

	/**
	 * An value with a key.
	 * 
	 * @param key the identifier of this
	 * @param value the value of this
	 */
	public Option(String key, boolean value)
	{
		this.key = key;
		this.originalValue = "" + (value ? "true" : "false");
		this.setBoolean(value);
	}

	/**
	 * An value with a key.
	 * 
	 * @param key the identifier of this
	 * @param value the value of this
	 */
	public Option(String key, String value)
	{
		this.key = key;
		this.originalValue = "" + value;
		this.setString(value);
	}

	/**
	 * @return the identifier for this
	 */
	public String key()
	{
		return key;
	}

	/**
	 * @return this value as a String.
	 */
	public String asString()
	{
		return value;
	}

	/**
	 * @return this value as a boolean.
	 */
	public boolean asBoolean()
	{
		return value.equals("true") ? true : false;
	}

	/**
	 * @return this value as an integer.
	 */
	public int asInteger()
	{
		return Integer.parseInt(value);
	}

	/**
	 * @return this value as a float.
	 */
	public float asFloat()
	{
		return Float.parseFloat(value);
	}

	/**
	 * Sets this value as a String.
	 * 
	 * @param value the new value
	 */
	public void setString(String value)
	{
		this.value = value;
	}

	/**
	 * Sets this value as a boolean.
	 * 
	 * @param value the new value
	 */
	public void setBoolean(boolean value)
	{
		this.value = value ? "true" : "false";
	}

	/**
	 * Sets this value as an integer.
	 * 
	 * @param value the new value
	 */
	public void setInteger(int value)
	{
		this.value = "" + value;
	}

	/**
	 * Sets this value as a float.
	 * 
	 * @param value the new value
	 */
	public void setFloat(float value)
	{
		this.value = "" + value;
	}
	
	/**
	 * @return if this has been unchanged.
	 */
	public boolean isUnchanged()
	{
		return value.equals(originalValue);
	}
	
	/**
	 * @return the default value of this.
	 */
	public String getDefault()
	{
		return originalValue;
	}
}
