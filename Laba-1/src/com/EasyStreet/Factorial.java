package com.EasyStreet;

import java.math.BigInteger;

public class Factorial {
    public static int calculate(int n) {
        if (n == 0 || n == 1){
            return 1;
        }
        else return n * calculate(n-1);
    }

}
