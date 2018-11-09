package frc.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Port;
import java.util.Arrays;

public class LEDBot extends IterativeRobot {

  //RobotController.getBatteryVoltage();
  SerialPort ledController;

  @Override
  public void robotInit() {

     ledController = new SerialPort(115200, Port.kOnboard);
     byte[] dataOut = new byte[14];
     Arrays.fill(dataOut, (byte)0x00);
     dataOut[0] = (byte)0xAA;
     dataOut[1] = (byte)0xff;
     dataOut[2] = (byte)0xff;
     
     short checkSum = 0;
     for(short i =0; i <13;i++) {

       checkSum += dataOut[i];

     }
     dataOut[13] = (byte) checkSum;

     ledController.write(dataOut, 14);


  }

  @Override
  public void teleopPeriodic() {



  }
  
}
