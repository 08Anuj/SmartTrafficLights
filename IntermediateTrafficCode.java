import java.util.*;
public class IntermediateTrafficCode {

    //Global variables
    static int noOfEmergencyAtLanes[]=new int[4];
    static int congessionQueue[]=new int[4];
    static boolean emergencyFlag;

    // Define the timer durations for each signal
    private static final int GREEN_BASE_TIME = 30;  // Green light duration in seconds
    private static int GREEN_TIME;  // Green light duration in seconds
    private static final int YELLOW_TIME=5;  // Yellow light duration in seconds
    private static int RED_TIME;  // Red light duration in seconds

    //Constructor for initialization
    public IntermediateTrafficCode(){
        // noOfEmergencyAtLanes=new int[4];
        // congessionQueue=new int[4];
        emergencyFlag=false;
        GREEN_TIME=0;
        RED_TIME=0;
    }
    public static void getTrafficInputsOfEveryLane(){
       // a function counts the values of a particular frame of the contineous video.
       Random r=new Random(); 
       System.out.println("Getting inputs from the frame.....");
        for(int i=0;i<4;i++){
            //Number of Emergency vehicle on that i th lane ==> noOfEmergencyAtLanes
            noOfEmergencyAtLanes[i]=r.nextInt(3); // range 0 --> 2
            //Number of Vehicle at that lane ==> Congession Queue
            congessionQueue[i]=r.nextInt(100); // range 0 --> 99
            // lane will be determine by the index of the array

            System.out.println("Lane["+(i+1)+"]: ");
            System.out.println("Emergency: "+noOfEmergencyAtLanes[i]);
            System.out.println("Congestion: "+congessionQueue[i]);
            
        }
    }

    public static void checkEmergency(){
        for(int i=0;i<4;i++){
            if(noOfEmergencyAtLanes[i]>0){
                emergencyFlag=true;
                break;
            }
        }
    }

    public static void suitableAction(){
        if(emergencyFlag==true){
            clearEmergencyVehicle();
        }else{
            clearCongestion();
        }
    }
    public static void clearEmergencyVehicle(){
        int max=noOfEmergencyAtLanes[0];
        int lane=0;
        for(int i=1;i<noOfEmergencyAtLanes.length;i++){
            if(noOfEmergencyAtLanes[i]>max){
                max=noOfEmergencyAtLanes[i];
                lane=i;
            }
        }
        System.out.println("----------Clear Emergency Vahicle at lane["+(lane+1)+"]----------");
        dynamicGreenDuration(lane);
        dynamicRedDuration();
        trafficLight((lane+1), GREEN_TIME, YELLOW_TIME, RED_TIME, true, true);  
        noOfEmergencyAtLanes[lane]=0;
        congessionQueue[lane]=0;
        emergencyFlag=false;
        System.out.println("----------Clear Ended----------");
        checkEmergency();
        suitableAction();
    }

        public static void clearCongestion(){
        int max=congessionQueue[0];
        int lane=0;
        for(int i=1;i<congessionQueue.length;i++){
            if(congessionQueue[i]>max){
                max=congessionQueue[i];
                lane=i;
            }
        }
        if(max==0){
            System.out.println("**********Complete Cycle Ended**********");
            return;
        }
        System.out.println("----------Clear Congestion at lane["+(lane+1)+"]----------");
        dynamicGreenDuration(lane);
        dynamicRedDuration();
        trafficLight((lane+1), GREEN_TIME, YELLOW_TIME, RED_TIME, true, true);  
        congessionQueue[lane]=0;
        System.out.println("----------Clear Ended----------");
        checkEmergency();
        suitableAction();
    }


        // Function to simulate each light's action for a given direction
    public static void trafficLight(int lane, int greenDuration, int yellowDuration, int redDuration, boolean leftTurn, boolean rightTurn) {
        System.out.println("\n Lane:" + lane + " - GREEN: " + greenDuration + "s");
        if (leftTurn) {
            System.out.println("Lane:"+ lane + " - LEFT TURN SIGNAL: Active");
        }
        if (rightTurn) {
            System.out.println("Lane:"+ lane + " - RIGHT TURN SIGNAL: Active");
        }
        // Red light for Other lanes except the current lane
        for(int i=1;i<5;i++){

            if(lane!=i){System.out.println("\n Lane:" + i + " - RED: " + redDuration + "s");}
        }

    }
    public static void dynamicGreenDuration(int lane){
        
        GREEN_TIME=GREEN_BASE_TIME+(congessionQueue[lane]/5);
        if(GREEN_TIME>=120){GREEN_TIME=120;}

    }
    public static void dynamicRedDuration(){
        RED_TIME=GREEN_TIME+YELLOW_TIME;
    }
        
    public static void main(String[] args){
        // while (true) {
            getTrafficInputsOfEveryLane();
            checkEmergency();
            suitableAction();
        // }
    }   
}