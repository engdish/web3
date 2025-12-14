package repository;

import model.Point;

import javax.ejb.Local;
import java.util.List;

@Local
public interface CheckResultRepository {
    Point save(Point p);
    List<Point> findAllDesc();
    void clear();
}
