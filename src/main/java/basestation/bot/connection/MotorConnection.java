package basestation.bot.connection;

/**
 * Created by Administrator on 11/15/2016.
 */
public abstract class MotorConnection extends Connection {

    public abstract void setMotorPower(double fl, double fr, double bl, double br);

}
