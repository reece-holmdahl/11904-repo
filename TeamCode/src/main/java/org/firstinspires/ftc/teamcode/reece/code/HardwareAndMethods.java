package org.firstinspires.ftc.teamcode.reece.code;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;

/**
 * Created by Reece on 09/16/2017.
 */

public abstract class HardwareAndMethods extends OpMode {

    DcMotor frontLeft, backLeft, frontRight, backRight;                                                     //Define motors
    double speedFL, speedBL, speedFR, speedBR;                                                              //Create variable for independent wheel speeds
    BNO055IMU imu;                                                                                          //Define IMU
    Orientation ori;                                                                                        //Register orientation as manipulatable variable
    Acceleration accel;                                                                                     //Register acceleration as manipulatable variable

    public void init() {
        frontLeft = hardwareMap.get(DcMotor.class, "front left");                                           //Find front left motor in hardware config
        backLeft = hardwareMap.get(DcMotor.class, "back left");
        frontRight = hardwareMap.get(DcMotor.class, "front right");
        backRight = hardwareMap.get(DcMotor.class, "back right");
        frontLeft.setDirection(DcMotor.Direction.REVERSE);                                                  //Set motor direction of front left motor to reverse for AndyMark
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);

        imu = hardwareMap.get(BNO055IMU.class, "imu");                                                      //Find integrated IMU on hardware config
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();                                       //Set IMU parameters and return value units
        parameters.loggingEnabled = true;
        parameters.loggingTag = "IMU";
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        imu.initialize(parameters);                                                                         //Initialize and calibrate gyro
    }

    public void start() {
        imu.startAccelerationIntegration(new Position(), new Velocity(), 1000);                             //Start thread to find acceleration
    }

    public void loop() {
        ori = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZXY, AngleUnit.DEGREES);         //Update orientation variable in loop
        accel = imu.getLinearAcceleration();                                                                //Update acceleration variable in loop
        telemetry.addData("Heading", Float.toString(heading()));                                            //IMU telemetry for debugging and testing
        telemetry.addData("Pitch", Float.toString(pitch()));
        telemetry.addData("Roll", Float.toString(roll()));
        telemetry.addData("Acceleration X", Double.toString(accel.xAccel));
        telemetry.addData("Acceleration Y", Double.toString(accel.yAccel));
        telemetry.addData("Acceleration Z", Double.toString(accel.zAccel));
        telemetry.update();
    }

    public float heading() {                                                                                //Easier access to robot's orientation
        return ori.firstAngle;
    }

    public float pitch() {
        return ori.secondAngle;
    }

    public float roll() {
        return ori.thirdAngle;
    }
}
