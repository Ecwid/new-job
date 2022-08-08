package com.gmail.mbotyuk.service;

public interface Reader extends Runnable {

    void read();

    void flush();
}
