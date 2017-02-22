package basestation.bot.connection;

import java.io.*;
import java.net.*;

/**
 * Created by Administrator on 2/14/2017.
 */
public class TCPConnection extends Connection {

    private String ip;
    private int port;
    private Socket clientSocket;
    private DataOutputStream outToServer;
    private BufferedReader inFromServer;

    public TCPConnection(String ip, int port) {
        this.ip=ip;
        this.port=port;
        try {
            this.clientSocket = new Socket(ip, port);
            this.outToServer = new DataOutputStream(clientSocket.getOutputStream());
            this.inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            System.err.println("Unable to establish connection! " + e);
        }
    }

    @Override
    public boolean connectionActive() {
        return clientSocket.isConnected();
    }

    public void destroy() {
        try {
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean sendKV(String messageType, String message)  {
        String payload;
        payload = "<<<<" + messageType + "," + message + ">>>>";
        try {
            outToServer.writeBytes(payload);
            outToServer.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}