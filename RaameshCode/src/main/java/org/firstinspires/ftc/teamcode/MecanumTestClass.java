package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.UtilityLibs.MecanumLibs.MecanumHelper;
import org.firstinspires.ftc.teamcode.UtilityLibs.RobotConfig;
import org.firstinspires.ftc.teamcode.UtilityLibs.TelemetryHelper;

public class MecanumTestClass extends LinearOpMode {

    TelemetryHelper telemetry = new TelemetryHelper(this);
    RobotConfig robot = new RobotConfig(hardwareMap, this, telemetry);
    MecanumHelper driveTrain = new MecanumHelper(robot, telemetry, this);

    @Override
    public void runOpMode() throws InterruptedException {
        robot.init();
        robot.calibrate();
        waitForStart();
        driveTrain.drive(90, 1, 0.2);
    }
}
