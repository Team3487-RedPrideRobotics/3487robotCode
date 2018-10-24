package net.bak3dnet.robotics.display.modules;

import net.bak3dnet.robotics.display.modules.DisplayModuleBase;
import net.bak3dnet.robotics.display.RevDigitDisplay;
import net.bak3dnet.robotics.display.DChar;
import net.bak3dnet.robotics.display.DCharFactory;

import java.util.Arrays;

public class TickerTapeModule implements DisplayModuleBase {

    protected String displayText;

    /**
     * Characters per minute
     */
    protected double charPassRate;

    protected int sumDeltaTime;
    protected int currentPosition;
    protected int roundsCompleted;
    protected int spacing;
    protected DChar[] displayBuffer;
    protected DChar[] outDString;

    public TickerTapeModule() {

        Arrays.fill(outDString, DCharFactory.getDChar(' ', false));

        this.charPassRate = 50;

    }
    
    @Override
    public void task(RevDigitDisplay display, double deltaTime) {

        sumDeltaTime += deltaTime;

        if(sumDeltaTime >= (charPassRate/60)*1000) {

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

    /**
     * Sets an object to be stringified.
     * @param object The object to be stringified.
     */

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

    /**
     * Displays a boolean on the display.
     * 
     * @param bool The boolean to be evaluated.
     * @param displayAsWord Whether to describe as true or 1.
     */

    public void setDisplayText(boolean bool, boolean displayAsWord) {

        if(displayAsWord) {
            setDisplayText(String.valueOf(bool));
        } else {

            setDisplayText(bool ? "1":"0");

        }

    }

    /**
     * Displays a boolean, with spacing!
     * 
     * @param bool The boolean to be evaluated.
     * @param displayAsWord Whether to describe as false or 0.
     * @param spacing The spacing between each showing of the data.
     */

    public void setDisplayText(boolean bool, boolean displayAsWord, int spacing) {

        if(displayAsWord) {

            setDisplayText(String.valueOf(bool), spacing);

        } else {

            setDisplayText(bool ? "1":"0", spacing);

        }

    }

    /**
     * Displays an integer on the display.
     * 
     * @param number The integer to be displayed.
     */

    public void setDisplayText(int number) {

        setDisplayText(Integer.toString(number));

    }

    /**
     * 
     * Displays an integer on the display.
     * 
     * @param number The integer to be displayed.
     * @param spacing SPACING! How far you want inbetween the digits.
     */

    public void setDisplayText(int number, int spacing) {

        setDisplayText(Integer.toString(number), spacing);

    }

    /**
     * Displays a byte on the display. Note that it displays the binary, not the raw byte on the leds. See {@link DChar}'s constructor.
     * 
     * @param Byte The byte to be displayed.
     */

    public void setDisplayText(byte Byte) {

        String binaryString = String.format("%8s", Integer.toBinaryString((int)Byte&0xFF)).replace(' ','0');
    
        this.setDisplayText(binaryString);
    }

    /**
     * 
     * Displays a byte on the display. Note that it displays the binary, not the raw byte on the leds. See {@link DChar}'s constructor.
     * 
     * @param Byte The byte to be displayed.
     * @param spacing The spacing between binary segments.
     */

    public void setDisplayText(byte Byte, int spacing) {

        String binaryString = String.format("%8s", Integer.toBinaryString((int)Byte&0xFF)).replace(' ','0');
    
        this.setDisplayText(binaryString, spacing);
    }

    /**
     * 
     * Displays a <code>long</code> number.
     * 
     * @param number The number.
     */

    public void setDisplayText(long number) {

        setDisplayText(Long.toString(number));

    }

    /**
     * Displays a <code>long</code> number.
     * 
     * @param number The number.
     * @param spacing The spacing between digits.
     */

    public void setDisplayText(long number, int spacing) {

        setDisplayText(Long.toString(number), spacing);

    }

    /**
     * Displays a <code>short</code> number.
     * 
     * @param number The number.
     */

    public void setDisplayText(short number) {

        setDisplayText(Short.toString(number));

    }

    /**
     * Displays a <code>short</code> number.
     * 
     * @param number The number.
     * @param spacing The spacing between the digits.
     */

    public void setDisplayText(short number, int spacing) {

        setDisplayText(Short.toString(number), spacing);

    }

    /**
     * 
     * Displays a <code>float</code> number.
     * 
     * @param number The number.
     */

    public void setDisplayText(float number) {

        setDisplayText(Float.toString(number));
        
    }

    /**
     * 
     * Displays a <code>float</code> number.
     * 
     * @param number The number.
     * @param spacing The spacing between the digits.
     */

    public void setDisplayText(float number, int spacing) {

        setDisplayText(Float.toString(number), spacing);

    }

    /**
     * 
     * Displays a <code>double</code> number.
     * 
     * @param number The number.
     */

    public void setDisplayText(double number) {

        setDisplayText(Double.toString(number));

    }

    /**
     * 
     * Displays <code>double</code> number.
     * 
     * @param number The number.
     * @param spacing The Spacing®. (Not actually registered)
     */
      
    public void setDisplayText(double number, int spacing) {

        setDisplayText(Double.toString(number), spacing);

    }

    /**
     * 
     * Displays a single character on the display.
     * 
     * @param character The character to be displayed.
     */

    public void setDisplayText(char character) {

        setDisplayText(Character.toString(character));

    }

    /**
     * Displays a single character on the display.
     * 
     * @param character The character.
     * @param spacing Space jvm. Space. Space. Space. Espai. Espacio. Espace. Locus. Chaw seem. 宇宙. 空間. 공간. (The spacing between characters).
     */

    public void setDisplayText(char character, int spacing) {

        setDisplayText(Character.toString(character), spacing);

    }

    /**
     * Stringifies objects and puts them on the display 
     * @param objects  The objects to be stringified.(☉_☉)
     */

    public void setDisplayText(Object[] objects) {

        String primaryString = new String();

        for(int i = 0; i < objects.length; i++) {

            primaryString += objects[i].toString();
            primaryString += " ";

        }

        setDisplayText(primaryString);

    }

    /**
     * The objects to be stringified. 
     * 
     * @param objects Objects to be STRINGIFIED. ~~~~~~~~~~~~~~~~~~~~~~~
     * @param spacing スペースing. 
     */

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

    /**
     * Gets the current position of the display.
     * @return The current position.
     */

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
      
    /**
    * Gets the current postion of the string.
    * @return Returns the index of the last character on the display.
    */

    public int getCurrentPosition() {

        return currentPosition;

    }

    /**
     * Resets the position.
     */
      
    public void resetPosition(){

        currentPosition = 0;

    }
      
    /**
     * @param charPassRate the charPassRate to set
     */
    public void setCharPassRate(double charPassRate) {
        this.charPassRate = charPassRate;
    }

    @Override
    public String toString() {

        return "Ticker Tape";

    }

}
