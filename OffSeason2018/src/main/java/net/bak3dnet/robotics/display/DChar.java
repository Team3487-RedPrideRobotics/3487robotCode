package net.bak3dnet.robotics.display;

/**
 * 
 * @author Jake Armstrong
 * @version 0.1.0
 * @since 0.1.0
 * 
 */
class DChar {

    private byte[] matrixId;
    
/**
 * Don't use the DChar constructor
 * 
 * @param dchar
 * @param includesDecimal
 * 
 */
    public DChar(byte[] matrixId) {

        this.matrixId = matrixId;

    }
    
    /**
     * 
     * @return Returns a binary array for the led matrix.
     */
    public byte[] getBinary() {

        return this.matrixId;

    }
    



}