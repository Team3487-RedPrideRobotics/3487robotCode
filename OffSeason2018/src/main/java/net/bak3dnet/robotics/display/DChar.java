package net.bak3dnet.robotics.display;

/**
 * 
 * @author Jake Armstrong
 * @version 0.1.0
 * @since 0.1.0
 * 
 */
class DChar {

    private byte[] matrixData;
    private char encapsulatedChar;
    
/**
 * Don't use the DChar constructor 
 * @see DCharFactory
 * 
 * @param encapsulatedChar The encapsulated char that is represented
 * @param hasDecimalPoint True if there should be a decimal in the current display character
 * @param matrixData The binary data sent to the i2c device
 * 
 */
    public DChar(char encapsulatedChar, boolean hasDecimalPoint, byte[] matrixData) {

        this.matrixData = matrixData;

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