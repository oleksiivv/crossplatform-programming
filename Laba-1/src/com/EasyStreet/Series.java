package com.EasyStreet;

import java.math.BigInteger;

public class Series {

    private Fraction sum;

    public Series(Fraction fraction){
        this.sum = fraction;
    }

    public void calculateSum(Fraction fraction){
        this.sum.add(fraction);
    }

    public void calculateSum(SmallFraction fraction){
        this.sum.add(fraction);
    }

    public void printSum(){
        this.sum.print();
    }
}
