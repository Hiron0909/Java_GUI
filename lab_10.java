// Write a client-server UDP socket program in java that the server listens for connection requests, and 
// whatever message within 1024 bytes the client sends, the server converts it to uppercase and sends it back after 6 ms.

UDPServer.java

import java.net.*;

public class UDPServer {
    public static void main(String[] args) throws Exception {

        DatagramSocket server = new DatagramSocket(5000);

        byte[] buffer = new byte[1024];

        DatagramPacket request = new DatagramPacket(buffer, buffer.length);

        System.out.println("Server is waiting...");

        server.receive(request);

        String message = new String(
            request.getData(), 0, request.getLength());

        System.out.println("Received: " + message);

        // Convert to uppercase
        String upper = message.toUpperCase();

        // Wait for 6 milliseconds
        Thread.sleep(6000);

        // Convert string to bytes
        byte[] data = upper.getBytes();

        // Send response to client
        DatagramPacket reply = new DatagramPacket(
            data,
            data.length,
            request.getAddress(),
            request.getPort()
        );

        server.send(reply);

        server.close();
    }
}




UDPClient.java


import java.net.*;
import java.util.Scanner;

public class UDPClient {
    public static void main(String[] args) throws Exception {

        DatagramSocket client = new DatagramSocket();
        Scanner sc = new Scanner(System.in);

        InetAddress address = InetAddress.getByName("localhost");

        System.out.print("Enter a message: ");
        String message = sc.nextLine();

        byte[] data = message.getBytes();

        DatagramPacket request = new DatagramPacket(
            data, data.length, address, 5000);

        client.send(request);

        byte[] buffer = new byte[1024];

        DatagramPacket reply = new DatagramPacket(
            buffer, buffer.length);

        client.receive(reply);

        String response = new String(
            reply.getData(), 0, reply.getLength());

        System.out.println("Message from server: " + response);

        client.close();
        sc.close();
    }
}
