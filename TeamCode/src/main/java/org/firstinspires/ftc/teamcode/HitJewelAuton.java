package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name = "Autonomous")
public class HitJewelAuton extends LinearOpMode {

    //Speed control coefficients
    private double speed    = 0.4;
    private double turn     = 0.2;

    //Define drive train objects
    private DcMotor frontLeft   = null;
    private DcMotor backLeft    = null;
    private DcMotor frontRight  = null;
    private DcMotor backRight   = null;

    //Jewel manipulator objects
    private Servo       jewel   = null;
    private ColorSensor color   = null;

    public void runOpMode() {

        //Hardware map drive train objects
        frontLeft   = hardwareMap.get(DcMotor.class,  "front left");
        backLeft    = hardwareMap.get(DcMotor.class,  "back left");
        frontRight  = hardwareMap.get(DcMotor.class,  "front right");
        backRight   = hardwareMap.get(DcMotor.class,  "back right");

        //Hardware map jewel manipulator objects
        jewel = hardwareMap.get(Servo.class,        "jewel");
        color = hardwareMap.get(ColorSensor.class,  "color");

        //Set direction of drive train motors
        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        backLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.FORWARD);

        //Enable color sensor LED
        color.enableLed(true);

        //Wait for driver to leave init phase
        waitForStart();

        //Send jewel servo down to jewel level
        jewel.setPosition(0.4);                                                                             //Servo position is subject to change
    }
}