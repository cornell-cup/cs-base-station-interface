package basestation.robot.connection;

import CommModule.BaseInterfacePrx;
import CommModule.BaseInterfacePrxHelper;

import java.util.Map;
import java.util.Set;

/**
 * Connection to support our Ice protocol
 * Sends controls over a rate-limited thread
 */
public class IceConnection extends Connection {

    private class ControlManager extends Thread {

        BaseInterfacePrx iface;

        private int COMMANDS_PER_SECOND = 10;
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
                iface.begin_setMotorSpeeds(fl, fr, bl, br);
                try {
                    Thread.sleep(1000 / COMMANDS_PER_SECOND);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void setMotors(int fl, int fr, int bl, int br) {
            this.fl = fl;
            this.fr = fr;
            this.bl = bl;
            this.br = br;
        }
    }

    final static double THROTTLE = 150; // Max 255 TODO set up a config file for this

    Ice.Communicator ic;
    BaseInterfacePrx iface;
    ControlManager cmonitor;

    public IceConnection(String ip, int port) {
        try {
            if (port == -1) port = 10000;
            ic = Ice.Util.initialize();
            Ice.ObjectPrx base = ic.stringToProxy("control -t -e 1.0:tcp -h " + ip + " -p " + port);
            iface = BaseInterfacePrxHelper.checkedCast(base);
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

    @Override
    public boolean sendKV(Map<String, String> kvMap) {
        // Movement support
        Set<String> keys = kvMap.keySet();

        boolean success = true;
        for (String k : keys) {
            success |= translateMovement(k, Double.parseDouble(kvMap.get(k)));
        }

        return success;
    }

    private boolean translateMovement(String direction, double amount) {
        // Speed
        int sp = (int) (( amount / 100.0 ) * THROTTLE);
        switch (direction) {
            case ("FORWARD"):
                setMotorSpeed(sp, sp, sp, sp);
                break;
            case ("BACKWARD"):
                setMotorSpeed(-sp, -sp, -sp, -sp);
                break;
            case ("LEFT"):
                setMotorSpeed(-sp, -sp, sp, sp);
                break;
            case ("RIGHT"):
                setMotorSpeed(sp, sp, -sp, -sp);
                break;
            case ("CLOCKWISE"):
                setMotorSpeed(sp, -sp, sp, -sp);
                break;
            case ("CCLOCKWISE"):
                setMotorSpeed(-sp, sp, -sp, sp);
                break;
            case ("STOP"):
                setMotorSpeed(0,0,0,0);
                break;
            default:
                System.err.println("unrecognized command to ice interface");
                return false;
        }

        return true;
    }

    private void setMotorSpeed(int fl, int fr, int bl, int br) {
        cmonitor.setMotors(fl,fr,bl,br);
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
}