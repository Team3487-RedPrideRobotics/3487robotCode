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
    static{

        Map<Character,byte[]> aMap = new HashMap<Character,byte[]>();

        //This is the only way I currently understand how to set the matrix
        byte[] char0 = {(byte) 0b00000000,(byte)0b00000000};
        aMap.put('0', char0);
        
        characterRegistry = Collections.unmodifiableMap(aMap);

    }

    /**
     * 
     * @param beforeString
     * @return Returns a processed array of DChars.
     */
    public static DChar[] createDChars(String beforeString) {
        //Regex stuff
        Pattern periods = Pattern.compile("[^1-z]");
        Matcher matcher = periods.matcher(beforeString);

        int periodCount = 0;

        //Counts all the periods in the string
        while(matcher.find()) {

            periodCount++;

        }

        //Makes an array of all the DChars without the inlcuded periods.
        DChar[] convertedString = new DChar[beforeString.length()-periodCount];
        
        for(int i = 0; i < beforeString.length(); i++) {

            //Because I am lazy
            char preChar = beforeString.charAt(i);

            /**
             * If it has a decimal for the next character, we want that to bring the period to the end of this character
             * so that the period can be on the character's display.
             * It also skips over the period character the next iteration.
             */
            if(beforeString.charAt(i+1) == '.') {

                convertedString[i] = new DChar(getBinary(preChar));
                i++;

            } else {

                convertedString[i] = new DChar(getBinary(preChar));

            }  
        
        }

        //Returns the converted String
        return convertedString;

    }


    private static byte[] getBinary(char charToMatrix) {

        

        //Tempoary
        return new byte[2];

    } 

}