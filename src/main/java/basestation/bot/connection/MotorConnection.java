package basestation.bot.connection;

/**
 * An extension to a normal connection which allows custom power setting to each of the four wheels.
 * Power values range from 0-255.
 */
public abstract class MotorConnection extends Connection {

    public abstract void setMotorPower(double fl, double fr, double bl, double br);

}
