package com.npe.fakefps.gson;

public class Config {
    private boolean lockfps;
    private int min;
    private int max;
    private int target;

    public Config() {
        this.lockfps = false;
        this.min = 400;
        this.max = 500;
        this.target = 450;
    }

    public boolean isFPSLocked() {
        return lockfps;
    }

    public void setLockfps(boolean lockfps) {
        this.lockfps = lockfps;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    @Override
    public String toString() {
        return "Config{" +
                "lockfps=" + lockfps +
                ", min=" + min +
                ", max=" + max +
                ", target=" + target +
                '}';
    }
}
