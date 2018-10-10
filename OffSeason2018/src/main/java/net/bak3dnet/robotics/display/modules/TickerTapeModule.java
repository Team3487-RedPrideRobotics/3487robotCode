package net.bak3dnet.robotics.display.modules;

import net.bak3dnet.robotics.display.modules.DisplayModuleBase;
import net.bak3dnet.robotics.display.RevDigitDisplay;
import net.bak3dnet.robotics.display.DChar;

public class TickerTapeModule implements DisplayModuleBase {

    protected String displayText;

    //Characters passing thru the display per minute
    protected double charPassRate;

    protected int sumDeltaTime;
    protected int currentPosition;
    protected int roundsCompleted;
    protected int spacing;
    
    @Override
    public void task(RevDigitDisplay display, double deltaTime) {

        sumDeltaTime += deltaTime;

        if(sumDeltaTime >= charPassRate) {

            display.setText(getCurrentDChars());

        }

    }

    @Override
    public void close() {

        resetPosition();
        sumDeltaTime = 0;
        roundsCompleted = 0;

    }

    public void setDisplayText(Object object) {

        displayText = object.toString();

    }

    public void setDisplayText(Object object, int spacing) {

        displayText = object.toString();

    }

    public void setDisplayText(boolean[] bool) {}

    public void setDisplayText(boolean[] bool, int spacing) {}

    public void setDisplayText(int number) {}

    public void setDisplayText(int number, int spacing) {}

    public void setDisplayText(byte bytes) {

        String binaryString = String.format("%8s", Integer.toBinaryString((int)bytes&0xFF)).replace(' ','0');
    
        this.setDisplayText(binaryString);
    }

    public void setDisplayText(byte bytes, int spacing) {

        String binaryString = String.format("%8s", Integer.toBinaryString((int)bytes&0xFF)).replace(' ','0');
    
        this.setDisplayText(binaryString, spacing);
    }

    public void setDisplayText(long number) {}

    public void setDisplayText(long number, int spacing) {}

    public void setDisplayText(short number) {}

    public void setDisplayText(short number, int spacing) {}

    public void setDisplayText(float number) {}

    public void setDisplayText(float number, int spacing) {}

    public void setDisplayText(double number) {}

    public void setDisplayText(double number, int spacing) {}

    public void setDisplayText(char character) {}

    public void setDisplayText(char character, int spacing) {}

    public void setDisplayText(Object[] objects) {}

    public void setDisplayText(Object[] objects, int spacing) {}

    public DChar[] getCurrentDChars() {

        DChar[] outString = new DChar[4];

        //TODO Actually ticker.

        currentPosition++;

        return outString;

    }

    public int getCurrentPosition() {

        return currentPosition;

    }

    public void resetPosition(){

        currentPosition = 0;

    }

    @Override
    public String toString() {

        return "Ticker Tape";

    }

}