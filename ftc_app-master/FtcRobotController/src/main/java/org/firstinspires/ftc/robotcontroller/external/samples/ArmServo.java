package org.firstinspires.ftc.robotcontroller.external.samples;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp (name="Servo_BASIC", group="Linear Opmode")

public class ArmServo extends LinearOpMode {

    public Servo claw  = null;
    double claw_min = 0.2;
    double claw_max = 0.8;

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {
        //Report Telemetry Status on the phone.
        telemetry.addData ("Status", "Initialized");
        telemetry.update();

        claw = hardwareMap.servo.get("claw");
        claw.setPosition(0.5);

        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive())  {

            // Press A to open claw
            if (gamepad1.a) {
                claw.setPosition(claw_max);
            }

            // Press B to close the claw
            else if (gamepad1.b) {
                claw.setPosition(claw_min);
            }
        }
    }
}
