package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="Tele-Op")
public class teleOp extends OpMode {
    
    public DcMotor leftFrontDrive, rightFrontDrive, leftBackDrive, rightBackDrive, armMotor1, armMotor2, carouselSpinner;
    public double speed, armspeed;
    public CRServo claw1, claw2;
    //expansion hub 1 = arm1, port 2 = arm2
    //claw 1 (right) = 5, claw 2  (left) = 1;
    public void init() {
        //initializes the variables
        leftFrontDrive = hardwareMap.get(DcMotor.class, "front left");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "front right");
        leftBackDrive = hardwareMap.get(DcMotor.class, "back left");
        rightBackDrive = hardwareMap.get(DcMotor.class, "back right");

        armMotor1 = hardwareMap.get(DcMotor.class, "arm1");
        armMotor2 = hardwareMap.get(DcMotor.class, "arm2");
        claw1 = hardwareMap.get(CRServo.class, "claw1");
        claw2 = hardwareMap.get(CRServo.class, "claw2");
        carouselSpinner = hardwareMap.get(DcMotor.class, "carouselSpinner");

        speed = 0.8;
        armspeed = 0.8;

        //set direction for motors
        leftFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        rightFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        leftBackDrive.setDirection(DcMotor.Direction.FORWARD);
        rightBackDrive.setDirection(DcMotor.Direction.REVERSE);
    }

    public void loop() {
        //move
        if (gamepad1.dpad_right) {
            move(speed, -speed);
        } else if (gamepad1.dpad_left) {
            move(-speed, speed);
        } else if (gamepad1.dpad_down) {
            move(-speed, -speed);
        } else if (gamepad1.dpad_up) {
            move(speed, speed);
        } else {
            move(0, 0);
        }
        if (gamepad1.left_stick_x > 0) {
            move(gamepad1.left_stick_x, -gamepad1.left_stick_x);
        } else if (gamepad1.left_stick_x < 0) {
            move(-gamepad1.left_stick_x,gamepad1.left_stick_x);
        } else if (gamepad1.left_stick_y > 0) {
            move(-gamepad1.left_stick_y, -gamepad1.left_stick_y);
        } else if (gamepad1.left_stick_y < 0) {
            move(gamepad1.left_stick_y, gamepad1.left_stick_y);
        } else {
            move(0, 0);
        }

        //carousel spinner
        if (gamepad1.right_bumper) {
            carouselSpinner.setPower(-0.2);
        } else if (gamepad1.left_bumper) {
            carouselSpinner.setPower(.225);
        } else {
            carouselSpinner.setPower(0);
        }


        //Arm
        if (gamepad1.y) {
            //lift arm
            armMotor1.setPower(-armspeed);
            armMotor2.setPower(armspeed);

        } else if (gamepad1.a) {
            //drop arm
            armMotor1.setPower(armspeed);
            armMotor2.setPower(-armspeed);
        } else {
            armMotor1.setPower(0);
            armMotor2.setPower(0);
        }

        if (gamepad1.left_bumper) {
            speed = 0.4;
        } else {
            speed = 0.8;
        }
        //emergency button!!
        if (gamepad1.right_bumper && gamepad1.left_bumper) {
            move(.6,.6);
            armMotor1.setPower(-armspeed);
            armMotor2.setPower(armspeed);
        } else {
            move(0, 0);
            armMotor1.setPower(0);
            armMotor2.setPower(0);
        }

        //claw
        if (gamepad1.x) {
            claw1.setPower(1);
            claw2.setPower(-1);
        } else if (gamepad1.b) {
            claw1.setPower(-1);
            claw2.setPower(1);
        } else {
            claw1.setPower(0);
            claw2.setPower(0);
        }

    }

    public void move(double powerLeft, double powerRight) {
        leftFrontDrive.setPower(powerLeft);
        leftBackDrive.setPower(powerLeft);
        rightFrontDrive.setPower(powerRight);
        rightBackDrive.setPower(powerRight);
    }
}

