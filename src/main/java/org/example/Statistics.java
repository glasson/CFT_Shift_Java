package org.example;

public class Statistics implements IStatistics {
    IStatistics statistics;

    public Statistics(IStatistics statistics) {
        this.statistics = statistics;
    }

    @Override
    public void addFloat(String line) {
        statistics.addFloat(line);
    }

    @Override
    public void addString(String line) {
        statistics.addString(line);
    }

    @Override
    public void addInteger(String line) {
        statistics.addInteger(line);
    }

    @Override
    public void printResult() {
        statistics.printResult();
    }

    public int getIntegerCount() {
        return statistics.getIntegerCount();
    }

    public int getFloatCount() {
        return statistics.getFloatCount();
    }

    public int getStringCount() {
        return statistics.getStringCount();
    }


}
