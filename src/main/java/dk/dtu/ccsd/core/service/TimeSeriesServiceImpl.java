package dk.dtu.ccsd.core.service;

import com.google.common.collect.Maps;
import dk.dtu.ccsd.exception.CDMSException;
import dk.dtu.ccsd.facade.data.req.TimeSeriesConditionRequest;
import dk.dtu.ccsd.facade.data.res.HighchartOption;
import dk.dtu.ccsd.facade.data.res.Serie;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.kairosdb.client.HttpClient;
import org.kairosdb.client.builder.*;
import org.kairosdb.client.response.GetResponse;
import org.kairosdb.client.response.QueryResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by xiuli on 3/3/15.
 */
@Service
public class TimeSeriesServiceImpl implements TimeSeriesService {

    private static final Logger LOG = LoggerFactory.getLogger(TimeSeriesServiceImpl.class);

    private HttpClient kairosdbClient;

    @Autowired
    public TimeSeriesServiceImpl(HttpClient kairosdbClient) {
        this.kairosdbClient = kairosdbClient;
    }


    @Override
    public List<String> getMetricNames() throws IOException {
        GetResponse response = kairosdbClient.getMetricNames();
        return response.getResults();
    }

    @Override
    public List<String> getTagValues() throws IOException {
        GetResponse response = kairosdbClient.getTagValues();
        return response.getResults();
    }

    @Override
    public List<String> getTagNames() throws IOException {
        GetResponse response = kairosdbClient.getTagNames();
        return response.getResults();
       /* try {


        GetResponse response = kairosdbClient.getMetricNames();
        List<String> metricNames = response.getResults();
        QueryBuilder builder = QueryBuilder.getInstance();
        for (String metricName: metricNames) {
            System.out.println(">>>>>>>>>>>>>Metricname="+metricName);
           builder.setStart(DateUtils.parseDate("1970-01-01", new String[]{"yyyy-MM-dd"})).addMetric(metricName);
            System.out.println(builder.build());
            response = kairosdbClient.getTagNamesByMetric(builder);
           // GetResponse response = kairosdbClient.getTagNames();
            List<String> tagNames = response.getResults();
            for(String tagName: tagNames){
                System.out.println(tagName);
            }
        }
        response = kairosdbClient.getTagNames();
        return response.getResults();
        } catch (Exception e){
            e.printStackTrace();
            throw new IOException(e);
        }*/
    }

    @Override
    public HighchartOption getTimeSeries(TimeSeriesConditionRequest param) throws CDMSException{
        try {
            Map<String, Object> chartOptions = new HashMap<String, Object>();
            QueryBuilder builder = QueryBuilder.getInstance();

            // Date theDate = DateUtils.parseDate("03/04/2005 1:26 PM", new String[]{"MM/dd/yyyy hh:mm a"});
            int field = Integer.parseInt(param.getAggregateBy());
            Date startTime = DateUtils.parseDate(param.getStartTime(), new String[]{"MM/dd/yyyy hh:mm a"});
            Date endTime = DateUtils.parseDate(param.getEndTime(), new String[]{"MM/dd/yyyy hh:mm a"});
            Date truncStartTime = DateUtils.truncate(startTime, field);
            Date truncEndTime = DateUtils.truncate(endTime, field);

            TimeUnit timeUnit;
            long minRange = 0L;
            switch (field) {
                case Calendar.HOUR:
                    timeUnit = TimeUnit.HOURS;
                    minRange = 24 * 3600 * 1000L;
                    break;
                case Calendar.DATE:
                    timeUnit = TimeUnit.DAYS;
                    minRange = 30 * 24 * 3600 * 1000L;
                    break;
                case Calendar.MONTH:
                    timeUnit = TimeUnit.MONTHS;
                    minRange = 6 * 30 * 24 * 3600 * 1000L;
                    break;
                case Calendar.YEAR:
                    timeUnit = TimeUnit.YEARS;
                    minRange = 12 * 30 * 24 * 3600 * 1000L;
                    break;
                default:
                    throw new CDMSException("Cannot find the aggregator!");
            }
            builder.setStart(truncStartTime)
                    .setEnd(truncEndTime)
                    .addMetric(param.getMetricName())
                    .addAggregator(AggregatorFactory.createSumAggregator(1, timeUnit));

            LOG.info(builder.build());

            QueryResponse response = kairosdbClient.query(builder);

            List<DataPoint> dataPoints = response.getQueries().get(0).getResults().get(0).getDataPoints();

            Serie serie = new Serie();
            serie.setName("Electricity consumption");
            serie.setType("area");
            for (DataPoint dataPoint : dataPoints) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(dataPoint.getTimestamp());
                Calendar roundDate = DateUtils.truncate(calendar, field);
                serie.addDataPoint(roundDate.getTimeInMillis(), dataPoint.doubleValue());
                //String timestampe = DateFormatUtils.format(roundDate, "yyyy-MM-dd HH:mm");
               // LOG.info(String.format("(%s, %f)", timestampe, dataPoint.doubleValue()));
            }
            HighchartOption highchartOption = new HighchartOption();
            highchartOption.setTitle("Time series data analysis");
            highchartOption.setMinRange(minRange);
            highchartOption.addSerie(serie);

            return highchartOption;
        } catch (Exception e){
            e.printStackTrace();
            throw new CDMSException(e);
        }
    }


}
