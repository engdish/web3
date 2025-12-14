package dto;

import java.io.Serializable;

public class PointDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private Integer x;
    private Double y;
    private Integer r;
    private boolean result;
    private String date;
    private long nano;

    public PointDto() {
        this.x = 0;
        this.y = 0.0;
        this.r = 2;
        this.result = false;
        this.nano = 0L;
    }

    public PointDto(int id, Integer x, Double y, Integer r, boolean result, String date, long nano) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.r = r;
        this.result = result;
        this.date = date;
        this.nano = nano;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Integer getR() {
        return r;
    }

    public void setR(Integer r) {
        this.r = r;
    }

    public boolean getResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getNano() {
        return nano;
    }

    public void setNano(long nano) {
        this.nano = nano;
    }
}
