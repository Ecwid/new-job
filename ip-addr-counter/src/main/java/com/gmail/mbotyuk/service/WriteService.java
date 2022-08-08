package com.gmail.mbotyuk.service;

import com.gmail.mbotyuk.configuration.AppConfiguration;
import com.gmail.mbotyuk.utils.Buffer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class WriteService implements Writer {

    private static final Logger logger = LogManager.getLogger(WriteService.class);

    private static BufferedWriter bufferedWriter;
    private final Buffer accumulativeWriteBuffer;

    public WriteService(Buffer accumulativeWriteBuffer) {
        this.accumulativeWriteBuffer = accumulativeWriteBuffer;
        init();
    }

    private void init() {
        FileWriter writer = null;
        try {
            writer = new FileWriter(AppConfiguration.OUTPUT_FILE, false);
        } catch (IOException e) {
            logger.error("Error opening file for writing", e);
        }
        bufferedWriter = new BufferedWriter(writer);
    }

    @Override
    public void write() {
        while (true) {
            Optional<List<String>> list = accumulativeWriteBuffer.get();

            if (list.isPresent()) {
                try {
                    for (String str : list.get()) {
                        bufferedWriter.write(str + "\n");
                    }
                } catch (IOException e) {
                    logger.error("Error write file");
                }
            }

            if (accumulativeWriteBuffer.endOfAddition()) {
                close();
                logger.warn("Stopped thread for writing");
                return;
            }
        }
    }

    @Override
    public void close() {
        logger.warn("Close file");
        try {
            bufferedWriter.close();
        } catch (IOException e) {
            logger.error("Error close file", e);
        }
    }

    @Override
    public void run() {
        write();
    }
}
