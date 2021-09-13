package com.EasyStreet;

import java.math.BigInteger;

public class Fraction {

    private BigInteger numerator;
    private BigInteger denumerator;
    private boolean mustBeSimplified;

    public BigInteger getDenumerator() {
        return denumerator;
    }

    public BigInteger getNumerator() {
        return numerator;
    }

    public Fraction setDenumerator(BigInteger denumerator) {
        this.denumerator = denumerator;
        return this;
    }

    public Fraction setNumerator(BigInteger numerator) {
        this.numerator = numerator;
        return this;
    }

    public Fraction setMustBeSimplified(boolean mustBeSimplified) {
        this.mustBeSimplified = mustBeSimplified;
        return this;
    }

    public Fraction(BigInteger numerator, BigInteger denumerator){
        this.numerator = numerator;
        this.denumerator = denumerator;
    }

    public void add(Fraction fraction){
        BigInteger newDenumerator = this.denumerator.multiply(fraction.denumerator);
        BigInteger firstNumerator = (newDenumerator.divide(this.denumerator)).multiply(this.numerator);
        BigInteger secondNumerator = (newDenumerator.divide(fraction.denumerator)).multiply(fraction.numerator);

        this.numerator = firstNumerator.add(secondNumerator);
        this.denumerator = newDenumerator;

        if (this.mustBeSimplified) {
            this.simlify();
        }
    }

    public void add(SmallFraction fraction){
        int smallFractionNumerator = fraction.getNumerator();
        int smallFractionDenumerator = fraction.getDenumerator();
        BigInteger newDenumerator = this.denumerator.multiply(new BigInteger(String.valueOf(smallFractionDenumerator)));
        BigInteger firstNumerator = (newDenumerator.divide(this.denumerator)).multiply(this.numerator);
        BigInteger secondNumerator = (newDenumerator.divide(new BigInteger(String.valueOf(smallFractionDenumerator))).multiply(new BigInteger(String.valueOf(smallFractionNumerator))));

        this.numerator = firstNumerator.add(secondNumerator);
        this.denumerator = newDenumerator;

        if (this.mustBeSimplified) {
            this.simlify();
        }
    }

    public void simlify(){
        BigInteger gcd = this.gcd(this.numerator, this.denumerator);

        this.numerator = this.numerator.divide(gcd);
        this.denumerator = this.denumerator.divide(gcd);
    }

    public BigInteger gcd(BigInteger a, BigInteger b) {
        return b.equals(BigInteger.ZERO) ? a : gcd(b, a.mod(b));
    }

    public void print(){
        System.out.println(this.numerator + "/" + this.denumerator);
    }
}
