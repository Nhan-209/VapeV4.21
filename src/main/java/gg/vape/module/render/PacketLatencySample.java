package gg.vape.module.render;

public class PacketLatencySample {
    private final long captureCount;
    private final int lowestFps;
    private final int highestFps;
    private final int fpsSum;

    public double getAverageFps() {
        return (long)this.fpsSum / this.captureCount;
    }

    public PacketLatencySample(long captureCount, int fpsSum, int highestFps, int lowestFps) {
        this.fpsSum = fpsSum;
        this.captureCount = captureCount;
        this.highestFps = highestFps;
        this.lowestFps = lowestFps;
    }

    public long getCaptureCount() {
        return this.captureCount;
    }

    public int getLowestFps() {
        return this.lowestFps;
    }

    public int getHighestFps() {
        return this.highestFps;
    }
}

