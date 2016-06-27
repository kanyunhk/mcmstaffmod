package qwertysam.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import qwertysam.log.LogType;
import qwertysam.log.QLogger;
import qwertysam.util.PathUtil;

public class OptionManager
{
	private File saveFile;
	private List<Option> options;

	/** The symbol that separates the saved option from the options key. **/
	private static final String splitSymbol = ":";

	public OptionManager(String fileName)
	{
		saveFile = new File(PathUtil.getMinecraftDir(), fileName);
		options = new ArrayList<Option>();
	}

	/**
	 * Registers an option entry.
	 * 
	 * @param option the option to register
	 */
	public void registerOption(Option option)
	{
		options.add(option);
	}

	/**
	 * Registers an option entry.
	 * 
	 * @param key the key of the option to unregister
	 */
	public void unregisterOption(String key)
	{
		Option toRemove = null;
		for (Option option : options)
		{
			if (option.key().equals(key)) toRemove = option;
		}

		if (toRemove != null)
		{
			options.remove(toRemove);
		}
		else
		{
			QLogger.log(LogType.OPTIONS_ERROR, "Could not remove any option with the key '" + key + "'");
		}
	}

	/**
	 * Gets the option with the specified key.
	 * 
	 * @param key the key
	 * @return the option with the specified key.
	 */
	public Option getOption(String key)
	{
		for (Option option : options)
		{
			if (option.key().equals(key)) return option;
		}
		QLogger.log(LogType.OPTIONS_ERROR, "No option exists with the key '" + key + "'");
		return null;
	}

	public void defaultResetOptions()
	{
		for (Option option : options)
		{
			option.setString(option.getDefault());
		}
	}

	/**
	 * Saves all the current options.
	 */
	public void saveOptions()
	{
		if (validateSaveFile())
		{
			try
			{
				PrintWriter printer;

				printer = new PrintWriter(new FileWriter(saveFile));

				// Writes all the options to new lines
				for (Option option : options)
				{
					printer.println(option.key() + splitSymbol + option.value());
				}

				printer.close();
			}
			catch (IOException e)
			{
				QLogger.log(LogType.OPTIONS_ERROR, "IOException occurred while saving options.");
				e.printStackTrace();
			}
		}
	}

	/**
	 * Loads all the options.
	 * 
	 * @return if the options were loaded successfully.
	 */
	public boolean loadOptions()
	{
		try
		{
			if (saveFile.exists())
			{
				BufferedReader reader = new BufferedReader(new FileReader(saveFile));
				String loadedOptionLine = "";

				while ((loadedOptionLine = reader.readLine()) != null)
				{
					String[] splitOptionsLine = loadedOptionLine.split(splitSymbol);

					String optionKey = "null";

					if (splitOptionsLine.length > 0) optionKey = splitOptionsLine[0];

					if (splitOptionsLine.length > 1) // If there is something on the line
					{
						String value = splitOptionsLine[1];
						
						for (int i = 2; i < splitOptionsLine.length; i++)
						{
							value = value + splitSymbol + splitOptionsLine[i];
						}

						if (value != null) // if there is a value
						{
							Option option = getOption(optionKey);

							if (option != null) // If the corresponding option exists
							{
								option.setString(value);
							}
							else
							{
								QLogger.log(LogType.OPTIONS_ERROR, "No corrosponding option has been registered for '" + optionKey + "'");
							}
						}
						else
						{
							QLogger.log(LogType.OPTIONS_ERROR, "Null value found on the provided line '" + optionKey + "'");
						}
					}
					else
					{
						QLogger.log(LogType.OPTIONS_ERROR, "No value on the provided line. '" + optionKey + "'");
					}
				}
				reader.close();
				return true;
			}
		}
		catch (IOException e)
		{
			QLogger.log(LogType.OPTIONS_ERROR, "IOException occurred while loading options.");
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * Tries to create the file if it doesn't exist.
	 * 
	 * @return if the file was created successfully or not.
	 */
	public boolean validateSaveFile()
	{
		if (!saveFile.exists())
		{
			try
			{
				saveFile.createNewFile();
			}
			catch (IOException e)
			{
				QLogger.log(LogType.OPTIONS_ERROR, "IOException occurred while creating options file.");
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
}
