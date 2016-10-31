package basestation.bot.connection;

import CommModule.BaseInterfacePrx;
import CommModule.BaseInterfacePrxHelper;

/**
 * Connection to support our Ice protocol
 * Sends controls over a rate-limited thread
 */
public class IceConnection extends Connection {

    private class ControlManager extends Thread {

        private BaseInterfacePrx iface;

        private int COMMANDS_PER_SECOND = 2; // Since the connection is already reliable, we do not need a high frequency
        private int fl;
        private int fr;
        private int bl;
        private int br;

        private ControlManager(BaseInterfacePrx iface) {
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

        private void setMotors(int fl, int fr, int bl, int br) {
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


    private final static double THROTTLE = 150; // Max 255 TODO set up a config file for this

    private Ice.Communicator ic;
    private ControlManager cmonitor;
    private String identity;

    public IceConnection(String ip, int port) {
        try {
            if (port == -1) port = 10000;
            ic = Ice.Util.initialize();
            identity = "control -t -e 1.0:tcp -h " + ip + " -p " + port;
            Ice.ObjectPrx base = ic.stringToProxy(identity);
            BaseInterfacePrx iface = BaseInterfacePrxHelper.checkedCast(base);
            if (iface == null)
                throw new Error("Invalid proxy");
            else {
                cmonitor = new ControlManager(iface);
                cmonitor.start();
            }

        } catch (Ice.LocalException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void setMotorPower(double fl, double fr, double bl, double br) {
        // Adjustment constant for speed
        double adjustment = THROTTLE / 100.0;
        setMotorSpeed((int) (fl * adjustment), (int) (fr * adjustment), (int) (bl * adjustment), (int) (br * adjustment));
    }

    private void setMotorSpeed(int fl, int fr, int bl, int br) {
        cmonitor.setMotors(fl, fr, bl, br);
    }

    @Override
    public boolean connectionActive() {
        return ic != null;
    }

    @Override
    public void destroy() {
        if (ic != null) {
            try {
                ic.destroy();
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public String toString() {
        return this.identity;
    }
}
