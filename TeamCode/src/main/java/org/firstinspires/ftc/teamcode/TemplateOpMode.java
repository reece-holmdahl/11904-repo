package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Reece on 09/15/2017.
 */

@TeleOp(name="Basic Op Mode", group="TeleOp")
@Disabled
public class TemplateOpMode extends OpMode {

    //Define hardware or other classes
    ElapsedTime runtime = new ElapsedTime();

    public void init() {
        /*
         * Code in init is run once after you hit init
         * This is usually only used to output some stats to telemetry or
         * activate any hardware like motors or sensors
         */
        hwMap();
    }

    public void init_loop() {
        /*
         * Code run within init loop is run constantly after you hit init
         * This may be to select your alliance, or even change your entire
         * autonomous program
         */
    }

    public void start() {
        /*
         * Code run within start will run once when you hit start
         * You usually want to reset variables in here, or start a timer
         * This is usually more useful for linear modes like autonomous
         */
        runtime.reset();
        telemetry.addLine("Match started");
        telemetry.addData("Time Elapsed", runtime.seconds());
    }

    public void loop() {
        /*
         * All code within loop is constantly run during the game
         * This is usually for TeleOp, and could constantly set the motor
         * speed to be equal to left and right stick y values
         */
    }

    public void stop() {
        /*
         * All code within stop will run once after you hit stop
         * This is usually used to either stop a thread, or shut off any
         * motors that are still running
         */
    }

    public void hwMap() {
        /*
         * Register all hardware on phones
         * Check that the set name is equal to the one on the phones
         * e.g. leftMotor = hardwareMap.get(DcMotor.class, "left motor");
         * This would look for leftMotor, and assign it as a DcMotor to
         * the motor called "left motor" on the phone
         */
    }
}
