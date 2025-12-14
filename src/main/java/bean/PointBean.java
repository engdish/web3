package bean;

import dto.PointDto;
import service.PointService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "pointBean")
@SessionScoped
public class PointBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @EJB
    private PointService pointService;

    private final List<PointDto> points = new ArrayList<>();
    private PointDto newPoint = new PointDto();

    public void addPoint() {
        try {
            PointDto saved = pointService.addPoint(newPoint);
            points.add(0, saved);
            newPoint = new PointDto();
        } catch (IllegalArgumentException ex) {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), null)
            );
        }
    }

    public void clearData() {
        pointService.clear();
        points.clear();
    }

    public List<PointDto> getPoints() {
        return points;
    }

    public PointDto getNewPoint() {
        return newPoint;
    }

    public void setNewPoint(PointDto newPoint) {
        this.newPoint = newPoint;
    }
}
