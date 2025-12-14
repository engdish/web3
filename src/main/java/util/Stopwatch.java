package util;

public class Stopwatch {
    private final long startNanos;

    private Stopwatch(long startNanos) {
        this.startNanos = startNanos;
    }

    public static Stopwatch start() {
        return new Stopwatch(System.nanoTime());
    }

    public long elapsedMicros() {
        return (System.nanoTime() - startNanos) / 1_000;
    }
}
