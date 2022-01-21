
//Add Phidgets Library
import com.phidget22.*;

public class PhidgetsRover {
    public static void main(String[] args) throws Exception {

        //Connect to wireless rover
        Net.addServer("", "192.168.100.1", 5661, "", 0);

        //Create
        DCMotor leftMotors = new DCMotor();
        DCMotor rightMotors = new DCMotor();
        DistanceSensor sonar = new DistanceSensor();

        //Address
        leftMotors.setChannel(0);
        rightMotors.setChannel(1);

        //Open
        leftMotors.open(1000);
        rightMotors.open(1000);
        sonar.open(1000);
        int duration = 0;
        boolean turned = false;
        
        while(true) {
        	
        	
	    	
	        
	    	while(duration < 1700) {
	    		
	    		leftMotors.setTargetVelocity(0.5);
	    		rightMotors.setTargetVelocity(0.5);
	        
	    		if(sonar.getDistance() < 200) {
	        		stop(leftMotors, rightMotors, 1000);
	        		leftMotors.setTargetVelocity(-0.5);
	                rightMotors.setTargetVelocity(0.5);
	                Thread.sleep(1600);
	                stop(leftMotors, rightMotors, 1000);
	                turned = !turned;
	                duration = 1700 - duration;
	    		}
	    	duration += 250;
	    	Thread.sleep(250);
	    	}
	   	duration = 0;
       
	        
        
        if(!turned) {
	        stop(leftMotors, rightMotors, 1000);
	        leftMotors.setTargetVelocity(-0.5);
	        rightMotors.setTargetVelocity(0.5);
        }else {
        	 stop(leftMotors, rightMotors, 1000);
        	 leftMotors.setTargetVelocity(0.5);
		     rightMotors.setTargetVelocity(-0.5);
        }
        Thread.sleep(700);
        stop(leftMotors, rightMotors, 1000);
        }
        
        //move
        /*while (true) {

            System.out.println("Distance: " + sonar.getDistance() + " mm");
            
            /*if (sonar.getDistance() < 200) {
                //Object detected! Stop motors
                leftMotors.setTargetVelocity(-0.5);
                rightMotors.setTargetVelocity(-0.5);
                Thread.sleep(600);
                stop(leftMotors, rightMotors, 1000);
                leftMotors.setTargetVelocity(-0.5);
                rightMotors.setTargetVelocity(0.5);
                Thread.sleep(1000);
            } else {
                //Move forward slowly (25% max speed)
                leftMotors.setTargetVelocity(0.25);
                rightMotors.setTargetVelocity(0.25);
            }

            //Wait for 250milliseconds
            Thread.sleep(250);
        }*/
        
    }
    
    static void stop(DCMotor left, DCMotor right, int time) throws Exception {
    	left.setTargetVelocity(0);
    	right.setTargetVelocity(0);
    	Thread.sleep(time);
    }
}
  