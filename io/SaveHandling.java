package net.playmcm.qwertysam.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Timer;
import java.util.TimerTask;

import net.minecraft.client.Minecraft;

/**
 * The class used to save, load, and set options.
 * @author Samson
 */
public class SaveHandling 
{
	private static final String backupFileName = "mcm_options.txt";
	
	/** The backup options save file. **/
	private static File backupOptionsFile = new File(Minecraft.getMinecraft().mcDataDir.getAbsolutePath(), backupFileName);
	
	/** The symbol that separates the saved option from the options key. **/
	private static final String splitSymbol = "<:<>:";
	
	/** Custom messages one title **/
	private static String customOneTitle;
	
	/** Custom message one first line **/
	private static String customOne1;
	
	/** Custom message one second line **/
	private static String customOne2;
	
	/** Custom messages two title **/
	private static String customTwoTitle;
	
	/** Custom message two first line **/
	private static String customTwo1;
	
	/** Custom message two second line **/
	private static String customTwo2;
	
	public static String getCustomOneTitle()
	{
		return customOneTitle;
	}
	
	public static String getCustomOne1()
	{
		return customOne1;
	}
	
	public static String getCustomOne2()
	{
		return customOne2;
	}
	
	public static String getCustomTwoTitle()
	{
		return customTwoTitle;
	}
	
	public static String getCustomTwo1()
	{
		return customTwo1;
	}
	
	public static String getCustomTwo2()
	{
		return customTwo2;
	}
	
	public static void setCustomOneTitle(String newMessage)
	{
		customOneTitle = newMessage;
	}
	
	public static void setCustomOne1(String newMessage)
	{
		customOne1 = newMessage;
	}
	
	public static void setCustomOne2(String newMessage)
	{
		customOne2 = newMessage;
	}
	
	public static void setCustomTwoTitle(String newMessage)
	{
		customTwoTitle = newMessage;
	}
	
	public static void setCustomTwo1(String newMessage)
	{
		customTwo1 = newMessage;
	}
	
	public static void setCustomTwo2(String newMessage)
	{
		customTwo2 = newMessage;
	}
	
	/**
	 * Resets and saves all saved option variables.
	 */
	public static void resetOptions()
	{
		customOneTitle = "Custom";
		customOne1 = "";
		customOne2 = "";
		
		customTwoTitle = "Custom";
		customTwo1 = "";
		customTwo2 = "";
	}
	
	/**
	 * Saves all the current options.
	 */
	public static void saveOptions()
    {
		if (!backupOptionsFile.exists())
        {
			try 
			{
				backupOptionsFile.createNewFile();
				resetOptions();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
        }
		
        try
        {
            PrintWriter printer = new PrintWriter(new FileWriter(backupOptionsFile));
            printer.println("customOneTitle" + splitSymbol + customOneTitle);
            printer.println("customOne1" + splitSymbol + customOne1);
            printer.println("customOne2" + splitSymbol + customOne2);
            printer.println("customTwoTitle" + splitSymbol + customTwoTitle);
            printer.println("customTwo1" + splitSymbol + customTwo1);
            printer.println("customTwo2" + splitSymbol + customTwo2);
            
            printer.close();
        }
        catch (Exception e)
        {
        	e.printStackTrace();
        }
    }
	
	/**
	 * Loads all the options.
	 */
	public static void loadOptions()
    {
		resetOptions();
		
        try
        {
            if (!backupOptionsFile.exists())
            {
            	resetOptions();
            	saveOptions();
            }
            
            BufferedReader reader = new BufferedReader(new FileReader(backupOptionsFile));
            String loadedOptionLine = "";
            
            while ((loadedOptionLine = reader.readLine()) != null)
            {
                try
                {
                    String[] splitOptionsLine = loadedOptionLine.split(splitSymbol);
                    
                    if(splitOptionsLine[0].equalsIgnoreCase("customOneTitle"))
                    {
                    	setCustomOneTitle(splitOptionsLine[1]);
                    }
                    
                    if(splitOptionsLine[0].equalsIgnoreCase("customOne1"))
                    {
                    	setCustomOne1(splitOptionsLine[1]);
                    }

                    else if(splitOptionsLine[0].equals("customOne2"))
                    {
                    	setCustomOne2(splitOptionsLine[1]);
                    }
                    
                    if(splitOptionsLine[0].equalsIgnoreCase("customTwoTitle"))
                    {
                    	setCustomTwoTitle(splitOptionsLine[1]);
                    }
                    
                    else if(splitOptionsLine[0].equals("customTwo1"))
                    {
                    	setCustomTwo1(splitOptionsLine[1]);
                    }

                    else if(splitOptionsLine[0].equals("customTwo2"))
                    {
                    	setCustomTwo2(splitOptionsLine[1]);
                    }
                }
                catch (Exception e)
                {
                	e.printStackTrace();
                }
            }
            
            reader.close();
        }
        catch (Exception e)
        {
        	e.printStackTrace();
        }
    }
}
