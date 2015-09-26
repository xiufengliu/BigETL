package dk.dtu.ccsd.facade.data.req;


import org.hibernate.validator.constraints.NotBlank;


/**
 * Created by xiuli on 3/2/15.
 */
public class TimeSeriesConditionRequest {

    @NotBlank(message="{metric.name.empty}")
    private String metricName;

    @NotBlank(message="{tagValue.name.empty}")
    private String tagValue;

    @NotBlank(message="{aggregate.by.empty}")
    private String aggregateBy;

    @NotBlank(message="{endtime.empty}")
    private String startTime;

    @NotBlank(message="{endtime.empty}")
    private String endTime;

    public String getMetricName() {
        return metricName;
    }

    public void setMetricName(String metricName) {
        this.metricName = metricName;
    }

    public String getTagValue() {
        return tagValue;
    }

    public void setTagValue(String tagValue) {
        this.tagValue = tagValue;
    }

    public String getAggregateBy() {
        return aggregateBy;
    }

    public void setAggregateBy(String aggregateBy) {
        this.aggregateBy = aggregateBy;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
