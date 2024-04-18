import java.io.*;
import java.net.Socket;

public class ChatRoomClientSocket {

    private Client _client;
    private Socket _clientSocket;
    private PrintWriter _outputStream;
    private BufferedReader _inputStream;

    public ChatRoomClientSocket() {
    }

    public ChatRoomClientSocket(Client client) {
        _client = client;
    }

    public void startConnection() {
        try {
            _clientSocket = new Socket(_client.getIp(),_client.getPort());

            _inputStream = new BufferedReader(new InputStreamReader(_clientSocket.getInputStream()));

            _outputStream = new PrintWriter(_clientSocket.getOutputStream(),true);
        } catch (IOException e) {
            // TODO: Add exception handle for network connectivity error
        }
    }

    public void stopConnection() {
        try {
            _clientSocket.close();
            _inputStream.close();
            _outputStream.close();
        } catch (IOException e) {
            // TODO: Add exception handle for closing network socket
        }

    }

    public void sendMessage(String message) {
        String serverMessage;

        _outputStream.println(message);

        try {
            serverMessage = _inputStream.readLine();

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
            System.out.println(_inputStream.readLine());
        } catch (IOException e) {
            // TODO: Add exception handle for server side broadcast
        }
    }
}
