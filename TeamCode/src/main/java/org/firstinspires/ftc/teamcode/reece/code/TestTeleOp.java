package org.firstinspires.ftc.teamcode.reece.code;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by Reece on 09/16/2017.
 */
@TeleOp(name="Test TeleOp", group="TeleOp")
public class TestTeleOp extends HardwareAndMethods {

    public void init() {

    }

    public void init_loop() {

    }

    public void start() {
        speedCoeff = 0.25f;
    }

    public void loop() {
        telemetry.addData("Heading", Float.toString(heading()));
        telemetry.addData("Pitch", Float.toString(ori.thirdAngle));
        telemetry.addData("Roll", Float.toString(ori.secondAngle));
        telemetry.update();
    }

    public void stop() {
        telemetry.addLine("TeleOp Stopped");
        speedCoeff = 0;
    }

}
