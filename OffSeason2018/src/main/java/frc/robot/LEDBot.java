package frc.robot;

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

  Spark frontRight;
  Spark backRight;

  Spark frontLeft;
  Spark backLeft;

  Joystick leftStick;
  Joystick  rightStick;

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

  }

  @Override
  public void teleopInit() {

    byte[] dataOut = new byte[14];
    Arrays.fill(dataOut, (byte)0x00);
    dataOut[0] = (byte)0xAA;

    //Sets Strip One to Soft White
    dataOut[1] = (byte)0xee; //Green
    dataOut[2] = (byte)0xfb; //Red
    dataOut[3] = (byte)0x00; //Blue
    
    //dataOut[2] = (byte)0x77;

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

  }

  @Override
  public void teleopPeriodic() {

    frontLeft.set(-leftStick.getRawAxis(1));    
    backLeft.set(-leftStick.getRawAxis(1));

    frontRight.set(rightStick.getRawAxis(1));
    backRight.set(rightStick.getRawAxis(1));

  }
  
}
