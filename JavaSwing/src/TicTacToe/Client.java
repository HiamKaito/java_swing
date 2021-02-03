package TicTacToe;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

// Client for Server4
public class Client implements Runnable {

    private String serverName = "localhost";
    private int serverPort = 8081;
    private Socket socket = null;
    private Thread thread = null;
    private DataInputStream dis = null;
    private DataOutputStream dos = null;
    private ChatClientThread client = null;

//    is connect tho server???
    public boolean Connect() {
        try {
            socket = new Socket(serverName, serverPort);
            System.out.println("Client started on port " + socket.getLocalPort() + "...");
            System.out.println("Connected to server " + socket.getRemoteSocketAddress());

            dis = new DataInputStream(System.in);
            dos = new DataOutputStream(socket.getOutputStream());
            client = new ChatClientThread(this, socket);
            thread = new Thread(this);
            thread.start();
            return true;
        } catch (IOException e) {
            System.out.println("Error : " + e.getMessage());
            return false;
        }
    }

    @Override
    public void run() {
        while (thread != null) {
            try {
                System.out.print("Message to server : ");
                dos.writeUTF(dis.readLine());
                dos.flush();

                try {
                    thread.sleep(500);
                } catch (InterruptedException e) {
                    System.out.println("Error : " + e.getMessage());
                }
            } catch (IOException e) {
                System.out.println("Sending error : " + e.getMessage());
                stop();
            }
        }
    }

//    Other client send messenger to here
    public void handleMessage(String message) {
        if (message.equals("exit")) {
            stop();
        } else {
            System.out.println(message);
            
//            that is status messenger
            if (message.equalsIgnoreCase("RESET") || message.equalsIgnoreCase("END")
                    || message.equalsIgnoreCase("Host win") || message.equalsIgnoreCase("Client win")) {
                    Memory.statusMessenger = message;
                    System.out.println("Tin nhan trang thai duoc dua vao hang doi: " + Memory.statusMessenger);
            } else {
                if ((!message.equalsIgnoreCase(" "))) {
//            say that we have a messenger
                    Memory.playerMessenger = true;
//            /127.0.0.1:50194 says : 0 1 2
                    Memory.messenger = message;
                    System.out.println("Tin nhan duoc dua vao hang doi: " + Memory.messenger);
                }
            }
        }
    }

//    Stop the connection
    public void stop() {
        try {
            thread = null;
            dis.close();
            dos.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("Error closing : " + e.getMessage());
        }
        client.close();
    }

    public String getServer() {
        return String.valueOf(socket.getRemoteSocketAddress());
    }

//    Try to find other servver
    public boolean findServer() {
        try {
            socket = new Socket(serverName, serverPort);
            System.out.println("Client started on port " + socket.getLocalPort() + "...");
            System.out.println("Connected to server " + socket.getRemoteSocketAddress());
            System.out.println("Disconnect to server");
            socket.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public void sendMessenger() {
        try {
            dos.writeUTF(Memory.messenger);
            dos.flush();

            try {
                thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("Error : " + e.getMessage());
            }
        } catch (IOException e) {
            System.out.println("Sending error : " + e.getMessage());
            stop();
        }
    }

    public void sendStatusMessenger() {
        try {
            dos.writeUTF(Memory.statusMessenger);
            dos.flush();

            try {
                thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("Error : " + e.getMessage());
            }
        } catch (IOException e) {
            System.out.println("Sending error : " + e.getMessage());
            stop();
        }
    }
}
