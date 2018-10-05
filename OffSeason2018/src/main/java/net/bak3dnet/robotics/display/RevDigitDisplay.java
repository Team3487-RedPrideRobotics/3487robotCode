package net.bak3dnet.robotics.display;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;
//import edu.wpi.first.wpilibj.DigitalInput;
//import edu.wpi.first.wpilibj.AnalogInput;

import net.bak3dnet.robotics.display.DChar;

/**
 * 
 * @author Jake Armstrong
 * @version 0.0.1
 * @since 0.1.0
 *  
 */
public class RevDigitDisplay {

    I2C i2c;

    public RevDigitDisplay() {

        i2c = new I2C(Port.kMXP,0x70);

    }

    /**
     *
     * @param startingText The string that should be placed on the display
     * 
     * @return Returns a pre-initialized Display with text automatically
     */
    public RevDigitDisplay(String startingText) {

        

    }

    /**
     * Sets the string that is displayed on the display
     * 
     * @param text The array of DChars that will scroll on the displays
     * 
     */
    public void setText(DChar[] text) {

        if(text.length > 4) {

            //Do a scrolling thing

        }

    }


}

