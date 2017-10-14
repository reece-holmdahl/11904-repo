package org.firstinspires.ftc.teamcode.reece.code;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by admin on 10/7/2017.
 */

@TeleOp(name = "TestMRServo", group = "TELEOP")
public class TestMRServo extends OpMode {

    Servo servo;
    double sPos;

    public void init() {
        servo = hardwareMap.get(Servo.class, "servo");
        //servo.setPosition(0.5);
    }

    public void loop() {
        double jStick = (gamepad1.left_stick_x * 0.0075);
        if (sPos <= 0.7 && sPos >= 0) {
            sPos += jStick;
        } else if (sPos > 0.7) {
            sPos = 0.7;
        } else if (sPos < 0) {
            sPos = 0;
        }
        servo.setPosition(sPos);
        telemetry.addData("Servo Pos", Double.toString(servo.getPosition()));
        telemetry.addData("Servo Variable", Double.toString(sPos));
        telemetry.addData("Joystick Value", Double.toString(jStick * 500));
    }

    public void stop() {
        //servo.setPosition(0);
    }
}
