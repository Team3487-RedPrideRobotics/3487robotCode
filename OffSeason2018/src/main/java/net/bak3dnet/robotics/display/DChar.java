package net.bak3dnet.robotics.display;

/**
 * 
 * @author Jake Armstrong
 * @version 0.1.0
 * @since 0.1.0
 * 
 */
public class DChar {

    private byte[] matrixData;
    private char encapsulatedChar;

    @Override
    public String toString() {

        return Character.toString(encapsulatedChar);

    }
    
/**
 * Don't use the DChar constructor. Please see {@link DCharFactory}
 * 
 * @param encapsulatedChar The encapsulated char that is represented
 * @param hasDecimalPoint True if there should be a decimal in the current display character
 * @param matrixData The binary data sent to the i2c device
 * 
 */
    public DChar(char encapsulatedChar, boolean hasDecimalPoint, byte[] matrixData) {

        int preInt = (int) matrixData[1];

        if(hasDecimalPoint) {
            //This edits the bit with the decimal point
            preInt+=64;
            this.matrixData = new byte[2];

            this.matrixData[0] = matrixData[0];

            this.matrixData[1] =(byte) preInt;

        } else {

            this.matrixData=matrixData;
            
        }

    }
    
    /**
     * 
     * @return Returns a binary array for the led matrix.
     * 
     */
    public byte[] getBinary() {

        return this.matrixData;

    }
    
    /**
     * @return Returns the encapsulated char
    */

    public char getEncapsulatedChar() {

        return this.encapsulatedChar;

    }

}