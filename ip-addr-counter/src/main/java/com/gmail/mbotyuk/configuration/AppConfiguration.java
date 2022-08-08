package com.gmail.mbotyuk.configuration;

import java.io.File;

public class AppConfiguration {
    public final static int MIN = (0 * 1000 + 1) + (0 * 100 + 1) + (0 * 10 + 1) + (0 * 1 + 1);
    public static final int MAX = (255 * 1000 + 1) + (255 * 100 + 1) + (255 * 10 + 1) + (255 * 1 + 1);
    public static final int DIFFERENCE_BETWEEN_MAX_AND_MIN = MAX - MIN;

    public static int BUFFER_MAX_ELEMENT; //13
    public static int NUMBER_OF_THREADS; //13
    public static int BUFFERED_READER_BUFFER_SIZE; //Integer.MAX_VALUE - 300_000_000  1848000000
    public static File INPUT_FILE; ///Users/18725902/Downloads/ip_addresses
    public static File OUTPUT_FILE; ///Users/18725902/Downloads/output

    public AppConfiguration(String bufferMaxElement, String numberOfThreads, String bufferedReaderBufferSize, String pathInput, String pathOutput) {
        BUFFER_MAX_ELEMENT = Integer.parseInt(bufferMaxElement);
        NUMBER_OF_THREADS = Integer.parseInt(numberOfThreads);
        BUFFERED_READER_BUFFER_SIZE = Integer.parseInt(bufferedReaderBufferSize);
        INPUT_FILE = new File(pathInput);
        OUTPUT_FILE = new File(pathOutput);
    }
}
