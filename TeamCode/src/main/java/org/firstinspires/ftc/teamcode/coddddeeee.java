package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.UtilityLibs.HelperClasses.PhantomOpMode;
import org.firstinspires.ftc.teamcode.UtilityLibs.HelperClasses.chassisType;

//Ignore this
@Autonomous(name = "coddddeeee")
public class coddddeeee extends PhantomOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        setChassis(chassisType.MECANUM);
        robot.init();
        robot.calibrate();
        waitForStart();
        stopwatch.resetAndRestart();
        double i = 0;
        while (!isStopRequested() && stopwatch.getTime() < 10000) {
            robot.backLeft.setPower(0.5 - robot.getAngle());
            robot.backRight.setPower(0.5 + robot.getAngle());
            robot.frontLeft.setPower(0.5 - robot.getAngle());
            robot.frontRight.setPower(0.5 + robot.getAngle());
            haha.telemetry.addData("Z:", robot.angles.firstAngle);
            haha.telemetry.addData("Y:", robot.angles.secondAngle);
            haha.telemetry.addData("X:", robot.angles.thirdAngle);
            haha.telemetry.addData("a",robot.getAngle());
            haha.telemetry.addData("i:", i);
            haha.telemetry.update();
            i += 1;
            sleep(50);
        }

//        sleep(60000);
        chassis.stopMotors();
    }
}
