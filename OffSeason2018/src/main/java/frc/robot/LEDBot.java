package frc.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Port;
import java.util.Arrays;

import net.bak3dnet.robotics.displays.RevDigitDisplay;
import net.bak3dnet.robotics.displays.modules.BatteryPercentModule;;

public class LEDBot extends IterativeRobot {

  //RobotController.getBatteryVoltage();
  SerialPort ledController;

  @Override
  public void robotInit() {

    RevDigitDisplay display = RevDigitDisplay.getInstance();
    display.setActiveModule(new BatteryPercentModule(12D));

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

    for(int i=0;i<13;i++) {

      dataOut[i] =(byte) ~ dataOut[i];

    }

    ledController.write(dataOut, 14);

  }

  @Override
  public void teleopPeriodic() {



  }
  
}
