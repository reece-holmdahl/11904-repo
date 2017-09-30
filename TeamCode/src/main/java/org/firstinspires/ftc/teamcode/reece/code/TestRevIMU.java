package org.firstinspires.ftc.teamcode.reece.code;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;

/**
 * Created by Reece on 09/28/2017.
 */

@TeleOp(name = "Test Rev IMU", group = "TeleOp")
@Disabled
public class TestRevIMU extends OpMode {

    DcMotor motor;
    BNO055IMU imu;
    Orientation ori;
    Acceleration accel;

    public void init() {
        motor = hardwareMap.get(DcMotor.class, "motor");
        imu = hardwareMap.get(BNO055IMU.class, "imu");
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.loggingEnabled = true;
        parameters.loggingTag = "IMU";
        parameters.calibrationDataFile = "BNO055IMUCalibration.json";
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();
        imu.initialize(parameters);
    }

    public void start() {
        imu.startAccelerationIntegration(new Position(), new Velocity(), 1000);
    }

    public void loop() {
        ori = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZXY, AngleUnit.DEGREES);
        accel = imu.getLinearAcceleration();
        telemetry.addData("Heading", Float.toString(ori.firstAngle));
        telemetry.addData("Pitch", Float.toString(ori.secondAngle));
        telemetry.addData("Roll", Float.toString(ori.thirdAngle));
        telemetry.addData("Acceleration X", Double.toString(accel.xAccel));
        telemetry.addData("Acceleration Y", Double.toString(accel.yAccel));
        telemetry.addData("Acceleration Z", Double.toString(accel.zAccel));
        telemetry.update();
        if (ori.firstAngle > 60 || ori.firstAngle < -60) {
            motor.setPower(1);
        } else {
            motor.setPower(0);
        }
    }

    public void stop() {
        motor.setPower(0);
    }
}
