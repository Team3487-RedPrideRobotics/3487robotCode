package net.bak3dnet.robotics.display.modules;

import net.bak3dnet.robotics.display.RevDigitDisplay;
import net.bak3dnet.robotics.display.modules.DisplayModuleBase;

import java.util.ArrayList;
import java.util.List;

public class RapidDiagnosticsModule extends TickerTapeModule implements DisplayModuleBase {

    private DisplayModuleBase activeModule;
    private boolean introPlayed = false;
    private boolean moduleIntroPlayed = false;
    private List<DisplayModuleBase> modules;

    private int currentModule;

    public RapidDiagnosticsModule(DisplayModuleBase[] modules) {

        this.modules = new ArrayList<DisplayModuleBase>();

        for(int i = 0; i< modules.length; i++) {

            if(modules[i] instanceof RapidDiagnosticsModule) {

                continue;

            }

            this.modules.add(modules[i]);

            if(this.modules.size() == 0) {

                throw new IllegalArgumentException("You may not use a Rapid Diagnostics Module inside a RapidDiagnostics Module");

            }

        }

    }

    public RapidDiagnosticsModule(DisplayModuleBase module) {

        this.modules = new ArrayList<DisplayModuleBase>();
        
        if(!(module instanceof RapidDiagnosticsModule)) {

            this.modules.add(module);

        } else  {

            throw new IllegalArgumentException("You may not use a Rapid Diagnostics Module inside a RapidDiagnostics Module");

        }

    }

    @Override
    public void task(RevDigitDisplay display, double delta) {

        if(!introPlayed) {

            if(display.buttonB.get() == true) {

                introPlayed = true;

            }
            
            TickerTapeModule intro = new TickerTapeModule();
            intro.setDisplayText("Rapid Diagnostics Tool v0.1");
            intro.task(display, delta);
            if(intro.roundsCompleted >= 1) {

                introPlayed = true;

            }

        }

        if(!moduleIntroPlayed) {
        
            if(display.buttonB.get() == true) {

                moduleIntroPlayed = true;

            }

            super.task(display, delta);

            if(super.roundsCompleted >= 1) {

                introPlayed = true;

            }

        } else {

            activeModule.task(display, delta);
            
            if(display.buttonB.get() == true) {

                this.nextModule();

            }

        }
    }


    @Override
    public void close() {

        moduleIntroPlayed = false;

        introPlayed = false;
        
        super.close();

    }

    public void switchModule(DisplayModuleBase module) {

        if(!(module instanceof RapidDiagnosticsModule)) {

            activeModule.close();

            activeModule = module;

            setDisplayText(module.toString());

        }

    }

    public void nextModule() {

        try{

            switchModule(modules.get(currentModule));

        } catch(IndexOutOfBoundsException e) {

            currentModule =0;
            switchModule(modules.get(currentModule));
            return;

        }

        currentModule++;

    }

    @Override
    public String toString() {

        return "Rapid Diagnostics";

    }

}