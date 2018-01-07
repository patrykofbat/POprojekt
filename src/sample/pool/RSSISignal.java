package sample.pool;

import java.util.ArrayList;

public class RSSISignal {

    private Pool pool;


    public RSSISignal(Pool pool) {
        this.pool = pool;
    }

    public double calculateSignal(double distance){
        return 100 * Math.log10(1000/distance);

    }

    public void sendSignal(){
        ArrayList<Robot> robots = this.pool.getRobotsList();
        ArrayList<Satelite> satelites = this.pool.getSateliteList();

        for(Robot robot:robots){
            double[] signals = new double[3];
            int i = 0;
            for(Satelite satelite:satelites){
                double distance = Math.sqrt(Math.pow(robot.getXPosition() - satelite.getXPosition(),2)+Math.pow(robot.getYPosition()-satelite.getYPosition(), 2));
                signals[i] = this.calculateSignal(distance);
                i++;
            }
            robot.setSignals(signals);
        }
    }


}
