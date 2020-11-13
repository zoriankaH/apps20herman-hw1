package ua.edu.ucu.tempseries;

import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {
    public double[] temperatureSeries;
    private double aver;

    public TemperatureSeriesAnalysis() {

    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        this.temperatureSeries = temperatureSeries;
    }

    public double average(){
        double[] temperatureSeries = this.temperatureSeries;
        if (temperatureSeries.length==0) {
            throw new IllegalArgumentException();
        }
        double sum = 0;
        for(int i=0; i<temperatureSeries.length; i++){
            sum+=temperatureSeries[i];
        }
        double average = sum/temperatureSeries.length;
        this.aver = average;
        return average;
    }

    public double deviation() {
        double[] temperatureSeries = this.temperatureSeries;
        if (temperatureSeries.length==0) {
            throw new IllegalArgumentException();
        }
        double sum = 0;
        double avar = this.aver;
        for(int i=0; i<temperatureSeries.length; i++){
            sum+=Math.pow(temperatureSeries[i]-avar, 2);
        }
        double devit = sum/temperatureSeries.length;
        return devit;
    }

    public double min() {
        double[] temperatureSeries = this.temperatureSeries;
        if (temperatureSeries.length==0) {
            throw new IllegalArgumentException();
        }
        double minV = temperatureSeries[0];
        for(int i=1;i<temperatureSeries.length;i++){
            if(temperatureSeries[i] < minV){
                minV = temperatureSeries[i];
            }
        }
        return minV;
    }

    public double max() {
        double[] temperatureSeries = this.temperatureSeries;
        if (temperatureSeries.length==0) {
            throw new IllegalArgumentException();
        }
        double maxV = temperatureSeries[0];
        for(int i=1;i<temperatureSeries.length;i++){
            if(temperatureSeries[i] > maxV){
                maxV = temperatureSeries[i];
            }
        }
        return maxV;
    }

    public double findTempClosestToZero() {
        double[] temperatureSeries = this.temperatureSeries;
        if (temperatureSeries.length==0) {
            throw new IllegalArgumentException();
        }
        double curr = 0;
        double closest = temperatureSeries[0];
        for (int i = 0; i < temperatureSeries.length; i++) {
            curr = temperatureSeries[i] * temperatureSeries[i];
            if (curr < (closest * closest)) {
                closest = temperatureSeries[i];
            }
            if (curr == (closest * closest)&&temperatureSeries[i]>0) {
                closest = temperatureSeries[i];
            }
        }
        return closest;
    }

    public double findTempClosestToValue(double tempValue) {
        double[] temperatureSeries = this.temperatureSeries;
        if (temperatureSeries.length == 0) {
            throw new IllegalArgumentException();
        }
        double curr = 0;
        double closest = temperatureSeries[0];
        for (int i = 0; i < temperatureSeries.length; i++) {
            curr = Math.pow((temperatureSeries[i] - tempValue), 2);
            if (curr < Math.pow((closest - tempValue), 2)) {
                closest = temperatureSeries[i];
            }
            if (curr == Math.pow((closest - tempValue), 2) &&  temperatureSeries[i]> closest) {
                closest = temperatureSeries[i];
            }
        }
        return closest;
    }

    public static double[] addToArray(int n, double[] arr, double val) {
        double newArray[] = new double[n + 1];
        for (int i = 0; i < n; i++){
            newArray[i] = arr[i];
        }
        newArray[n] = val;
        return newArray;
    }

    public double[] findTempsLessThen(double tempValue) {
        double[] temperatureSeries = this.temperatureSeries;
        double[] newSeries = new double[0];
        for (int i = 0; i < temperatureSeries.length; i++) {
            if (temperatureSeries[i]<tempValue){
                newSeries = addToArray(newSeries.length, newSeries, temperatureSeries[i]);
            }
        }
        return newSeries;
    }

    public double[] findTempsGreaterThen(double tempValue) {
        double[] temperatureSeries = this.temperatureSeries;
        double[] newSeries = new double[0];
        for (int i = 0; i < temperatureSeries.length; i++) {
            if (temperatureSeries[i]>=tempValue){
                newSeries = addToArray(newSeries.length, newSeries, temperatureSeries[i]);
            }
        }
        return newSeries;
    }

    public TempSummaryStatistics summaryStatistics() {
        if (this.temperatureSeries.length==0) {
            throw new IllegalArgumentException();
        }
        return new TempSummaryStatistics(this.aver, this.deviation(), this.min(), this.max());
    }

    public int addTemps(double... temps) {
        double[] temperatureSeries = this.temperatureSeries;
        double[] newSeries = new double[temperatureSeries.length*2];
        for (int i = 0; i < temperatureSeries.length; i++) {
            newSeries[i] = temperatureSeries[i];
        }
        int len = temperatureSeries.length;
        for(double value : temps){
            if (value<-273){
                throw new InputMismatchException();
            }
            temperatureSeries = addToArray(len, temperatureSeries, value);
            len++;
        }
        this.temperatureSeries = temperatureSeries;
        return len;
    }
}