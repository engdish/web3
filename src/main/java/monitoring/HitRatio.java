package monitoring;

public class HitRatio implements HitRatioMBean {
    private int totalClicks;
    private int hitClicks;

    public synchronized void recordPoint(boolean isHit) {
        totalClicks++;
        if (isHit) {
            hitClicks++;
        }
    }

    @Override
    public synchronized int getTotalClicks() {
        return totalClicks;
    }

    @Override
    public synchronized int getHitClicks() {
        return hitClicks;
    }

    @Override
    public synchronized double getHitRatio() {
        return totalClicks == 0 ? 0.0 : (double) hitClicks / totalClicks * 100.0;
    }

    @Override
    public synchronized void reset() {
        totalClicks = 0;
        hitClicks = 0;
    }
}
