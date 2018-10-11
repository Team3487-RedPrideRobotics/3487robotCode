package net.bak3dnet.robotics.display;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;

import net.bak3dnet.robotics.display.modules.DisplayModuleBase;
import net.bak3dnet.robotics.display.modules.TickerTapeModule;

/**
 * 
 * @author Jake Armstrong
 * @version 0.0.1
 * @since 0.1.0
 * 
 */
public class RevDigitDisplay {

    I2C i2c;

    private static RevDigitDisplay singleton;

    /**
     * Processor Chip Commands:
     * Source: https://cdn-shop.adafruit.com/datasheets/ht16K33v110.pdf
     * 
     * Constants!
     * 
     */

     /**
      * Sets the display to active.
      */
    public static final byte[] OSCILATOR_ON = {(byte) 0x21}; 
    /**
     *  Sets the display to inactive.
     */
    public static final byte[] OSCILATOR_OFF = {(byte) 0x20};

    /**
     * Sets the blinking to off.
     */
    private final byte[] DEFAULT_BLINKING = {(byte) 0x81};

    /**
     * Sets the brightness to bright.
     */
    private final byte[] DEFAULT_BRIGHTNESS = {(byte) 0xEF};

    public DigitalInput buttonA;
    public DigitalInput buttonB;

    public AnalogInput potentiometer;

    private DisplayModuleBase activeModule;
    private DisplayTaskManager taskManager;

    private Thread taskCoordinator;


    private static class DisplayTaskManager implements Runnable{

        private RevDigitDisplay display;

        public DisplayTaskManager(RevDigitDisplay display) {

            this.display = display;

        }

        public void run() {
            long previousTime = System.currentTimeMillis();
            while(!Thread.interrupted()){

                long currentTime = System.currentTimeMillis();

                short deltaTime = (short)(currentTime - previousTime);

                display.getActiveModule().task(this.display,deltaTime);

                previousTime = currentTime;

                try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
                    break;
                }

            }

        }

    }

    /**
     * Checks to see that a singleton has been made.
     */
    private static void singletonCheck() {

        if(singleton == null) {
            singleton = new RevDigitDisplay();
        }

    }
    /**
     * 
     * @return Returns the singleton class.
     */
    public static RevDigitDisplay getInstance() {

        singletonCheck();
        return singleton;

    }
    
    /**
     * @param setToString The string to pre initialize with.
     * 
     * @return Returns the singleton class, pre-initialized to ticker whatever string you have set.
     * 
     */
    public static RevDigitDisplay getInstance(String setToString) {

        singletonCheck();

        TickerTapeModule module = new TickerTapeModule();
        module.setDisplayText(setToString);

        singleton.setActiveModule(module);

        return singleton;

    }

    /**
     * @param dChars A set of dChars to pre-initialize with.
     * 
     *@return Returns the singleton class, pre-initialized to ticker whatever string you have set.
     *  
     */
    public static RevDigitDisplay getInstance(DChar[] dChars) {

        singletonCheck();

        TickerTapeModule tickerTapeModule = new TickerTapeModule();
        
        tickerTapeModule.setDisplayText(dChars);

        singleton.setActiveModule(tickerTapeModule);

        return singleton;

    }

    /**
     * @param number A double to be pre-initialized with.
     *@return Returns the singleton class, pre-initialized to ticker whatever string you have set.
     *  
     */
    public static RevDigitDisplay getInstance(double number) {

        singletonCheck();

        TickerTapeModule tickerTapeModule = new TickerTapeModule();
        tickerTapeModule.setDisplayText(number);

        singleton.setActiveModule(tickerTapeModule);

        return singleton;
    }

    private RevDigitDisplay() {

        buttonA = new DigitalInput(19);
        buttonB = new DigitalInput(20);

        potentiometer = new AnalogInput(3);

        i2c = new I2C(Port.kMXP,0x70);
        
        i2c.writeBulk(OSCILATOR_ON);

        i2c.writeBulk(DEFAULT_BRIGHTNESS);

        i2c.writeBulk(DEFAULT_BLINKING);

        taskManager = new DisplayTaskManager(this);

    }

    /**
     * 
     * @param module Whichever module you want to have set to display.
     * 
     */
    public void setActiveModule(DisplayModuleBase module) {

        if(this.taskCoordinator != null) {
            
            this.taskCoordinator.interrupt();
        
        }
        
        this.activeModule = module;
        this.taskCoordinator = new Thread(taskManager);
        this.taskCoordinator.start();



    }

    /**
     * 
     * @return Returns the current module that is being displayed right now.
     * 
     */
    public DisplayModuleBase getActiveModule() {

        return this.activeModule;

    }

    /**
     * Sets the string that is displayed on the display
     * 
     * @param text The array of DChars that will scroll on the displays
     * Warning the DChar Array will be truncated to four characters.
     * 
     */
    public void setText(DChar[] text) {

        DChar[] truncated = new DChar[4];

        if(text.length>4) {

            for(int i =0; i <4;i++) {

                truncated[i] = text[i];

            }

        } else {

            truncated = text;

        }

        byte[] preSend =  {(byte)0b00001111,(byte)0b00001111}; 

        i2c.writeBulk(preSend);

    }

    /**
     * Sets the string that is displayed on the display;
     * 
     * @param text The string that will be displayed on the display.
     * Warning! The string will be truncated to four characters.
     */
    public void setText(String text) {

        DChar[] unTruncated = DCharFactory.getDChars(text);
        DChar[] truncated = new DChar[4];

        if(text.length() > 4) {

            for(int i = 0; i<4; i++) {

                truncated[i] = unTruncated[i];
    
            }

        } else {

            truncated = unTruncated;

        }

        setText(truncated);

    }

}