package org.firstinspires.ftc.teamcode.CustomSamplePrograms;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class RobotConfiguration {
    public DcMotor leftDrive = null;
    public DcMotor rightDrive = null;

    HardwareMap hwMap = null;


    public void init(HardwareMap ahwmp) {
        hwMap = ahwmp;

        leftDrive = hwMap.get(DcMotor.class, "LM");
        rightDrive = hwMap.get(DcMotor.class, "LM");

        leftDrive.setPower(0);
        rightDrive.setPower(0);

        leftDrive.setDirection(DcMotorSimple.Direction.FORWARD);
        rightDrive.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void stop() {
        leftDrive.setPower(0);
        rightDrive.setPower(0);
    }
    public boolean s = false;
    public void swapMotors() {
        if (s) {
            leftDrive = hwMap.get(DcMotor.class, "LM");
            rightDrive = hwMap.get(DcMotor.class, "RM");
            s = false;
        } else {
            leftDrive = hwMap.get(DcMotor.class, "RM");
            rightDrive = hwMap.get(DcMotor.class, "LM");
            s = true;
        }

    }

    public boolean r;
    public void directionSwap() {
        if (r) {
            forward();
            r = false;
        } else {
            reverse();
            r = true;
        }
    }

    public void reverse() {
        leftDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        rightDrive.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    public void forward() {
        leftDrive.setDirection(DcMotorSimple.Direction.FORWARD);
        rightDrive.setDirection(DcMotorSimple.Direction.REVERSE);
    }
}