package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by admin on 11/25/2017.
 */

@TeleOp(name = "Test Joysticks")
public class TestJoysticks extends OpMode {

    public void init() {

    }

    public void loop() {
        telemetry.addData("Dpad left",  Boolean.toString(gamepad1.dpad_left));
        telemetry.addData("Dpad right", Boolean.toString(gamepad1.dpad_right));
    }
}
