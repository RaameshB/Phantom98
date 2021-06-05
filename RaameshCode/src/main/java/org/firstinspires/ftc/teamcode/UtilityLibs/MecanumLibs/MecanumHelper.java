package org.firstinspires.ftc.teamcode.UtilityLibs.MecanumLibs;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.teamcode.UtilityLibs.RobotConfig;
import org.firstinspires.ftc.teamcode.UtilityLibs.TelemetryHelper;

public class MecanumHelper {

    RobotConfig robot;

    TelemetryHelper telemetry;

    public MecanumHelper(RobotConfig rbt, TelemetryHelper tlm) {
        robot = rbt;
        telemetry = tlm;
    }

    public void drive(float movementDirectionRelativeToFront, double power, double reservedCorrectionPower) {
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

        frontRight = Math.sin(angleRad) * Math.sqrt(2) * (1 - reservedCorrectionPower);
        frontLeft = -1 * Math.cos(angleRad) * Math.sqrt(2) * (1 - reservedCorrectionPower);
        backLeft = frontRight;
        backRight = frontLeft;

        robot.frontRight.setPower(frontRight + correction(AxesOrder.XYZ));
        robot.frontLeft.setPower(frontLeft - correction(AxesOrder.XYZ));
        robot.backRight.setPower(backRight + correction(AxesOrder.XYZ));
        robot.backLeft.setPower(backLeft - correction(AxesOrder.XYZ));

    }

    public double correction (AxesOrder axesOrder) {
        robot.angles = robot.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES);
        //TODO: Make this a lot better
        double correction = -robot.angles.firstAngle/2;
        //end of TODO
        robot.angles = robot.imu.getAngularOrientation(AxesReference.INTRINSIC, axesOrder, AngleUnit.DEGREES);

        return correction;
    }
}
