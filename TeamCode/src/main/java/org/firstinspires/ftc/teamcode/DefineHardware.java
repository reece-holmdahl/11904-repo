package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Reece on 09/16/2017.
 */

public abstract class DefineHardware extends OpMode {

    //Define drive train objects
    public DcMotor frontLeft   = null;
    public DcMotor backLeft    = null;
    public DcMotor frontRight  = null;
    public DcMotor backRight   = null;
    public Servo jewel         = null;
    public DcMotor[] drive     = {frontLeft, backLeft, frontRight, backRight};

    //Define relic manipulator objects
    public DcMotor slide   = null;
    public Servo   gripper = null;

    //Array full of all motors
    public DcMotor[] motors = {frontLeft, backLeft, frontRight, backRight, slide};

    /**
     * The init method is run once when the init phase is active on the robot controller. This
     * method is used for hardware mapping hardware devices and setting parameters for the devices.
     */

    @Override
    public void init() {
        //Hardware map drive train objects
        frontLeft   = hardwareMap.get(DcMotor.class,  "front left");
        backLeft    = hardwareMap.get(DcMotor.class,  "back left");
        frontRight  = hardwareMap.get(DcMotor.class,  "front right");
        backRight   = hardwareMap.get(DcMotor.class,  "back right");
        jewel       = hardwareMap.get(Servo.class,    "jewel");

        //Hardware map relic manipulator objects
        //slide   = hardwareMap.get(DcMotor.class,  "slide");
        //gripper = hardwareMap.get(Servo.class,    "gripper");

        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        backLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.FORWARD);
        //slide.setDirection(DcMotor.Direction.FORWARD);
    }

    /**
     * The stop method is ran once when the robot controller ends the op mode. It is usually used to
     * turn off motors or other moving devices so the robot doesn't accidentally go haywire.
     */

    @Override
    public void stop() {
        frontLeft.setPower(0);
        backLeft.setPower(0);
        frontRight.setPower(0);
        backRight.setPower(0);
        //slide.setPower(0);
    }
}