package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.UtilityLibs.HelperClasses.PhantomOpMode;
@TeleOp(name="mecanumTeleop")
public class TestTeleop extends PhantomOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        init();
        calibrateImu();
        waitForStart();
        double deg;
        while (!isStopRequested()) {
            deg = Math.atan(gamepad1.left_stick_y/gamepad1.left_stick_x);
            chassis.correctionAngleMod = Math.atan(gamepad1.left_stick_y/gamepad1.left_stick_x);
            chassis.drive((float) deg,1,0.2);
            sleep(20);
        }
    }
}
