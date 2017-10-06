package org.firstinspires.ftc.teamcode.reece.code;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

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

public abstract class DefineHardware extends OpMode {

    public DcMotor frontLeft, backLeft, frontRight, backRight;                                              //Define motors
    public double speedFL, speedBL, speedFR, speedBR;                                                       //Create variable for independent wheel speeds
    public BNO055IMU imu;                                                                                   //Define IMU
    public Orientation ori;                                                                                 //Register orientation as manipulatable variable
    public Acceleration accel;                                                                              //Register acceleration as manipulatable variable

    @Override                                                                                               //Override any other initialization methods so this file can map hardware
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

    @Override                                                                                               //Override other instances of this method for now to stop motors, more params may be needed in future
    public void stop() {
        allMotorPower(0);
    }

    public void imuDataCons() {                                                                             //IMU telemetry for debugging and testing
        telemetry.addData("Heading", Float.toString(heading()));
        telemetry.addData("Pitch", Float.toString(pitch()));
        telemetry.addData("Roll", Float.toString(roll()));
        telemetry.addData("Acceleration X", Double.toString(accel.xAccel));
        telemetry.addData("Acceleration Y", Double.toString(accel.yAccel));
        telemetry.addData("Acceleration Z", Double.toString(accel.zAccel));
        telemetry.update();
    }

    public void allMotorPower(double power) {                                                               //Method to set all motors to desired power
        frontLeft.setPower(power);
        backLeft.setPower(power);
        frontRight.setPower(power);
        backRight.setPower(power);
    }

    public void startIMUThread() {                                                                          //Starts IMU Thread to track acceleration and angular orientation
        imu.startAccelerationIntegration(new Position(), new Velocity(), 100);
    }

    public void updateIMUValues() {                                                                         //Put this method in the loop if you are going to use the IMU
        ori = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZXY, AngleUnit.DEGREES);
        accel = imu.getLinearAcceleration();
    }

    /*
     * The methods below are just ease of access to the angular orientation part of the IMU
     */
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
