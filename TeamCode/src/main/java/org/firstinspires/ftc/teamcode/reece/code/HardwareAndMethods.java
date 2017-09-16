package org.firstinspires.ftc.teamcode.reece.code;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Reece on 09/16/2017.
 */

public abstract class HardwareAndMethods extends OpMode {

    //Define motors
    DcMotor lMotor, rMotor;

    public void init() {
        //Map motors and set direction
        lMotor = hardwareMap.get(DcMotor.class, "left motor");
        rMotor = hardwareMap.get(DcMotor.class, "right motor");
        lMotor.setDirection(DcMotor.Direction.FORWARD);
        rMotor.setDirection(DcMotor.Direction.FORWARD);
    }

}
