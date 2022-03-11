package com.ethnicthv.redpie.util;

public class Math {

    public static <T extends Comparable> T clamp(T max, T min, T value){
        return (value.compareTo(min) < 0) ? min: (value.compareTo(max) > 0) ? max: value;
    }
}
