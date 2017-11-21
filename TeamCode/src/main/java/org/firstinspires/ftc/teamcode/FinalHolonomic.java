package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple.Direction;
import com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * This is the final holonomic TeleOp file that will be used at the tournament, it incorporates
 * some designs used in my TeleOp concepts but also uses more proven and effective ways.
 * Created by Reece on 11/04/2017.
 */

@TeleOp(name = "FinalHolonomic")
public class FinalHolonomic extends OpMode {

    /* Power variables for hardware devices */
    
    //Drive train motor and servo variables
    private double                      frontLeftPower  = 0;
    private double                      backLeftPower   = 0;
    private double                      frontRightPower = 0;
    private double                      backRightPower  = 0;
    private double                      jewelPos        = 1;
    
    //Glyph manipulator motor and servo variables
    private double                      armPower        = 0;
    private double                      leftClawPos     = 0;
    private double                      rightClawPos    = 1;
    
    //Relic manipulator motor and servo variables
    private double                      slidePower      = 0;
    private int                         slideEncVal     = 0;
    private int                         slideEncMax     = 0;
    private double                      clampPos        = 0;
    private double                      pivotPos        = 0;


    /* Variable coefficients */

    //Speed control coefficients
    private final double                driveSpd        = 0.4;
    private final double                turnSpd         = 0.2;
    private final double                servoSpd        = 0.02;

    //Drive train variables used for coefficients in driveCode methods
    private final int                   front           =  1;
    private final int                   back            = -1;
    private final int                   left            = -1;
    private final int                   right           =  1;


    /* Define variables for hardware device parameters */

    //Variables for motor parameters
    private final Direction             forward         = Direction.FORWARD;
    private final Direction             reverse         = Direction.REVERSE;
    private final ZeroPowerBehavior     brake           = ZeroPowerBehavior.BRAKE;
    private final ZeroPowerBehavior     drift           = ZeroPowerBehavior.FLOAT;

    //Variables for servo parameters
    private final Servo.Direction       sForward        = Servo.Direction.FORWARD;
    private final Servo.Direction       sReverse        = Servo.Direction.REVERSE;


    /* Define hardware devices */

    //Drive train objects
    private DcMotor                     frontLeft       = null;
    private DcMotor                     backLeft        = null;
    private DcMotor                     frontRight      = null;
    private DcMotor                     backRight       = null;
    private Servo                       jewel           = null;

    //Glyph manipulator objects
    private DcMotor                     arm             = null;
    private Servo                       leftClaw        = null;
    private Servo                       rightClaw       = null;

    //Relic manipulator objects
    private DcMotor                     slide           = null;
    private Servo                       clamp           = null;
    private Servo                       pivot           = null;

    /**
     * The init method is run once when the init phase is active on the robot controller. This
     * method is used for hardware mapping hardware devices and setting parameters for the devices.
     */

    public void init() {

        /* Hardware map all hardware devices */

        //Hardware map drive train objects
        frontLeft   = hardwareMap.get(DcMotor.class,    "front left");
        backLeft    = hardwareMap.get(DcMotor.class,    "back left");
        frontRight  = hardwareMap.get(DcMotor.class,    "front right");
        backRight   = hardwareMap.get(DcMotor.class,    "back right");
        jewel       = hardwareMap.get(Servo.class,      "jewel");

        //Hardware map glyph manipulator objects
        arm         = hardwareMap.get(DcMotor.class,    "arm");
        leftClaw    = hardwareMap.get(Servo.class,      "leftClaw");
        rightClaw   = hardwareMap.get(Servo.class,      "rightClaw");

        //Hardware map relic manipulator objects
        slide       = hardwareMap.get(DcMotor.class,    "slide");
        clamp       = hardwareMap.get(Servo.class,      "clamp");
        pivot       = hardwareMap.get(Servo.class,      "pivot");


        /* Set parameters of all hardware devices */

        //Drive train motor parameters
        frontLeft.setDirection(forward);
        backLeft.setDirection(forward);
        frontRight.setDirection(forward);
        backRight.setDirection(forward);

        //Arm motor parameters
        arm.setDirection(reverse);
        arm.setZeroPowerBehavior(brake);

        //Linear slide motor parameters
        slide.setDirection(reverse);
        slide.setZeroPowerBehavior(brake);
        slide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //Jewel servo parameters
        jewel.setDirection(sReverse);
        jewel.setPosition(jewelPos);

        //Relic manipulator servo parameters
        clamp.setDirection(sForward);
        clamp.setPosition(clampPos);
        pivot.setDirection(sForward);
        pivot.setPosition(pivotPos);

        //Glyph manipulator servo parameters
        leftClaw.setDirection(sForward);
        leftClaw.setPosition(leftClawPos);
        rightClaw.setDirection(sReverse);
        rightClaw.setPosition(rightClawPos);
    }

