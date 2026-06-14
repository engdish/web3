package monitoring;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

public class MonitoringRegistry {
    private static final PointsStatistics STATISTICS = new PointsStatistics();
    private static final HitRatio HIT_RATIO = new HitRatio();

    static {
        register();
    }

    private MonitoringRegistry() {
    }

    public static PointsStatistics getStatistics() {
        return STATISTICS;
    }

    public static HitRatio getHitRatio() {
        return HIT_RATIO;
    }

    private static void register() {
        try {
            MBeanServer server = ManagementFactory.getPlatformMBeanServer();
            registerIfAbsent(server, "opi4:type=PointsStatistics", STATISTICS);
            registerIfAbsent(server, "opi4:type=HitRatio", HIT_RATIO);
        } catch (Exception e) {
            throw new IllegalStateException("Failed to register monitoring MBeans", e);
        }
    }

    private static void registerIfAbsent(MBeanServer server, String objectName, Object bean) throws Exception {
        ObjectName name = new ObjectName(objectName);
        if (!server.isRegistered(name)) {
            server.registerMBean(bean, name);
        }
    }
}
