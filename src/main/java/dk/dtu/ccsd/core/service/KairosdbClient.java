package dk.dtu.ccsd.core.service;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.kairosdb.client.HttpClient;
import org.apache.http.HttpResponse;
import org.kairosdb.client.response.GetResponse;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

/**
 * Created by xiuli on 3/5/15.
 */
public class KairosdbClient extends HttpClient {

    public KairosdbClient(String url) throws MalformedURLException {
        super(url);
    }



}
