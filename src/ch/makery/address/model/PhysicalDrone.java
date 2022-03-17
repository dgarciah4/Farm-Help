package ch.makery.address.model;
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//
import java.io.IOException;

public abstract class PhysicalDrone {
    protected DroneController controller;

    public PhysicalDrone() {
    }

    public abstract void setSpeed(int var1) throws IOException;

    public abstract double getSpeed() throws NumberFormatException, IOException;

    public abstract int getBattery() throws NumberFormatException, IOException;
}
