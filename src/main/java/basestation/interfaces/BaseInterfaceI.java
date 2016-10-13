package basestation.interfaces;

import CommModule.BaseInterfacePrx;
import CommModule._BaseInterfaceDisp;
import Ice.Current;

/**
 * Created by trevor on 10/12/16.
 */
public class BaseInterfaceI extends _BaseInterfaceDisp {
    public void sendMovementData(int forward, int strafe, int rotate, Current __current) {

    }

    public void setMotorSpeeds(int frontLeft, int frontRight, int backLeft, int backRight, Current __current) {

        ((BaseInterfacePrx) null).begin_setMotorSpeeds(frontLeft, backLeft, backLeft, backRight);

    }

    public void moveArm(String pos, Current __current) {

    }

    public void sendControlInfo(int port, int on, Current __current) {

    }

    public void init(String ip, String name, int port, int id, Current __current) {

    }

    public void pokeBot(String baseIP, Current __current) {

    }

    public void shoot(Current __current) {

    }

    public void reload(Current __current) {

    }

    public void allOff(Current __current) {

    }

    public void laserTagFire(Current __current) {

    }

    public void laserTagShield(Current __current) {

    }

    public void sendSteeringData(int direction, Current __current) {

    }

    public void sendTurretData(int horizontal, int vertical, Current __current) {

    }

    public void setDuneBotMotorSpeeds(int frontLeft, int frontRight, int backRight, int backLeft, Current __current) {

    }

    public void requestImageProcessing(Current __current) {

    }

    public void restartBot(Current __current) {

    }

    public void shutdownBot(Current __current) {

    }
}
