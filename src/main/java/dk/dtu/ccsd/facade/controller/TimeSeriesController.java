package dk.dtu.ccsd.facade.controller;

import dk.dtu.ccsd.core.service.TimeSeriesService;
import dk.dtu.ccsd.exception.CDMSException;
import dk.dtu.ccsd.facade.data.res.HighchartOption;
import dk.dtu.ccsd.facade.data.res.TimeseriesForm;
import dk.dtu.ccsd.facade.data.req.TimeSeriesConditionRequest;
import org.kairosdb.client.builder.QueryBuilder;
import org.kairosdb.client.builder.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

/**
 * Created by xiuli on 2/26/15.
 */

@RestController
@RequestMapping(value = "/timeseries")
public class TimeSeriesController {

    private static final Logger LOG = LoggerFactory.getLogger(TimeSeriesController.class);
    private TimeSeriesService timeSeriesService;

    @Autowired
    public TimeSeriesController(TimeSeriesService timeSeriesService) {
        this.timeSeriesService = timeSeriesService;
    }

    @RequestMapping(value = "form", method = RequestMethod.GET)
    public TimeseriesForm getTimeseriesConditions() throws IOException {
        TimeseriesForm form = new TimeseriesForm();
        form.setMetricNames(timeSeriesService.getMetricNames());
        form.setTagNames(timeSeriesService.getTagNames());
        form.setTagValues(timeSeriesService.getTagValues());
        return form;

    }

    @RequestMapping(value = "query", method = RequestMethod.POST)
    public HighchartOption getElectricityTimeSeries(@RequestBody @Valid TimeSeriesConditionRequest request) throws CDMSException {
        try {
            LOG.info(">>>>>>>>>>>>>>>>metricName=" + request.getMetricName());
            LOG.info("metricName=" + request.getMetricName());
            LOG.info("tagValue=" + request.getTagValue());
            LOG.info("aggregateBy=" + request.getAggregateBy());
            LOG.info("startTime=" + request.getStartTime());
            LOG.info("endTime=" + request.getEndTime());

           return timeSeriesService.getTimeSeries(request);
        } catch (Exception e){
            e.printStackTrace();
            throw new CDMSException(e);
        }
    }

    @RequestMapping(value = "data", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public void getTimeSeries() throws IOException {
        System.out.println(">>>>>>>>>>>>>>>>Time series: Get");
    }
}
