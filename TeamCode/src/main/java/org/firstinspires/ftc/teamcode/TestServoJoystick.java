package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Reece on 11/20/2017.
 */

@TeleOp(name = "Test Servo")
public class TestServoJoystick extends OpMode {

    Servo servo = null;
    int sPos = 0;

    public void init() {
        servo = hardwareMap.get(Servo.class, "servo");
        servo.setDirection(Servo.Direction.FORWARD);
    }

    public void loop() {
        if (gamepad1.a) {
            servo.setDirection(Servo.Direction.FORWARD);
        } else if (gamepad1.b) {
            servo.setDirection(Servo.Direction.REVERSE);
        }

        if (sPos <= 1 && sPos >= 0) {
            sPos += gamepad1.left_stick_y * 0.02;
        }

        if (sPos > 1) {
            sPos = 1;
        } else if (sPos < 0) {
            sPos = 0;
        }

        servo.setPosition(sPos);

        telemetry.addData("Servo Pos", Double.toString(servo.getPosition()));
        telemetry.update();
    }
}
