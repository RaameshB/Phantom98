package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(group = "telop", name = "ExampleLinearTeleOp")
public class BasicLinearTeleOp extends LinearOpMode {

    RobotConfiguration robot = new RobotConfiguration();

    @Override
    public void runOpMode() {
        double leftPower;
        double rightPower;
        robot.init(hardwareMap);

        DcMotor leftDrive = robot.leftDrive;
        DcMotor rightDrive = robot.rightDrive;

        while(!isStopRequested()) {
             leftPower = gamepad1.left_stick_y;
             rightPower = gamepad1.right_stick_y;

             if(gamepad1.a) {
                 robot.reverse();
             }
             if(gamepad1.b) {
                 robot.swapMotors();
             }

             leftDrive.setPower(leftPower/2);
             rightDrive.setPower(rightPower/2);

             sleep(50);
        }
    }
}
