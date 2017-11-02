package org.firstinspires.ftc.robotcontroller.external.samples;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Code to control servo 1 that opens and closes arm to grab glyph cubes
 *
 * _ button is used to toggle servo on and off
 */
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com
@TeleOp (name="Servo_BASIC", group="Linear Opmode")
@Disabled
public class Servo_BASIC extends LinearOpMode {

    public Servo claw  = null;
    double claw_min = 0.8;

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {
        //Report Telemetry Status on the phone.
        telemety.addData ("Status", "Initialized");
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
