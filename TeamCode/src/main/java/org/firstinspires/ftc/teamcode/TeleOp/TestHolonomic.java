package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.DefineHardware;

/**
 * Created by Reece on 09/25/2017.
 */

@TeleOp(name = "TestHolonomic")
public class TestHolonomic extends DefineHardware {

    double speedModifier = 0.45;                                                                            //Easy variable for modifying speed percentage
    double turnModifier = 0.35;                                                                             //Easy variable for modifying turning speed percentage

    public void init_loop() {

    }

    public void loop() {
        double gamepadLeftX = scaleDouble(gamepad1.left_stick_x);                                           //Declare left stick as usable variable
        double gamepadLeftY = -scaleDouble(gamepad1.left_stick_y);
        double gamepadRightX = gamepad1.right_stick_x;
        double powerFL = (-gamepadLeftY - gamepadLeftX) * speedModifier - gamepadRightX * turnModifier;     //Scaled front left motor power value
        double powerBL = (-gamepadLeftY + gamepadLeftX) * speedModifier - gamepadRightX * turnModifier;
        double powerFR = (gamepadLeftY - gamepadLeftX) * speedModifier - gamepadRightX * turnModifier;
        double powerBR = (gamepadLeftY + gamepadLeftX) * speedModifier - gamepadRightX * turnModifier;
        frontLeft.setPower(Range.clip(powerFL, -1, 1));                                                     //Clip and set power of front left motor
        backLeft.setPower(Range.clip(powerBL, -1, 1));
        frontRight.setPower(Range.clip(powerFR, -1, 1));
        backRight.setPower(Range.clip(powerBR, -1, 1));
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