    /**
     * The loop method is looped when the robot controller is in the start phase. It is used for the
     * core functionality of the robot like movement with joysticks or other means.
     */

    public void loop() {

        /* Set drive train motor power */

        //Update drive train motor powers with driveCode method and stop them from drifting
        frontLeftPower  =   driveCode(front,    left);
        backLeftPower   =   driveCode(back,     left);
        frontRightPower =   driveCode(front,    right);
        backRightPower  =   driveCode(back,     right);

        //Set wheels to brake when not being powered
        if (driverLeftX() + driverLeftY() == 0) {
            frontLeft.setZeroPowerBehavior(brake);
            backLeft.setZeroPowerBehavior(brake);
            frontRight.setZeroPowerBehavior(brake);
            backRight.setZeroPowerBehavior(brake);
        } else {
            frontLeft.setZeroPowerBehavior(drift);
            backLeft.setZeroPowerBehavior(drift);
            frontRight.setZeroPowerBehavior(drift);
            backRight.setZeroPowerBehavior(drift);
        }


        /* Set arm motor power */

        //Update arm power variable using dpad up and down
        if (gamepad1.dpad_up) {
            armPower    =   0.6;
        } else if (gamepad1.dpad_down) {
            armPower    =  -0.01;
        } else {
            armPower    =   0;
        }


        /* Set linear slide motor power */

        //Update linear slide power variable using dpad left and dpad right
        if (gamepad1.dpad_right) {
            slidePower  =   1;
        } else if (gamepad1.dpad_left) {
            slidePower  =  -0.4;
        } else {
            slidePower  =   0;
        }


        /* Jewel servo position */

        //Keep jewel servo position at 1 the whole match so it doesn't fall over
        jewel.setPosition(jewelPos);


        /* Glyph manipulator servo positions */

        //Update left and right claw positions
        if (gamepad1.left_bumper) {
            if (leftClawPos <= 1 && leftClawPos >= 0 && rightClawPos <= 1 && rightClawPos >= 0) {
                leftClawPos     +=  servoSpd;
                rightClawPos    -=  servoSpd;
            }
        } else if (gamepad1.right_bumper) {
            if (leftClawPos <= 1 && leftClawPos >= 0 && rightClawPos <= 1 && rightClawPos >= 0) {
                leftClawPos     -=  servoSpd;
                rightClawPos    +=  servoSpd;
            }
        }

        //Keep left and right claw positions within 0 and 1
        if (leftClawPos > 1 || rightClawPos < 0) {
            leftClawPos         =   1;
            rightClawPos        =   0;
        } else if (leftClawPos < 0 || rightClawPos > 1) {
            leftClawPos         =   0;
            rightClawPos        =   1;
        }


        /* Relic manipulator servo positions */

        //Update clamp position
        if (gamepad1.a) {
            if (clampPos <= 1 && clampPos >= 0) {
                clampPos        +=  servoSpd;
            }
        } else if (gamepad1.b) {
            if (clampPos <= 1 && clampPos >= 0) {
                clampPos        -=  servoSpd;
            }
        }

        //Keep left and right claw positions within 0 and 1
        if (clampPos > 1) {
            clampPos            =   1;
        } else if (clampPos < 0) {
            clampPos            =   0;
        }


        /* Power or set position to all hardware devices */

        //Set power to drive train motors
        frontLeft.setPower(frontLeftPower);
        backLeft.setPower(backLeftPower);
        frontRight.setPower(frontRightPower);
        backRight.setPower(backRightPower);

        //Set power to arm motor
        arm.setPower(armPower);

        //Set power to slide motor and get encoder value
        slide.setPower(slidePower);
        slideEncVal = slide.getCurrentPosition();

        //Set position of glyph manipulator servos
        leftClaw.setPosition(leftClawPos);
        rightClaw.setPosition(rightClawPos);

        //Set position of relic manipulator servos
        clamp.setPosition(clampPos);
        pivot.setPosition(pivotPos);

        //Set position of jewel servo
        jewel.setPosition(jewelPos);


        /* Send out and update telemetry for debugging */

        //Joystick data telemetry
        telemetry.addData("Left X", Double.toString(driverLeftX()));
        telemetry.addData("Left Y", Double.toString(driverLeftY()));
        telemetry.addData("Right X", Double.toString(driverRightX()));

        //Drive train motor telemetry
        telemetry.addData("Speed: FL", Double.toString(frontLeftPower));
        telemetry.addData("Speed: BL", Double.toString(backLeftPower));
        telemetry.addData("Speed: FR", Double.toString(frontRightPower));
        telemetry.addData("Speed: BR", Double.toString(backRightPower));

        //Arm motor telemetry
        telemetry.addData("Speed: Arm",  Double.toString(armPower));

        //Linear slide motor telemetry
        telemetry.addData("Enc: Slide",  Integer.toString(slideEncVal));

        //Glyph manipulator servo telemetry
        telemetry.addData("Pos: Left Claw",     Double.toString(leftClawPos));
        telemetry.addData("Pos: Right Claw",    Double.toString(rightClawPos));

        //Relic manipulator servo telemetry
        telemetry.addData("Pos: Clamp", Double.toString(clampPos));
        telemetry.addData("Pos: Pivot", Double.toString(pivotPos));

        //Update telemetry
        telemetry.update();
    }

