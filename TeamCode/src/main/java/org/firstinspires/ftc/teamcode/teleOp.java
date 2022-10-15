package org.firstinspires.ftc.teamcode;



import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name="Tele-Op")
public class teleOp extends OpMode {
    
    public DcMotor frontLeftWheel, frontRightWheel, backLeftWheel, backRightWheel; //arm;
    //public CRServo claw;
    public void init() {
        //initializes the variables
        frontLeftWheel = hardwareMap.get(DcMotor.class, "front left");
        frontRightWheel = hardwareMap.get(DcMotor.class, "front right");
        backLeftWheel = hardwareMap.get(DcMotor.class, "back left");
        backRightWheel = hardwareMap.get(DcMotor.class, "back right");
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
    }

    public void loop() {
        //move
        double x = gamepad1.left_stick_x;
        double y = -gamepad1.left_stick_y;
        double turn = 0.0;
        double theta = Math.atan2(y, x);
        double power = Math.hypot(x,y);
        double sin = Math.sin(theta - Math.PI/4);
        double cos = Math.cos(theta - Math.PI/4);
        double max = Math.max(Math.abs(sin), Math.abs(cos));
        double[] wheels = new double[4];
        /*double armSpeed = 1.0;
        double clawSpeed = 1.0;
         */
        if(gamepad1.left_bumper){
            turn = -0.5;
        }else if(gamepad1.right_bumper){
            turn = 0.5;
        }

        for (int i = 0; i < wheels.length; i++) {
            if(i%2 == 0){
                wheels[i] = (power * (sin/max));
                if(i == 0){
                    wheels[i] -= turn;
                }else{
                    wheels[i] += turn;
                }
            }else{
                wheels[i] = (power * (cos/max));
                if(i == 3){
                    wheels[i] -= turn;
                }else{
                    wheels[i] += turn;
                }
            }
            if((power + Math.abs(turn))>1){
                wheels[i] /= power+turn;
            }

        }
        move(wheels);
        /* if(!(gamepad1.right_trigger > 0 && gamepad1.left_trigger > 0)) {
           if(gamepad1.right_trigger > 0){
               arm.setPower(armSpeed);
           }else if (gamepad1.left_trigger > 0){
               arm.setPower(-armSpeed);
           }
        }
        if(gamepad1.x){
            claw.setPower(clawSpeed);
            clawSpeed = -clawSpeed;
        }
         */
    }

    public void move(double[] wheels) {
        frontLeftWheel.setPower(wheels[1]);
        backLeftWheel.setPower(wheels[2]);
        frontRightWheel.setPower(wheels[0]);
        backRightWheel.setPower(wheels[3]);
    }
}

