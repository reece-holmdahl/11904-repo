package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

/**
 * Created by Reece on 10/05/2017.
 */

@Autonomous(name = "BasicTest")
public class BasicTest extends Methods {

    public void init_loop() {

    }

    public void start() {
        startIMUThread();
        moveDirec(up_right, 3);
        pause(2000);
        faceHeading(180);
    }

    public void loop() {
        updateIMUValues();
    }

    public void stop() {
        allMotorPower(0);
    }
}
