package basestation.bot.robot.minibot;

import basestation.bot.commands.CommandCenter;
import basestation.bot.connection.TCPConnection;

/**
 * Class who's methods are all the commands that can be issued to a bot
 * <p>
 * Each bot must implement this class with their own commands.
 */
public class MinibotCommandCenter implements CommandCenter {
    private final TCPConnection connection;


    public MinibotCommandCenter(TCPConnection connection, MiniBot myBot) {
        this.connection = connection;
    }

    @Override
    public boolean sendKV(String type, String payload) {
        return connection.sendKV(type, payload);
    }

}
