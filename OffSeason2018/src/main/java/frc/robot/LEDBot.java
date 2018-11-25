package frc.robot;

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

  DifferentialDrive myRobot;
  Joystick leftStick;
  Joystick rightStick;

  @Override
  public void robotInit() {

    myRobot = new DifferentialDrive(new Spark(0), new Spark(1));
    leftStick = new Joystick(0);
    rightStick = new Joystick(1);

    RevDigitDisplay display = RevDigitDisplay.getInstance();
    display.setActiveModule(new BatteryPercentModule(12D));

    /*
    //Led Controller Test Code
    ledController = new SerialPort(115200, Port.kOnboard);
    ledController.setWriteBufferMode(SerialPort.WriteBufferMode.kFlushOnAccess);

    byte[] dataOut = new byte[14];
    Arrays.fill(dataOut, (byte)0x00);
    dataOut[0] = (byte)0xAA;

    //Sets Strip One to Yellow
    dataOut[1] = (byte)0xff;
    dataOut[2] = (byte)0xff;
     
    byte checkSum = 0;
    for(byte i =0; i <13;i++) {

      checkSum += dataOut[i];

    }

    dataOut[13] = checkSum;

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
  public void teleopPeriodic() {

    myRobot.tankDrive(leftStick.getY(), rightStick.getY());

  }
  
}
