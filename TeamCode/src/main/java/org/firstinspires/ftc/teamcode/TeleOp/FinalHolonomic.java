package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.DefineHardware;

/**
 * This is the final holonomic TeleOp file that will be used at the tournament, it incorporates
 * some designs used in my TeleOp concepts but also uses more proven and effective ways.
 * Created by Reece on 11/04/2017.
 */

@TeleOp(name = "FinalHolonomic")
public class FinalHolonomic extends DefineHardware {

    //Speed control coefficients
    private double speed    = 0.6;
    private double turn     = 0.3;

    //Variable names used for cleaner code
    private int front   = 1;
    private int left    = 1;
    private int back    = -1;
    private int right   = -1;

    /**
     * The loop method is looped when the robot controller is in the start phase. It is used for the
     * core functionality of the robot like movement based on joysticks or other means.
     */

    public void loop() {
        //Use driveCode method to set power to motors based on joystick values
        frontLeft.setPower(driveCode(front, left));
        backLeft.setPower(driveCode(back, left));
        frontRight.setPower(driveCode(front, right));
        backRight.setPower(driveCode(back, right));

        //Set wheels to brake when not being powered
        if (driverLeftX() + driverLeftY() == 0) {
            frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        } else {
            frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        }

        //Keep jewel servo upright so it doesn't get broken off
        jewel.setPosition(0);
    }

    /**
     * The driveCode method calculates what motors need to be set to what power by giving it which
     * side and end the motor is on
     *
     * @param side  The side (horizontally) that the motor is on
     * @param end   The end (vertically) that the motor is on
     * @return      Returns the power at which the motor needs to be set
     */

    private double driveCode(int side, int end) {
        double move = end * driverLeftX() + side * driverLeftY();
        double turn = driverRightX();
        return Range.clip(move * speed + turn * this.turn, -1, 1);
    }

    /**
     * The round method rounds the input to the place specified. Ex: tenths place is 0.1, hundredths
     * place is 0.01, etc.
     * @param input The double you want to round
     * @param place The decimal place it should be rounded to, any multiple of 10 works
     * @return      Returns the rounded value
     */

    private double round(double input, double place) {
        double multiplier = 1 / place;
        return ((int) (input * multiplier)) / multiplier;
    }

    private double driverLeftX() {
        return round(driver.left_stick_x, 0.1);
    }

    private double driverLeftY() {
        return round(driver.left_stick_y, 0.1);
    }

    private double driverRightX() {
        return round(driver.right_stick_x, 0.1);
    }
}
