package model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "points")
public class Point implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private Integer x;

    @Column(nullable = false)
    private Double y;

    @Column(nullable = false)
    private Integer r;

    @Column(nullable = false)
    private boolean result;

    @Column
    private String date;

    @Column(nullable = false)
    private long nano;

    public Point() {
    }

    public Point(Integer x, Double y, Integer r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    public int getId() {
        return id;
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
