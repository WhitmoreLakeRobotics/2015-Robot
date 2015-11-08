package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoController;

/**
 * Created by techclub on 9/12/15.
 */
public class operatormode2 extends OpMode {
    private DcMotorController dc_drive_controller;
    private ServoController servoController;
    private DcMotor dc_drive_left;
    private Servo bucket_servo;
    private Servo left_slide;
    private Servo right_slide;
    private DcMotor dc_drive_right;
    private DcMotor dc_4link;
    private TwoMotorDrive rightWheel;
    private TwoMotorDrive leftWheel;
    private int fourLinkUpper = 1920;
    private int fourLinkLower = 0;
    private DcMotor dc_drive_right2;
    private DcMotor dc_drive_left2;
    private DcMotor dc_sweeper;

    @Override
    public void init() {
        dc_drive_controller = hardwareMap.dcMotorController.get("drive_controller");
        servoController = hardwareMap.servoController.get("servoController");
        bucket_servo = hardwareMap.servo.get("bucket_servo");
        left_slide = hardwareMap.servo.get("left_slide");
        right_slide = hardwareMap.servo.get("right_slide");
        dc_drive_left = hardwareMap.dcMotor.get("left_drive");
        dc_4link = hardwareMap.dcMotor.get("4link");
        dc_drive_right = hardwareMap.dcMotor.get("right_drive");
        dc_drive_right2 = hardwareMap.dcMotor.get("right_drive2");
        dc_drive_left2 = hardwareMap.dcMotor.get("left_drive2");
        dc_sweeper = hardwareMap.dcMotor.get("sweeper");
        dc_drive_left2.setDirection(DcMotor.Direction.REVERSE);
        dc_drive_left.setDirection(DcMotor.Direction.REVERSE);
        leftWheel = new TwoMotorDrive(dc_drive_left, dc_drive_left2);
        rightWheel = new  TwoMotorDrive(dc_drive_right, dc_drive_right2);
        dc_4link.setChannelMode
                ( DcMotorController.RunMode.RUN_USING_ENCODERS
                );

    }

    @Override
    public void loop() {
        if (gamepad2.a && bucket_servo.getPosition()!=0.5) { //sets bucket to normal with a button
            bucket_servo.setPosition(0.5);
        }
        if (gamepad2.a && bucket_servo.getPosition()!=0.25) { //sets bucket to dump left with a button
            bucket_servo.setPosition(0.25);

        }

        if (gamepad2.y && bucket_servo.getPosition()!=0.5) { //sets bucket to normal with y button
            bucket_servo.setPosition(0.5);
        }
        if (gamepad2.y && bucket_servo.getPosition()!=0.75) { //sets bucket to dump right with y button
            bucket_servo.setPosition(0.75);

        }

        if (gamepad2.x && left_slide.getPosition()!=0.0) { //sets left slide to in with x button
            left_slide.setPosition(0.0);
        }
        if (gamepad2.x && left_slide.getPosition()!=0.3) { //sets left slide to out with x button
            left_slide.setPosition(0.3);

        }

        if (gamepad2.b && right_slide.getPosition()!=0.0) { //sets right slide to in with b button
            right_slide.setPosition(0.0);
        }
        if (gamepad2.b && right_slide.getPosition()!=0.3) { //sets right slide to out with b button
            right_slide.setPosition(0.3);

        }

        if ( (dc_4link.getCurrentPosition()<=fourLinkLower && gamepad2.right_stick_y > 0.0 ) ||                 //
        (dc_4link.getCurrentPosition()>=fourLinkUpper && gamepad2.right_stick_y < 0.0) ||                       //Crazy Long 'If' statement 2.0!
                (dc_4link.getCurrentPosition()< fourLinkUpper && dc_4link.getCurrentPosition()>fourLinkLower)){ //
            dc_4link.setPower(gamepad2.right_stick_y);

        }
        dc_sweeper.setPower(gamepad2.left_stick_y);
        leftWheel.setPower(gamepad1.left_stick_y);
        rightWheel.setPower(gamepad1.right_stick_y);
    }
}