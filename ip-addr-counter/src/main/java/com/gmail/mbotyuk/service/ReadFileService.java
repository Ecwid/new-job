package com.gmail.mbotyuk.service;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.gmail.mbotyuk.configuration.AppConfiguration;
import com.gmail.mbotyuk.utils.Buffer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ReadFileService implements Reader {

    private final Buffer accumulativeWriteBuffer;

    private static final Logger logger = LogManager.getLogger(ReadFileService.class);

    private static final List<String> list = new ArrayList<>(AppConfiguration.DIFFERENCE_BETWEEN_MAX_AND_MIN / AppConfiguration.NUMBER_OF_THREADS);
    private static final ExecutorService executor = Executors.newFixedThreadPool(AppConfiguration.NUMBER_OF_THREADS);

    public ReadFileService(Buffer accumulativeWriteBuffer) {
        this.accumulativeWriteBuffer = accumulativeWriteBuffer;
    }

    @Override
    public void read() {
        logger.warn("Start reading file = " + LocalDateTime.now());

        try (BufferedReader br = new BufferedReader(new FileReader(AppConfiguration.INPUT_FILE), AppConfiguration.BUFFERED_READER_BUFFER_SIZE)) {
            String line;
            while ((line = br.readLine()) != null) { //21-31 14-20
                list.add(line);
                if (list.size() > AppConfiguration.DIFFERENCE_BETWEEN_MAX_AND_MIN / AppConfiguration.NUMBER_OF_THREADS) {
                    flush();
                    list.clear();
                }
            }
            logger.warn("Stop reading file = " + LocalDateTime.now());
        } catch (IOException e) {
            logger.error("Error reading file", e);
        }

        while (accumulativeWriteBuffer.getSizeBuffer() > 0) {
            logger.warn("Buffer size = " + accumulativeWriteBuffer.getSizeBuffer());
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        accumulativeWriteBuffer.finishAdding();
        executor.shutdown();
        logger.warn("Stopped thread for reading");
    }

    @Override
    public void flush() {
        Runnable worker = new MultiThreadService(list);
        executor.submit(worker);
    }

    @Override
    public void run() {
        read();
    }
}
