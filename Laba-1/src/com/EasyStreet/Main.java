package com.EasyStreet;

import java.math.BigInteger;
import java.rmi.ServerError;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = 1;

        System.out.println("Enter number of steps: ");
        int stepsCount = sc.nextInt();

        System.out.println("Simplify? ");
        int simplify = sc.nextInt();
        Series series = new Series(new Fraction(new BigInteger(String.valueOf(n)),new BigInteger(String.valueOf(Factorial.calculate(n)))).setMustBeSimplified(simplify == 1));

        do {
            System.out.print("Sum (n = "+String.valueOf(n)+"): ");
            series.printSum();
            n++;

            if(n > 15){
                series.calculateSum(new Fraction(new BigInteger(String.valueOf(n)),new BigInteger(String.valueOf(Factorial.calculate(n)))));
            }
            else{
                series.calculateSum(new SmallFraction(n,Factorial.calculate(n)));
            }
        } while (n < stepsCount);

        System.out.print("Sum (n = "+String.valueOf(n)+"): ");
        series.printSum();
        sc.close();

    }
}
