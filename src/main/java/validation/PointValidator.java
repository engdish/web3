package validation;

import javax.ejb.Stateless;

@Stateless
public class PointValidator {

    public void validate(Integer x, Double y, Integer r) {
        if (x == null || y == null || r == null) {
            throw new IllegalArgumentException("Координаты не заданы");
        }
        if (x < -5 || x > 3) {
            throw new IllegalArgumentException("Некорректное X");
        }
        if (y < -5.0 || y > 5.0) {
            throw new IllegalArgumentException("Некорректное Y");
        }
        if (r < 1 || r > 5) {
            throw new IllegalArgumentException("Некорректное R");
        }
    }
}
