package net.bak3dnet.robotics.display.modules;

import net.bak3dnet.robotics.display.RevDigitDisplay;
import net.bak3dnet.robotics.display.modules.DisplayModuleBase;

public class RapidDiagnosticsModule implements DisplayModuleBase {

    private DisplayModuleBase activeModule;


    @Override
    public void task(RevDigitDisplay display, double delta) {



        activeModule.task(display, delta);

    }

    @Override
    public void close() {}

    public void switchModule(DisplayModuleBase module) {

        if(!(module instanceof RapidDiagnosticsModule)) {

            activeModule.close();

            activeModule = module;

        }

    }

}
