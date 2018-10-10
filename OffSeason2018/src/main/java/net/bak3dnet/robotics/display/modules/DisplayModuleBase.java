package net.bak3dnet.robotics.display.modules;

import net.bak3dnet.robotics.display.RevDigitDisplay;

public interface DisplayModuleBase {

    static boolean changed = false;

    public void task(RevDigitDisplay display, double deltaTime);

    public void close();

    @Override
    public String toString();

}
