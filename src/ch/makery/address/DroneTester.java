package ch.makery.address;
import ch.makery.address.model.PhysicalTelloDrone;

import java.io.IOException;

public class DroneTester {

    public static void droneTestGetters() throws IOException {
        PhysicalTelloDrone telloDrone = new PhysicalTelloDrone();

        telloDrone.activateSDK();
        telloDrone.beginProgram();
        telloDrone.getBattery();
        // System.out.println(Integer(telloDrone.getBattery()));
        /*System.out.println(telloDrone.getAltitude(100));
        System.out.println(telloDrone.getSpeed());
        System.out.println(telloDrone.getTemp());
        System.out.println(telloDrone.getBarometer());
        System.out.println(telloDrone.getAttitudePitch());
        System.out.println(telloDrone.getAttitudeRoll());
        System.out.println(telloDrone.getAttitudeYaw());
        System.out.println(telloDrone.getAccelerationX());
        System.out.println(telloDrone.getAccelerationY());
        System.out.println(telloDrone.getAccelerationZ());
        System.out.println(telloDrone.getTOF());
        System.out.println(telloDrone.getFlightTime());
        System.out.println(telloDrone.getWIFI());
        System.out.println(telloDrone.getVersionSDK());
        System.out.println(telloDrone.getSerialNumber());*/
        telloDrone.endProgram();
    }

    public static void flightTest() throws IOException, InterruptedException {
        PhysicalTelloDrone telloDrone = new PhysicalTelloDrone();
        telloDrone.activateSDK();
        telloDrone.beginProgram();
        telloDrone.streamOn();
        telloDrone.takeoff();
        telloDrone.gotoXYZ(30, 30, 52, 100);
        telloDrone.flyDown(5);
        telloDrone.flyForward(5);
        telloDrone.turnCCW(360);
        telloDrone.flip("b"); //back
        telloDrone.hoverInPlace(5);
        telloDrone.flip("f"); //forward
        telloDrone.flyForward(5);
        telloDrone.land();
        telloDrone.streamOff();
        telloDrone.endProgram();

    }

    public static void main(String[] args) throws IOException, InterruptedException {
        //droneTestGetters();
        //flightTest();
    }

}
