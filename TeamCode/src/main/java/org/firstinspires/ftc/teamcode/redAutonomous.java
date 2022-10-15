package org.firstinspires.ftc.teamcode;



import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@Autonomous(name="redAutonomous")
public class redAutonomous extends LinearOpMode {

    public DcMotor frontLeftWheel, frontRightWheel, backLeftWheel, backRightWheel; //arm;
    public DistanceSensor frontDistance, leftDistance, backDistance, rightDistance;
    //public CRServo claw;


    public void runOpMode() {
        //move
        //initializes the variables
        frontLeftWheel = hardwareMap.get(DcMotor.class, "front left");
        frontRightWheel = hardwareMap.get(DcMotor.class, "front right");
        backLeftWheel = hardwareMap.get(DcMotor.class, "back left");
        backRightWheel = hardwareMap.get(DcMotor.class, "back right");
        frontDistance = hardwareMap.get(DistanceSensor.class, "distance front");
        leftDistance = hardwareMap.get(DistanceSensor.class, "distance left");
        backDistance = hardwareMap.get(DistanceSensor.class, "distance back");
        rightDistance = hardwareMap.get(DistanceSensor.class, "distance right");
        /*arm = hardwareMap.get(DcMotor.class, "arm motor");
        claw = hardwareMap.get(CRServo.class, "claw motor");

         */
        //set direction for motors
        //Old code: left is forward and right is backward
        //New code: right is forward and left is backwards
        frontLeftWheel.setDirection(DcMotor.Direction.FORWARD);
        frontRightWheel.setDirection(DcMotor.Direction.REVERSE);
        backLeftWheel.setDirection(DcMotor.Direction.FORWARD);
        backRightWheel.setDirection(DcMotor.Direction.REVERSE);
        /*arm.setDirection(DcMotorSimple.Direction.FORWARD);
        claw.setDirection(DcMotorSimple.Direction.FORWARD);
        */
        double front = frontDistance.getDistance(DistanceUnit.INCH);
        double left = leftDistance.getDistance(DistanceUnit.INCH);
        double back = backDistance.getDistance(DistanceUnit.INCH);
        double right = rightDistance.getDistance(DistanceUnit.INCH);
        for (int i = 0; i < 10; i++) {
            Log.i(null, front+"");
            Log.i(null, front+"");
            Log.i(null, front+"");
            Log.i(null, front+"");
            sleep(30000);
        }




    }

    public void move(double[] wheels) {
        frontLeftWheel.setPower(wheels[1]);
        backLeftWheel.setPower(wheels[2]);
        frontRightWheel.setPower(wheels[0]);
        backRightWheel.setPower(wheels[3]);
    }
}

