package org.firstinspires.ftc.teamcode;

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

    //Define hardware or other classes
    DcMotor lMotor, rMotor;
    ElapsedTime runtime = new ElapsedTime();

    public void init() {
        hwMap();
    }

    public void init_loop() {
        /*
         * Test gamepad responsiveness in init loop
         * Will most likely be used in autonomous to select alliance
         */
        if (gamepad1.a) {
            telemetry.addLine("You pressed A");
        } if (gamepad1.b) {
            telemetry.addLine("You pressed B");
        }
    }

    public void start() {
        runtime.reset();
        telemetry.addLine("Match started");
        telemetry.addData("Time Elapsed", runtime.seconds());
    }

    public void loop() {
        /*
         * Tank controls setup
         * Made for a robot with a generic wheel setup
         */
        lMotor.setPower(gamepad1.left_stick_y);
        rMotor.setPower(gamepad1.right_stick_y);
    }

    public void stop() {

    }

    public void hwMap() {
        /*
         * Register motors as left and right motor on phone
         * Set direction of motors to forward
         */
        lMotor = hardwareMap.get(DcMotor.class, "left motor");
        rMotor = hardwareMap.get(DcMotor.class, "right motor");
        lMotor.setDirection(DcMotor.Direction.FORWARD);
        rMotor.setDirection(DcMotor.Direction.FORWARD);
    }
}
