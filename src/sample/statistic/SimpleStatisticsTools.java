package sample.statistic;

public class SimpleStatisticsTools {


    private double[] dataSet;

    public SimpleStatisticsTools(double[] dataSet) {
        this.dataSet = dataSet;
    }

    public void setDataSet(double[] dataSet) {
        this.dataSet = dataSet;
    }

    public double averageValue(){
        double sum = 0.0;


        for(double data:this.dataSet){
            sum += data;
        }

        return sum/this.dataSet.length;
    }

    public double standardDeviation(){
        double sum = 0.0;
        double average = this.averageValue();
        for(double data: this.dataSet)
            sum += Math.pow((data - average), 2);

        return Math.sqrt(sum/this.dataSet.length);
    }
}
