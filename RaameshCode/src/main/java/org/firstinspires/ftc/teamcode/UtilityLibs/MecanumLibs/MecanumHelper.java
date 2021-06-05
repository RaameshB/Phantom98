package org.firstinspires.ftc.teamcode.UtilityLibs.MecanumLibs;

import org.firstinspires.ftc.teamcode.UtilityLibs.RobotConfig;
import org.firstinspires.ftc.teamcode.UtilityLibs.TelemetryHelper;

public class MecanumHelper {

    RobotConfig robot;

    TelemetryHelper telemetry;

    public MecanumHelper(RobotConfig rbt, TelemetryHelper tlm) {
        robot = rbt;
        telemetry = tlm;
    }

    public void drive(float movementDirectionRelativeToFront, double power) {
        float angle;
        double frontRight;
        double frontLeft;
        double backRight;
        double backLeft;
        double angleRad;

        movementDirectionRelativeToFront += 45;

        if (movementDirectionRelativeToFront < 0) {
            angle = 360 + movementDirectionRelativeToFront;
        } else {
            if (movementDirectionRelativeToFront > 360) {
                angle = movementDirectionRelativeToFront - 360;
            } else {
                angle = movementDirectionRelativeToFront;
            }
        }
        angleRad = Math.toRadians(angle);

        frontRight = Math.sin(angleRad) * Math.sqrt(2);
        frontLeft = -1 * Math.cos(angleRad) * Math.sqrt(2);
        backLeft = frontRight;
        backRight = frontLeft;

        robot.frontRight.setPower(frontRight);
        robot.frontLeft.setPower(frontLeft);
        robot.backRight.setPower(backRight);
        robot.backLeft.setPower(backLeft);
    }

}
