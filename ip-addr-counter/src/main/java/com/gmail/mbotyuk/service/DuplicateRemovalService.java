package com.gmail.mbotyuk.service;

import com.gmail.mbotyuk.configuration.AppConfiguration;

import java.util.Arrays;

public class DuplicateRemovalService {

    private static final byte[] array = new byte[AppConfiguration.DIFFERENCE_BETWEEN_MAX_AND_MIN];

    public boolean isNotDuplicate(final String ip) {
        final int number = transform(ip);
        if (contains(number)) {
            return false;
        }
        array[number] = 1;
        return true;
    }

    private boolean contains(int number) {
        return array[number] == 1;
    }

    private int transform(String ip) {
        int factor = 1000;
        int result = 0;
        String[] valuesStr = ip.split("\\.");
        int[] valuesInt = Arrays.stream(valuesStr)
                .mapToInt(Integer::parseInt)
                .toArray();

        for (int value : valuesInt) {
            result += value * factor;
            factor /= 10;
        }

        return result - AppConfiguration.MIN;
    }
}
