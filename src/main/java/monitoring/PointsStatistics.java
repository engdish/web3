package monitoring;

import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

public class PointsStatistics extends NotificationBroadcasterSupport implements PointsStatisticsMBean {
    private static final String TWO_MISSES_NOTIFICATION = "opi4.points.two-misses";
    private int totalPoints;
    private int hitPoints;
    private int missPoints;
    private int consecutiveMisses;
    private long sequenceNumber;

    public synchronized void recordPoint(boolean isHit) {
        totalPoints++;
        if (isHit) {
            hitPoints++;
            consecutiveMisses = 0;
            return;
        }
        missPoints++;
        consecutiveMisses++;

        if (consecutiveMisses == 2) {
            sendTwoMissesNotification();
        }
    }

    private void sendTwoMissesNotification() {
        Notification notification = new Notification(
                TWO_MISSES_NOTIFICATION,
                this,
                ++sequenceNumber,
                System.currentTimeMillis(),
                "Two consecutive misses recorded"
        );
        sendNotification(notification);
    }

    @Override
    public synchronized int getTotalPoints() {
        return totalPoints;
    }

    @Override
    public synchronized int getHitPoints() {
        return hitPoints;
    }

    @Override
    public synchronized int getMissPoints() {
        return missPoints;
    }

    @Override
    public synchronized int getConsecutiveMisses() {
        return consecutiveMisses;
    }

    @Override
    public synchronized void reset() {
        totalPoints = 0;
        hitPoints = 0;
        missPoints = 0;
        consecutiveMisses = 0;
        sequenceNumber = 0;
    }
}
