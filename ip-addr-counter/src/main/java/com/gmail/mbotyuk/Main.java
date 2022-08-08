package com.gmail.mbotyuk;

import com.gmail.mbotyuk.service.ReadFileService;
import com.gmail.mbotyuk.service.WriteService;
import com.gmail.mbotyuk.configuration.AppConfiguration;
import com.gmail.mbotyuk.utils.AccumulativeWriteBuffer;
import com.gmail.mbotyuk.utils.Buffer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws Exception {
        new AppConfiguration(args[0], args[1], args[2], args[3], args[4]);
        Buffer accumulativeWriteBuffer = new AccumulativeWriteBuffer();

        Thread thread1 = new Thread(new ReadFileService(accumulativeWriteBuffer));
        thread1.start();
        Thread thread2 = new Thread(new WriteService(accumulativeWriteBuffer));
        thread2.start();

        thread1.join();
        thread2.join();

        logger.warn("End app");
    }
}
