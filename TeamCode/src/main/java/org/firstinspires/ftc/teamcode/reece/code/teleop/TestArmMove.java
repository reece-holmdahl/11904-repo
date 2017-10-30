package org.firstinspires.ftc.teamcode.reece.code.teleop;

import com.qualcomm.robotcore.eventloop.opmode.*;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by admin on 10/17/2017.
 */

@TeleOp(name = "TestArmMove", group = "11904")
public class TestArmMove extends OpMode {

    DcMotor motor;
    double powerVar = 0.0;

    public void init() {
        motor = hardwareMap.get(DcMotor.class, "motor");
    }

    public void loop() {
        double lStickPower = gamepad1.left_stick_y * 0.005;
        if (lStickPower > 1) {
            lStickPower = 1;
        } else if (lStickPower < -1) {
            lStickPower = -1;
        }
        powerVar += lStickPower;
        motor.setPower(powerVar * 0.5);
        telemetry.addData("Curr power var", Double.toString(powerVar * 0.5));
    }

    public void stop() {
        motor.setPower(0);
    }
}
