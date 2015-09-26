package dk.dtu.ccsd.facade.data.res;

import java.util.List;

/**
 * Created by xiuli on 2/26/15.
 */
public class TimeseriesForm {

    private List<String> tagNames;

    private List<String> metricNames;

    private List<String> tagValues;

    public List<String> getMetricNames() {
        return metricNames;
    }

    public void setMetricNames(List<String> metricNames) {
        this.metricNames = metricNames;
    }

    public List<String> getTagNames() {
        return tagNames;
    }

    public void setTagNames(List<String> tagNames) {
        this.tagNames = tagNames;
    }


    public List<String> getTagValues() {
        return tagValues;
    }

    public void setTagValues(List<String> tagValues) {
        this.tagValues = tagValues;
    }
}
