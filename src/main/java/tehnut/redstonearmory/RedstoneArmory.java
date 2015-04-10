package tehnut.redstonearmory;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import tehnut.redstonearmory.blocks.BlockRecipeRegistry;
import tehnut.redstonearmory.blocks.BlockRegistry;
import tehnut.redstonearmory.gui.CreativeTabRArm;
import tehnut.redstonearmory.items.ItemRecipeRegistry;
import tehnut.redstonearmory.items.ItemRegistry;
import tehnut.redstonearmory.proxies.CommonProxy;
import tehnut.redstonearmory.util.OreDictHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.config.Configuration;
import tterrag.core.common.Handlers;

@Mod(modid = ModInformation.ID, name = ModInformation.NAME, version = ModInformation.VERSION, dependencies = ModInformation.REQUIRED, guiFactory = ModInformation.GUIFACTORY)
public class RedstoneArmory {

    @SidedProxy(clientSide = ModInformation.CLIENTPROXY, serverSide = ModInformation.COMMONPROXY)
    public static CommonProxy proxy;

    public static CreativeTabs tabRArm = new CreativeTabRArm(ModInformation.ID + ".creativeTab");

    @Mod.Instance
    public static RedstoneArmory instance;
    public static Configuration config;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {

        ConfigHandler.INSTANCE.initialize(event.getSuggestedConfigurationFile());

        ItemRegistry.registerAllItems();
        BlockRegistry.registerAllBlocks();

        OreDictHandler.registerOreDict();
        Handlers.addPackage("tehnut.redstonearmory");

        proxy.load();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        ItemRecipeRegistry.registerItemRecipes();
        BlockRecipeRegistry.registerBlockRecipes();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

        ItemRecipeRegistry.registerLateItemRecipes();
    }
}