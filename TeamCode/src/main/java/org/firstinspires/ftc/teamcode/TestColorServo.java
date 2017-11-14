package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

/**
 * Created by admin on 11/7/2017.
 */

@TeleOp(name = "TestColorServo")
public class TestColorServo extends OpMode {

    private Servo jewel         = null;
    private ColorSensor color   = null;
    private DistanceSensor dist = null;

    double sPos = 1.0;

    public void init() {

        jewel   = hardwareMap.get(Servo.class,          "jewel");
        color   = hardwareMap.get(ColorSensor.class,    "color");
        dist    = hardwareMap.get(DistanceSensor.class, "dist");

        color.enableLed(true);
    }

    public void loop() {

        if (sPos >= 0.525 && sPos <= 1) {
            sPos -= gamepad1.left_stick_y * 0.01;
        } else if (sPos < 0.525) {
            sPos = 0.525;
        } else if (sPos > 1) {
            sPos = 1;
        }
        jewel.setPosition(sPos);
        /*
        telemetry.addData("Color",      Integer.toString(color.getNormalizedColors().toColor()));
        telemetry.addData("Red",        Float.toString(color.getNormalizedColors().red * 256));
        telemetry.addData("Blue",       Float.toString(color.getNormalizedColors().blue * 256));
        telemetry.addData("Green",      Float.toString(color.getNormalizedColors().green * 256));
        telemetry.addData("Alpha",      Float.toString(color.getNormalizedColors().alpha * 256));
        */

        telemetry.addData("Red",    color.red());
        telemetry.addData("Blue",   color.blue());
        telemetry.addData("Green",  color.green());
        telemetry.addData("Alpha",  color.alpha());
        telemetry.addData("Dist",   dist.getDistance(DistanceUnit.MM));

        telemetry.addData("Servo Pos",  Double.toString(jewel.getPosition()));
        telemetry.update();
    }
}