    /**
     * The stop method is ran once when the robot controller ends the op mode. It is usually used to
     * turn off motors or other moving devices so the robot doesn't accidentally go haywire.
     */

    public void stop() {

        //Stop motors
        frontLeft.setPower(0);
        backLeft.setPower(0);
        frontRight.setPower(0);
        backRight.setPower(0);
        arm.setPower(0);
        slide.setPower(0);

        //Reset servos
        jewel.setPosition(1);
        //leftClaw.setPosition(0.3);
        //rightClaw.setPosition(0.7);
        //clamp.setPosition(0);
        //pivot.setPosition(0);
    }

    /**
     * The driveCode method calculates what motors need to be set to what power by giving it which
     * side and end the motor is on
     *
     * @param end   The end (vertically) that the motor is on. Ex: front, back
     * @param side  The side (horizontally) that the motor is on. Ex: left, right
     * @return      Returns the power at which the motor needs to be set
     */

    private double driveCode(int end, int side) {
        double move = side * driverLeftX() + end * driverLeftY();
        double turn = driverRightX();
        return Range.clip(move * driveSpd + turn * turnSpd, -1, 1);
    }

    /**
     * The armCode method uses simple math in order to not have the arm slam into the robot when it
     * is set to go downwards
     *
     * @param joystick  The joystick values to multiply by and return
     * @return          The adjusted power returned from the method
     */

    private double armCode(double joystick) {
        double power;
        if (joystick < 0) {
            power = joystick * 0.1;
        } else {
            power = joystick * 0.5;
        }
        return power;
    }

    /**
     * The round method rounds the input to the place specified. Ex: tenths place is 0.1, hundredths
     * place is 0.01, etc.
     *
     * @param input The double you want to round
     * @param place The decimal place it should be rounded to, any multiple of 10 works
     * @return      Returns the rounded value
     */

    private double round(double input, double place) {
        double multiplier = 1 / place;
        return ((int) (input * multiplier)) / multiplier;
    }

    private double driverLeftX() {
        return round(gamepad1.left_stick_x, 0.1);
    }

    private double driverLeftY() {
        return round(gamepad1.left_stick_y, 0.1);
    }

    private double driverRightX() {
        return round(gamepad1.right_stick_x, 0.2);
    }
}