package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.DefineHardware;

/**
 * Created by Reece on 10/05/2017.
 */

public abstract class Methods extends DefineHardware {

    ElapsedTime mTime = new ElapsedTime();                                                                  //Time counter for use in methods
    ElapsedTime rTime = new ElapsedTime();                                                                  //Master clock that counts time in autonomous

    int up = 0;
    int up_left = 1;
    int left = 2;
    int down_left = 3;                                           //Improper format of declaring variables but it saves space
    int down = 4;
    int down_right = 5;
    int right = 6;
    int up_right = 7;                                      //Said variables are for easier usage in moveDirec method

    /*
     * Parameters for this method are
     * Option: Check beside the array to see options, use name or int. Either will work.
     * Time: Amount of time method runs for.
     */
    /*public void moveDirec(int option, double time) {                                                        //Method to move our robot in 8 axis of movement and compensate for any drift
        final double startHeading = heading();                                                              //Variable to stop constant update of heading
        double[][] speedArray = {                                                                           //All descriptions of speed set are based on when the REV expansion hub is facing you
                {0.4, 0.4, -0.4, -0.4},                                                                     //Up        (0)
                {0.8, 0.0, 0.0, -0.8},                                                                      //Up_Left   (1)
                {0.4, -0.4, 0.4, -0.4},                                                                     //Left      (2)
                {0.0, 0.8, -0.8, 0.0},                                                                      //Down_Left (3)
                {-0.4, -0.4, 0.4, 0.4},                                                                     //Down      (4)
                {-0.8, 0.0, 0.0, 0.8},                                                                      //Down_Right(5)
                {-0.4, 0.4, -0.4, 0.4},                                                                     //Right     (6)
                {0.0, -0.8, 0.8, 0.0},                                                                      //Up_Right  (7)
        };
        mTime.reset();                                                                                      //Reset method time variable
        while (mTime.time() < time) {                                                                       //If statement to make motors work. Can use if because method is constantly called
            if (heading() + 0.5 <= startHeading || heading() - 0.5 >= startHeading) {
                frontLeft.setPower(speedArray[option][0]);                                                  //Based on option sets front left motor to certain speed
                backLeft.setPower(speedArray[option][1]);
                frontRight.setPower(speedArray[option][2]);
                backRight.setPower(speedArray[option][3]);
            } else if (heading() - 0.5 > startHeading) {
                frontLeft.setPower(speedArray[option][0] + 0.1);                                            //Turns robot to the left to compensate for any drift
                backLeft.setPower(speedArray[option][1] + 0.1);
                frontRight.setPower(speedArray[option][2] + 0.1);
                backRight.setPower(speedArray[option][3] + 0.1);
            } else if (heading() + 0.5 < startHeading) {
                frontLeft.setPower(speedArray[option][0] - 0.1);                                            //Turns robot to the right to compensate for any drift
                backLeft.setPower(speedArray[option][1] - 0.1);
                frontRight.setPower(speedArray[option][2] - 0.1);
                backRight.setPower(speedArray[option][3] - 0.1);
            }
        }
        allMotorPower(0);                                                                                   //Turns all motors off
    }*/

    /*
     * The way this method works
     * Enter heading from +360 to -360 (-values being left, +values being right)
     * Robot will turn (slowly) until it reaches the desired heading
     * If the robot's heading goes over that value it will compensate (not yet implemented)
     */
    /*public void faceHeading(int dHeading) {                                                                 //Method to change our heading (turn) to a specific angle
        final double startHeading = heading();                                                              //Variable to stop constant update of heading
        double turnAmount = dHeading - startHeading;
        if (turnAmount > 0) {
            while (heading() + 0.5 < startHeading + turnAmount) {
                allMotorPower(-0.2);
            }
        } else if (turnAmount < 0) {
            while (heading() - 0.5 > startHeading + turnAmount) {
                allMotorPower(0.2);
            }
        } else {
        }
        allMotorPower(0);                                                                                   //Turns all motors off
    }*/

    /*
     * This method is a replacement of the wait method
     * Enter the amount of milliseconds you want the program to pause for
     * and it will run a while loop until that time is reacher
     */
    public void pause(int millis) {                                                                         //Method to replace wait()
        mTime.reset();
        final double reachSec = mTime.time() + millis / 1000;
        while (mTime.time() < reachSec) {

        }
    }
}
