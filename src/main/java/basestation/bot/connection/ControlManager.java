package basestation.bot.connection;

import CommModule.BaseInterfacePrx;

/**
 * Public to make matlab work
 */
public class ControlManager extends Thread {

    private BaseInterfacePrx iface;

    private int COMMANDS_PER_SECOND = 2; // Since the connection is already reliable, we do not need a high frequency
    private int fl;
    private int fr;
    private int bl;
    private int br;

    public ControlManager(BaseInterfacePrx iface) {
        this.iface = iface;
        fl = fr = bl = br = 0;
    }

    public void run() {
        while (true) {
            sendMotors();
            try {
                Thread.sleep(1000 / COMMANDS_PER_SECOND);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    protected void setMotors(int fl, int fr, int bl, int br) {
        this.fl = fl;
        this.fr = fr;
        this.bl = bl;
        this.br = br;
        sendMotors();
    }

    private synchronized void sendMotors() {
        iface.begin_setMotorSpeeds(fl, fr, bl, br);
    }

}
