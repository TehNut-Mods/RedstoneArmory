package tehnut.redstonearmory.enums;

import java.util.Locale;

public enum EnergyType {

    CREATIVE(100000, 0, 100000),
    TUBEROUS(160, 0, 32000),
    LEADSTONE(80, 200, 80000),
    HARDENED(400, 800, 400000),
    REINFORCED(4000, 8000, 4000000),
    RESONANT(16000, 32000, 20000000);

    public final int capacity;
    public final int send;
    public final int recieve;

    EnergyType(int send, int recieve, int capacity) {
        this.send = send;
        this.recieve = recieve;
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return name().toLowerCase(Locale.ENGLISH);
    }
}
