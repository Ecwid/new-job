package com.gmail.mbotyuk.utils;

import java.util.List;
import java.util.Optional;

public interface Buffer {

    void add(List<String> bufferElement);

    Optional<List<String>> get();

    int getSizeBuffer();

    boolean endOfAddition();

    void finishAdding();
}
