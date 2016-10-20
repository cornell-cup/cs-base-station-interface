// **********************************************************************
//
// Copyright (c) 2003-2016 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
//
// **********************************************************************
//
// Ice version 3.6.3
//
// <auto-generated>
//
// Generated from file `CommModule.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package CommModule;

public interface _BaseInterfaceOperations
{
    void sendMovementData(int forward, int strafe, int rotate, Ice.Current __current);

    void setMotorSpeeds(int frontLeft, int frontRight, int backLeft, int backRight, Ice.Current __current);

    void moveArm(String pos, Ice.Current __current);

    void sendControlInfo(int port, int on, Ice.Current __current);

    void init(String ip, String name, int port, int id, Ice.Current __current);

    void pokeBot(String baseIP, Ice.Current __current);

    void shoot(Ice.Current __current);

    void reload(Ice.Current __current);

    void allOff(Ice.Current __current);

    void laserTagFire(Ice.Current __current);

    void laserTagShield(Ice.Current __current);

    void sendSteeringData(int direction, Ice.Current __current);

    void sendTurretData(int horizontal, int vertical, Ice.Current __current);

    void setDuneBotMotorSpeeds(int frontLeft, int frontRight, int backRight, int backLeft, Ice.Current __current);

    void requestImageProcessing(Ice.Current __current);

    void restartBot(Ice.Current __current);

    void shutdownBot(Ice.Current __current);
}
