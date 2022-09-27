package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="Tele-Op")
public class teleOp extends OpMode {
    
    public DcMotor leftFrontDrive, rightFrontDrive, leftBackDrive, rightBackDrive;
    public double speed;
    //expansion hub 1 = arm1, port 2 = arm2
    //claw 1 (right) = 5, claw 2  (left) = 1;
    public void init() {
        //initializes the variables
        leftFrontDrive = hardwareMap.get(DcMotor.class, "front left");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "front right");
        leftBackDrive = hardwareMap.get(DcMotor.class, "back left");
        rightBackDrive = hardwareMap.get(DcMotor.class, "back right");
        speed = 0;
        //set direction for motors
        leftFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        rightFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        leftBackDrive.setDirection(DcMotor.Direction.FORWARD);
        rightBackDrive.setDirection(DcMotor.Direction.REVERSE);
    }

    public void loop() {
        //move
       speed=gamepad1.right_stick_y;
       move(-speed,speed);
       if(gamepad1.right_stick_x != 0){
           if (gamepad1.right_stick_x < 0) {
               move(speed / 2, speed);
           }else{
               move(speed, speed/2);
           }
       }
       



    }

    public void move(double powerLeft, double powerRight) {
        leftFrontDrive.setPower(powerLeft);
        leftBackDrive.setPower(powerLeft);
        rightFrontDrive.setPower(powerRight);
        rightBackDrive.setPower(powerRight);
    }
}

