package frc.robot;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.Timer;

//import net.bak3dnet.robotics.display.RevDigitDisplay;

public class Team3487Bot extends IterativeRobot {

  //RobotController.getBatteryVoltage();

  /*
  * This is an experimental edition
  */

  //I2C to Segmented Display
  I2C i2c;

  @Override
  public void robotInit() {

    i2c = new I2C(Port.kMXP, 0x70);

    byte[] osc = new byte[1];
    byte[] bright = new byte[1];
    byte[] blink = new byte[1];
    byte[] off = new byte[1];

    byte[] a = new byte[2];
    a[0] = (byte)0b01111000;
    a[1] = (byte) 0b00000101;

    osc[0] = (byte)0x21;
    bright[0] = (byte)0xEF;
    blink[0] = (byte)0x81;
    off[0] = (byte)0x00;

    i2c.writeBulk(osc);
    Timer.delay(1);
    i2c.writeBulk(bright);
    Timer.delay(1);
    i2c.writeBulk(blink);
    Timer.delay(1);
    i2c.writeBulk(off);
    Timer.delay(0.1);

    //Writes 'a' to the far left char on the display
    byte[] array = {off[0],off[0],off[0],off[0],a[0],a[1]};
    i2c.writeBulk(array);
    Timer.delay(5);

    /**
     * I hypothesize that the i2c device reads bytes in chunks of eight
     * Each pair represents one 15 segment display.
     * Hypothesis: The first eight lights (from documentation) will come one, then the next set.
     */
    byte[] repititionArray = {((byte)0b00000000),((byte)0b00000001),off[0],off[0],off[0],off[0],off[0],off[0]};
    i2c.writeBulk(repititionArray);
    Timer.delay(1);
    repititionArray[1] = (byte)0b00000011;
    i2c.writeBulk(repititionArray);
    Timer.delay(1);
    repititionArray[1] = (byte)0b00000111;
    i2c.writeBulk(repititionArray);
    Timer.delay(1);
    repititionArray[1] = (byte)0b00001111;
    i2c.writeBulk(repititionArray);
    Timer.delay(1);
    repititionArray[1] = (byte)0b00011111;
    i2c.writeBulk(repititionArray);
    Timer.delay(1);
    repititionArray[1] = (byte)0b00111111;
    i2c.writeBulk(repititionArray);
    Timer.delay(1);
    repititionArray[1] = (byte)0b01111111;
    i2c.writeBulk(repititionArray);
    Timer.delay(1);
    repititionArray[1] = (byte)0b11111111;
    i2c.writeBulk(repititionArray);
    Timer.delay(1);
//Switch between vars
    repititionArray[0] = (byte)0b00000001;
    i2c.writeBulk(repititionArray);
    Timer.delay(1);
    repititionArray[0] = (byte)0b00000011;
    i2c.writeBulk(repititionArray);
    Timer.delay(1);
    repititionArray[0] = (byte)0b00000111;
    i2c.writeBulk(repititionArray);
    Timer.delay(1);
    repititionArray[0] = (byte)0b00001111;
    i2c.writeBulk(repititionArray);
    Timer.delay(1);
    repititionArray[0] = (byte)0b00011111;
    i2c.writeBulk(repititionArray);
    Timer.delay(1);
    repititionArray[0] = (byte)0b00111111;
    i2c.writeBulk(repititionArray);
    Timer.delay(1);
    repititionArray[0] = (byte)0b01111111;
    i2c.writeBulk(repititionArray);
    Timer.delay(1);
    repititionArray[0] = (byte)0b11111111;
    i2c.writeBulk(repititionArray);
    Timer.delay(1);


  }

  @Override
  public void teleopPeriodic() {



  }
}
