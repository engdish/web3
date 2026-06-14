package monitoring;

public interface HitRatioMBean {
    int getTotalClicks();
    int getHitClicks();
    double getHitRatio();


    void reset();
}
