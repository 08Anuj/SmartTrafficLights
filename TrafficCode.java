class TrafficCode{
    public static int[] checkCongestionOfEveryLane(){
        double arr[]=new double[4];
        int[] indexes = {0, 1, 2, 3};
        
        for(int i=0;i<4;i++){
            arr[i]=checkCongestion(i+1);
        }
        for (int i = 0; i < indexes.length - 1; i++) {
            for (int j = i + 1; j < indexes.length; j++) {
                if (arr[indexes[i]] < arr[indexes[j]]) {
                    int temp = indexes[i];
                    indexes[i] = indexes[j];
                    indexes[j] = temp;
                }
            }
        }
        return indexes;
        
    }

    
    // This Function shows the Congestion values
    public static  double checkCongestion(int n){
        switch(n){
            case 1: return 50.0;    /*Fixed Congestion values on percentage */
            case 2: return 30.0;
            case 3: return 20.0;
            case 4: return 80.0;
        }
        return 0;
    }
    
    public static void clearCongestion(int n){
        greenLight(n);
    }

    public static void greenLight(int n){
        // green light for that lane for specific time.
        System.out.println("Green Light for that lane"+(n+1));
    }

    public static void checkEmergencyVehicle(){
        
    }

    public static void main(String[] args){
        // int laneOne=1;
        // int laneTwo=2;
        // int laneThree=3;
        // int laneFour=4;

        int ca[]=new int[4];
             
        while(true){
            checkEmergencyVehicle();
        ca=checkCongestionOfEveryLane();
            // for(int i=0; i<4 ;i++){
            //     System.out.println(ca[i]);
            // }
        clearCongestion(ca[0]);
        }
    }
}
