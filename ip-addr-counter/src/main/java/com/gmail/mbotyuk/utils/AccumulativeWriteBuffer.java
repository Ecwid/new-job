package com.gmail.mbotyuk.utils;

import com.gmail.mbotyuk.configuration.AppConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class AccumulativeWriteBuffer implements Buffer {
    private static final BlockingQueue<List<String>> buffer = new LinkedBlockingQueue(AppConfiguration.BUFFER_MAX_ELEMENT);
    private static final TimeUnit time = TimeUnit.SECONDS;
    private static volatile boolean flagEndAdding = false;

    @Override
    public void add(List<String> bufferElement) {
        while (true) {
            try {
                buffer.put(new ArrayList<>(bufferElement));
                return;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            try {
                time.sleep(60);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @Override
    public Optional<List<String>> get() {
        while (true) {
            try {
                final List<String> element = buffer.poll(5, TimeUnit.SECONDS);
                return Optional.ofNullable(element);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            try {
                time.sleep(60);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @Override
    public int getSizeBuffer() {
        return buffer.size();
    }

    @Override
    public boolean endOfAddition() {
        return flagEndAdding;
    }

    public void finishAdding() {
        flagEndAdding = true;
    }
}
