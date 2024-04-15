import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ChatRoomClient {
    private String name;
    private String ip;
    private int port;
    private Socket clientSocket;
    private PrintWriter outputStream;
    private BufferedReader inputStream;

    public ChatRoomClient() {
    }

    public void startConnection() {
        try {
            clientSocket = new Socket(getIp(),getPort());

            inputStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            outputStream = new PrintWriter(clientSocket.getOutputStream(),true);
        } catch (IOException e) {
            // TODO: Add exception handle for network connectivity error
        }
    }

    public void stopConnection() {
        try {
            clientSocket.close();
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            // TODO: Add exception handle for closing network socket
        }

    }

    public void sendMessage(String message) {
        String serverMessage;

        outputStream.println(message);

        try {
            serverMessage = inputStream.readLine();

            receiveMessage(serverMessage);

        } catch (IOException e) {
            // TODO: Add exception handle for server side broadcast
        }
    }

    public void receiveMessage(String message) {
        System.out.println(message);
    }

    public void receiveMessage() {
        try {
            System.out.println(inputStream.readLine());
        } catch (IOException e) {
            // TODO: Add exception handle for server side broadcast
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public Socket getClientSocket() {
        return clientSocket;
    }

    public void setClientSocket(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public PrintWriter getOutputStream() {
        return outputStream;
    }

    public void setOutputStream(PrintWriter outputStream) {
        this.outputStream = outputStream;
    }

    public BufferedReader getInputStream() {
        return inputStream;
    }

    public void setInputStream(BufferedReader inputStream) {
        this.inputStream = inputStream;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ChatRoomClient client = new ChatRoomClient();

        client.setName("Client 1");
        client.setIp("127.0.0.1");
        client.setPort(6666);

        client.startConnection();

        client.receiveMessage();

        while(true) {
            client.sendMessage(scanner.nextLine());
        }
    }
}
