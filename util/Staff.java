package net.playmcm.qwertysam.util;

public enum Staff
{
	INVALID("", ""),
	SAM("55058335a49a44ef920cfb0864225bcf", "Sammeh"), // Sam
	LAUREN("fa9b5735afc0432785f38e6f58277520", "Seppeh"), // Lauren
	ERE("797f44ea30664868adb9a64272927f24", "Ere"), // Ere
	BEN("95a8d801900d45ec812b0c4c099e96cc", "Benneh bOI"), // Ben
	LINU("4bebde164e7c49a0b00f732bd56f840f", "Linu"), // Linus
	ALENA("485759fc52e34d31a3202a54cc709c82", "Aleenuu"), // Alena
	TYLER("bd550bb734524f579df1abb4bb12f076", "Telurr"), // Tyler
	ALEX("7f44ed5ed7f74d54939f5e5b5dd1c2e4", "Alecks"), // Alex
	BRANDON("f17f30cd60ae402c87e1645be92577ab", "Brenden"), // Brandon
	EMILY("b9f0500fc3c944dbbdb6f44f6b38bcfe", "Emelee"), // Emily
	ETIAN("e38dac7884d9469c9cb7287bcf88a690", "Etien"), // Etian
	FAITH("0d3ea2215d52459a92b3d0186b851aed", "Fetheh"); // Faithhh
	
	private String uuid;
	private String nickname;
	
	Staff(String uuid, String nickname)
	{
		this.uuid = uuid;
		this.nickname = nickname;
	}
	
	public String getUUID()
	{
		return uuid;
	}
	
	public String getName()
	{
		return nickname;
	}
	
	public boolean isEtian()
	{
		return this == ETIAN;
	}
	
	public static Staff valueByUUID(String uuid)
	{
		for (Staff staff : Staff.values())
		{
			if (staff.getUUID().equals(uuid)) return staff;
		}
		
		return INVALID;
	}
	
	public static boolean isValid(Staff staff)
	{
		return staff != INVALID;
	}
}
