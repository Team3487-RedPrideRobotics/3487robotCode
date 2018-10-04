package frc.robot;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.Timer;

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
    a[1] = (byte)0b01111000;
    a[0] = (byte) 0b00000101;

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
    i2c.writeBulk(a);
    Timer.delay(2);

  }

  @Override
  public void teleopPeriodic() {



  }
}
