package ch.makery.address.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


//import main.java.surelyhuman.jdrone.*;
import main.java.surelyhuman.jdrone.util.StreamPlayer;
import main.java.surelyhuman.jdrone.util.StreamRecorder;

import java.net.Socket;



public class PhysicalTelloDrone extends MultiRotorDrone {

    private final int maxGoto = 500, minGoto = -500, minDist = 20, maxSpeed = 100, minSpeed = 10, maxDegrees = 360, minDegrees = 1;
    private final int maxDist = maxGoto;

    public PhysicalTelloDrone() throws SocketException, UnknownHostException, FileNotFoundException {
       // this.controller = new DroneController (9000,11111,8889, "192.168.10.1" );

        this.controller = new DroneController(9000, 11111, 8889, "192.168.10.1"); //(int hostPort, int dronePort, String destinationAddress)
        StreamPlayer flightCamera = new StreamPlayer(11111); //int port
        StreamRecorder flightRecorder = new StreamRecorder(11111); // videoPort

    }


    public void beginProgram() {

    }

    public void endProgram() {
        this.controller.closeControlSocket();
        System.out.println("Sockets Closed / End Program");
    }


    public void activateSDK() throws IOException {
        this.controller.sendCommand("command");
    }


    @Override
    public void takeoff() throws IOException {
        this.controller.sendCommand("takeoff");
    }

    @Override
    public void land() throws IOException {
        this.controller.sendCommand("land");
    }


    public void streamOn() throws IOException {
        this.controller.sendCommand("streamon");

    }

    public void streamOff() throws IOException {
        this.controller.sendCommand("streamoff");

    }

    public void missionPadOn() throws IOException {
        this.controller.sendCommand("mon");

    }

    public void missionPadOff() throws IOException {
        this.controller.sendCommand("moff");

    }

    public void missionPadDirection(int param) throws IOException {
        this.controller.sendCommand("mdirection " + param);
    }

    public void flyUpward(int up) throws IOException {
        //TODO
        if (up <= 20) {
            this.controller.sendCommand("up 20");
        } else if (up > 500) {
            this.controller.sendCommand("up 500");
            this.flyUpward(Math.abs(500 - up));
        } else {
            this.controller.sendCommand("up " + up);
        }
    }

    public void flyDown(int down) throws IOException {
        //TODO
        int height = this.getHeight();
        if (height - down <= 0) {
            down = height - 10;
        }

        if (down <= 20) {
            this.controller.sendCommand("down 20");
        } else if (down > 500) {
            this.controller.sendCommand("down 500");
            this.flyDown(Math.abs(500 - down));
        } else {
            this.controller.sendCommand("down " + down);
        }
    }

    @Override
    public void flyForward(int front) throws IOException {
        if (front <= 20) {
            this.controller.sendCommand("forward 20");
        } else if (front > 500) {
            this.controller.sendCommand("forward 500");
            this.flyForward(Math.abs(500 - front));
        } else {
            this.controller.sendCommand("forward " + front);
        }
    }

    @Override
    public void flyBackward(int back) throws IOException {
        if (back <= 20) {
            this.controller.sendCommand("back 20");
        } else if (back > 500) {
            this.controller.sendCommand("back 500");
            this.flyBackward(Math.abs(500 - back));
        } else {
            this.controller.sendCommand("back " + back);
        }
    }

    @Override
    public void flyLeft(int left) throws IOException {
        if (left <= 20) {
            this.controller.sendCommand("left 20");
        } else if (left > 500) {
            this.controller.sendCommand("left 500");
            this.flyLeft(Math.abs(500 - left));
        } else {
            this.controller.sendCommand("left " + left);
        }
    }

    public void flyRight(int right) throws IOException {
        if (right <= 20) {
            this.controller.sendCommand("right 20");
        } else if (right > 500) {
            this.controller.sendCommand("right 500");
            this.flyRight(Math.abs(500 - right));
        } else {
            this.controller.sendCommand("right " + right);
        }

    }

