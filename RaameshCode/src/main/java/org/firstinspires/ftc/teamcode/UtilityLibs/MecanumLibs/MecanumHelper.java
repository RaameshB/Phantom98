package org.firstinspires.ftc.teamcode.UtilityLibs.MecanumLibs;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.teamcode.UtilityLibs.RobotConfig;
import org.firstinspires.ftc.teamcode.UtilityLibs.TelemetryHelper;

public class MecanumHelper {

    RobotConfig robot;

    TelemetryHelper telemetry;

    LinearOpMode ln;

    public MecanumHelper(RobotConfig rbt, TelemetryHelper tlm, LinearOpMode linearOpMode) {
        robot = rbt;
        telemetry = tlm;
        ln = linearOpMode;
    }

    void driveInternal(float movementDirectionRelativeToFront, double totalPower, double reservedCorrectionPower) {
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

        frontRight = Math.sin(angleRad) * Math.sqrt(2) * (totalPower - reservedCorrectionPower);
        frontLeft = -1 * Math.cos(angleRad) * Math.sqrt(2) * (totalPower - reservedCorrectionPower);
        backLeft = frontRight;
        backRight = frontLeft;

        robot.frontRight.setPower(frontRight + correction(AxesOrder.XYZ));
        robot.frontLeft.setPower(frontLeft - correction(AxesOrder.XYZ));
        robot.backRight.setPower(backRight + correction(AxesOrder.XYZ));
        robot.backLeft.setPower(backLeft - correction(AxesOrder.XYZ));
    }

    public void drive(final float movementDirectionRelativeToFront, final double totalPower, final double reservedCorrectionPower) {
        Thread iHateThreads = new Thread() {
            @Override
            public void run() {
                double currentPowerAvg = 0;
                double oldPowerAvg = 0;
                boolean threadInterrupt = false;
                while (!threadInterrupt) {
                    driveInternal(movementDirectionRelativeToFront, totalPower, reservedCorrectionPower);
                    oldPowerAvg = (robot.frontLeft.getPower() + robot.frontRight.getPower() + robot.backLeft.getPower() + robot.backRight.getPower())/4;
                    try {
                        sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    currentPowerAvg = (robot.frontLeft.getPower() + robot.frontRight.getPower() + robot.backLeft.getPower() + robot.backRight.getPower())/4;
                    if (ln.isStopRequested() || oldPowerAvg != currentPowerAvg) {
                        threadInterrupt = true;
                    }
                }
            }
        };
        iHateThreads.start();
    }

    public double correction (AxesOrder axesOrder) {
        robot.angles = robot.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES);
        //TODO: Make this a lot better
        double correction = -robot.angles.firstAngle/360/2;
        //end of TODO
        robot.angles = robot.imu.getAngularOrientation(AxesReference.INTRINSIC, axesOrder, AngleUnit.DEGREES);
        return correction;
    }
}
