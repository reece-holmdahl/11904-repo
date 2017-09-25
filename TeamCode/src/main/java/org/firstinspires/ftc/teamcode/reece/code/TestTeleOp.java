package org.firstinspires.ftc.teamcode.reece.code;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by Reece on 09/16/2017.
 */
@TeleOp(name="Test TeleOp", group="TeleOp")
public class TestTeleOp extends HardwareAndMethods {

    int direction = 0;

    public void init() {
        telemetry.addLine("TeleOp Initialized");
    }

    public void init_loop() {
        telemetry.addData("Direction", Integer.toString(direction));
        telemetry.update();
        if (gamepad1.a) {
            direction = 1;
        } if (gamepad1.b) {
            direction = -1;
        }
    }

    public void start() {
        telemetry.addLine("TeleOp Started");
    }

    public void loop() {
        lMotor.setPower(gamepad1.left_trigger * direction);
        rMotor.setPower(gamepad1.right_trigger * direction);
    }

    public void stop() {
        telemetry.addLine("TeleOp Stopped");
        lMotor.setPower(0);
        rMotor.setPower(0);
    }

}
