package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * This is the final holonomic TeleOp file that will be used at the tournament, it incorporates
 * some designs used in my TeleOp concepts but also uses more proven and effective ways.
 * Created by Reece on 11/04/2017.
 */

@TeleOp(name = "FinalHolonomic")
public class FinalHolonomic extends OpMode {

    //Speed control coefficients
    private double speed    = 0.4;
    private double turn     = 0.2;

    //Servo claw variables
    private double leftClawPosition     = 0;
    private double rightClawPosition    = 1;
    private double clawSpeed            = 0.02;

    //Relic clamp and pivot variables
    private double clampPos = 0;
    private double pivotPos = 0;

    //Relic slide motor encoder value variables
    private int slideEncMax = 0;
    private int slideEncVal = 0;

    //Variable names used for cleaner code
    private int front   = 1;
    private int back    = -1;
    private int left    = -1;
    private int right   = 1;

    //Define drive train objects
    private DcMotor frontLeft   = null;
    private DcMotor backLeft    = null;
    private DcMotor frontRight  = null;
    private DcMotor backRight   = null;
    private Servo   jewel       = null;

    //Define glyph manipulator objects
    private DcMotor arm         = null;
    private Servo   leftClaw    = null;
    private Servo   rightClaw   = null;

    //Define relic manipulator objects
    private DcMotor slide = null;
    private Servo   clamp = null;
    private Servo   pivot = null;

    /**
     * The init method is run once when the init phase is active on the robot controller. This
     * method is used for hardware mapping hardware devices and setting parameters for the devices.
     */

    public void init() {

        //Hardware map drive train objects
        frontLeft   = hardwareMap.get(DcMotor.class,  "front left");
        backLeft    = hardwareMap.get(DcMotor.class,  "back left");
        frontRight  = hardwareMap.get(DcMotor.class,  "front right");
        backRight   = hardwareMap.get(DcMotor.class,  "back right");
        jewel       = hardwareMap.get(Servo.class,    "jewel");

        //Hardware map relic manipulator objects
        arm       = hardwareMap.get(DcMotor.class,    "arm");
        leftClaw  = hardwareMap.get(Servo.class,      "leftClaw");
        rightClaw = hardwareMap.get(Servo.class,      "rightClaw");

        //Hardware map relic manipulator objects
        slide = hardwareMap.get(DcMotor.class,  "slide");
        clamp = hardwareMap.get(Servo.class,    "clamp");
        pivot = hardwareMap.get(Servo.class,    "pivot");

        //Set direction of drive train motors
        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        backLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.FORWARD);

        //Set motor parameters of arm motor
        arm.setDirection(DcMotor.Direction.REVERSE);
        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //Set motor parameters of linear slide motor
        slide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slide.setDirection(DcMotor.Direction.REVERSE);
        slide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //Set servo parameters of jewel
        //jewel.scaleRange(0.5, 1.0);
        //jewel.setDirection(Servo.Direction.REVERSE);

        //Set servo parameters of claws
        leftClaw.setDirection(Servo.Direction.FORWARD);
        rightClaw.setDirection(Servo.Direction.FORWARD);

        //Set start position of servo claws
        leftClaw.setPosition(0.3);
        rightClaw.setPosition(0.7);
    }

    /**
     * The loop method is looped when the robot controller is in the start phase. It is used for the
     * core functionality of the robot like movement with joysticks or other means.
     */

    public void loop() {

        //Update left and right claw positions
        leftClawPosition    +=  manipulatorRightX() * clawSpeed;
        rightClawPosition   -=  manipulatorRightX() * clawSpeed;

        //Check to see if leftClawPosition goes over 1 or under 0
        if (leftClawPosition > 1)
            leftClawPosition = 1;
        if (leftClawPosition < 0)
            leftClawPosition = 0;

        //Check to see if rightClawPosition goes over 1 or under 0
        if (rightClawPosition > 1)
            rightClawPosition = 1;
        if (rightClawPosition < 0)
            rightClawPosition = 0;

        //Update slide encoder value
        slideEncVal = slide.getCurrentPosition();

        //Send drive train motor speeds to telemetry
        telemetry.addData("Speed FL", Double.toString(frontLeft.getPower()));
        telemetry.addData("Speed BL", Double.toString(backLeft.getPower()));
        telemetry.addData("Speed FR", Double.toString(frontRight.getPower()));
        telemetry.addData("Speed BR", Double.toString(backRight.getPower()));

        //Send gamepad1 joystick positions to telemetry
        telemetry.addData("Driver Left X", Double.toString(driverLeftX()));
        telemetry.addData("Driver Left Y", Double.toString(driverLeftY()));
        telemetry.addData("Driver Right X", Double.toString(driverRightX()));

        //Send arm motor speed to telemetry
        telemetry.addData("Speed Arm",  Double.toString(arm.getPower()));

        //Send gamepad2 joystick positions to telemetry
        telemetry.addData("Manipulator Left X", Double.toString(manipulatorLeftX()));
        telemetry.addData("Manipulator Left Y", Double.toString(manipulatorLeftY()));

        //Send glyph manipulator servo positions to telemetry
        telemetry.addData("Left Claw Position",     Double.toString(leftClaw.getPosition()));
        telemetry.addData("Right Claw Position",    Double.toString(rightClaw.getPosition()));

        //Send slide encoder value to telemetry
        telemetry.addData("Slide Encoder",  Integer.toString(slideEncVal));

        //Send relic manipulator servo positions to telemetry
        telemetry.addData("Clamp Position", Double.toString(clamp.getPosition()));
        telemetry.addData("Pivot Position", Double.toString(pivot.getPosition()));

        //Update telemetry
        telemetry.update();

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
        jewel.setPosition(1.0);

        //Use armCode method to set power to arm motor based so it isn't too fast
        arm.setPower(armCode(manipulatorLeftY()));

        //Use clawCode method to set position of claw and keep values within range
        leftClaw.setPosition(leftClawPosition);
        rightClaw.setPosition(rightClawPosition);
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
        leftClaw.setPosition(0.3);
        rightClaw.setPosition(0.7);
        //clamp.setPosition(0);
        //pivot.setPosition(0);
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
        double move = side * driverLeftX() + end * driverLeftY();
        double turn = driverRightX();
        return Range.clip(move * speed + turn * this.turn, -1, 1);
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

    private double manipulatorLeftX() {
        return round(gamepad2.left_stick_x, 0.05);
    }

    private double manipulatorLeftY() {
            return round(gamepad2.left_stick_y, 0.05);
        }

    private double manipulatorRightX() {
            return round(gamepad2.right_stick_x, 0.01);
        }
}