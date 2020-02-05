package com.kamalova.amphora;

public class PlainPair<T, R> implements Pair<T, R> {
    private T first;
    private R second;

    PlainPair() {}

    PlainPair(T first, R second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public R getSecond() {
        return second;
    }

    public void setSecond(R second) {
        this.second = second;
    }
}
