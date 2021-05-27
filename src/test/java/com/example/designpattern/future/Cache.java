package com.example.designpattern.future;

import java.util.concurrent.FutureTask;

public interface Cache {
    Integer query(String key);
    void save(String key, FutureTask<Integer> value);
}
