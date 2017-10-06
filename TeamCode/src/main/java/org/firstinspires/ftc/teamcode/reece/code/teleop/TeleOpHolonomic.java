package org.firstinspires.ftc.teamcode.reece.code.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.reece.code.DefineHardware;

/**
 * Created by Reece on 09/25/2017.
 */

@TeleOp(name = "TeleOpHolonomic", group = "TELEOP")
//Register op mode in FTC App
public class TeleOpHolonomic extends DefineHardware {

    public void init_loop() {

    }

    public void loop() {
        imuDataCons();                                                                                      //IMU telemetry for debugging and testing
        double gamepadLeftX = scaleDouble(gamepad1.left_stick_x);
        double gamepadLeftY = -scaleDouble(gamepad1.left_stick_y);
        double gamepadRightX = gamepad1.right_stick_x;
        double unscaledFL = -gamepadLeftY - gamepadLeftX - gamepadRightX;                                   //Unscaled front left controller value
        double unscaledBL = -gamepadLeftY + gamepadLeftX - gamepadRightX;
        double unscaledFR = gamepadLeftY - gamepadLeftX - gamepadRightX;
        double unscaledBR = gamepadLeftY + gamepadLeftX - gamepadRightX;
        double scaledFL = unscaledFL * 0.5;                                                                 //Scaled front left controller value
        double scaledBL = unscaledBL * 0.5;
        double scaledFR = unscaledFR * 0.5;
        double scaledBR = unscaledBR * 0.5;
        frontLeft.setPower(scaledFL);                                                                       //Set power of front left motor
        backLeft.setPower(scaledBL);
        frontRight.setPower(scaledFR);
        backRight.setPower(scaledBR);
    }

    public void stop() {
        allMotorPower(0);                                                                                   //Turns all motors off
    }

    public double scaleDouble(double input) {
        double[] scaleArray = {0, 0.50, 0.55, 0.6, 0.65, 0.7, 0.75,                                         //Array full of scalable values
                                  0.8, 0.85, 0.9, 0.95, 1.00, 1.00};

        int index = (int) (input * 12);                                                                     //Finds index location based on input.

        if (index < 0) {                                                                                    //Makes sure value can't exceed index
            index *= -1;
        }
        if (index > 12) {
            index = 12;
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
