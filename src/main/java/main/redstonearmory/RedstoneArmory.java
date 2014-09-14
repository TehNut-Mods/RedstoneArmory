package main.redstonearmory;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import main.redstonearmory.blocks.BlockRecipeRegistry;
import main.redstonearmory.blocks.BlockRegistry;
import main.redstonearmory.gui.CreativeTabRArm;
import main.redstonearmory.items.ItemRecipeRegistry;
import main.redstonearmory.items.ItemRegistry;
import main.redstonearmory.network.PacketHandler;
import main.redstonearmory.proxies.CommonProxy;
import main.redstonearmory.util.EnviroChecks;
import main.redstonearmory.util.EventHandler;
import main.redstonearmory.util.OreDictHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.config.Configuration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = ModInformation.ID, name = ModInformation.NAME, version = ModInformation.VERSION, dependencies = ModInformation.REQUIRED, guiFactory = ModInformation.GUIFACTORY)
public class RedstoneArmory {

    @SidedProxy(clientSide = ModInformation.CLIENTPROXY, serverSide = ModInformation.COMMONPROXY)
    public static CommonProxy proxy;

    public static CreativeTabs tabRArm = new CreativeTabRArm(ModInformation.ID + ".creativeTab");
    public static Logger logger = LogManager.getLogger(ModInformation.NAME);

    @Mod.Instance
    public static RedstoneArmory instance;
    public static Configuration config;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {

	    ConfigHandler.init(event.getSuggestedConfigurationFile());

        ItemRegistry.registerAllItems();
        BlockRegistry.registerAllBlocks();

        OreDictHandler.registerOreDict();
	    FMLCommonHandler.instance().bus().register(new EventHandler());
//	    MinecraftForge.EVENT_BUS.register(new ParticleRegistry());
//	    FMLCommonHandler.instance().bus().register(new ParticleScatterEventHandler());

        proxy.load();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        ItemRecipeRegistry.registerItemRecipes();
        BlockRecipeRegistry.registerBlockRecipes();
        PacketHandler.init();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

        ItemRecipeRegistry.registerLateItemRecipes();
	    EnviroChecks.verifyEnviro();
    }
}