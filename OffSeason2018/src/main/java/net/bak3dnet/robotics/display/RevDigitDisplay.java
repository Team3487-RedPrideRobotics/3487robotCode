package net.bak3dnet.robotics.display;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;
//import edu.wpi.first.wpilibj.DigitalInput;
//import edu.wpi.first.wpilibj.AnalogInput;

import net.bak3dnet.robotics.display.DChar;
import net.bak3dnet.robotics.display.DCharFactory;

/**
 * 
 * @author Jake Armstrong
 * @version 0.0.1
 * @since 0.1.0
 *  
 */
public class RevDigitDisplay {

    I2C i2c;

    private static RevDigitDisplay singleton;

    /**
     * Processor Chip Commands:
     * Source: https://cdn-shop.adafruit.com/datasheets/ht16K33v110.pdf
     * 
     * oscilatorX determines if the chip is updating the display
     * 
     * defBlinkin: The default setting of wheter or not to blink.
     *  - Default set to off.
     * 
     * defBrightness: Sets the default brightess of the display
     *  - Default set to bright
     * 
     */
    private final byte oscilatorOn = (byte) 0x21; 
    private final byte oscilatorOff = (byte) 0x20;

    private final byte defBlinking = (byte) 0x81;

    private final byte defBrightness = (byte) 0xEF;

    /**
     * Checks to see that a singleton has been made.
     */
    private static void singletonCheck() {

        if(singleton == null) {
            singleton = new RevDigitDisplay();
        }

    }
    /**
     * 
     * @return Returns the singleton class.
     */
    public static RevDigitDisplay getInstance() {

        singletonCheck();
        return singleton;

    }

    public static RevDigitDisplay getInstance(String setToString) {

        singletonCheck();

        DChar[] string = DCharFactory.getDChars(setToString);
        singleton.setText(string);
        return singleton;

    }

    public static RevDigitDisplay getInstance(DChar[] dChars) {

        singletonCheck();

        singleton.setText(dChars);

        return singleton;

    }

    public static RevDigitDisplay getInstance(double number) {

        singletonCheck();

        singleton.setText(DCharFactory.getDChars(Double.toString(number)));

        return singleton;
    }

    private RevDigitDisplay() {

        i2c = new I2C(Port.kMXP,0x70);
        

    }


    /**
     * Sets the string that is displayed on the display
     * 
     * @param text The array of DChars that will scroll on the displays
     * 
     */
    public void setText(DChar[] text) {

        //TODO: MULTITHREADING

    }


}
