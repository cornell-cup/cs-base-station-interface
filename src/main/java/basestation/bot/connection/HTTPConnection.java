package basestation.bot.connection;

import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Map;

/**
 * Implements Connection by serializing through HTTP. Assumes the given endpoint is a server.
 * FIXME: This is currently unused and untested.`
 */
public class HTTPConnection extends Connection {
    private String ip;
    private int port;

    private Socket clientSocket;
    private BufferedReader inFromBot;
    private DataOutputStream outToBot;

    /**
     * Initializes a keep-alive retrying TCP connection with IP at port
     */
    public HTTPConnection(String ip, int port) throws IOException {
        this.ip = ip;
        this.port = port;

        setupConnection();
    }

    private void setupConnection() throws IOException {
        clientSocket = new Socket(ip, port);
        inFromBot = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        outToBot = new DataOutputStream(clientSocket.getOutputStream());
    }

    public boolean sendKV(Map<String, String> kvMap) {
        JsonObject payload = new JsonObject();
        for (String key : kvMap.keySet()) {
            payload.addProperty(key, kvMap.get(key));
        }

        if (!connectionActive()) {
            // TODO: Retry mechanism
            return false;
        } else {
            try {
                outToBot.writeBytes(payload.getAsString());
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }

            return true;
        }
    }

    @Override
    public void destroy() {
        //Do nothing
    }

    @Override
    public boolean connectionActive() {
        return clientSocket != null && clientSocket.isConnected();
    }
}
