package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Reece on 09/15/2017.
 */

@TeleOp(name="Test Op Mode", group="TeleOp")
//@Disabled
public class TestOpMode extends OpMode {

    // Declare DcMotors
    DcMotor lMotor, rMotor;
    ElapsedTime runtime = new ElapsedTime();

    public void init() {
        hwMap();
    }

    public void init_loop() {

    }

    public void start() {
        runtime.reset();
        telemetry.addLine("Match started");
        telemetry.addData("Time Elapsed", runtime.seconds());
    }

    public void loop() {
        lMotor.setPower(gamepad1.left_stick_y);
        rMotor.setPower(gamepad1.right_stick_y);
    }

    public void stop() {

    }

    public void hwMap() {
        lMotor = hardwareMap.get(DcMotor.class, "left motor");
        rMotor = hardwareMap.get(DcMotor.class, "right motor");
    }
}
