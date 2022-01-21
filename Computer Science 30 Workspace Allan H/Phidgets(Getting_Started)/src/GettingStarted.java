import com.phidget22.*;

//Add Phidgets Library | You added a file called phidget22 when configuring your project. Import gives you access to the Phidgets library code inside that file. 

public class GettingStarted {
    //Handle Exceptions | Exceptions will happen in your code from time to time. These are caused by unexpected things happening. Make sure you’ve added "throws Exception" to your main method.
    public static void main(String[] args) throws Exception{
    	
    	

        //Create | Here you've created a DigitalOutput object for your LED. An object represents how you interact with your device. DigitalOutput is a class from the Phidgets library that's used to provide a voltage to things like LEDs.
        DigitalOutput redLED = new DigitalOutput();
        DigitalOutput greenLED = new DigitalOutput();
        DigitalInput redButton = new DigitalInput();
        DigitalInput greenButton = new DigitalInput();

        //Address | This tells your program where to find the device you want to work with. Your LED is connected to port 1 and your code reflects that. IsHubPortDevice must be set if you are not using a Smart Phidget (more on this later).
        redLED.setHubPort(1);
        redLED.setIsHubPortDevice(true);
        greenLED.setHubPort(4);
        greenLED.setIsHubPortDevice(true);
        redButton.setHubPort(0);
        redButton.setIsHubPortDevice(true);
        greenButton.setHubPort(5);
        greenButton.setIsHubPortDevice(true);

        //Open | Open establishes a connection between your object and your physical Phidget. You provide a timeout value of 1000 to give the program 1000 milliseconds (1 second) to locate your Phidget. If your Phidget can't be found, an exception will be thrown.
        redLED.open(1000);
        greenLED.open(1000);
        redButton.open(1000);
        greenButton.open(1000);
        //int counter = 0;
        //Use your Phidgets | Here is where you can have some fun and use your Phidgets! You can turn your LED on/off by setting the state to true/false. The sleep command keeps your LED on by letting 1000 milliseconds pass before turning the LED off.
        
        
        int i = 0;
        float brightness = 0;
        
        while(i < 10) {
        	
        	redLED.setDutyCycle(brightness);
        	i += 1;
        	brightness = (float) (i / 10.0);
        	Thread.sleep(20);
        }
    
    	while(i >= 0) {
    		
    		redLED.setDutyCycle(brightness);
    		i-= 1;
    		brightness = (float) (i / 10.0);
    		Thread.sleep(20);
    	}
        
        redLED.close();
        
        
        
        /*redLED.setState(true);//TUG OF WAR
    	greenLED.setState(true);
        Thread.sleep(1000);
        redLED.setState(false);
        greenLED.setState(false);
        
        boolean redCurrentState = false;
        boolean greenCurrentState = false;
        int redButtonPresses = 0;
        int greenButtonPresses = 0;
        while(true){ 

            if( redButton.getState()){
            	if(redCurrentState != redButton.getState()) {
            		redButtonPresses++;
            		redCurrentState = true;
            	}
            } else {
                redCurrentState = false;
            }

            if(greenButton.getState()){
            	if(greenCurrentState != greenButton.getState()) {
            		greenButtonPresses++;
            		greenCurrentState = true;
            	}
            } else {
                greenCurrentState = false;
            }

            if(greenButtonPresses == 10) {
            	redLED.setState(true);
            	greenLED.setState(true);
                Thread.sleep(1000);
                redLED.setState(false);
                greenLED.setState(false);
                Thread.sleep(1000);
                
                for(int i = 0; i < 5; i++) {
                	greenLED.setState(true);
                	Thread.sleep(500);
                	greenLED.setState(false);
                	Thread.sleep(500);
                }         
                break;
            }else if(redButtonPresses == 10) {
            	redLED.setState(true);
            	greenLED.setState(true);
                Thread.sleep(1000);
                redLED.setState(false);
                greenLED.setState(false);
                Thread.sleep(1000);
                
                for(int i = 0; i < 5; i++) {
                	redLED.setState(true);
                	Thread.sleep(500);
                	redLED.setState(false);
                	Thread.sleep(500);
                } 
                break;
            }
            
            Thread.sleep(10);
            
        }*/
        
        
        
        
        /*boolean currentState = false; //ONLY OUTPUT WHEN BUTTON PRESSED
        currentState = greenButton.getState();
        while(true){
            if(greenButton.getState() != currentState) {
            	System.out.println("Button State: " + greenButton.getState());
            	currentState = greenButton.getState();
            }
        }
        
        /*while(counter < 3){ //TEST LEDS
            redLED.setState(true);
            Thread.sleep(2000);
            redLED.setState(false);
            greenLED.setState(true);
            Thread.sleep(1000);
            greenLED.setState(false);
            counter++;
        }*/
        
    }
}
  