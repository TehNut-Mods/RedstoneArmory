package main.redstonearmory;

//This is just a place to quickly change information about the mod. More can be added here later, but this is really all you need to start with.

public class ModInformation {
	public static final String NAME = "Redstone Armory";
	public static final String ID = "RArm";
	public static final String CHANNEL = "RedstoneArmory";
	public static final String VERSION = "1.1pre1";
	public static final String GUIFACTORY = "main.redstonearmory.gui.ConfigGuiFactory";
	public static final String REQUIRED = "required-after:RedstoneArsenal;" + "after:ThermalFoundation;";
	public static final String CLIENTPROXY = "main.redstonearmory.proxies.ClientProxy";
	public static final String COMMONPROXY = "main.redstonearmory.proxies.CommonProxy";
	public static final String WEBASSETSDIR = "http://rarm.tehnut.info/assets/trails/";
}
