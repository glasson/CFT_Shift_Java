package org.example;

public class ShortStatistics implements IStatistics {

    private int floatCount = 0;
    private int stringCount = 0;
    private int integerCount = 0;

//    public ShortStatistics(){
//
//    }

    @Override
    public void addFloat(String line) {
        floatCount++;
    }

    @Override
    public void addString(String line) {
        stringCount++;
    }

    @Override
    public void addInteger(String line) {
        integerCount++;
    }


    @Override
    public void printResult() {
        System.out.printf("Натуральных чисел: %d\n", floatCount);
        System.out.printf("Целых чисел: %d\n", integerCount);
        System.out.printf("Строк: %d\n", stringCount);
    }

    public int getFloatCount() {
        return floatCount;
    }

    public int getStringCount() {
        return stringCount;
    }

    public int getIntegerCount() {
        return integerCount;
    }
}
