package main.redstonearmory.util;

import cpw.mods.fml.common.Loader;

public class EnviroChecks {

    public static boolean isTELoaded() {
        return Loader.isModLoaded("ThermalExpansion");
    }
}
