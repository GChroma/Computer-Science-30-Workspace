
//Add Phidgets Library
import com.phidget22.*;

public class PhidgetsJoystickRover{
    public static void main(String[] args) throws Exception{

        //Connect to wireless rover
        Net.addServer("", "192.168.100.1", 5661, "", 0);

        //Create
        DCMotor leftMotors = new DCMotor();
        DCMotor rightMotors = new DCMotor();
        VoltageRatioInput vAxis = new VoltageRatioInput(); 
        VoltageRatioInput hAxis = new VoltageRatioInput();
        DigitalInput jButton = new DigitalInput();
        DistanceSensor sonar = new DistanceSensor();

        //Address
        leftMotors.setChannel(0);
        rightMotors.setChannel(1);
        vAxis.setChannel(0);
        hAxis.setChannel(1);
        jButton.setHubPort(2);
       

        //Open
        leftMotors.open(1000);
        rightMotors.open(1000);
        vAxis.open(1000);
        hAxis.open(1000);
        jButton.open(1000);
        sonar.open(1000);

        //Increase acceleration
        leftMotors.setAcceleration(leftMotors.getMaxAcceleration());
        rightMotors.setAcceleration(rightMotors.getMaxAcceleration());

        //Use your Phidgets
        while(true){

            //Get data from vertical and horizontal axis (values between -1 and 1)
            double verticalAxis = vAxis.getVoltageRatio();
            double horizontalAxis = hAxis.getVoltageRatio();
            horizontalAxis = -horizontalAxis;
            //Use thumbstick data to figure how each side of rover should move
            double leftMotorsSpeed = verticalAxis + horizontalAxis;
            double rightMotorsSpeed = verticalAxis - horizontalAxis;

            //Limit values to between -1 and 1
            if(leftMotorsSpeed > 1) leftMotorsSpeed = 1;
            if(leftMotorsSpeed < -1) leftMotorsSpeed = -1;
            if(rightMotorsSpeed > 1) rightMotorsSpeed = 1;
            if(rightMotorsSpeed < -1) rightMotorsSpeed = -1;

            //Apply values 
            leftMotors.setTargetVelocity(leftMotorsSpeed); 
            rightMotors.setTargetVelocity(rightMotorsSpeed);
            
            if(jButton.getState()) {
        		leftMotors.setTargetVelocity(-0.5);
                rightMotors.setTargetVelocity(0.5);
                Thread.sleep(1600);
            }
            if (sonar.getDistance() < 200) {
                //Object detected! Stop motors
                leftMotors.setTargetVelocity(-0.5);
                rightMotors.setTargetVelocity(-0.5);
                Thread.sleep(600);
                stop(leftMotors, rightMotors, 1000);
                leftMotors.setTargetVelocity(-0.5);
                rightMotors.setTargetVelocity(0.5);
                Thread.sleep(1000);
            }
            //Wait for 100 milliseconds
            Thread.sleep(100);
        }
    }
    
    static void stop(DCMotor left, DCMotor right, int time) throws Exception {
    	left.setTargetVelocity(0);
    	right.setTargetVelocity(0);
    	Thread.sleep(time);
    }
}
