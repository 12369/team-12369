package org.firstinspires.ftc.teamcode; 										// Put this in part of teamcode folder.

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;                          // Imports the TeleOp package member.
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode; 					// imports the LinearOpMode package member.
import com.qualcomm.robotcore.hardware.DcMotor;									// imports the DcMotor package member.
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;
import com.vuforia.Marker;
// vcs test

@TeleOp(name = "OMNI_Wheel_Basic", group = "linear Opmode")                     // Register your Opmode. On the RC the Opmode will show up as the name string.

/* Extend the LinearOpMode.*/
public class Holonomic_Basic extends LinearOpMode {

    /* Declare OpMode members. */
    DcMotor F1, F2, R1, R2;														// Declares the DcMotor variables F1, F2, R1, R2.
    double heading, heading_degrees, power, leftstick_xsq, leftstick_ysq;       // Declares the double variables for telemetry.
    double powerF1, powerF2, powerR1, powerR2;
    // Declares the double variables for calculating the power levels for each motor.

    //@Override																	// This should be commented out when we want Android Studio to run this OpMode.
    public void runOpMode() 													/* This begins the initialization process fro the robot (after driver presses "INIT" on DS */
    {

        F1 = hardwareMap.get(DcMotor.class, "f1");	// Initialize the motor variable for F1.  Named f1 in the RC phone.
        F2 = hardwareMap.get(DcMotor.class, "f2");								// Initialize the motor variable for F2.  Named f2 in the RC phone.
        R1 = hardwareMap.get(DcMotor.class, "r1");								// Initialize the motor variable for R1.  Named r1 in the RC phone.
        R2 = hardwareMap.get(DcMotor.class, "r2");
        F1.setDirection(DcMotorSimple.Direction.FORWARD);
        F2.setDirection(DcMotorSimple.Direction.FORWARD);
        R2.setDirection(DcMotorSimple.Direction.FORWARD);
        R1.setDirection(DcMotorSimple.Direction.FORWARD);
        // Initialize the motor variable for R2.  Named r2 in the RC phone.

        waitForStart();															// Wait for the game to start (driver presses PLAY).

        while (opModeIsActive()) 												/* Run until the end of the match (driver presses STOP). */
        {
            /* Calculate the telemetry values for heading (Left stick determines heading). */
            heading = -1 * (Math.atan2(gamepad1.left_stick_y, gamepad1.left_stick_x));  // Calculates the angle in radians measured counterclockwise from the positive x-axis and the heading (x, y)
            heading_degrees = heading * (180 / Math.PI);                        // Calculates the heading in degrees ??? not used again.
            leftstick_xsq = gamepad1.left_stick_x * gamepad1.left_stick_x;      // ??? this is not used again
            leftstick_ysq = gamepad1.left_stick_y * gamepad1.left_stick_y;              // ??? this is not used again

			/* Report telemetry data on RC phone ??? Should this be below the power settings but inside the if statement? */
            telemetry.addData("heading", heading);
            telemetry.addData("pwr", power);
            power = Math.sqrt(leftstick_xsq + leftstick_ysq);

            /* Find and set power (Right stick determines power). */
            /* .5 is the speed, .5 = medium speed */
            if (Math.abs(gamepad1.left_stick_x) > .1 || Math.abs(gamepad1.left_stick_y) > .1 || Math.abs(gamepad1.right_stick_x) > .1) {
                powerF1 = (-Math.cos((Math.PI/4) - heading)) - gamepad1.right_stick_x;
                powerF2 = (-Math.cos((Math.PI/4) + heading)) - gamepad1.right_stick_x;
                powerR1 = (Math.cos((Math.PI/4) + heading)) - gamepad1.right_stick_x;
                powerR2 = (-Math.cos((Math.PI/4) - heading)) - gamepad1.right_stick_x; // this works


            }
            else{
                powerF1 = 0;
                powerF2 = 0;
                powerR1 = 0;
                powerR2 = 0;

            }
//            powerF1 = Range.clip(powerF1, -1, 1);
//            powerF2 = Range.clip(powerF2, -1, 1);
//            powerR1 = Range.clip(powerR1, -1, 1);
//            powerR2 = Range.clip(powerR2, -1, 1);

            // test the motors directly and isolate variable

//            powerF1 = gamepad1.left_stick_y;
//            powerF2 = gamepad1.left_stick_y;
//            powerR1 = -gamepad1.left_stick_y;
//            powerR2 = gamepad1.left_stick_y;

			/* Make the motors move based on values calculated above. */
            F1.setPower(powerF1);
            F2.setPower(powerF2);
            R1.setPower(powerR1);
            R2.setPower(powerR2);


        }


    }
}