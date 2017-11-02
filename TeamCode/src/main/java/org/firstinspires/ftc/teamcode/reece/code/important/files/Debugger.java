package org.firstinspires.ftc.teamcode.reece.code.important.files;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Reece on 11/01/2017.
 */

@TeleOp(name = "Debugger", group = "EXP")
public class Debugger extends OpMode {

    DcMotor frontLeft, backLeft, frontRight, backRight, lowerArm, upperArm;
    Servo leftClaw, rightClaw;

    DcMotor[] motors = {frontLeft, backLeft, frontRight, backRight, lowerArm, upperArm};
    Servo[] servos = {leftClaw, rightClaw};

    public void init() {
        //New method to hardware map objects in bulk, pretty fancy

        //Motors
        hwMap(motors, DcMotor.class);

        //Servos
        hwMap(servos, Servo.class);
    }

    public void loop() {
        testMotors(motors);
        testServos(servos);
    }

    public void stop() {
        int x = 0;
        int y = 0;
        while (x < greatestInt(motors.length, servos.length)[0]) {
            motors[x].setPower(0);
            x++;
            if (y < greatestInt(motors.length, servos.length)[1]) {
                servos[y].setPosition(0);
                y++;
            }
        }
    }

    private void hwMap(Object[] hwVar, Class hwType) {
        int i = 0;
        int length = hwVar.length;
        while (i < length) {
            hwVar[i] = hardwareMap.get(hwType, hwVar[i].toString().toLowerCase());
            i++;
        }
    }

    private void testMotors(DcMotor[] devices) {
        int i = 0;
        int length = devices.length;
        while (i < length) {
            devices[i].setPower(1);
            i++;
        }
    }

    private void testServos(Servo[] devices) {
        int i = 0;
        int length = devices.length;
        while (i < length) {
            devices[i].setPosition(1.0);
            i++;
        }
    }

    private int[] greatestInt(int a, int b) {
        int[] array = {};
        if (a > b) {
            array[0] = a;
            array[1] = b;
        } else {
            array[0] = b;
            array[1] = a;
        }
        return array;
    }
}
