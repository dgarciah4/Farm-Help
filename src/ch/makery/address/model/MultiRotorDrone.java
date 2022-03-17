package ch.makery.address.model;
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)


import java.io.IOException;

public abstract class MultiRotorDrone extends PhysicalDrone implements FlightControllable {

    public abstract void flyBackward(int var1) throws IOException;

    public abstract void flip(String var1) throws IOException;

    public abstract void hoverInPlace(int var1) throws InterruptedException, IOException;
}

