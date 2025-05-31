package org.example.model;

public enum Speed {
    SUPER_SLOW(1500),
    SLOW(1000),
    MEDIUM(500),
    FAST(200),
    SUPER_FAST(100);
    int timeInterval;
    Speed(int timeInterval) {
        this.timeInterval = timeInterval;
    }
    public int getTimeInterval() {
        return timeInterval;
    }
}
