package org.firstinspires.ftc.teamcode.UtilityLibs;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import java.util.ArrayList;

public class TelemetryHelper {



    LinearOpMode ln;

    int length;

    ArrayList<String> captionsAndLines = new ArrayList<>();
    ArrayList<Object> dataPts = new ArrayList<>();

    public TelemetryHelper (LinearOpMode lnOpMd) {
        ln = lnOpMd;
        thread.start();
    }

    boolean telemEnabled = false;

    Thread thread = new Thread() {
        @Override
        public void run() {
            while(!telemEnabled);
            while (!ln.isStopRequested()) {
                compose();
                ln.telemetry.update();
            }
        }
    };

    public void enableTelemetry() {
        telemEnabled = true;
    }

    int i = 0;

    void compose() {
        length = captionsAndLines.size() - 1;
        while (i < length) {
            if(dataPts.get(i) != ""){
                ln.telemetry.addData(captionsAndLines.get(i), dataPts.get(i));
            } else {
                ln.telemetry.addLine(captionsAndLines.get(i));
            }
        }
    }

    public void addData(String caption, Object data) {
        if (captionsAndLines.contains(caption)) {
            throw new IllegalArgumentException();
        }
        captionsAndLines.add(caption);
        dataPts.add(data);
    }

    public void addLine(String caption) {
        if (!captionsAndLines.contains(caption)) {
            throw new IllegalArgumentException();
        }
        captionsAndLines.add(caption);
        dataPts.add("");
    }

    public void modData(String caption, Object data) {
        if (!captionsAndLines.contains(caption)) {
            throw new IllegalArgumentException();
        }
        dataPts.set(captionsAndLines.indexOf(caption), data);
    }

    public void modData(String oldCaption, String newCaption, Object data) {
        if (!captionsAndLines.contains(oldCaption)) {
            throw new IllegalArgumentException();
        }
        captionsAndLines.set(captionsAndLines.indexOf(oldCaption), newCaption);
        dataPts.set(captionsAndLines.indexOf(oldCaption), data);
    }

    public void modLine(String oldCaption, String newCaption) {
        if (!captionsAndLines.contains(oldCaption)) {
            throw new IllegalArgumentException();
        }
        captionsAndLines.set(captionsAndLines.indexOf(oldCaption), newCaption);
    }

    public void removeData(String caption) {
        if (!captionsAndLines.contains(caption)) {
            throw new IllegalArgumentException();
        }
        captionsAndLines.remove(captionsAndLines.indexOf(caption));
        dataPts.remove(captionsAndLines.indexOf(caption));
    }

    public void removeLine(String caption) {
        removeData(caption);
    }
}