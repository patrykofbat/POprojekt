package sample.pool;

import java.util.ArrayList;

public class RSSISignal {

    private Pool pool;


    public RSSISignal(Pool pool) {
        this.pool = pool;
    }


    public void sendSignal(){
        ArrayList<Robot> robots = this.pool.getRobotsList();
        ArrayList<Satelite> satelites = this.pool.getSateliteList();
        MotherRobot motherRobot = this.pool.getMotherRobot();
        double[] signalsPrim = new double[3];
        int j = 0;
        for(Satelite satelitePrim:satelites){
            double distance = Math.sqrt(Math.pow(motherRobot.getXPosition() - satelitePrim.getXPosition(),2)+Math.pow(motherRobot.getYPosition()-satelitePrim.getYPosition(), 2));
            signalsPrim[j] = 400 - 10 * 10 * Math.log10(distance);
            j++;
        }
        motherRobot.setSignals(signalsPrim);


        for(Robot robot:robots){
            double[] signals = new double[3];
            int i = 0;
            for(Satelite satelite:satelites){
                double distance = Math.sqrt(Math.pow(robot.getXPosition() - satelite.getXPosition(),2)+Math.pow(robot.getYPosition()-satelite.getYPosition(), 2));
                signals[i] = 400 - 10 * 10 * Math.log10(distance);
                i++;
            }
            robot.setSignals(signals);
        }
    }


}
