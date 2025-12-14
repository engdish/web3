package service;

import dto.PointDto;
import dto.PointMapper;
import calculate.AreaChecker;
import model.Point;
import repository.CheckResultRepository;
import util.DateTimeProvider;
import util.Stopwatch;
import validation.PointValidator;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class PointService {

    @EJB
    private CheckResultRepository repository;

    @EJB
    private PointValidator validator;

    @EJB
    private AreaChecker areaChecker;

    @EJB
    private DateTimeProvider dateTimeProvider;

    public PointDto addPoint(PointDto input) {
        Stopwatch watch = Stopwatch.start();

        validator.validate(input.getX(), input.getY(), input.getR());

        Point entity = PointMapper.toEntity(input);

        boolean hit = areaChecker.isHit(entity.getX(), entity.getY(), entity.getR());
        entity.setResult(hit);

        entity.setDate(dateTimeProvider.now());
        entity.setNano(watch.elapsedMicros());

        Point saved = repository.save(entity);
        return PointMapper.toDto(saved);
    }

    public List<PointDto> findAll() {
        List<Point> entities = repository.findAllDesc();
        List<PointDto> dtos = new ArrayList<>(entities.size());
        for (Point p : entities) {
            dtos.add(PointMapper.toDto(p));
        }
        return dtos;
    }

    public void clear() {
        repository.clear();
    }
}
