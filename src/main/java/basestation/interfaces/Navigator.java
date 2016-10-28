package basestation.interfaces;

import basestation.BaseStation;
import basestation.bot.modbot.ModbotCommandCenter;
import basestation.vision.VisionCoordinate;

/**
 * Controller for moving the bot to specified coordinates
 */
public class Navigator {

    BaseStation abs;
    int botid;
    VisionCoordinate destination;

    static final double DISTANCE_BUBBLE = 0.04;
    static final double THETA_BUBBLE = 10;
    static final int POWER_CONST = 20;
    static final int START_SPEED = 10;

    boolean success;

    public Navigator(BaseStation abs, int botid) {
        this.abs = abs;
        this.botid = botid;
        success = true;
        NavigationManager nm = new NavigationManager(this);
        nm.start();
    }

    public boolean destinationReached() {
        return success;
    }

    public void setDestination(VisionCoordinate dest) {
        this.destination = dest;
        success = false;
        calcRoute();
    }

    private double mod(double a, double n) {
        return a - Math.floor(a/n) * n;
    }

    private void calcRoute() {
        if (success) return;
        VisionCoordinate vc = abs.getBotCoordinate(abs.getBotById(botid));
        System.out.println(vc);
        if (vc==null) {
            ((ModbotCommandCenter)abs.getBotById(botid).getCommandCenter()).stop();
            return;
        }
        double spectheta = vc.getTheta();
        double toAngle = vc.getAngleTo(destination); // 360 scale
        double angle = mod((toAngle - spectheta + 180),360) - 180;
        double dist = vc.getDistanceTo(destination);
        double speed = START_SPEED;
        if (dist > 0.2) {
            speed = START_SPEED + (POWER_CONST - START_SPEED) * (dist - 0.2) * 3;
        }

        double angSpeed = START_SPEED;
        if (Math.abs(angle) > 20) {
            angSpeed = START_SPEED + (POWER_CONST - START_SPEED) * (Math.abs(angle) - 20) / 160.;
        }

        if (speed > POWER_CONST) speed = POWER_CONST;
        if (angSpeed > POWER_CONST) angSpeed = POWER_CONST;
        System.out.println("ang:"+angle);
        int last = -1;
        if (dist > DISTANCE_BUBBLE) {
            if (Math.abs(angle) > THETA_BUBBLE) {
                if (angle > 0) {
                    ((ModbotCommandCenter) abs.getBotById(botid).getCommandCenter()).counterClockwise(angSpeed);
                } else {
                    ((ModbotCommandCenter) abs.getBotById(botid).getCommandCenter()).clockwise(angSpeed);
                }
            } else {
                if (dist > DISTANCE_BUBBLE) {
                    ((ModbotCommandCenter) abs.getBotById(botid).getCommandCenter()).forward(speed);
                } else {
                    ((ModbotCommandCenter) abs.getBotById(botid).getCommandCenter()).stop();
                    success = true;
                }
            }
        } else {
            ((ModbotCommandCenter) abs.getBotById(botid).getCommandCenter()).stop();
            success = true;
        }
    }

    private class NavigationManager extends Thread {

        Navigator parent;

        NavigationManager(Navigator parent) {
            this.parent = parent;
        }

        public void run() {
            while (true) {
                parent.calcRoute();
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
