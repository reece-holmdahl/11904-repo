package org.firstinspires.ftc.teamcode.reece.code;

import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by Reece on 09/25/2017.
 */

@TeleOp(name = "Holonomic Test Op Mode", group = "TeleOp")
//Register op mode in FTC App
public class HolonomicTeleOp extends HardwareAndMethods {

    public void init_loop() {

    }

    public void loop() {
        double gamepadLeftX = scaleDouble(gamepad1.left_stick_x);
        double gamepadLeftY = scaleDouble(gamepad1.left_stick_y);
        double gamepadRightX = scaleDouble(gamepad1.right_stick_x);
        double unscaledFL = -gamepadLeftY - gamepadLeftX - gamepadRightX;                                   //Unscaled front left controller value
        double unscaledBL = -gamepadLeftY + gamepadLeftX - gamepadRightX;
        double unscaledFR = gamepadLeftY - gamepadLeftX - gamepadRightX;
        double unscaledBR = gamepadLeftY + gamepadLeftX - gamepadRightX;
        double scaledFL = Range.clip(unscaledFL, -1, 1) * 0.6;                                              //Scaled front left controller value
        double scaledBL = Range.clip(unscaledBL, -1, 1) * 0.6;
        double scaledFR = Range.clip(unscaledFR, -1, 1) * 0.6;
        double scaledBR = Range.clip(unscaledBR, -1, 1) * 0.6;
        frontLeft.setPower(scaledFL);                                                                       //Set power of front left motor
        backLeft.setPower(scaledBL);
        frontRight.setPower(scaledFR);
        backRight.setPower(scaledBR);
    }

    public void stop() {
        frontLeft.setPower(0);                                                                              //Turn off front left motor ICE
        backLeft.setPower(0);
        frontRight.setPower(0);
        backRight.setPower(0);
    }

    public double scaleDouble(double input) {
        double[] scaleArray = {0.00, 0.0625, 0.125, 0.1875, 0.25, 0.3125, 0.375, 0.4375,                    //Array full of scalable values
                0.5, 0.5625, 0.625, 0.6875, 0.75, 0.8125, 0.875, 0.9375, 1.00};

        int index = (int) input * 16;                                                                       //Finds index location based on input.

        if (index < 0) {                                                                                    //Makes sure value can't exceed index
            index *= -1;
        }
        if (index > 16) {
            index = 16;
        }

        double scaledValue;
        if (input >= 0) {
            scaledValue = scaleArray[index];                                                                //If input was positive, return positive scale value
        } else {
            scaledValue = -scaleArray[index];                                                               //If input was negative, return negative scale value
        }
        return scaledValue;
    }
}
