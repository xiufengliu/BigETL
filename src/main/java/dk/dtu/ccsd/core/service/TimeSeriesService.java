package dk.dtu.ccsd.core.service;

import dk.dtu.ccsd.exception.CDMSException;
import dk.dtu.ccsd.facade.data.req.TimeSeriesConditionRequest;
import dk.dtu.ccsd.facade.data.res.HighchartOption;

import java.io.IOException;
import java.util.List;
import java.util.Map;


/**
 * Created by xiuli on 2/26/15.
 */



public interface TimeSeriesService {
    List<String> getMetricNames() throws IOException;

    List<String> getTagValues() throws IOException;

    List<String> getTagNames() throws IOException;

    HighchartOption getTimeSeries(TimeSeriesConditionRequest param) throws CDMSException;
}
