package com.gmail.mbotyuk.service;

import com.gmail.mbotyuk.utils.AccumulativeWriteBuffer;
import com.gmail.mbotyuk.configuration.AppConfiguration;
import com.gmail.mbotyuk.utils.Buffer;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class MultiThreadService implements Runnable {

    private final List<String> listOfReadRecords;
    private static final DuplicateRemovalService duplicateRemovalService = new DuplicateRemovalService();
    private static final Buffer accumulativeWriteBuffer = new AccumulativeWriteBuffer();
    private final List<String> listUniqueValues = new ArrayList<>(AppConfiguration.DIFFERENCE_BETWEEN_MAX_AND_MIN / AppConfiguration.NUMBER_OF_THREADS);
    private static final AtomicInteger numberOfUniqueValues = new AtomicInteger(0);

    public MultiThreadService(List<String> listOfReadRecords) {
        this.listOfReadRecords = new ArrayList<>(listOfReadRecords);
    }

    @Override
    public void run() {
        if (numberOfUniqueValues.get() == (AppConfiguration.DIFFERENCE_BETWEEN_MAX_AND_MIN)) {
            return;
        }
        for (String str : listOfReadRecords) {
            if (duplicateRemovalService.isNotDuplicate(str)) {
                listUniqueValues.add(str);
                numberOfUniqueValues.getAndIncrement();
            }
        }
        accumulativeWriteBuffer.add(listUniqueValues);
    }
}
