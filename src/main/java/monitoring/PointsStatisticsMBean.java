package monitoring;

public interface PointsStatisticsMBean {
    int getTotalPoints();
    int getHitPoints();
    int getMissPoints();
    int getConsecutiveMisses();

    void reset();
}
