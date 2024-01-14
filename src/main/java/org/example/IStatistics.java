package org.example;

public interface IStatistics {
    void addFloat(String line);

    void addString(String line);

    void addInteger(String line);

    int getIntegerCount();

    int getStringCount();

    int getFloatCount();

    void printResult();
}
