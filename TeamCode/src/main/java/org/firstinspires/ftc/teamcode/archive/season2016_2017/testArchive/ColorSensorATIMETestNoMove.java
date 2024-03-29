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
import org.firstinspires.ftc.teamcode.robotHandlers.MultiplexedColorSensors;
import org.firstinspires.ftc.teamcode.robotHandlers.StandardRobotDrive;

// Created on 2/27/2017 at 8:58 AM by Chandler, originally part of ftc_app under org.firstinspires.ftc.teamcode

@TeleOp(name = "ColorSensorATIMETestNoMove", group = "Iterative Opmode")
@Disabled
public class ColorSensorATIMETestNoMove extends OpMode {

    private ElapsedTime runtime = new ElapsedTime();
    private StandardRobotDrive drive;
    private DebugLogger log;
    private MultiplexedColorSensors colorSensors;
    private final int numberOfSensors = 2;

    //Code to run ONCE when the driver hits INIT
    @Override
    public void init() {

        log = new DebugLogger();

        log.log("Initializing multiplexer...");
        colorSensors = new MultiplexedColorSensors(this.hardwareMap, "mux", "ada", numberOfSensors, MultiplexedColorSensors.ATIME.SLOWEST, MultiplexedColorSensors.GAIN._16X);

        log.log("Initialized color sensor test.");
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

        telemetry.addData("Status", "Running: " + runtime.toString());

        doIntegrationTimeToggle(gamepad1.a);

        log.log("ATIME = \t" + ATIMEToString(toggleSet[atimeNum]));
        for (int i = 0; i < numberOfSensors; i++) {
            int[] CRGB = colorSensors.getCRGB(i);
            log.log("Values for sensor " + (i + 1) + " -");
            log.log("Clear = \t\t" + CRGB[0]);
            log.log("Red = \t\t" + CRGB[1]);
            log.log("Green = \t" + CRGB[2]);
            log.log("Blue = \t\t" + CRGB[3]);
            log.log("CCT = \t\t" + colorSensors.colorTemp(i));
        }
        telemetry.addData("ATIME", ATIMEToString(toggleSet[atimeNum]));
        for (int i = 0; i < numberOfSensors; i++) {
            int[] CRGB = colorSensors.getCRGB(i);
            telemetry.addData("", "Values for sensor " + (i + 1));
            telemetry.addData("Clear", CRGB[0]);
            telemetry.addData("clear()", colorSensors.clear(i));
            telemetry.addData("Red", CRGB[1]);
            telemetry.addData("red()", colorSensors.red(i));
            telemetry.addData("Green", CRGB[2]);
            telemetry.addData("green()", colorSensors.green(i));
            telemetry.addData("Blue", CRGB[3]);
            telemetry.addData("blue()", colorSensors.blue(i));
            telemetry.addData("Color Temp", colorSensors.colorTemp(i));
        }

    }

    //Code to run ONCE after the driver hits STOP
    @Override
    public void stop() {log.log("Done testing color sensors."); log.close_log();}

    private boolean toggling = false;
    private MultiplexedColorSensors.ATIME[] toggleSet = {
            MultiplexedColorSensors.ATIME.SLOWEST,
            MultiplexedColorSensors.ATIME.SLOW,
            MultiplexedColorSensors.ATIME.MED,
            MultiplexedColorSensors.ATIME.FAST,
            MultiplexedColorSensors.ATIME.FASTEST
    };
    private int atimeNum = 0;
    private void doIntegrationTimeToggle (boolean toggle) {
        if (!toggle) {toggling = false; return;}
        if (toggling) return;
        // ^Speed things up by exiting if nothing is really going to happen otherwise.
        if (atimeNum >= toggleSet.length) {atimeNum = 0;}
            else {atimeNum++;}
        colorSensors.setATIME(toggleSet[atimeNum], numberOfSensors);
        toggling = true;
    }
    private String ATIMEToString (MultiplexedColorSensors.ATIME atime) {
        switch (atime) {
            case SLOWEST:
                return "SLOWEST";
            case SLOW:
                return "SLOW";
            case MED:
                return "MED";
            case FAST:
                return "FAST";
            case FASTEST:
                return "FASTEST";
            default:
                return "ERROR";
        }
    }

}
