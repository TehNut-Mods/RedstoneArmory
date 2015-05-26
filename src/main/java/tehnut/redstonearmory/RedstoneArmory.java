package tehnut.redstonearmory;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLMissingMappingsEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.config.Configuration;
import tehnut.redstonearmory.blocks.BlockRecipeRegistry;
import tehnut.redstonearmory.blocks.BlockRegistry;
import tehnut.redstonearmory.compat.CompatibilityBaubles;
import tehnut.redstonearmory.client.gui.CreativeTabRArm;
import tehnut.redstonearmory.items.ItemRecipeRegistry;
import tehnut.redstonearmory.items.ItemRegistry;
import tehnut.redstonearmory.proxies.CommonProxy;
import tehnut.redstonearmory.util.OreDictHandler;
import tehnut.redstonearmory.util.annot.Registerer;
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

        Registerer.scan(ItemRegistry.class);
        Registerer.scan(BlockRegistry.class);

        if (Loader.isModLoaded("Baubles"))
            CompatibilityBaubles.load();

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

    @Mod.EventHandler
    public void onMissingMapping(FMLMissingMappingsEvent event) {
        for (FMLMissingMappingsEvent.MissingMapping mapping : event.get()) {
            if (mapping.type == GameRegistry.Type.ITEM && mapping.name.contains("ItemBaubleCapacitor") && Loader.isModLoaded("Baubles"))
                mapping.remap(CompatibilityBaubles.capacitorBauble);
        }
    }
}