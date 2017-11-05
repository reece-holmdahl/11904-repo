package org.firstinspires.ftc.teamcode.TeleOp.concepts;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.DefineHardware;

/**
 * Created by Reece on 10/30/2017.
 */

@TeleOp(name = "HolonomicChanges")
public class HolonomicChanges extends DefineHardware {

    double speedMod = 0.6;                                                                                  //Variable for quick manipulation of speed
    double turnMod = 0.3;                                                                                   //Same as before but for turning
    int tickValue = 0;                                                                                      //Initialize variable for a method
    boolean armMovable = true;                                                                              //Variable to allow arm to be moved (not allowed if over certain motor tick value

    public void init_loop() {

    }

    @Override
    public void loop() {
        //Drive train motor code
        frontLeft.setPower(holoCode(0));                                                                    //Set all motors to their speed based on holonomic formula method and motor pos
        backLeft.setPower(holoCode(1));
        frontRight.setPower(holoCode(2));
        backRight.setPower(holoCode(3));

        //Glyph manipulator motor and servo code
        if (armMovable) {
            lowerArm.setPower(Range.clip(manipLeftY() + forceMod(), -1, 1));
            upperArm.setPower(-lowerArm.getPower());
        }
    }

    private double scaleDouble(double input, boolean precise) {
        double[] scaleArray = {0, 0.50, 0.55, 0.6, 0.65, 0.7, 0.75,                                         //Array with scalable values
                0.8, 0.85, 0.9, 0.95, 1.00, 1.00};

        int index = Math.abs((int) (input * 12));                                                           //Finds index location based on input and makes sure its positive

        if (index > 12) {                                                                                   //If variable exceeds index for some reason, make sure it can't go over
            index = 12;
        }

        double scaledValue;
        if (input >= 0) {
            scaledValue = scaleArray[index];                                                                //If input was positive, return positive scale value
        } else {
            scaledValue = -scaleArray[index];                                                               //If input was negative, return negative scale value
        }
        if (!precise) {                                                                                     //If precise movement is active, make values of joystick lower for slow movement
            return scaledValue;                                                                             //Returns value in range from 0.5 to 1.0
        } else {
            return scaledValue * 0.4;                                                                       //Returns value in range from 0.2 to 0.4
        }
    }

    private double holoCode(int motorPos) {
        double[] motorPower = {(-driveLeftX() - driveLeftY()) * speedMod - driveRightX() * turnMod,         //Front left motor, or motor pos 0
                (driveLeftX() - driveLeftY()) * speedMod - driveRightX() * turnMod,                         //Back left motor, or motor pos 1
                (-driveLeftX() + driveLeftY()) * speedMod - driveRightX() * turnMod,                        //Front right motor, or motor pos 2
                (driveLeftX() + driveLeftY()) * speedMod - driveRightX() * turnMod};                        //Back right motor, pos motor pos 3
        return Range.clip(motorPower[motorPos], -1, 1);                                                     //Returns value for motor based on selection and makes sure it doesn't exceed motor power regulations
    }

    private int motorTick() {                                                                               //Motor tick variable to get relative place of lower arm motor (0)
        tickValue += (int) ((lowerArm.getPower() - forceMod()) * 100);                                      //Subtract force modification and multiply by 100 for accurate representation when cast to an int
        if (tickValue > 400) {                                                                              //Value cannot exceed 400 (subject to change)
            tickValue = 400;
            armMovable = false;                                                                             //Disallow movement of arm so tick value doesn't break (greater than 400)
        } else if (tickValue < 0) {
            tickValue = 0;
            armMovable = false;                                                                             //Same as before except when it is below (lower than 0)
        } else {
            armMovable = true;
        }
        return tickValue;
    }

    private double forceMod() {                                                                             //Force modification method for adaptive adding to or subtracting from power of arm speed
        //Left blank for now because there hasn't been any testing for what power adjustments are needed
        return 0;
    }

    private double driveLeftX() {                                                                           //Easier way to scale joystick, uses scaleDouble method and checks for precise movement
        return scaleDouble(gamepad1.left_stick_x, gamepad1.right_bumper);
    }

    private double driveLeftY() {                                                                           //Same as method above except applies to the left stick Y value instead
        return scaleDouble(gamepad1.left_stick_y, gamepad1.right_bumper);
    }

    private double driveRightX() {                                                                          //Scales the right stick X for turning
        return scaleDouble(gamepad1.right_stick_x, gamepad1.right_bumper);
    }

    private double manipLeftY() {                                                                           //This "method" is here for ease of programming
        return gamepad2.left_stick_y;
    }
}
