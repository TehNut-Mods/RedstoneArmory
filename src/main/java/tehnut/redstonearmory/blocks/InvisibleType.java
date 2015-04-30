package tehnut.redstonearmory.blocks;

import java.util.Locale;

public enum InvisibleType {

    REDSTONE(0),
    LUMIUM(15);

    public final int lightLevel;

    private InvisibleType(int lightLevel) {
        this.lightLevel = lightLevel;
    }

    @Override
    public String toString() {
        return name().toLowerCase(Locale.ENGLISH);
    }

    public int getLightLevel() {
        return lightLevel;
    }
}
