package org.firstinspires.ftc.teamcode.reece.code.teleop;

import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.reece.code.DefineHardware;

/**
 * Created by admin on 10/26/2017.
 */

public class TeleOpDriveArmTest extends DefineHardware {

    double speedModifier = 0.45;                                                                            //Easy variable for modifying speed percentage
    double turnModifier = 0.35;
    double dComp = 0.00;                                                                                    //Filler variable for drift compensation

    public void init_loop() {

    }

    public void loop() {
        holoDriveLoop();                                                                                    //Loop holonomic formula for driving
        frontLeft.setPower(Range.clip(powerFL - dComp, -1, 1));                                             //Clip and set power of front left motor
        backLeft.setPower(Range.clip(powerBL - dComp, -1, 1));
        frontRight.setPower(Range.clip(powerFR - dComp, -1, 1));
        backRight.setPower(Range.clip(powerBR - dComp, -1, 1));

        armLower.setPower(Range.clip(gamepad2.left_stick_y - gamepad2.left_stick_y * 0.05, -1, 1));
        armUpper.setPower(Range.clip(-gamepad2.left_stick_y + gamepad2.left_stick_y * 0.05, -1, 1));
    }

    public void stop() {
        allMotorPower(0);
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

    public void holoDriveLoop() {                                                                           //Holonomic drive code for cleaner loop method
        double gamepadLeftX = scaleDouble(gamepad1.left_stick_x);                                           //Declare left stick as usable variable
        double gamepadLeftY = -scaleDouble(gamepad1.left_stick_y);
        double gamepadRightX = gamepad1.right_stick_x;
        powerFL = (-gamepadLeftY - gamepadLeftX) * speedModifier - gamepadRightX * turnModifier;            //Scaled front left motor power value
        powerBL = (-gamepadLeftY + gamepadLeftX) * speedModifier - gamepadRightX * turnModifier;
        powerFR = (gamepadLeftY - gamepadLeftX) * speedModifier - gamepadRightX * turnModifier;
        powerBR = (gamepadLeftY + gamepadLeftX) * speedModifier - gamepadRightX * turnModifier;
    }
}
