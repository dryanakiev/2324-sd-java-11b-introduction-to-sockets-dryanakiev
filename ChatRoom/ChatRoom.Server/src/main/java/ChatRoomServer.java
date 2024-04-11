import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatRoomServer {
    private String name;
    private String ip;
    private int port;
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter outputStream;
    private BufferedReader inputStream;

    public ChatRoomServer() {
    }

    public void startServer() {
        try {
            serverSocket = new ServerSocket(getPort());

            clientSocket = serverSocket.accept();

            inputStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            outputStream = new PrintWriter(clientSocket.getOutputStream(),true);


        } catch (IOException e) {
            // TODO: Add exception handle for server connectivity issues
        }
    }

    public void stopServer() {
        try {
            inputStream.close();
            outputStream.close();
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            // TODO: Add exception handle for close server socket
        }
    }

    public void HandleUserInput() {
        String clientMessage;
        try {
            clientMessage = inputStream.readLine();

            System.out.println("Client :" + clientMessage);

            broadcastMessage("Server received: \"" + clientMessage + "\"");

        } catch (IOException e) {
            // TODO: Add exception handle for client side message
        }

    }

    public void broadcastMessage(String message) {
        outputStream.println(message);
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

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
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
        ChatRoomServer server = new ChatRoomServer();

        server.setPort(6666);
        server.startServer();

        while (true) {
            server.HandleUserInput();
        }
    }
}
