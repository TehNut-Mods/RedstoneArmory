package main.redstonearmory.util;

import cpw.mods.fml.common.Loader;

public class EnviroChecks {

    /**
     *
     * @return - If ThermalExpansion is loaded.
     */
    public static boolean isTELoaded() {
        return Loader.isModLoaded("ThermalExpansion");
    }
}
