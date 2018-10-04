package net.bak3dnet.robotics.display;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;
//import edu.wpi.first.wpilibj.DigitalInput;
//import edu.wpi.first.wpilibj.AnalogInput;


public class RevDigitDisplay {

    I2C i2c;

    public RevDigitDisplay() {

        i2c = new I2C(Port.kMXP,0x70);

    }

    public RevDigitDisplay(String startingText) {


    }

    public void setText() {



    }

    //public DChar[]


}

