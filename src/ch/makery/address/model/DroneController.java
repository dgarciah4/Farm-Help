package ch.makery.address.model;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class DroneController {
    private DatagramSocket hostSocket,videoSocket;
    private int dronePort;
    //private final int receiveBufferSize = 8192;
    private InetAddress droneAddress;


    public DroneController(int hostPort, int videoPort, int dronePort, String destinationAddress) throws SocketException, UnknownHostException {
        this.hostSocket = new DatagramSocket(hostPort);
        videoSocket = new DatagramSocket(videoPort);
        this.dronePort = dronePort;
        this.droneAddress = InetAddress.getByName(destinationAddress);
    }


    public String sendCommand(String msg) throws IOException {
        System.out.println("Sending command: " + msg);
        byte[] data = msg.getBytes();
        String output = "";
        DatagramPacket call = new DatagramPacket(data, data.length, droneAddress, dronePort);
        DatagramPacket response = new DatagramPacket(new byte[8192], 8192);
        this.hostSocket.send(call);
        this.hostSocket.setSoTimeout(15000);

        try {
            this.hostSocket.receive(response);
            output = (new String(response.getData(), "UTF-8")).trim();
            System.out.println("Incoming response: " + output);
            return output;
        } catch (SocketTimeoutException var7) {
            System.out.println("Timeout reached!!! " + var7);
            return "Timeout!!!";
        }
    }

    public void closeControlSocket() {
        this.hostSocket.close();
        System.out.println("All sockets closed... ");
    }

    public DatagramSocket getHostSocket() {
        return this.hostSocket;
    }

    public int getDronePort() {
        return this.dronePort;
    }

    public int getReceiveBufferSize() {
        return 8192;
    }

    public InetAddress getDroneAddress() {
        return this.droneAddress;
    }

    public static void main(String[] args) throws IOException {
        main.java.surelyhuman.jdrone.control.DroneController tester = new main.java.surelyhuman.jdrone.control.DroneController(9000, 8889, "192.168.10.1");
        System.out.println("Drone Controller Demo\n");
        System.out.println("Try any string to test\n");
        System.out.println("end -- quit demo\n");
        Scanner scan = new Scanner(System.in);

        for(String command = scan.nextLine(); !command.equals("end") && command != null && !command.trim().isEmpty(); command = scan.nextLine()) {
            tester.sendCommand(command);
        }

        scan.close();
        tester.closeControlSocket();
        System.out.println("Exit Program...");
    }
}
