package net.bak3dnet.robotics.display.modules;

import net.bak3dnet.robotics.display.RevDigitDisplay;

public interface DisplayModuleBase {

    public void task(RevDigitDisplay display, double deltaTime);

    public void close();

    @Override
    public String toString();

}
