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

public interface _BaseInterfaceOperationsNC
{
    void sendMovementData(int forward, int strafe, int rotate);

    void setMotorSpeeds(int frontLeft, int frontRight, int backLeft, int backRight);

    void moveArm(String pos);

    void sendControlInfo(int port, int on);

    void init(String ip, String name, int port, int id);

    void pokeBot(String baseIP);

    void shoot();

    void reload();

    void allOff();

    void laserTagFire();

    void laserTagShield();

    void sendSteeringData(int direction);

    void sendTurretData(int horizontal, int vertical);

    void setDuneBotMotorSpeeds(int frontLeft, int frontRight, int backRight, int backLeft);

    void requestImageProcessing();

    void restartBot();

    void shutdownBot();
}
