package org.firstinspires.ftc.teamcode.zach.code;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by admin on 10/7/2017.
 */

@TeleOp(name = "TestMotorArm", group = "TELEOP")
public class TestMotorArm extends OpMode {

    DcMotor armMotor;
    double sPos;

    public void init() {
        armMotor = hardwareMap.get(DcMotor.class, "arm motor");
    }

    public void loop() {
        double defaultPower = 0.05
        double jStick = gamepad1.right_stick_y;
        if (jStick >= 0)
        {
            jStick = jStick * 0.35;
        }
        else
        {
            jStick = jStick * defaultPower;
        }
        armMotor.setPower(defaultPower + jStick);
        telemetry.addData("Joystick Value", Double.toString(jStick * 500));
    }

    public void stop() {
        //servo.setPosition(0);
    }
}
