package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Reece on 12/01/2017.
 */

public class TestQuickHWM extends LinearOpMode {

    DcMotor motor1, motor2, motor3, motor4;

    DcMotor[] devices = {motor1, motor2, motor3, motor4};

    public void runOpMode() {
        hwMap(devices);
        waitForStart();
        while (opModeIsActive()) {
            motor1.setPower(1);
            motor2.setPower(-1);
            motor3.setPower(1);
            motor4.setPower(-1);
        }
    }

    private void hwMap(Object[] devices) {
        for (int i = 0; i < devices.length; i++) {
            devices[i] = hardwareMap.get(devices[i].getClass(), devices[i].toString().toLowerCase());
            idle();
        }
    }
}
