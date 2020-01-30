package mcmstaffmod.util;

public enum Staff
{
	// NAME("uuidwithouthyphens", "nickname")
	INVALID("", ""),
	SAM("55058335a49a44ef920cfb0864225bcf", "Sampai"), // Sam
	LAUREN("8efd6e89c6cb462286d38ef2dc08f69e", "Seppu"), // Lauren (old one fa9b5735afc0432785f38e6f58277520)
	CHRIS("797f44ea30664868adb9a64272927f24", "Erebby"), // Chris
	BEN("95a8d801900d45ec812b0c4c099e96cc", "Benneh bOI"), // Ben
	LINUS("4bebde164e7c49a0b00f732bd56f840f", "Linu"), // Linus
	ARLYN("485759fc52e34d31a3202a54cc709c82", "Aleenu"), // Arlyn
	TYLER("bd550bb734524f579df1abb4bb12f076", "Telurr"), // Tyler
	ALEX("7f44ed5ed7f74d54939f5e5b5dd1c2e4", "Alecks"), // Alex
	EMILY("b9f0500fc3c944dbbdb6f44f6b38bcfe", "Emelee"), // Emily
	ETIAN("e38dac7884d9469c9cb7287bcf88a690", "Etienne"), // Etian
	FAITH("0d3ea2215d52459a92b3d0186b851aed", "Fethheh"), // Faithhh
	LACY("e85ea51e4c844b4bb5f5dfe210fef709", "Lecceh"), // Lacy
	KYMMA("4372ba0a8c50441eaf43a6cedb2df7d4", "Kymmy"), // Kymma
	SKYE("fd172f24378341428bfe53ea4d92ef12", "Sandwich"), // Skye
	JENNA("98090da156f94c93a64f939dd4fab92f", "Slacks"), // Jenna
	INVENTRAX("aee12c579ec54974bd1d1c77b359d1ab", "Venny"), // InVentrax
	AMBER("e80a482139c34bc5bebf0ffd2ae90725", "Embur"), // Amber
	KANNN("bd4f27d249464d34b743a1765030a3e4", "Kanny"); // Kannn
	
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
		return this == ETIAN || this == ALEX;
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
