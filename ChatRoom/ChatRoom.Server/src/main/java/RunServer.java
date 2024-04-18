public class RunServer {
    public static void main(String[] args) {
        Server server = new Server();

        server.setName("EXAMPLE");
        server.setPort(6666);

        ChatRoomServerSocket serverSocket = new ChatRoomServerSocket(server);

        serverSocket.startServer();

        if (serverSocket.clientIsConnected()) {
            serverSocket.onClientConnect();
        }

        while (true) {
            serverSocket.handleClientInput(serverSocket.receiveMessage());
        }
    }
}
