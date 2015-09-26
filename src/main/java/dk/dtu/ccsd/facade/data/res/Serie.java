package dk.dtu.ccsd.facade.data.res;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiuli on 3/4/15.
 */
public class Serie {

    private String type;

    private String name;

    private List<Point> data = new ArrayList<Point>();

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addDataPoint(long x, double y) {
        data.add(new Point(x, y));
    }

    public List<Point> getData() {
        return data;
    }

    public void setData(List<Point> data) {
        this.data = data;
    }
}
