package org.firstinspires.ftc.teamcode.CustomSamplePrograms;

//The imports here should be done for you when you're writing the code with suggestions.
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@Disabled //comment out this line if you want to run this

//We tell the app that this is a TeleOp here, we also give it a name here as well.
@TeleOp(group = "Tele-Op", name = "ExampleLinearTeleOp")

/*
We extend LinearOpMode here because it gives us more control over what we are doing, we define
the loop and everything the robot does. This can be a bit dangerous to go right into though as
loops need to have a proper interrupt or the apps will crash (which has some issues, it takes time
to restart them, which isn't a problem in normal testing but in the heat of competition this can
cause serious delays in switching to the autonomous programs.
 */
public class BasicLinearTeleOp extends LinearOpMode {

    /*
    We use object oriented programing here, the idea here is that you write the same initialization
    scripts for basically every program, so the RobotConfiguration class has some universal
    functions that are commonly used.
     */
    RobotConfiguration robot = new RobotConfiguration();

    /*
    The runOpMode() function is specific to the LinearOpMode class. The robot will run the contents
    of this function when you hit initialize.
     */
    @Override
    public void runOpMode() throws InterruptedException {

        /*
        We define left power and right power separately here. It's not necessary but it just helps
        with organization.
         */
        double leftPower;
        double rightPower;

        /*
        The init() function takes in the robot's hardwareMap (a variable from LinearOpMode that
        basically defines what hardware the robot has, based on the configuration made on the
        phones) and then does the hardware initialization in a singular function. This is a utility
        function that exists so you don't have to write all of those lines for every program you
        create.
         */
        robot.init(hardwareMap);

        /*
        Another thing using object oriented programing. Instead of writing robot.leftDrive or
        robot.rightDrive every single time we need to use the motors, we just create two new
        variables and set them to the same things as the old ones. It works the same way but less
        things to type.
         */
        DcMotor leftDrive = robot.leftDrive;
        DcMotor rightDrive = robot.rightDrive;

        /*
        Another function from LinearOpMode, this waits until the start button is pressed after the
        initialize button.
         */
        waitForStart();

        /*
        This is our loop, it is basically a while loop that runs until a stop is requested. The
        isStopRequested is one more function from linearOpMode, it basically checks if the driver
        has pressed stop and returns a boolean that says true if they did but false if they did not.
         */
        while(!isStopRequested()) {
            // We just set the power variables to the y axis of our controller sticks
            leftPower = gamepad1.left_stick_y;
            rightPower = gamepad1.right_stick_y;

            if(gamepad1.a) {
                /*
                This is a utility function from RobotConfiguration that reverses the direction of
                the motors, basically makes the front the back and the back the front. It is
                triggered when the "a" button on the controller is pressed.
                 */
                robot.directionSwap();
            }
            if(gamepad1.b) {
                /*
                Another utility function from RobotConfiguration. This one swaps the right and left
                motors, in case the person configuring the robot on the phones somehow got them
                backwards. This is useful because you no longer need to stop the robot to continue
                testing your TeleOp. So you can test what you need to and fix this issue later. This
                is triggered if the "b" button is pressed.
                 */
                robot.swapMotors();
            }

            /*
            Set the power to 1/2 the input. I can't verify the traction of the wheels so to prevent
            them from spinning out this is there. Normally you'd implement an acceleration algorithm
            but that's a bit more complex and this is only an example program.
             */
            leftDrive.setPower(leftPower/2);
            rightDrive.setPower(rightPower/2);

            /*
            We add a sleep for 50 ms here. This is usually not necessary but for more complex TelOps
            it prevents the phone from being overloaded with commands.
             */
            sleep(50);
        }
    }
}