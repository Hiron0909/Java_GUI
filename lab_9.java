//Server.java


import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) throws IOException {

        ServerSocket server = new ServerSocket(5000);
        System.out.println("Server is waiting for client...");

        Socket socket = server.accept();
        System.out.println("Client connected.");

        BufferedReader input = new BufferedReader(
            new InputStreamReader(socket.getInputStream()));

        PrintWriter output = new PrintWriter(
            socket.getOutputStream(), true);

        String message = input.readLine();

        System.out.println("Received message: " + message);

        String upper = message.toUpperCase();

        output.println(upper);

        System.out.println("Message sent: " + upper);

        socket.close();
        server.close();
    }
}



// Client.java


import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("localhost", 5000);

        Scanner sc = new Scanner(System.in);

        PrintWriter output = new PrintWriter(
            socket.getOutputStream(), true);

        BufferedReader input = new BufferedReader(
            new InputStreamReader(socket.getInputStream()));

        System.out.print("Enter a message: ");
        String message = sc.nextLine();

        output.println(message);

        String response = input.readLine();

        System.out.println("Message from server: " + response);

        socket.close();
        sc.close();
    }
}
