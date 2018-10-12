package net.bak3dnet.robotics.display.modules;

import net.bak3dnet.robotics.display.modules.DisplayModuleBase;
import net.bak3dnet.robotics.display.RevDigitDisplay;
import net.bak3dnet.robotics.display.DChar;
import net.bak3dnet.robotics.display.DCharFactory;

import java.util.Arrays;

public class TickerTapeModule implements DisplayModuleBase {

    protected String displayText;

    //Characters passing thru the display per minute
    protected double charPassRate;

    protected int sumDeltaTime;
    protected int currentPosition;
    protected int roundsCompleted;
    protected int spacing;
    protected DChar[] displayBuffer;
    protected DChar[] outDString;

    public TickerTapeModule() {

        Arrays.fill(outDString, DCharFactory.getDChar(' ', false));

    }
    
    @Override
    public void task(RevDigitDisplay display, double deltaTime) {

        sumDeltaTime += deltaTime;

        if(sumDeltaTime >= charPassRate) {

            display.setText(getCurrentDChars());
            sumDeltaTime = 0;

        }

    }

    @Override
    public void close() {

        resetPosition();
        sumDeltaTime = 0;
        roundsCompleted = 0;

    }

    public void setDisplayText(Object object) {

        if(object.toString().charAt(object.toString().length()-1) == ' ') {

            setDisplayText(object.toString(),0);

        } else {

            setDisplayText(object.toString(), 1);

        }

    }

    /**
     * 
     * @param object The object to be turned into a string
     * @param spacing The spacing between the re-occuring string.
     */
    public void setDisplayText(Object object, int spacing) {

        displayText = object.toString();

        for(int i = 0; i < spacing; i++) {

            displayText += " ";

        }

        if(object.toString().length() < 4) {

            String spacingBuffer = displayText;

            for(int i = 0; i < 4-object.toString().length(); i++) {

                spacingBuffer = " " + spacingBuffer;

            }

            displayText = spacingBuffer;

        }
        
        this.displayBuffer = DCharFactory.getDChars(displayText);

        this.close();

    }

    public void setDisplayText(boolean bool, boolean displayAsWord) {

        if(displayAsWord) {
            setDisplayText(String.valueOf(bool));
        } else {

            setDisplayText(bool ? "1":"0");

        }

    }

    public void setDisplayText(boolean bool, boolean displayAsWord, int spacing) {

        if(displayAsWord) {

            setDisplayText(String.valueOf(bool), spacing);

        } else {

            setDisplayText(bool ? "1":"0", spacing);

        }

    }

    public void setDisplayText(int number) {

        setDisplayText(Integer.toString(number));

    }

    public void setDisplayText(int number, int spacing) {

        setDisplayText(Integer.toString(number), spacing);

    }

    public void setDisplayText(byte Byte) {

        String binaryString = String.format("%8s", Integer.toBinaryString((int)Byte&0xFF)).replace(' ','0');
    
        this.setDisplayText(binaryString);
    }

    public void setDisplayText(byte Byte, int spacing) {

        String binaryString = String.format("%8s", Integer.toBinaryString((int)Byte&0xFF)).replace(' ','0');
    
        this.setDisplayText(binaryString, spacing);
    }

    public void setDisplayText(long number) {

        setDisplayText(Long.toString(number));

    }

    public void setDisplayText(long number, int spacing) {

        setDisplayText(Long.toString(number), spacing);

    }

    public void setDisplayText(short number) {

        setDisplayText(Short.toString(number));

    }

    public void setDisplayText(short number, int spacing) {

        setDisplayText(Short.toString(number), spacing);

    }

    public void setDisplayText(float number) {

        setDisplayText(Float.toString(number));
        
    }

    public void setDisplayText(float number, int spacing) {

        setDisplayText(Float.toString(number), spacing);

    }

    public void setDisplayText(double number) {

        setDisplayText(Double.toString(number));

    }

    public void setDisplayText(double number, int spacing) {

        setDisplayText(Double.toString(number), spacing);

    }

    public void setDisplayText(char character) {

        setDisplayText(Character.toString(character));

    }

    public void setDisplayText(char character, int spacing) {

        setDisplayText(Character.toString(character), spacing);

    }

    public void setDisplayText(Object[] objects) {

        String primaryString = new String();

        for(int i = 0; i < objects.length; i++) {

            primaryString += objects[i].toString();
            primaryString += " ";

        }

        setDisplayText(primaryString);

    }

    public void setDisplayText(Object[] objects, int spacing) {

        String primaryString = new String();

        for(int i = 0; i < objects.length;i++) {

            primaryString += objects[i].toString();
            for(int j = 0; j < spacing; j++) {

                primaryString+=" ";

            }

        }

        setDisplayText(primaryString);

    }

    public DChar[] getCurrentDChars() {

        outDString[0] = outDString[1];
        outDString[1] = outDString[2];
        outDString[2] = outDString[3];

        try {

            outDString[3] = displayBuffer[currentPosition];

        } catch(IndexOutOfBoundsException e) {

            outDString[3] = displayBuffer[currentPosition];
            currentPosition = 0;
            roundsCompleted++;

        }

        currentPosition++;

        return outDString;

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