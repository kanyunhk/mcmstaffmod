package net.playmcm.qwertysam.util;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.network.play.client.C14PacketTabComplete;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MovingObjectPosition;
import net.playmcm.qwertysam.gui.GuiCustomMessages;
import net.playmcm.qwertysam.gui.ModGui;

public class NameAutocomplete 
{
	private static boolean playerNamesFound;
    private static boolean waitingOnAutocomplete;
    private static int autocompleteIndex;
    private static List foundPlayerNames = Lists.newArrayList();

    //Sent to from S3APacketTabComplete.java
    public static void requestAutocomplete(String[] playerList)
    {
    	if (Minecraft.getMinecraft().currentScreen instanceof ModGui)
    	{
    		ModGui screen = (ModGui) Minecraft.getMinecraft().currentScreen;
    		screen.performAutocomplete(playerList);
    	}
    	else if (Minecraft.getMinecraft().currentScreen instanceof GuiCustomMessages)
    	{
    		GuiCustomMessages screen = (GuiCustomMessages) Minecraft.getMinecraft().currentScreen;
    		screen.performAutocomplete(playerList);
    	}
    }
    
    //2
    public static void autocompletePlayerNames(GuiTextField textField)
    {
        String var3;

        if (playerNamesFound)
        {
            textField.deleteFromCursor(textField.func_146197_a(-1, textField.getCursorPosition(), false) - textField.getCursorPosition());

            if (autocompleteIndex >= foundPlayerNames.size())
            {
                autocompleteIndex = 0;
            }
        }
        else
        {
            int var1 = textField.func_146197_a(-1, textField.getCursorPosition(), false);
            foundPlayerNames.clear();
            autocompleteIndex = 0;
            String var2 = textField.getText().substring(var1).toLowerCase();
            var3 = textField.getText().substring(0, textField.getCursorPosition());
            sendAutocompleteRequest(var3, var2);

            if (foundPlayerNames.isEmpty())
            {
                return;
            }

            playerNamesFound = true;
            textField.deleteFromCursor(var1 - textField.getCursorPosition());
        }

        if (foundPlayerNames.size() > 1)
        {
            StringBuilder var4 = new StringBuilder();

            for (Iterator var5 = foundPlayerNames.iterator(); var5.hasNext(); var4.append(var3))
            {
                var3 = (String)var5.next();

                if (var4.length() > 0)
                {
                    var4.append(", ");
                }
            }

            Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessageWithOptionalDeletion(new ChatComponentText(var4.toString()), 1);
        }

        textField.writeText((String)foundPlayerNames.get(autocompleteIndex++));
    }

    //3
    private static void sendAutocompleteRequest(String p_146405_1_, String p_146405_2_)
    {
        if (p_146405_1_.length() >= 1)
        {
            BlockPos var3 = null;

            if (Minecraft.getMinecraft().objectMouseOver != null && Minecraft.getMinecraft().objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
            {
                var3 = Minecraft.getMinecraft().objectMouseOver.func_178782_a();
            }

            Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C14PacketTabComplete(p_146405_1_, var3));
            waitingOnAutocomplete = true;
        }
    }
    
    //1
    public static void onAutocompleteResponse(String[] p_146406_1_, GuiTextField textField)
    {
        if (waitingOnAutocomplete)
        {
            playerNamesFound = false;
            foundPlayerNames.clear();
            String[] var2 = p_146406_1_;
            int var3 = p_146406_1_.length;

            for (int var4 = 0; var4 < var3; ++var4)
            {
                String var5 = var2[var4];

                if (var5.length() > 0)
                {
                    foundPlayerNames.add(var5);
                }
            }

            String var6 = textField.getText().substring(textField.func_146197_a(-1, textField.getCursorPosition(), false));
            String var7 = StringUtils.getCommonPrefix(p_146406_1_);

            if (var7.length() > 0 && !var6.equalsIgnoreCase(var7))
            {
            	textField.deleteFromCursor(textField.func_146197_a(-1, textField.getCursorPosition(), false) - textField.getCursorPosition());
            	textField.writeText(var7);
            }
            else if (foundPlayerNames.size() > 0)
            {
                playerNamesFound = true;
                autocompletePlayerNames(textField);
            }
        }
    }
}
