import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;

public class ChatRoomServerSocket {
    private Server _server;
    private ServerSocket _chatRoomServerSocket;
    private Socket _chatRoomClientSocket;
    private PrintWriter _outputStream;
    private BufferedReader _inputStream;

    public ChatRoomServerSocket() {
    }

    public ChatRoomServerSocket(Server server) {
        _server = server;
    }

    public void startServer() {
        try {
            _chatRoomServerSocket = new ServerSocket(_server.getPort());

            _chatRoomClientSocket = _chatRoomServerSocket.accept();

            _inputStream = new BufferedReader(new InputStreamReader(_chatRoomClientSocket.getInputStream()));

            _outputStream = new PrintWriter(_chatRoomClientSocket.getOutputStream(),true);


        } catch (IOException e) {
            // TODO: Add exception handle for server connectivity issues
        }
    }

    public void stopServer() {
        try {
            _inputStream.close();
            _outputStream.close();
            _chatRoomClientSocket.close();
            _chatRoomServerSocket.close();
        } catch (IOException e) {
            // TODO: Add exception handle for close server socket
        }
    }

    public String receiveMessage() {
        String clientMessage = null;
        try {
            clientMessage = _inputStream.readLine();

            System.out.println("Client (" + clientMessage);

        } catch (IOException e) {
            // TODO: Add exception handle for client side message
        }
        return clientMessage;
    }

    /*public void handleClientInput(String clientMessage) {
        int parsedMessage = Integer.parseInt(clientMessage);
        // TODO: Add parse exception
        // TODO: Fix new line
        switch (parsedMessage) {
            case 1: {
                broadcastMessage("The time is " + LocalDateTime.now());

                break;
            }

            case 2: {
                broadcastMessage("id,first_name,last_name,email,gender,ip_address" +
                        "1,Bertie,Boxell,bboxell0@people.com.cn,Male,53.161.222.126" +
                        "2,Rice,Ashmore,rashmore1@about.com,Male,160.254.167.201" +
                        "3,Karrie,Stirzaker,kstirzaker2@noaa.gov,Female,36.190.240.204");

                break;
            }

            case 3: {
                broadcastMessage("Server is stopping");

                stopServer();

                break;
            }
        }
    }*/
    public void handleClientInput(String clientMessage) {
        broadcastMessage(clientMessage);
    }


    public void onClientConnect() {
        broadcastMessage("Hello, welcome to " + _server.getName() + " Server!");
    }

    public boolean clientIsConnected() {
        if (_chatRoomClientSocket.isConnected())
        {
            return true;
        }
        clientIsConnected();
        return false;
    }

    public void broadcastMessage(String message) {
        _outputStream.println(message);
    }
}
