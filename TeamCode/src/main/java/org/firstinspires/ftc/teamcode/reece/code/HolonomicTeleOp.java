package org.firstinspires.ftc.teamcode.reece.code;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by Reece on 09/25/2017.
 */

@TeleOp(name = "Holonomic TeleOp", group = "TeleOp")
public class HolonomicTeleOp extends HardwareAndMethods {

    public void init_loop() {

    }

    public void start() {

    }

    public void loop() {
        //Determine speeds based on joystick
        double frontLeft = -gamepad1.left_stick_y - gamepad1.left_stick_x - gamepad1.right_stick_x;
        double frontRight = gamepad1.left_stick_y - gamepad1.left_stick_x - gamepad1.right_stick_x;
        double backLeft = -gamepad1.left_stick_y + gamepad1.left_stick_x - gamepad1.right_stick_x;
        double backRight = gamepad1.left_stick_y + gamepad1.left_stick_x - gamepad1.right_stick_x;
        speedFL = Range.clip((speedAll + frontLeft) * speedCoeff, -1, 1);
        speedFR = Range.clip((speedAll + frontRight) * speedCoeff, -1, 1);
        speedBL = Range.clip((speedAll + backLeft) * speedCoeff, -1, 1);
        speedBR = Range.clip((speedAll + backRight) * speedCoeff, -1, 1);
        motorFL.setPower(speedFL);
        motorFR.setPower(speedFR);
        motorBL.setPower(speedBL);
        motorBR.setPower(speedBR);
        telemetry.addData("Heading", Float.toString(heading()));
        telemetry.addData("Acceleration", Double.toString(acceleration()));
        telemetry.update();
    }

    public void stop() {

    }
}
