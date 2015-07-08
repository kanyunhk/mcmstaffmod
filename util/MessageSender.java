package net.playmcm.qwertysam.util;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.playmcm.qwertysam.io.SaveHandling;

public class MessageSender 
{
	public static final int delay = 200;
	
	/**
	 * @param messageID = The ID of the message to be send.
	 * @param _
	 * @param become_mod = Sends information on how to become a moderator.
	 * @param rules_link = Sends the link to the rules page.
	 * @param vote_link = Sends the link to the voting page.
	 * @param donate_link = Sends the link to the donation page.
	 * @param report_link = Sends the link to the reports page.
	 * @param appeal_link = Sends the link to the appeals page.
	 * @param explain_teaming = Explains the definition of teaming.
	 * @param explain_rdm = Explains the definition of RDM.
	 * @param explain_argue = Explains the consequences of arguing in-game.
	 */
	public static void sendInfo(String messageID)
	{
		Random rand = new Random();
		
		if(messageID == "become_mod")
		{
			int randInt = rand.nextInt(5);
			switch(randInt)
			{
				case 0:
					sendMessage("If you'd like to learn how you can become a moderator, check out this page.");
					break;
				case 1:
					sendMessage("If you're interested in becoming a staff member, you can read about it here.");
					break;
				case 2:
					sendMessage("If you would like to become a staff member, please read this page.");
					break;
				case 3:
					sendMessage("If you want to know how to become a moderator then please check out this page.");
					break;
				case 4:
					sendMessage("If you'd like to become a moderator, please read about it here.");
					break;
			}
			
			Thread t = new Thread(new Runnable() 
			{
			    public void run() 
			    {
			    	try 
			    	{
						Thread.sleep(delay);
					} 
			    	catch (InterruptedException e) 
			    	{
						e.printStackTrace();
					}
			    	sendMessage("http://goo.gl/8o8rbv"); // The link to the how to become moderator page
			    }
			});
			t.start();
			
		}
		else if(messageID == "rules_link")
		{
			int randInt = rand.nextInt(5);
			switch(randInt)
			{
				case 0:
					sendMessage("There's no official rules page yet, but you can read about what's reportable here.");
					break;
				case 1:
					sendMessage("You can find the rules under the \"What Is Against The Rules\" section on this page.");
					break;
				case 2:
					sendMessage("You can read about what's against the rules on this page.");
					break;
				case 3:
					sendMessage("To get to the rules section, click on this link and look under \"What Is Against The Rules\"");
					break;
				case 4:
					sendMessage("If you'd like to find the server rules, click on this link and read about what's against the rules.");
					break;
			}
			
			Thread t = new Thread(new Runnable() 
			{
			    public void run() 
			    {
			    	try 
			    	{
						Thread.sleep(delay);
					} 
			    	catch (InterruptedException e) 
			    	{
						e.printStackTrace();
					}
			    	sendMessage("http://goo.gl/dniAkM"); // The link to the reports help page
			    }
			});
			t.start();
		}
		else if(messageID == "vote_link")
		{
			int randInt = rand.nextInt(5);
			switch(randInt)
			{
				case 0:
					sendMessage("If you'd like to support us by voting for the server, please click here.");
					break;
				case 1:
					sendMessage("If you're interested in voting for our server, please click here.");
					break;
				case 2:
					sendMessage("If you're looking to vote for the server, please go to this page.");
					break;
				case 3:
					sendMessage("If you'd like to vote for our server, please click on this link.");
					break;
				case 4:
					sendMessage("If you are looking to vote for our server, please click here.'");
					break;
			}
			
			Thread t = new Thread(new Runnable() 
			{
			    public void run() 
			    {
			    	try 
			    	{
						Thread.sleep(delay);
					} 
			    	catch (InterruptedException e) 
			    	{
						e.printStackTrace();
					}
			    	sendMessage("http://minecraft-murder.net/vote"); // The link to the vote page
			    }
			});
			t.start();
		}
		else if(messageID == "donate_link")
		{
			int randInt = rand.nextInt(5);
			switch(randInt)
			{
				case 0:
					sendMessage("If you'd like to purchase ranks, you can donate here.");
					break;
				case 1:
					sendMessage("If you're interested in donating to get a rank, you can purchase them here.");
					break;
				case 2:
					sendMessage("If you'd like to donate to get a rank, please click on this link.");
					break;
				case 3:
					sendMessage("If you would like to donate, you can purchase ranks on this page.");
					break;
				case 4:
					sendMessage("If you're interested donating to get a rank, please take a look at this page.");
					break;
			}
			
			Thread t = new Thread(new Runnable() 
			{
			    public void run() 
			    {
			    	try 
			    	{
						Thread.sleep(delay);
					} 
			    	catch (InterruptedException e) 
			    	{
						e.printStackTrace();
					}
			    	sendMessage("http://minecraft-murder.net/shop"); // The link to the donation page
			    }
			});
			t.start();
		}
		else if(messageID == "report_link")
		{
			int randInt = rand.nextInt(5);
			switch(randInt)
			{
				case 0:
					sendMessage("If you'd like to file a report against someone, you can do so here.");
					break;
				case 1:
					sendMessage("If you want to make a report, you can do so on this page.");
					break;
				case 2:
					sendMessage("If you want to make a report, please go to this page and make a new thread.");
					break;
				case 3:
					sendMessage("If you'd like to report someone, please click on this link.");
					break;
				case 4:
					sendMessage("If you want to report someone, please go here and create a new thread.");
					break;
			}
			
			Thread t = new Thread(new Runnable() 
			{
			    public void run() 
			    {
			    	try 
			    	{
						Thread.sleep(delay);
					} 
			    	catch (InterruptedException e) 
			    	{
						e.printStackTrace();
					}
			    	sendMessage("http://goo.gl/cGaXAm"); // The link to the reports page
			    }
			});
			t.start();
		}
		else if(messageID == "appeal_link")
		{
			int randInt = rand.nextInt(4);
			switch(randInt)
			{
				case 0:
					sendMessage("If you'd like to create a ban appeal, you can do so on this page.");
					break;
				case 1:
					sendMessage("If you want to create a ban appeal, please click on this link.");
					break;
				case 2:
					sendMessage("If you'd like to make a ban appeal, please go to this page and create a new thread.");
					break;
				case 3:
					sendMessage("If you want to make a ban appeal, please go here and make a new thread.");
					break;
			}
			
			Thread t = new Thread(new Runnable() 
			{
			    public void run() 
			    {
			    	try 
			    	{
						Thread.sleep(delay);
					} 
			    	catch (InterruptedException e) 
			    	{
						e.printStackTrace();
					}
			    	sendMessage("http://goo.gl/SK7140"); // The link to the ban appeals page
			    }
			});
			t.start();
		}
		else if(messageID == "explain_teaming")
		{
			sendMessage("Teaming is when the gunner knows who the murderer is and purposefully shoots an innocent.");
			
			Thread t = new Thread(new Runnable() 
			{
			    public void run() 
			    {
			    	try 
			    	{
						Thread.sleep(delay);
					} 
			    	catch (InterruptedException e) 
			    	{
						e.printStackTrace();
					}
			    	sendMessage("It is against the rules to team.");
			    }
			});
			t.start();
		}
		else if(messageID == "explain_rdm")
		{
			sendMessage("RDMing, or random killing, is when a player has a gun and shoots a random person not knowing whether");
			
			Thread t = new Thread(new Runnable() 
			{
			    public void run() 
			    {
			    	try 
			    	{
						Thread.sleep(delay);
					} 
			    	catch (InterruptedException e) 
			    	{
						e.printStackTrace();
					}
			    	sendMessage("they're innocent or not. RDMing is not against the rules.");
			    }
			});
			t.start();
		}
		else if(messageID == "explain_argue")
		{
			sendMessage("If you disagree with the punishment that was received, you may go to the forum to discuss the issue.");
			
			Thread t = new Thread(new Runnable() 
			{
			    public void run() 
			    {
			    	try 
			    	{
						Thread.sleep(delay);
					} 
			    	catch (InterruptedException e) 
			    	{
						e.printStackTrace();
					}
			    	sendMessage("http://goo.gl/SK7140"); // The link to the ban appeals page
			    }
			});
			t.start();
			
			Thread t1 = new Thread(new Runnable() 
			{
			    public void run() 
			    {
			    	try 
			    	{
						Thread.sleep(delay*2);
					} 
			    	catch (InterruptedException e) 
			    	{
						e.printStackTrace();
					}
			    	sendMessage("If you continue to debate over this punishment in-game, you will also receive punishment.");
			    }
			});
			t1.start();
		}
		else if(messageID == "custom_one")
		{
			if (SaveHandling.getCustomOne1() != "")
			{
				sendMessage(SaveHandling.getCustomOne1());
			}
			
			if (SaveHandling.getCustomOne2() != "")
			{
				Thread t = new Thread(new Runnable() 
				{
				    public void run() 
				    {
				    	try 
				    	{
							Thread.sleep(delay);
						} 
				    	catch (InterruptedException e) 
				    	{
							e.printStackTrace();
						}
				    	sendMessage(SaveHandling.getCustomOne2());
				    }
				});
				t.start();
			}
		}
		else if(messageID == "custom_two")
		{
			if (SaveHandling.getCustomTwo1() != "")
			{
				sendMessage(SaveHandling.getCustomTwo1());
			}
			
			if (SaveHandling.getCustomTwo2() != "")
			{
				Thread t = new Thread(new Runnable() 
				{
				    public void run() 
				    {
				    	try 
				    	{
							Thread.sleep(delay);
						} 
				    	catch (InterruptedException e) 
				    	{
							e.printStackTrace();
						}
				    	sendMessage(SaveHandling.getCustomTwo2());
				    }
				});
				t.start();
			}
		}
	}
	
	/**
	 * Sends a message from the player.
	 * @param message = The message to be sent.
	 */
	public static void sendMessage(String message)
	{
		Minecraft.getMinecraft().thePlayer.sendChatMessage(message);
	}
}
