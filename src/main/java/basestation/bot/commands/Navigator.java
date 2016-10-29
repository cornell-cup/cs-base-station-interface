package basestation.bot.commands;

import basestation.BaseStation;
import basestation.bot.Bot;
import basestation.bot.robot.modbot.ModbotCommandCenter;
import basestation.vision.VisionCoordinate;

/**
 * Controller for moving the bot to specified coordinates
 */
public class Navigator {

    private BaseStation abs;
    private VisionCoordinate destination;
    private Bot myBot;

    private static final double DISTANCE_BUBBLE = 0.04;
    private static final double THETA_BUBBLE = 10;
    private static final int POWER_CONST = 20;
    private static final int START_SPEED = 10;

    private boolean success;

    public Navigator(BaseStation abs, Bot myBot) {
        this.abs = abs;
        this.myBot = myBot;
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
        return a - Math.floor(a / n) * n;
    }

    private void calcRoute() {
        if (success) return;
        VisionCoordinate vc = abs.getVisionManager().getBotCoordinate(myBot);
        System.out.println(vc);
        if (vc == null) {
            ((ModbotCommandCenter) myBot.getCommandCenter()).stop();
            return;
        }
        double spectheta = vc.getThetaOrZero();
        double toAngle = vc.getAngleTo(destination); // 360 scale
        double angle = mod((toAngle - spectheta + 180), 360) - 180;
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
        System.out.println("ang:" + angle);
        int last = -1;
        if (dist > DISTANCE_BUBBLE) {
            if (Math.abs(angle) > THETA_BUBBLE) {
                if (angle > 0) {
                    ((ModbotCommandCenter) myBot.getCommandCenter()).counterClockwise(angSpeed);
                } else {
                    ((ModbotCommandCenter) myBot.getCommandCenter()).clockwise(angSpeed);
                }
            } else {
                if (dist > DISTANCE_BUBBLE) {
                    ((ModbotCommandCenter) myBot.getCommandCenter()).forward(speed);
                } else {
                    ((ModbotCommandCenter) myBot.getCommandCenter()).stop();
                    success = true;
                }
            }
        } else {
            ((ModbotCommandCenter) myBot.getCommandCenter()).stop();
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
