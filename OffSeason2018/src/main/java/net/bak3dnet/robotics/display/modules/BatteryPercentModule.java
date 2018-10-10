package net.bak3dnet.robotics.display.modules;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import edu.wpi.first.wpilibj.RobotController;
import net.bak3dnet.robotics.display.RevDigitDisplay;

public class BatteryPercentModule extends TickerTapeModule implements DisplayModuleBase {

    private static final NumberFormat subHundo;
    private static final NumberFormat supHundo;

    static {

        subHundo = new DecimalFormat("#.##");
        supHundo = new DecimalFormat("#.#");

    }

    @Override
    public void task(RevDigitDisplay display, double deltaTime) {

        display.setText(getFormattedPercentage());

    }

    private double getPercentage() {
        
        return (RobotController.getBatteryVoltage()-12D)*10D;

    }

    private String getFormattedPercentage() {

        double percentage = getPercentage();
        String formattedDecimal;

        if(percentage > 100) {

            formattedDecimal = supHundo.format(percentage);

        } else {

            formattedDecimal = subHundo.format(percentage);

        }

        return formattedDecimal;

    }

    public String toString() {

        return "Battery Percentage";

    }

}
