package org.firstinspires.ftc.teamcode.TeleOp.concepts;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import java.util.HashMap;

/**
 * Created by Reece on 11/02/2017.
 */

@TeleOp(name = "BulkArrayMapping")
public class BulkArrayMapping extends OpMode {

    //Assigning variables to meaningful names for methods
    private int front = 1;
    private int back = -1;
    private int left = 1;
    private int right = -1;

    //Redefine gamepads
    private Gamepad driver = gamepad1;
    private Gamepad manipulator = gamepad2;

    //Drive train objects
    private DcMotor frontLeft, backLeft, frontRight, backRight;
    private Servo jewel;
    private BNO055IMU imu;
    private ColorSensor color;

    //Glyph manipulator objects
    private DcMotor armBot, armTop;
    private Servo clawLeft, clawRight;

    //Relic manipulator objects
    private DcMotor slide;
    private Servo gripper;

    //Arrays of hardware sectioned off by type of device
    private DcMotor[] motors = {frontLeft, backLeft, frontRight, backRight, armBot, armTop, slide};
    private Servo[] servos = {jewel, clawLeft, clawRight, gripper};
    private Object[] sensors = {imu, color};

    //Arrays of hardware divided by sub-team
    private DcMotor[] driveMotors = {frontLeft, backLeft, frontRight, backRight};
    private DcMotor[] armMotors = {armBot, armTop};

    //HashMap to find class of object name stored in hardware arrays
    private HashMap<String, Class> getClass = new HashMap<String, Class>() {{
        //Add all motors to HashMap
        for (int i = 0; i < motors.length; i++) {
            put(motors[i].toString(), DcMotor.class);
        }

        //Add all servos to HashMap
        for (int i = 0; i < servos.length; i++) {
            put(servos[i].toString(), Servo.class);
        }

        //Add sensors to HashMap
        put("imu", BNO055IMU.class);
        put("color", ColorSensor.class);
    }};

    public void init() {
        //Hardware map devices
        hwMap(motors);
        hwMap(servos);
        hwMap(sensors);

        //Set motor directions
        motorDirec(driveMotors, DcMotorSimple.Direction.FORWARD);
        motorDirec(armMotors, DcMotorSimple.Direction.FORWARD);

        //Set motor behaviors
        motorBehav(armMotors, DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void loop() {
        //Set motor's power with driveCode
        frontLeft.setPower(driveCode(front, left));
        backLeft.setPower(driveCode(back, left));
        frontRight.setPower(driveCode(front, right));
        backRight.setPower(driveCode(back, right));

        //Stop motor drift on drive motors
        if (driver.left_stick_x + driver.left_stick_y == 0) {
            motorBehav(driveMotors, DcMotor.ZeroPowerBehavior.BRAKE);
        } else {
            motorBehav(driveMotors, DcMotor.ZeroPowerBehavior.FLOAT);
        }

        motorPower(armMotors, manipulator.left_stick_y);
    }

    public void stop() {
        //Stop motors
        motorPower(motors, 0);
    }

    private void hwMap(Object[] devices) {
        for (int i = 0; i < devices.length; i++) {
            devices[i] = hardwareMap.get(getClass.get(devices[i].toString()), devices[i].toString().toLowerCase());
        }
    }

    private double driveCode(int side, int end) {
        double speedCoeff = 0.6;
        double turnCoeff = 0.3;
        double strafe = (end * driver.left_stick_x + side * driver.left_stick_y) * speedCoeff;
        double turn = driver.right_stick_x * turnCoeff;
        return Range.clip(strafe + turn, -1, 1);
    }

    private void motorPower(DcMotor[] motors, double speed) {
        for (int i = 0; i < motors.length; i++) {
            motors[i].setPower(speed);
        }
    }

    private void motorDirec(DcMotor[] motors, DcMotor.Direction direction) {
        for (int i = 0; i < motors.length; i++) {
            motors[i].setDirection(direction);
        }
    }

    private void motorBehav(DcMotor[] motors, DcMotor.ZeroPowerBehavior behavior) {
        for (int i = 0; i < motors.length; i++) {
            motors[i].setZeroPowerBehavior(behavior);
        }
    }
}
