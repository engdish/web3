package calculate;

import javax.ejb.Stateless;

@Stateless
public class AreaChecker {

    public boolean isHit(int x, double y, int r) {
        double halfR = r / 2.0;

        if (x <= 0 && y >= 0 && x >= -r) {
            double edge = 0.5 * (x + r);
            if (y <= edge && y <= halfR) {
                return true;
            }
        }

        if (x <= 0 && y <= 0) {
            double r2 = halfR * halfR;
            if ((double) x * x + y * y <= r2) {
                return true;
            }
        }

        if (x >= 0 && y <= 0) {
            if (x <= r && y >= -r) {
                return true;
            }
        }

        return false;
    }
}
