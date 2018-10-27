package frc.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Robot extends IterativeRobot {
  private DifferentialDrive myRobot;
  private Joystick leftStick;
  private Joystick rightStick;

  //RobotController.getBatteryVoltage();

  @Override
  public void robotInit() {
    myRobot = new DifferentialDrive(new Spark(0), new Spark(1));
    leftStick = new Joystick(0);
    rightStick = new Joystick(1);
  }

  @Override
  public void teleopPeriodic() {
    myRobot.tankDrive(leftStick.getY(), rightStick.getY());
  }
  
}
