package main.redstonearmory;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.relauncher.Side;
import main.redstonearmory.blocks.BlockRecipeRegistry;
import main.redstonearmory.blocks.BlockRegistry;
import main.redstonearmory.gui.CreativeTabRedstoneArmory;
import main.redstonearmory.items.ItemRecipeRegistry;
import main.redstonearmory.items.ItemRegistry;
import main.redstonearmory.proxies.CommonProxy;
import main.redstonearmory.tile.TERegistry;
import main.redstonearmory.util.CapeHandler;
import main.redstonearmory.util.OreDictHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;

import java.util.logging.LogManager;
import java.util.logging.Logger;

@Mod(modid = ModInformation.ID, name = ModInformation.NAME, version = ModInformation.VERSION, dependencies = "required-after:Redstone Arsenal;required-after:ttCore@[MC1.7.10-0.0.3-27,)")
@NetworkMod(channels = ModInformation.CHANNEL, clientSideRequired = true, serverSideRequired = false)
public class RedstoneArmory {

    public static CreativeTabs tabRedstoneArmory = new CreativeTabRedstoneArmory(ModInformation.ID + ".creativetab.name");
    public static Logger logger = LogManager.getLogManager().getLogger(ModInformation.NAME);

    @Instance(ModInformation.ID)
    public static RedstoneArmory instance;

    @SidedProxy(clientSide = "main.redstonearmory.proxies.ClientProxy", serverSide = "main.redstonearmory.proxies.CommonProxy")
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ConfigHandler.registerConfig(event.getSuggestedConfigurationFile());

	    BlockRegistry.registerBlocks();
	    BlockRecipeRegistry.registerRecipes();
	    ItemRegistry.registerItems();
	    TERegistry.registerTileEntities();

	    OreDictHandler.registerFulloreDict();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        if (event.getSide() == Side.CLIENT) {
            MinecraftForge.EVENT_BUS.register(new CapeHandler());
        }
	    ItemRecipeRegistry.registerFullRecipes();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }
}
