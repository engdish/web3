package dto;

import model.Point;

public final class PointMapper {
    private PointMapper() {
    }

    public static PointDto toDto(Point entity) {
        if (entity == null) {
            return null;
        }
        return new PointDto(
                entity.getId(),
                entity.getX(),
                entity.getY(),
                entity.getR(),
                entity.getResult(),
                entity.getDate(),
                entity.getNano()
        );
    }

    public static Point toEntity(PointDto dto) {
        if (dto == null) {
            return null;
        }
        return new Point(dto.getX(), dto.getY(), dto.getR());
    }
}
