package basestation.bot.robot.minibot;

/**
 * Created by Administrator on 1/28/2017.
 */

import basestation.BaseStation;
import basestation.bot.commands.CommandCenter;
import basestation.bot.connection.TCPConnection;

/**
 * Class who's methods are all the commands that can be issued to a bot
 * <p>
 * Each bot must implement this class with their own commands.
 */
public class MinibotCommandCenter implements CommandCenter {
    private final TCPConnection connection;


    public MinibotCommandCenter(BaseStation parent, TCPConnection connection, MiniBot myBot) {
        this.connection = connection;
    }

    public boolean sendKV(String type, String payload) {
        return connection.send(type,payload);
    }

}
