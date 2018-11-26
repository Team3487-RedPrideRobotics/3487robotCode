package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import net.bak3dnet.robotics.displays.RevDigitDisplay;
import net.bak3dnet.robotics.displays.modules.BatteryPercentModule;

import net.bak3dnet.robotics.led.LightDrive12;
import net.bak3dnet.robotics.led.modules.AGradientModule;
import net.bak3dnet.robotics.led.modules.util.GradientMap;
import net.bak3dnet.robotics.led.Color;

public class LEDBot extends IterativeRobot {

  // RobotController.getBatteryVoltage();
  SerialPort ledController;

  Spark frontRight;
  Spark backRight;

  Spark frontLeft;
  Spark backLeft;

  Joystick leftStick;
  Joystick rightStick;

  @Override
  public void robotInit() {

    leftStick = new Joystick(0);
    rightStick = new Joystick(1);

    frontRight = new Spark(0);
    backRight = new Spark(1);

    frontLeft = new Spark(2);
    backLeft = new Spark(3);

    RevDigitDisplay display = RevDigitDisplay.getInstance();
    display.setActiveModule(new BatteryPercentModule(12D));

    /*
    //Led Controller Test Code
    ledController = new SerialPort(115200, Port.kMXP);
    ledController.setWriteBufferMode(SerialPort.WriteBufferMode.kFlushOnAccess);

    byte[] dataOut = new byte[14];
    Arrays.fill(dataOut, (byte)0x00);
    dataOut[0] = (byte)0xAA;

    //Sets Strip One to Soft White
    /*dataOut[1] = (byte)0xee; //Green
    dataOut[2] = (byte)0xfb; //Red
    dataOut[3] = (byte)0xe4; //Blue
    */
    dataOut[2] = (byte)0x77;

    //dataOut[1] = (byte)0xbf;
    //dataOut[2] = (byte)0x53;
    //dataOut[3] = (byte)0xc6;
    byte checkSum = 0;
    for(byte i =0; i <13;i++) {

      checkSum += dataOut[i];

    }

    dataOut[13] = checkSum;
    /*
    for(byte i=0;i<14;i++) {

      dataOut[i]= (byte)~dataOut[i];

    }*/

    ledController.write(dataOut, 14);
*/

    LightDrive12 ld12 = new LightDrive12(Port.kOnboard);
    GradientMap colors = new GradientMap(new Color("F00"),0L);
    colors.put(new Color("FF0"), 150L);
    colors.put(new Color("0F0"), 150L);
    colors.put(new Color("0Ff"), 150L);
    colors.put(new Color("00f"), 150L);
    colors.put(new Color("F0F"), 150L);
    colors.put(new Color("F00"), 150L);

    ld12.setChannelModule(new AGradientModule(colors));
  
  }

  @Override
  public void teleopInit() {

    myRobot.tankDrive(leftStick.getY(), rightStick.getY());

  }
  
}
