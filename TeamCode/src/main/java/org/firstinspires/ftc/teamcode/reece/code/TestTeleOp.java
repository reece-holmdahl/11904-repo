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
        if (heading() != 0) {
            speedCoeff = 0.25;
            if (heading() >= 180) {
                speedFR = 1;
                speedBL = 1;
                speedFL = 0;
                speedBR = 0;
            } else if (heading() < 180) {
                speedFL = 1;
                speedBR = 1;
                speedFR = 0;
                speedBL = 0;
            }
        } else if (heading() == 0) {
            speedCoeff = 0;
        }
        motorFL.setPower(speedFL * speedCoeff);
        motorFR.setPower(speedFR * speedCoeff);
        motorBL.setPower(speedBL * speedCoeff);
        motorBR.setPower(speedBR * speedCoeff);
    }

    public void stop() {
        telemetry.addLine("TeleOp Stopped");
        telemetry.addData("")
        speedCoeff = 0;
    }

}
