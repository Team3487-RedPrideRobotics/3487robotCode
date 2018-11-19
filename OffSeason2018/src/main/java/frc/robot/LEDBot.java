package frc.robot;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Port;
import java.util.Arrays;

import net.bak3dnet.robotics.displays.RevDigitDisplay;
import net.bak3dnet.robotics.displays.modules.BatteryPercentModule;;

public class LEDBot extends IterativeRobot {

  //RobotController.getBatteryVoltage();
  SerialPort ledController;

  DifferentialDrive myRobot;
  Joystick leftStick;
  Joystick  rightStick;

  @Override
  public void robotInit() {

    myRobot = new DifferentialDrive(new Spark(0), new Spark(1));
    leftStick = new Joystick(0);
    rightStick = new Joystick(1);

    RevDigitDisplay display = RevDigitDisplay.getInstance();
    display.setActiveModule(new BatteryPercentModule(12D));

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

  }

  @Override
  public void teleopPeriodic() {

    
    myRobot.tankDrive(leftStick.getY(), rightStick.getY());

  }
  
}
