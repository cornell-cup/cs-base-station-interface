package basestation.robot.modbot;

import basestation.robot.commands.ExtendedFourWheelMovement;
import basestation.robot.connection.Connection;
import basestation.robot.connection.IceConnection;

/**
 * Class who's methods are all the commands that can be issued to a robot
 * <p>
 * Each robot must implement this class with their own commands.
 */
public class ModbotCommandCenter extends ExtendedFourWheelMovement {
    //Modbot command center currently implemented with ice connection
    private Connection connection;

    public ModbotCommandCenter(Connection connection) {
        this.connection = connection;
    }

    /**
     * robot translates left
     *
     * @param power the amount of power that should be supplied to the wheels. It should be between 0 and 100
     */
    public void left(double power) {
        assert power >= 0 && power <= 100;
        setWheelPower(-power, power, power, -power);
    }

    /**
     * robot translates right
     *
     * @param power the amount of power that should be supplied to the wheels. It should be between 0 and 100
     */
    public void right(double power) {
        assert power >= 0 && power <= 100;
        setWheelPower(power, -power, -power, power);
    }

    /**
     * robot translates forward
     *
     * @param power the amount of power that should be supplied to the wheels. It should be between 0 and 100
     */
    public void forward(double power) {
        assert power >= 0 && power <= 100;
        setWheelPower(power, power, power, power);
    }

    /**
     * robot translates backward
     *
     * @param power the amount of power that should be supplied to the wheels. It should be between 0 and 100
     */
    public void backward(double power) {
        assert power >= 0 && power <= 100;
        setWheelPower(-power, -power, -power, -power);
    }

    /**
     * robot rotates clockwise
     *
     * @param power the amount of power that should be supplied to the wheels. It should be between 0 and 100
     */
    public void clockwise(double power) {
        assert power >= 0 && power <= 100;
        setWheelPower(power, -power, power, -power);
    }

    /**
     * robot rotates counterclockwise
     *
     * @param power the amount of power that should be supplied to the wheels. It should be between 0 and 100
     */
    public void counterClockwise(double power) {
        assert power >= 0 && power <= 100;
        setWheelPower(-power, power, -power, power);
    }

    @Override
    public void setWheelPower(double fl, double fr, double bl, double br) {
        if (connection instanceof IceConnection) {
            ((IceConnection) connection).setMotorPower(fl, fr, bl, br);
        }
    }
}