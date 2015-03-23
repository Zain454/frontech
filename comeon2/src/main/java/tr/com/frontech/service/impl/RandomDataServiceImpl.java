package tr.com.frontech.service.impl;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import org.apache.log4j.Logger;
import tr.com.frontech.service.RandomDataService;

import java.io.IOException;

public class RandomDataServiceImpl implements RandomDataService {

    private final static Logger LOGGER = Logger.getLogger(RandomDataServiceImpl.class);

    private final OkHttpClient client;
    private final Request request;

    @Inject
    public RandomDataServiceImpl(@Named("random.service.url") String url) {
        this.client = new OkHttpClient();
        this.request = new Request.Builder().url(url).build();
    }

    @Override
    public String callApi() {
        String response = "";

        try {
            response = client.newCall(request).execute().body().string();
        } catch (IOException e) {
            LOGGER.error(e);
        } finally {
            LOGGER.info("callApi has runned. response is [" + response + "]");

            return response;
        }
    }

}
