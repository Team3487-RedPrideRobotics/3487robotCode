package net.bak3dnet.robotics.display;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Jake Armstrong
 * @since 0.1.0
 * @version 0.1.0
 */
public class DCharFactory {

    private static final Map<Character,byte[]> characterRegistry;

    //Initialize Static Variables
    static {

        Map<Character,byte[]> aMap = new HashMap<Character,byte[]>();

        //This is the only way I currently understand how to set the matrix
        byte[] char0 = {(byte) 0b00111111,(byte)0b00001100};
        aMap.put('0', char0);

        byte[] char1 = {(byte) 0b00000110,(byte)0b00000000};
        aMap.put('1', char1);

        byte[] char2 = {(byte) 0b11011011,(byte)0b00000000};
        aMap.put('2', char2);

        byte[] char3 = {(byte) 0b11001111,(byte)0b00000000};
        aMap.put('3', char3);

        byte[] char4 = {(byte) 0b11100110,(byte)0b00000000};
        aMap.put('4', char4);

        byte[] char5 = {(byte) 0b11101101,(byte)0b00000000};
        aMap.put('5', char5);

        byte[] char6 = {(byte) 0b11111101,(byte)0b00000000};
        aMap.put('6', char6);

        byte[] char7 = {(byte) 0b00000111,(byte)0b00000000};
        aMap.put('7', char7);

        byte[] char8 = {(byte) 0b11111111,(byte)0b00000000};
        aMap.put('8', char8);

        byte[] char9 = {(byte) 0b00100111,(byte)0b00000000};
        aMap.put('9', char9);

        byte[] charA = {(byte) 0b11110111,(byte)0b00000000};
        aMap.put('A', charA);

        byte[] charB = {(byte) 0b10001111,(byte)0b00010010};
        aMap.put('B', charB);

        byte[] charC = {(byte) 0b00111001,(byte)0b00000000};
        aMap.put('C', charC);

        byte[] charD = {(byte) 0b00001111,(byte)0b00010010};
        aMap.put('D', charD);

        byte[] charE = {(byte) 0b11111001,(byte)0b00000000};
        aMap.put('E', charE);

        byte[] charF = {(byte) 0b11110001,(byte)0b00000000};
        aMap.put('F', charF);

        byte[] charG = {(byte) 0b11111001,(byte)0b00000000};
        aMap.put('G', charG);

        byte[] charH = {(byte) 0b11110110,(byte)0b00000000};
        aMap.put('H', charH);

        byte[] charI = {(byte) 0b00001001,(byte)0b00010010};
        aMap.put('I', charI);

        byte[] charJ = {(byte) 0b00011001,(byte)0b00010010};
        aMap.put('J', charJ);

        byte[] charK = {(byte) 0b01110000,(byte)0b00100100};
        aMap.put('K', charK);

        byte[] charL = {(byte) 0b00111000,(byte)0b00000000};
        aMap.put('L', charL);

        byte[] charM = {(byte) 0b00110111,(byte)0b00000000};
        aMap.put('M', charM);

        byte[] charN = {(byte) 0b00110000,(byte)0b00010011};
        aMap.put('N', charN);

        byte[] charO = {(byte) 0b00111111,(byte)0b00000000};
        aMap.put('O', charO);

        byte[] charP = {(byte) 0b11110011,(byte)0b00000000};
        aMap.put('P', charP);

        byte[] charQ = {(byte) 0b00111111,(byte)0b00100000};
        aMap.put('Q', charQ);

        byte[] charR = {(byte) 0b11110011,(byte)0b00100000};
        aMap.put('R', charR);

        byte[] charS = {(byte) 0b00001001,(byte)0b00100001};
        aMap.put('S', charS);

        byte[] charT = {(byte) 0b00000001,(byte)0b00010010};
        aMap.put('T', charT);

        byte[] charU = {(byte) 0b00111110,(byte)0b00000000};
        aMap.put('U', charU);

        byte[] charV = {(byte) 0b00000000,(byte)0b00000101};
        aMap.put('V', charV);

        byte[] charW = {(byte) 0b00110011,(byte)0b00111010};
        aMap.put('W', charW);

        byte[] charX = {(byte) 0b00000000,(byte)0b00111111};
        aMap.put('X', charX);

        byte[] charY = {(byte) 0b00000000,(byte)0b00010101};
        aMap.put('Y', charY);

        byte[] charZ = {(byte) 0b00001001,(byte)0b00001100};
        aMap.put('Z', charZ);

        byte[] charSpace = {(byte) 0b00000000,(byte)0b00000000};
        aMap.put(' ', charSpace);
        
        characterRegistry = Collections.unmodifiableMap(aMap);

    }

    /**
     * 
     * @param beforeString The string before it is converted into a DChar Array
     * @return Returns a processed array of DChars.
     * 
     */
    public static DChar[] getDChars(String beforeString) {
        //Regex stuff
        Pattern periods = Pattern.compile("[^1-z]");
        Matcher matcher = periods.matcher(beforeString);

        int periodCount = 0;

        //Counts all the periods in the string
        while(matcher.find()) {

            periodCount++;

        }

        String CAPSLOCK = beforeString.toUpperCase();

        //Makes an array of all the DChars without the inlcuded periods.
        DChar[] convertedString = new DChar[beforeString.length()-periodCount];
        
        for(int i = 0; i < beforeString.length(); i++) {

            //Because I am lazy
            char preChar = CAPSLOCK.charAt(i);

            /**
             * If it has a decimal for the next character, we want that to bring the period to the end of this character
             * so that the period can be on the character's display.
             * It also skips over the period character the next iteration.
             */
            if(beforeString.charAt(i+1) == '.') {

                convertedString[i] = new DChar(preChar, true, getBinaryMatrix(preChar));
                i++;

            } else {

                convertedString[i] = new DChar(preChar, false, getBinaryMatrix(preChar));

            }  
        
        }

        //Returns the converted String
        return convertedString;

    }

    public static DChar getDChar(char preChar, boolean withDecimal) {

        String capStr = Character.toString(preChar);

        char capChar = capStr.toUpperCase().toCharArray()[0];

        return new DChar(capChar, withDecimal, characterRegistry.get(capChar));

    }

    /**
     * @return Returns the matrix data for the passed char
     * 
     * @param characterToMatrix The character to become the matrix
     */
    private static byte[] getBinaryMatrix(char charToMatrix) {

        byte[] defaultValue = {(byte)0b11111111,(byte)0b11111011};

        return characterRegistry.getOrDefault(charToMatrix,defaultValue);

    } 

}