package com.EasyStreet;

import java.math.BigInteger;

public class SmallFraction {

    private int numerator;
    private int denumerator;
    private boolean mustBeSimplified;

    public int getDenumerator() {
        return denumerator;
    }

    public int getNumerator() {
        return numerator;
    }

    public SmallFraction setDenumerator(int denumerator) {
        this.denumerator = denumerator;
        return this;
    }

    public SmallFraction setNumerator(int numerator) {
        this.numerator = numerator;
        return this;
    }

    public SmallFraction setMustBeSimplified(boolean mustBeSimplified) {
        this.mustBeSimplified = mustBeSimplified;
        return this;
    }

    public SmallFraction(int numerator, int denumerator){
        this.numerator = numerator;
        this.denumerator = denumerator;
    }

    public void add(SmallFraction fraction){
        int denumerator = this.denumerator * fraction.getDenumerator();

        int firstNumerator = (denumerator / this.denumerator) * this.numerator;
        int secondNumerator = (denumerator / fraction.getDenumerator()) * fraction.getNumerator();

        this.numerator = firstNumerator + secondNumerator;
        this.denumerator = denumerator;

        if (this.mustBeSimplified) {
            this.simlify();
        }
    }

    public void simlify(){
        int gcd = this.gcd(this.numerator, this.denumerator);

        this.numerator /= gcd;
        this.denumerator /= gcd;
    }

    public int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public void print(){
        System.out.println(this.numerator + "/" + this.denumerator);
    }
}
