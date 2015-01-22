package main.redstonearmory.compat;

import cpw.mods.fml.common.registry.GameRegistry;
import main.redstonearmory.ConfigHandler;
import main.redstonearmory.ModInformation;
import main.redstonearmory.RedstoneArmory;
import main.redstonearmory.items.armor.ItemEnderiumJetPlate;
import main.redstonearmory.util.TextHelper;
import net.minecraft.item.Item;
import tonius.simplyjetpacks.item.ItemIndex;
import tonius.simplyjetpacks.setup.ModItems;
import tterrag.core.common.compat.ICompatability;

public class SimplyJetpacksCompat implements ICompatability {

	public static Item enderiumJetPlate;

	private static void constructItems() {

	}

	private static void registerItems() {
		enderiumJetPlate = new ItemEnderiumJetPlate(ItemIndex.COMMON, ModItems.ModType.THERMAL_EXPANSION);
		// Need to specify mod ID here or else it will register as a ttCore item.
		GameRegistry.registerItem(enderiumJetPlate, "ItemEnderiumJetplate", ModInformation.ID);
	}

	private static void registerRecipes() {

	}

	public static void load() {
		if (ConfigHandler.enableSimplyJetpacksCompat && ConfigHandler.enableTestingEnviro) {
			RedstoneArmory.logger.info(TextHelper.localize("info.RArm.console.compat.simplyjetpacks"));
			constructItems();
			registerItems();
			registerRecipes();
		}
	}
}
