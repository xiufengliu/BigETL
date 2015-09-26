package dk.dtu.ccsd;


import dk.dtu.ccsd.common.Utils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.kairosdb.client.HttpClient;
import org.kairosdb.client.builder.*;
import org.kairosdb.client.response.QueryResponse;
import org.kairosdb.client.response.Response;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by xiuli on 2/21/15.
 */
public class TestMain {

    public void addDataPoints() {
        try {
            long time = Utils.millsecondsSinceEpoch(2011, 1, 1, 0);

            MetricBuilder builder = MetricBuilder.getInstance();
            Metric metric = builder.addMetric("electricity")
                    .addTag("custid", "4");
            for (int i = 0; i < 600; ++i) {
                metric.addDataPoint(time + 3600 * 1000L * i, Utils.randomInRange(0.1, 3.0));
            }
            System.out.println(builder.build());

            HttpClient client = new HttpClient("http://localhost:8080");
            Response response = client.pushMetrics(builder);

            client.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteMetric() {
        try {


            HttpClient client = new HttpClient("http://localhost:8080");
            Response response = client.deleteMetric("electricity");
            client.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void queryDataPoints() {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
            Date startTime = dateFormat.parse("01/01/2011 00:00:00");
            Date endTime = dateFormat.parse("02/01/2011 00:00:00");

            QueryBuilder builder = QueryBuilder.getInstance();
            builder.setStart(startTime).setEnd(endTime)
                    .addMetric("electricity").addTag("custid", "1");

            System.out.println(builder.build());

            HttpClient client = new HttpClient("http://localhost:8080");
            QueryResponse response = client.query(builder);
            List<DataPoint> dataPoints = response.getQueries().get(0).getResults().get(0).getDataPoints();

            for (DataPoint dataPoint : dataPoints) {
                System.out.println(dataPoint.getValue());
            }
            client.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        //TestMain test = new TestMain();
        // test.deleteMetric();
        // test.addDataPoints();
        //test.queryDataPoints();
        // System.out.println(Utils.secondsSinceEpoch(1970,1,1,0));
        try {
            Date theDate = DateUtils.parseDate("03/04/2005 1:26 PM", new String[]{"MM/dd/yyyy hh:mm a"});
            Date truncDate = DateUtils.truncate(theDate, Calendar.YEAR);
            System.out.println(DateFormatUtils.format(truncDate.getTime(), "MM/dd/yyyy HH:mm:ss"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
