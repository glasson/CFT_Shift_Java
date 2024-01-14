package org.example;

public class FullStatistics implements IStatistics{

    private int floatCount = 0;
    private int stringCount = 0;
    private int integerCount = 0;
    private float maxNumber=Float.MIN_VALUE;
    private float minNumber=Float.MAX_VALUE;
    private float maxString=Integer.MIN_VALUE;
    private float minString=Integer.MAX_VALUE;
    private float sum, average;



    public void addFloat(String line) {
        floatCount++;
        checkNumber(line);
    }



    public void addString(String line) {
        stringCount++;
        checkString(line);
    }


    public void addInteger(String line) {
        integerCount++;
        checkNumber(line);
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

    public void printResult(){
        System.out.printf("Натуральных чисел: %d\n", floatCount);
        System.out.printf("Целых чисел: %d\n", integerCount);
        System.out.printf("Максимальное число: %f\n", maxNumber);
        System.out.printf("Минимальное число: %f\n", minNumber);
        System.out.printf("Сумма: %f\n", sum);
        System.out.printf("Среднее: %f\n", average);
        System.out.printf("Строк: %d\n", stringCount);
        System.out.printf("Максимальная длина строки: %f\n", maxString);
        System.out.printf("Минимальная длина строки: %f\n", minString);

    }

    public void checkNumber(String line){
        float number = Float.parseFloat(line);
        if (number>maxNumber)
            maxNumber=number;
        if (number<minNumber)
            minNumber=number;

        sum+=number;
        average = sum/(integerCount+floatCount);
    }

    public void checkString(String line){
        if (line.length()>maxString)
            maxString = line.length();
        if (line.length()<minString)
            minString = line.length();
    }
}
