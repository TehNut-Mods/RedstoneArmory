package tehnut.redstonearmory.enums;

import java.util.Locale;

public enum EnergyType {

    CREATIVE(20000, 0, 100000),
    TUBEROUS(80, 0, 16000),
    LEADSTONE(80, 80, 80000),
    HARDENED(400, 400, 400000),
    REINFORCED(2000, 2000, 2000000),
    RESONANT(10000, 10000, 10000000);

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
