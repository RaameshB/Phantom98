package org.firstinspires.ftc.teamcode.UtilityLibs;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
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

    public DcMotor frontLeft;
    public DcMotor frontRight;
    public DcMotor backLeft;
    public DcMotor backRight;

    BNO055IMU imu;

    public void init() {

        telemetry.enableTelemetry();

        findMotors("front_right","front_left","back_right","back_left");

        //TODO: Add the rest of the init code, so yes the motor reverses and stuff

        setMotorDirection(DcMotorSimple.Direction.FORWARD);

        setZeroPowerBehaviour(DcMotor.ZeroPowerBehavior.BRAKE);

        stopMotors();

        isInit = true;
    }

    public void findMotors(String frontRightName, String frontLeftName, String backRightName, String backLeftName) {
        frontLeft = hwMap.get(DcMotor.class, frontRightName);
        frontRight = hwMap.get(DcMotor.class, frontLeftName);
        backLeft = hwMap.get(DcMotor.class, backRightName);
        backRight = hwMap.get(DcMotor.class, backLeftName);
    }

    public void stopMotors() {
        frontRight.setPower(0);
    }

    public void setMotorDirection(DcMotorSimple.Direction direction) {
        if (direction == DcMotorSimple.Direction.FORWARD) {
            frontRight.setDirection(DcMotorSimple.Direction.FORWARD);
            frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
            backRight.setDirection(DcMotorSimple.Direction.FORWARD);
            backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        }
        else {
            if (direction == DcMotorSimple.Direction.REVERSE) {
                frontLeft.setDirection(DcMotorSimple.Direction.FORWARD);
                frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
                backLeft.setDirection(DcMotorSimple.Direction.FORWARD);
                backRight.setDirection(DcMotorSimple.Direction.REVERSE);
            } else {
                throw new IllegalArgumentException();
            }
        }
    }

    public void setZeroPowerBehaviour(DcMotor.ZeroPowerBehavior behaviour) {
        frontRight.setZeroPowerBehavior(behaviour);
        frontLeft.setZeroPowerBehavior(behaviour);
        backRight.setZeroPowerBehavior(behaviour);
        frontRight.setZeroPowerBehavior(behaviour);
    }

    Orientation angles;

    boolean stopper = false;
    boolean stopped = true;

    Thread thread = new Thread() {
        @Override
        public void run() {
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

        telemetry.addLine("Gyro Calibrating...");

        while (!imu.isGyroCalibrated());

        telemetry.modLine("Gyro Calibrating...", "Gyro Calibration Complete! This message will disappear in 2 seconds.");

        ln.sleep(2000);

        telemetry.removeLine("Gyro Calibration Complete! This message will disappear in 2 seconds.");

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
