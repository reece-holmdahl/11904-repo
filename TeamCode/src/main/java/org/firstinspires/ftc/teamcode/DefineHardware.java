package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Reece on 09/16/2017.
 */

public abstract class DefineHardware extends OpMode {

    //Link HardwareMap object
    private HardwareMap hwMap   = null;

    //Rename gamepads for easier use
    public Gamepad driver      = gamepad1;
    public Gamepad manipulator = gamepad2;

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
        frontLeft   = hwMap.get(DcMotor.class,  "front left");
        backLeft    = hwMap.get(DcMotor.class,  "back left");
        frontRight  = hwMap.get(DcMotor.class,  "front right");
        backRight   = hwMap.get(DcMotor.class,  "back right");
        jewel       = hwMap.get(Servo.class,    "jewel");

        //Hardware map relic manipulator objects
        //slide   = hwMap.get(DcMotor.class,  "slide");
        //gripper = hwMap.get(Servo.class,    "gripper");

        for (int i = 0; i < drive.length; i++) {
            drive[i].setDirection(DcMotor.Direction.FORWARD);
            //drive[i] = hwMap.get(DcMotor.class, drive[i].toString().toLowerCase());                         //Potential way to map an array of DcMotors
        }
    }

    /**
     * The stop method is ran once when the robot controller ends the op mode. It is usually used to
     * turn off motors or other moving devices so the robot doesn't accidentally go haywire.
     */

    @Override
    public void stop() {
        for (int i = 0; i < motors.length; i++) {
            motors[i].setPower(0);
        }
    }
}