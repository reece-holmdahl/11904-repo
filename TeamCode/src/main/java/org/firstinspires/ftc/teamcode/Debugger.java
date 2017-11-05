package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Debugger")
public class Debugger extends OpMode {

    //Speed to run motors at
    double speed = 0.2;

    //Servo reset position
    double jewelReset = 1.0;

    //Define motor objects
    DcMotor frontLeft   = null;
    DcMotor backLeft    = null;
    DcMotor frontRight  = null;
    DcMotor backRight   = null;

    //Define servo objects
    Servo jewel = null;

    public void init() {
        //Map motors
        frontLeft   = hardwareMap.get(DcMotor.class,    "front left");
        backLeft    = hardwareMap.get(DcMotor.class,    "back left");
        frontRight  = hardwareMap.get(DcMotor.class,    "front right");
        backRight   = hardwareMap.get(DcMotor.class,    "back right");

        //Map servos
        jewel = hardwareMap.get(Servo.class, "jewel");
    }

    public void loop() {
        //Run motors
        frontLeft.setPower(speed);
        backLeft.setPower(speed);
        frontRight.setPower(speed);
        backRight.setPower(speed);

        //Move servos
        jewel.setPosition(0.5);
    }

    public void stop() {
        //Stop motors
        frontLeft.setPower(0);
        backLeft.setPower(0);
        frontRight.setPower(0);
        backRight.setPower(0);

        //Reset servos
        jewel.setPosition(jewelReset);
    }
}
