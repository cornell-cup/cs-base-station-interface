package basestation.bot.robot.minibot;

import basestation.bot.commands.ExtendedFourWheelMovement;
import basestation.bot.connection.Connection;
import basestation.bot.connection.MotorConnection;

/**
 * Class who's methods are all the commands that can be issued to a bot
 * <p>
 * Each bot must implement this class with their own commands.
 */
public class MinibotCommandCenter extends ExtendedFourWheelMovement {
    private final Connection connection;

    public MinibotCommandCenter(Connection connection, MiniBot myBot) {
        super(connection);
        this.connection = connection;
    }

    @Override
    public void setWheelPower(double fl, double fr, double bl, double br) {
        if (connection instanceof MotorConnection) {
            ((MotorConnection) connection).setMotorPower(fl, fr, bl, br);
        }
    }

}
