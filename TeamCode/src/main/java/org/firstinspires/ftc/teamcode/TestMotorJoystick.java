package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Created by Reece on 11/20/2017.
 */

@TeleOp(name = "Test Motor")
public class TestMotorJoystick extends OpMode {

    DcMotor motor = null;
    int encVal = 0;

    public void init() {
        motor = hardwareMap.get(DcMotor.class, "motor");
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void start() {
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void loop() {
        if (gamepad1.a) {
            motor.setDirection(DcMotor.Direction.FORWARD);
        } else if (gamepad1.b) {
            motor.setDirection(DcMotor.Direction.REVERSE);
        }

        if (gamepad1.x) {
            motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        } else if (!gamepad1.x) {
            motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }

        motor.setPower(gamepad1.left_stick_y);

        encVal = motor.getCurrentPosition();

        telemetry.addData("Enc Val", Integer.toString(encVal));
        telemetry.update();
    }
}
