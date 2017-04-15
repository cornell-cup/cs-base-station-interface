package basestation.bot.commands;

/**
 * Class who's methods are all the commands that can be issued to a bot
 * <p>
 * Each bot must implement this class with their own commands.
 * <p>
 * If there are any important commands we believe all robots should have then place them here
 */
public interface CommandCenter {

	/**
     * Starts logging of data
     */
    void startLogging();

    /**
     * Returns whether or not data is currently being logged.
     * @return True if the command center is currently logging data
     */
    boolean isLogging();

    /**
     * Stores key value pair. 
     * @param name A key to identify the type of command
     * @param value The value of the command
     */
    void setData(String name, String value);

    /**
     * Stores the value for each wheel simultaneously.
     * @param fl, fr, bl, br The key assigned to each wheel
     * @param v1, v2, v3, v4 The value assigned to each wheel
     */
    void setWheelsData(String fl, String v1, String fr, String v2, String bl, String v3, String br, String v4);

    /**
     * Retrieves key value pair.
     * @param name A key to identify the type of command
     * @return Returns a single key value pair, returns "" for value if command does not exist in record
     */
    JsonObject getData(String name);

	/**
     * Retrieves all key value pair.
     * @return Returns all key value pairs
     */
    JsonObject getAllData();

    /**
     * Sends an arbitrary key and value over the associated bot's connection. Should only be used for quick prototyping,
     * with the goal of creating a method wrapper around sending the KV.
     * @param key A key to identify the type of command
     * @param value The value of the command
     * @return True if the command seems to have sent correctly
     */
    boolean sendKV(String key, String value);
}