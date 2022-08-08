package com.gmail.mbotyuk.service;

import java.io.IOException;

public interface Writer extends Runnable {

    void write() throws IOException;

    void close();
}
