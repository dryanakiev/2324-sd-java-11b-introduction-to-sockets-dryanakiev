import java.util.Scanner;

public class RunClient {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Client client = new Client();

        client.setName("Client 1");
        client.setIp("127.0.0.1");
        client.setPort(6666);

        ChatRoomClientSocket clientSocket = new ChatRoomClientSocket(client);

        clientSocket.startConnection();
        clientSocket.receiveMessage();

        while(true) {
            clientSocket.sendMessage(client.getName() + ") said: " + scanner.nextLine());
        }
    }
}
