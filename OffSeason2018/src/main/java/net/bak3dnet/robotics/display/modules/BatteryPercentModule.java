package net.bak3dnet.robotics.display.modules;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import edu.wpi.first.wpilibj.RobotController;
import net.bak3dnet.robotics.display.DChar;
import net.bak3dnet.robotics.display.DCharFactory;
import net.bak3dnet.robotics.display.RevDigitDisplay;
import net.bak3dnet.robotics.display.modules.DisplayModuleBase;

public class BatteryPercentModule extends DisplayModuleBase {

    private static final NumberFormat subHundo;
    private static final NumberFormat supHundo;

    private double previousVoltage = 0;

    static {

        subHundo = new DecimalFormat("#.##");
        supHundo = new DecimalFormat("#.#");

    }

    @Override
    public void task(RevDigitDisplay display) {

        double percentage = getPercentage();
        String formattedDecimal;

        if(percentage > 100) {

            formattedDecimal = subHundo.format(percentage);

        } else {

            formattedDecimal = subHundo.format(percentage);

        }



    }

    public double getPercentage() {

        double voltage = RobotController.getBatteryVoltage();

            while(true) {

                if(previousVoltage != voltage) {

                    return (RobotController.getBatteryVoltage()-12D)*100D;

                }

                Thread.sleep(1);
        }   

    }

}