    public void gotoXYZ(int x, int y, int z, int speed) throws IOException {
        //TODO

        int zNext = 0;
        if (z < -500) {
            zNext = z + 500;
            z = -500;
        } else if (z > 500) {
            zNext = z + -500;
            z = 500;
        }

        double slope = (double)y / (double)x;
        if (speed > 100) {
            speed = 100;
        } else if (speed < 10) {
            speed = 10;
        }

        if (x <= 500 && x >= -500 && y <= 500 && y >= -500) {
            this.controller.sendCommand(String.format("go %1$d %2$d %3$d %4$d", x, -y, z, speed));
            if (zNext > 0) {
                this.gotoXYZ(0, 0, zNext, speed);
            }
        } else {
            int partialX;
            if ((x <= 500 || y > 500 || y < -500) && (x <= 500 && x >= -500 || y <= 500 && y >= -500 || Math.abs(x) <= Math.abs(y) || x <= 500)) {
                if ((x >= -500 || y > 500 || y < -500) && (x <= 500 && x >= -500 || y <= 500 && y >= -500 || Math.abs(x) <= Math.abs(y) || x >= -500)) {
                    if (y > 500 && x <= 500 && x >= -500 || (x > 500 || x < -500) && (y > 500 || y < -500) && Math.abs(y) > Math.abs(x) && y > 500) {
                        partialX = (int)Math.round(500.0D / slope);
                        this.controller.sendCommand(String.format("go %1$d %2$d %3$d %4$d", partialX, -500, z, speed));
                        this.gotoXYZ(x - partialX, y + -500, zNext, speed);
                    } else if (y < -500 && x <= 500 && x >= -500 || (x > 500 || x < -500) && (y > 500 || y < -500) && Math.abs(y) > Math.abs(x) && y < 500) {
                        partialX = (int)Math.round(-500.0D / slope);
                        this.controller.sendCommand(String.format("go %1$d %2$d %3$d %4$d", partialX, 500, z, speed));
                        this.gotoXYZ(x - partialX, y + 500, zNext, speed);
                    } else if (x > 500 && y < -500) {
                        this.controller.sendCommand(String.format("go %1$d %2$d %3$d %4$d", 500, 500, z, speed));
                        this.gotoXYZ(x + -500, y + 500, zNext, speed);
                    } else if (x < -500 && y > 500) {
                        this.controller.sendCommand(String.format("go %1$d %2$d %3$d %4$d", -500, -500, z, speed));
                        this.gotoXYZ(x + 500, y + -500, zNext, speed);
                    } else if (x > 500 && x == y) {
                        this.controller.sendCommand(String.format("go %1$d %2$d %3$d %4$d", 500, -500, z, speed));
                        this.gotoXYZ(x + -500, y + -500, zNext, speed);
                    } else {
                        this.controller.sendCommand(String.format("go %1$d %2$d %3$d %4$d", -500, 500, z, speed));
                        this.gotoXYZ(x + 500, y + 500, zNext, speed);
                    }
                } else {
                    partialX = (int)Math.round(slope * -500.0D);
                    this.controller.sendCommand(String.format("go %1$d %2$d %3$d %4$d", -500, partialX, z, speed));
                    this.gotoXYZ(x + 500, y - partialX, zNext, speed);
                }
            } else {
                partialX = (int)Math.round(slope * 500.0D);
                this.controller.sendCommand(String.format("go %1$d %2$d %3$d %4$d", 500, -partialX, z, speed));
                this.gotoXYZ(x + -500, y - partialX, zNext, speed);
            }
        }

    }

    public void gotoMissionPadXYZ(int x, int y, int z, int speed, String ID) throws IOException{

    }

    public void flyCurve(int x1, int y1, int z1, int x2, int y2, int z2, int speed) {

    }

    public void flyCurveMissionPad(int x1, int y1, int z1, int x2, int y2, int z2, int speed, String ID) throws IOException {

    }

    @Override
    public void turnCW(int degrees) throws IOException {
        if (degrees <= 1) {
            this.controller.sendCommand("cw 1");
        } else if (degrees > 360) {
            this.controller.sendCommand("cw 360");
            this.turnCW(Math.abs(360 - degrees));
        } else {
            this.controller.sendCommand("cw " + degrees);
        }
    }

    @Override
    public void turnCCW(int degrees) throws IOException {
        if (degrees <= 1) {
            this.controller.sendCommand("ccw 1");
        } else if (degrees > 360) {
            this.controller.sendCommand("ccw 360");
            this.turnCCW(Math.abs(360 - degrees));
        } else {
            this.controller.sendCommand("ccw " + degrees);
        }
    }

    @Override
    public void flip(String direction) throws IOException {
        this.controller.sendCommand("flip " + direction);

    }

    public void jumpMissionPad(int x, int y, int z, int speed, int yaw, String ID1, String ID2) {

    }

    @Override
    public void hoverInPlace(int seconds) throws InterruptedException {
        //TODO
        if (seconds > 15) {
            this.getBattery();
            TimeUnit.MILLISECONDS.sleep(14970L);
            this.hoverInPlace(Math.abs(seconds - 15));
        } else if (seconds == 15) {
            this.getBattery();
            TimeUnit.MILLISECONDS.sleep(14970L);
        } else {
            this.getBattery();
            TimeUnit.SECONDS.sleep((long)seconds);
        }
    }

    public void stopInPlace() throws IOException {
        this.controller.sendCommand("stop");

    }

    @Override
    public void setSpeed(int speed) throws IOException {
        if (speed <= 10) {
            this.controller.sendCommand("speed 10");
        } else if (speed > 100) {
            this.controller.sendCommand("speed 100");
        } else {
            this.controller.sendCommand("speed " + speed);
        }

    }

    @Override
    public double getSpeed() {
        try {
            return Double.parseDouble(this.controller.sendCommand("speed?"));
        } catch (NumberFormatException | IOException var2) {
            System.out.println("Number Format Exception due to timeout! " + var2);
            return 0.0D;
        }
    }

