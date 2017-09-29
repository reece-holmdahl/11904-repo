package org.firstinspires.ftc.teamcode.reece.code;

import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by Reece on 09/25/2017.
 */

@TeleOp(name = "Holonomic Test Op Mode", group = "TeleOp")                                                  //Register op mode in FTC App
public class HolonomicTeleOp extends HardwareAndMethods {

    public void init_loop() {

    }

    public void loop() {
        //Determine speeds based on joystick
        double unscaledFL = -gamepad1.left_stick_y - gamepad1.left_stick_x - gamepad1.right_stick_x;        //Unscaled front left controller value
        double unscaledBL = -gamepad1.left_stick_y + gamepad1.left_stick_x - gamepad1.right_stick_x;
        double unscaledFR = gamepad1.left_stick_y - gamepad1.left_stick_x - gamepad1.right_stick_x;
        double unscaledBR = gamepad1.left_stick_y + gamepad1.left_stick_x - gamepad1.right_stick_x;
        double scaledFL   = scaleDouble(Range.clip(unscaledFL, -1, 1)) * 0.25;                              //Scaled front left controller value
        double scaledBL   = scaleDouble(Range.clip(unscaledBL, -1, 1)) * 0.25;
        double scaledFR   = scaleDouble(Range.clip(unscaledFR, -1, 1)) * 0.25;
        double scaledBR   = scaleDouble(Range.clip(unscaledBR, -1, 1)) * 0.25;
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
        double[] scaleArray = {0.00, 0.05, 0.10, 0.15, 0.20, 0.25, 0.30, 0.35,                              //Array full of scalable values
                               0.40, 0.50, 0.60, 0.70, 0.75, 0.80, 0.90, 1.00};

        int index = (int) input * 16;                                                                       //Finds index location based on input.

        if (index < 0) {                                                                                    //Makes sure value can't exceed index
            index = -index;
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
