


import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.bonitasoft.connectors.chain.Asset;
import org.bonitasoft.connectors.chain.ChainGetAssetConnector;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


public class JsonParseTest {

    final Logger log = LoggerFactory.getLogger(JsonParseTest.class);

    @Test
    public void test() throws Exception {

        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpGet getRequest = new HttpGet(ChainGetAssetConnector.CAR_LIST_URL);

        getRequest.addHeader("accept", "application/json");

        HttpResponse response = httpClient.execute(getRequest);
        log.info("response is {}", response);

        String json = IOUtils.toString(response.getEntity().getContent());
        JSONArray array = new JSONArray(json);
        for (int i = 0; i < array.length(); i++) {
            JSONObject object = array.getJSONObject(i);

            log.info("color is {}", object.getString("color"));
            log.info("modelis {}", object.getString("model"));
            log.info("the weight is {}", object.getInt("weight"));

        }
    }

    @Test
    public void testList () throws Exception {

        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpGet getRequest = new HttpGet("http://localhost:10007/api/template/get-asset");

        getRequest.addHeader("accept", "application/json");


        HttpResponse response = httpClient.execute(getRequest);

        if (response.getStatusLine().getStatusCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + response.getStatusLine().getStatusCode());
        }

        String json = IOUtils.toString(response.getEntity().getContent());

        JSONArray array = new JSONArray(json);


        List<Asset> assetList = new ArrayList<>();


        for (int i = 0; i < array.length(); i++) {

            JSONObject object = array.getJSONObject(i);
            JSONObject state = object.getJSONObject("state");
            JSONObject data = state.getJSONObject("data");

            JSONObject car = data.getJSONObject("car");

            String  model = car.get("model").toString();
            String  color = car.get("color").toString();
            String  weight = car.get("weight").toString();

            Asset asset = new Asset(color, model,weight);

            assetList.add(asset);
        }
    }
}