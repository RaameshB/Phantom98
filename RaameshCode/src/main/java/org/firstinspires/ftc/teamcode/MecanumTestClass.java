package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.UtilityLibs.HelperClasses.PhantomOpMode;
import org.firstinspires.ftc.teamcode.UtilityLibs.HelperClasses.chassisType;

@Autonomous (name = "MecanumTestClass")
public class MecanumTestClass extends PhantomOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        setChassis(chassisType.MECANUM);
        initialize();
        calibrateImu();
        waitForStart();
        chassis.drive(90, 1, 0.2);
        sleep(2000);
        chassis.stopMotors();
    }
}