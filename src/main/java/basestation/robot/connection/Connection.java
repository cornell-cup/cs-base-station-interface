package basestation.robot.connection;

import java.util.Map;

/**
 * Represents a connection between the base station and a bot
 */
public abstract class Connection {

    /**
     * Sends the keys and values associated with kvMap over the connection
     * @param kvMap
     * @return true if the KV were successfull sent
     */
    public abstract boolean sendKV(Map<String,String> kvMap);

    public abstract boolean connectionActive();

}
