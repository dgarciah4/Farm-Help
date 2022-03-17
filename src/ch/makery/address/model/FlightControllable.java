package ch.makery.address.model;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import java.io.IOException;

interface FlightControllable {
    void takeoff() throws IOException;

    void land() throws IOException;

    void flyUpward(int var1) throws IOException;

    void flyDown(int var1) throws IOException;

    void flyForward(int var1) throws IOException;

    void flyLeft(int var1) throws IOException;

    void flyRight(int var1) throws IOException;

    void turnCW(int var1) throws IOException;

    void turnCCW(int var1) throws IOException;

    int getFlightTime() throws IOException;

    int getHeight() throws IOException;

    int getAttitudePitch() throws IOException;

    int getAttitudeRoll() throws IOException;

    int getAttitudeYaw() throws IOException;

    double getAccelerationX() throws IOException;

    double getAccelerationY() throws IOException;

    double getAccelerationZ() throws IOException;

    int getTOF() throws IOException;
}

