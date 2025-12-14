package repository;

import model.Point;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class CheckResultDatabaseRepository implements CheckResultRepository {

    @PersistenceContext(unitName = "model.Point")
    private EntityManager em;

    @Override
    public Point save(Point p) {
        em.persist(p);
        em.flush();
        return p;
    }

    @Override
    public List<Point> findAllDesc() {
        return em.createQuery("select p from Point p order by p.id desc", Point.class)
                .getResultList();
    }

    @Override
    public void clear() {
        em.createQuery("delete from Point").executeUpdate();
        em.clear();
    }
}
