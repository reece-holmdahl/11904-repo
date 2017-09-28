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

/**
 * Created by Reece on 09/16/2017.
 */

public abstract class HardwareAndMethods extends OpMode {

    //Define motors, sensors, and variables
    DcMotor motorFL, motorFR, motorBL, motorBR;
    double speedCoeff, speedAll, speedFL, speedFR, speedBL, speedBR;
    BNO055IMU gyro;
    Orientation ori;
    Acceleration accel;

    public void init() {
        //Map motors and set direction
        motorFL = hardwareMap.get(DcMotor.class, "motorFL");
        motorFR = hardwareMap.get(DcMotor.class, "motorFR");
        motorBL = hardwareMap.get(DcMotor.class, "motorRL");
        motorBR = hardwareMap.get(DcMotor.class, "motorRR");
        motorFL.setDirection(DcMotor.Direction.REVERSE);
        motorFR.setDirection(DcMotor.Direction.REVERSE);
        motorBL.setDirection(DcMotor.Direction.REVERSE);
        motorBR.setDirection(DcMotor.Direction.REVERSE);

        //Gyro setup
        gyro = hardwareMap.get(BNO055IMU.class, "rev gyro");
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.loggingEnabled = true;
        parameters.loggingTag = "GYRO";
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        gyro.initialize(parameters);
        ori = gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        accel = gyro.getLinearAcceleration();
    }

    public float heading() {
        return ori.firstAngle;
    }

    public double acceleration() {
        return accel.yAccel;
    }

}
