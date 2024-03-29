/*
Copyright (c) 2016 Robert Atkinson

All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted (subject to the limitations in the disclaimer below) provided that
the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list
of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice, this
list of conditions and the following disclaimer in the documentation and/or
other materials provided with the distribution.

Neither the name of Robert Atkinson nor the names of his contributors may be used to
endorse or promote products derived from this software without specific prior
written permission.

NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESSFOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package org.firstinspires.ftc.teamcode.archive.season2016_2017.testArchive;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.robotHandlers.DebugLogger;
import org.firstinspires.ftc.teamcode.robots.jorge.Jorge;

// Created on 2/27/2017 at 7:47 AM by Chandler, originally part of ftc_app under org.firstinspires.ftc.teamcode

@TeleOp(name = "ConceptStreamline", group = "Iterative Opmode")
@Disabled
public class ConceptStreamline extends OpMode {

    private ElapsedTime runtime = new ElapsedTime();
    //private RobotHandler robot;
    private Jorge jorge;
    private DebugLogger log;

    //Code to run ONCE when the driver hits INIT
    @Override
    public void init() {

        log = new DebugLogger();

        /*robot = new RobotHandler(new RobotConfig(new StandardRobotDrive(this.hardwareMap))) {
            @Override
            public void drive() {

                float straight = -gamepad1.right_stick_y;
                float strafe = gamepad1.right_stick_x;
                float rotate = gamepad1.left_stick_x;
                float slow = gamepad1.right_trigger;

                float powerRF = straight;
                float powerRB = straight;
                float powerLF = straight;
                float powerLB = straight;
                powerRF -= strafe;
                powerRB += strafe;
                powerLF += strafe;
                powerLB -= strafe;
                powerRF -= rotate;
                powerRB -= rotate;
                powerLF += rotate;
                powerLB += rotate;

                if (powerRF > 1) {powerRF = 1;} else if (powerRF < -1) {powerRF = -1;}
                if (powerRB > 1) {powerRB = 1;} else if (powerRB < -1) {powerRB = -1;}
                if (powerLF > 1) {powerLF = 1;} else if (powerLF < -1) {powerLF = -1;}
                if (powerLB > 1) {powerLB = 1;} else if (powerLB < -1) {powerLB = -1;}

                if (slow > 0.25) {
                    powerRF /= 4 * slow;
                    powerRB /= 4 * slow;
                    powerLF /= 4 * slow;
                    powerLB /= 4 * slow;
                }

                StandardRobotDrive drive = robot.getRobotDrive();
                drive.setPowers(new String[]{"rf", "rb", "lf", "lb"}, new double[]{powerRF, powerRB, powerLF, powerLB});

            }
        };

        robot.getRobotDrive().setSideDirections(
                new StandardRobotDrive.SIDE[]{StandardRobotDrive.SIDE.RIGHT, StandardRobotDrive.SIDE.LEFT},
                new DcMotorSimple.Direction[]{DcMotorSimple.Direction.REVERSE, DcMotorSimple.Direction.FORWARD}
        );*/

        jorge = new Jorge(this);

        log.log("Initialized streamline test.");
        telemetry.addData("Status", "Initialized");

    }

    //Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
    @Override
    public void init_loop() {}

    //Code to run ONCE when the driver hits PLAY
    @Override
    public void start() {runtime.reset();}

    //Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
    @Override
    public void loop() {
        log.log("Driving. Run time is: " + runtime.seconds() + " seconds.");
        telemetry.addData("Status", "Running: " + runtime.toString());
        jorge.drive();
    }

    //Code to run ONCE after the driver hits STOP
    @Override
    public void stop() {
        jorge.stop();
        log.log("Done testing streamline.");
        log.close_log();
    }

}
