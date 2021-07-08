package org.firstinspires.ftc.teamcode;
//Imports
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.UtilityLibs.HelperClasses.PhantomOpMode;
import org.firstinspires.ftc.teamcode.UtilityLibs.HelperClasses.chassisType;
//Declare as an autonomous program and give it a name
@Autonomous (name = "MecanumTestClass")
public class BasicMecanumAuto extends PhantomOpMode {
    //Inside this function goes code that makes the robot actually do things
    @Override
    public void runOpMode() throws InterruptedException {
        /*
         Set the type of chassis you are using, currently two options, PUSHBOT and MECANUM
         pushbots are two wheel, two motor robots, and mecanum drives are well, mecanum drives
         */
        setChassis(chassisType.MECANUM);
        // this function takes care of finding all your motors
        robot.init();
        // Calibrate the IMU
        robot.calibrate();
        // Wait for the user to hit start
        waitForStart();
        // Drive function, does a lot of math for you
        // Tell it its max power and total power reserved for correction
        chassis.drive(90, 1, 0.2);
        // Wait 2 seconds
        sleep(2000);
        // Stop all the motors the chassis has
        chassis.stopMotors();
    }
}