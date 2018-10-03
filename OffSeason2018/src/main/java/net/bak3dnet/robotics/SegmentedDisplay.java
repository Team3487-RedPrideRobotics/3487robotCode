package net.bak3dnet.robotics;

import frc.robot.Team3487Bot;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.AnalogInput;

class SegmentedDisplay {

    I2C i2c = void;

    public Display12Segment() {

        i2c = new I2C(Port.kMXP,0x70);

    }


}