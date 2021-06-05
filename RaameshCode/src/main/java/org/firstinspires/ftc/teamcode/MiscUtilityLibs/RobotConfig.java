package org.firstinspires.ftc.teamcode.MiscUtilityLibs;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

public class RobotConfig {

    boolean isInit = false;

    HardwareMap hwMap;
    LinearOpMode ln;
    TelemetryHelper telemetry;

    public RobotConfig (HardwareMap ahwMap, LinearOpMode lnOpMode, TelemetryHelper tel){
        hwMap = ahwMap;
        ln = lnOpMode;
        telemetry = tel;
    };

    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backLeft;
    DcMotor backRight;

    BNO055IMU imu;

    public void init() {

        telemetry.enableTelemetry();

        frontLeft = hwMap.get(DcMotor.class, "front_left");
        frontRight = hwMap.get(DcMotor.class, "front_right");
        backLeft = hwMap.get(DcMotor.class, "back_left");
        backRight = hwMap.get(DcMotor.class, "back_right");

        //TODO: Add the rest of the init code, so yes the motor reverses and stuff

        isInit = true;
    }

    Orientation angles;

    boolean stopper = false;
    boolean stopped = true;

    Thread thread = new Thread() {
        @Override
        public void run() {
            super.run();
            stopped = false;
            while (!ln.isStopRequested() && !stopper) {
                telemetry.modData("X: ", angles.firstAngle);
                telemetry.modData("Y: ", angles.secondAngle);
                telemetry.modData("Z: ", angles.thirdAngle);
            }
            stopped = true;
        }
    };

    public void calibrate() {
        imu = hwMap.get(BNO055IMU.class, "imu");

        BNO055IMU.Parameters para = new BNO055IMU.Parameters();
        para.angleUnit = BNO055IMU.AngleUnit.DEGREES;

        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES);

        imu.initialize(para);

        telemetry.addLine("Angles:");
        telemetry.addData("X: ", angles.firstAngle);
        telemetry.addData("Y: ", angles.secondAngle);
        telemetry.addData("Z: ", angles.thirdAngle);

    }

    public void enableImuTelemetry() {
        telemetry.addLine("Angles:");
        telemetry.addData("X: ", angles.firstAngle);
        telemetry.addData("Y: ", angles.secondAngle);
        telemetry.addData("Z: ", angles.thirdAngle);
        stopper = false;
        thread.start();
    }

    public void disableTelemetry() {
        stopper = true;
        while (!stopped);
        telemetry.removeLine("Angles:");
        telemetry.removeData("X: ");
        telemetry.removeData("Y: ");
        telemetry.removeData("Z: ");
    }

}
