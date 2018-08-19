/**
 *
 */
package org.bonitasoft.connectors.chain;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.bonitasoft.connectors.exception.BadURLException;
import org.bonitasoft.engine.connector.ConnectorException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The connector execution will follow the steps
 * 1 - setInputParameters() --> the connector receives input parameters values
 * 2 - validateInputParameters() --> the connector can validate input parameters values
 * 3 - connect() --> the connector can establish a connection to a remote server (if necessary)
 * 4 - executeBusinessLogic() --> execute the connector
 * 5 - getOutputParameters() --> output are retrieved from connector
 * 6 - disconnect() --> the connector can close connection to remote server (if any)
 */
public class ChainGetAssetConnector extends AbstractChainGetAssetImpl {

    @Override
    protected void executeBusinessLogic() throws ConnectorException {
        //Get access to the connector input parameters
        //getUrl();
        //getAccountToken();
        //getAlias();


        final Logger logger = LoggerFactory.getLogger(ChainGetAssetConnector.class);

        DefaultHttpClient httpClient = new DefaultHttpClient();


        logger.info("===== ChainGetAccountConnector ======");
        try {

            String url ;
            if (StringUtils.isNotEmpty(getUrl())) {
                url = getUrl();
            }
            else
            {
                url = "http://localhost:10007/api/template/get-asset";
            }


            HttpGet getRequest = new HttpGet(url);


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

            setAsset(assetList);

        } catch (ClientProtocolException e) {
            logger.error(e.getMessage());
            e.printStackTrace();

        } catch (IOException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {

            httpClient.getConnectionManager().shutdown();
        }
    }

    @Override
    public void connect() throws ConnectorException {
        //[Optional] Open a connection to remote server

    }

    @Override
    public void disconnect() throws ConnectorException {
        //[Optional] Close connection to remote server

    }

}