    @Override
    public int getBattery() {
        try {
            return Integer.parseInt(this.controller.sendCommand("battery?"));
        } catch (NumberFormatException | IOException var2) {
            System.out.println("Number Format Exception due to timeout! " + var2);
            return 0;
        }
    }

    @Override
    public int getFlightTime() {
        try {
            String time = this.controller.sendCommand("time?");
            return Integer.parseInt(time.substring(0, time.length() - 1));
        } catch (NumberFormatException | IOException var2) {
            System.out.println("Number Format Exception due to timeout! " + var2);
            return 0;
        }
    }

    @Override
    public int getHeight() throws IOException {
        try {
            String height = this.controller.sendCommand("height?");
            return 10 * Integer.parseInt(height.substring(0, height.length() - 2));
        } catch (NumberFormatException var2) {
            System.out.println("Number Format Exception due to timeout! " + var2);
            return 0;
        }
    }


    public int getAltitude(int i) {
        try {
            String altitude = this.controller.sendCommand("height?");
            return 10 * Integer.parseInt(altitude.substring(0, altitude.length() - 2));
        } catch (NumberFormatException | IOException var2) {
            System.out.println("Number Format Exception due to timeout! " + var2);
            return 0;
        }
    }

    public int getTemp() {
        try {
            String temperature = this.controller.sendCommand("temp?");
            String[] arrayOfStr = temperature.split("~|C", 2);
            int temp1 = Integer.parseInt(arrayOfStr[0]);
            int temp2 = Integer.parseInt(arrayOfStr[1].substring(0, arrayOfStr[1].length() - 1));
            return (temp1 + temp2) / 2;
        } catch (NumberFormatException | IOException var5) {
            System.out.println("Number Format Exception due to timeout! " + var5);
            return 0;
        }    }

    @Override
    public int getAttitudePitch() {
        try {
            String attitude = this.controller.sendCommand("attitude?");
            String[] arrayOfStr = attitude.split(":|;", 7);
            return Integer.parseInt(arrayOfStr[1]);
        } catch (Exception var3) {
            System.out.println("Exception due to timeout! " + var3);
            return 0;
        }
    }

    @Override
    public int getAttitudeRoll() {
        try {
            String attitude = this.controller.sendCommand("attitude?");
            String[] arrayOfStr = attitude.split(":|;", 7);
            return Integer.parseInt(arrayOfStr[3]);
        } catch (Exception var3) {
            System.out.println("Exception due to timeout! " + var3);
            return 0;
        }
    }

    @Override
    public int getAttitudeYaw() {
        try {
            String attitude = this.controller.sendCommand("attitude?");
            String[] arrayOfStr = attitude.split(":|;", 7);
            return Integer.parseInt(arrayOfStr[5]);
        } catch (Exception var3) {
            System.out.println("Exception due to timeout! " + var3);
            return 0;
        }
    }

    public double getBarometer() {
        try {
            return Double.parseDouble(this.controller.sendCommand("baro?"));
        } catch (NumberFormatException | IOException var2) {
            System.out.println("Number Format Exception due to timeout! " + var2);
            return 0.0D;
        }
    }

    @Override
    public double getAccelerationX() {
        try {
            String acceleration = this.controller.sendCommand("acceleration?");
            String[] arrayOfStr = acceleration.split(":|;", 7);
            return Double.parseDouble(arrayOfStr[1]);
        } catch (Exception var3) {
            System.out.println("Exception due to timeout! " + var3);
            return 0.0D;
        }
    }

    @Override
    public double getAccelerationY() {
        try {
            String acceleration = this.controller.sendCommand("acceleration?");
            String[] arrayOfStr = acceleration.split(":|;", 7);
            return Double.parseDouble(arrayOfStr[3]);
        } catch (Exception var3) {
            System.out.println("Exception due to timeout! " + var3);
            return 0.0D;
        }
    }

    @Override
    public double getAccelerationZ() {
        try {
            String acceleration = this.controller.sendCommand("acceleration?");
            String[] arrayOfStr = acceleration.split(":|;", 7);
            return Double.parseDouble(arrayOfStr[5]);
        } catch (Exception var3) {
            System.out.println("Exception due to timeout! " + var3);
            return 0.0D;
        }
    }

    @Override
    public int getTOF() {
        try {
            String timeOfFlight = this.controller.sendCommand("tof?");
            return Integer.parseInt(timeOfFlight.substring(0, timeOfFlight.length() - 2)) / 10;
        } catch (NumberFormatException | IOException var2) {
            System.out.println("Number Format Exception due to timeout! " + var2);
            return 0;
        }
    }

    public String getWIFI() throws IOException {
        return this.controller.sendCommand("wifi?");

    }


    public String getVersionSDK() throws IOException {
        return this.controller.sendCommand("sdk?");
    }

    public String getSerialNumber() throws IOException {
        return this.controller.sendCommand("sn?");
    }

}
