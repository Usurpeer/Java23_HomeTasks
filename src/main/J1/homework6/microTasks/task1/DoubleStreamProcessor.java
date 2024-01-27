package j1.homework6.microTasks.task1;

import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.stream.DoubleStream;

public class DoubleStreamProcessor {
    public static int getQuantityNull(DoubleStream doubleStream) {
        if(doubleStream == null){
            throw new NullPointerException("Пустой поток.");
        }
        return (int) doubleStream.filter(value -> value == 0).count();
    }

    public static boolean hasDoubleValue(DoubleStream doubleStream) {
        if(doubleStream == null){
            throw new NullPointerException("Пустой поток.");
        }
        return doubleStream.anyMatch(value -> value % 1 != 0);
    }

    public static double differenceBetweenMinMax(DoubleStream doubleStream) {
        if(doubleStream == null){
            throw new NullPointerException("Пустой поток.");
        }
        DoubleSummaryStatistics statistics = doubleStream.summaryStatistics();
        double max = statistics.getMax();
        double min = statistics.getMin();
        return Math.abs(max - min);
    }

    public static double[] filterValues(DoubleStream doubleStream, double limit) {
        if(doubleStream == null){
            throw new NullPointerException("Пустой поток.");
        }
        if(Double.isNaN(limit)){
            throw new IllegalArgumentException("Лимит - не число.");
        }
        return doubleStream.filter(value -> value > limit).toArray();
    }

    public static Double maxLength(DoubleStream doubleStream) {
        if(doubleStream == null){
            throw new NullPointerException("Пустой поток.");
        }
        return Double.parseDouble(doubleStream.mapToObj(Double::toString).max(Comparator.comparingInt(String::length))
                .get());
    }
}
