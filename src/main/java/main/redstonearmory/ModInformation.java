package main.redstonearmory;

public class ModInformation {

	public static final String NAME = "Redstone Armory";
	public static final String ID = "RArm";
	public static final String CHANNEL = "RedstoneArmory";
	public static final String VERSION = "1.1pre";
	public static final String GUIFACTORY = "main.redstonearmory.gui.ConfigGuiFactory";
	public static final String REQUIRED = "required-after:RedstoneArsenal;" + "required-after:ttCore;" + "after:ThermalFoundation;" + "after:simplyjetpacks";
	public static final String CLIENTPROXY = "main.redstonearmory.proxies.ClientProxy";
	public static final String COMMONPROXY = "main.redstonearmory.proxies.CommonProxy";
}
