package frc.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import net.bak3dnet.robotics.displays.RevDigitDisplay;
import net.bak3dnet.robotics.displays.modules.BatteryPercentModule;
import net.bak3dnet.robotics.displays.modules.RapidDiagnosticsModule;

public class TutorialBot extends IterativeRobot {

  @Override
  public void robotInit() {

    //This is an example as to how to setup the display.

    RevDigitDisplay display = RevDigitDisplay.getInstance();

    //The following shows that the display is given a new Rapid Diagnostics Module, which
    //is then given a new BatteryPercentModule

    display.setActiveModule(new RapidDiagnosticsModule(new BatteryPercentModule(12D)));

    //The layering of modules enables the Rapid Diagnostics Module to be given new modules.

  }

  @Override
  public void teleopPeriodic() {



  }
}