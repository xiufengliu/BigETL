package dk.dtu.ccsd.facade.data.res;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiuli on 3/4/15.
 */
public class HighchartOption {

    private String title;

    List<Serie> series = new ArrayList<Serie>();

    long minRange;

    public List<Serie> getSeries() {
        return series;
    }

    public void addSerie(Serie serie) {
        this.series.add(serie);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getMinRange() {
        return minRange;
    }

    public void setMinRange(long minRange) {
        this.minRange = minRange;
    }
}
