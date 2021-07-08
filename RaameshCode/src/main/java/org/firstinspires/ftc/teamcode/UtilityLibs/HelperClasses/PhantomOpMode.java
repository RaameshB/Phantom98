package org.firstinspires.ftc.teamcode.UtilityLibs.HelperClasses;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.UtilityLibs.MecanumLibs.MecanumHelper;

public abstract class PhantomOpMode extends LinearOpMode {


    TelemetryHelper telemetry = new TelemetryHelper(this);
    RobotConfig robot = new RobotConfig(hardwareMap, this, telemetry);
    public MecanumHelper chassis;

    public void setChassis(chassisType type){
        robot.robotType = type;
        if (type == chassisType.MECANUM) {
             chassis = new MecanumHelper(robot, telemetry, this);
        }
    }
    @Override
    public void runOpMode() throws InterruptedException {

    }
    public void initialize() {
        robot.init();
    }
    public void calibrateImu() {
        robot.calibrate();
    }
}