/**
 *
 */
package org.bonitasoft.connectors.chain;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
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

    public static final  String CAR_LIST_URL = "http://localhost:8080/api/get-assets2";
    @Override
    protected void executeBusinessLogic() throws ConnectorException {
        //Get access to the connector input parameters
        //getUrl();
        //getAccountToken();
        //getAlias();

        final Logger logger = LoggerFactory.getLogger(ChainGetAssetConnector.class);
        logger.info("===== ChainGetAccountConnector ======");
        try {

            logger.info("===== 1 ======");
            DefaultHttpClient httpClient = new DefaultHttpClient();

            logger.info("===== 2 ======");
            HttpGet getRequest = new HttpGet(CAR_LIST_URL);

            logger.info("===== 3 ======");
            getRequest.addHeader("accept", "application/json");

            logger.info("===== 4 ======");
            HttpResponse response = httpClient.execute(getRequest);


            logger.info("===== 5 ======");
         /*   if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatusLine().getStatusCode());
            }
*/

            List<Asset> assetList = new ArrayList<>();

            String json = IOUtils.toString(response.getEntity().getContent());
            JSONArray array = new JSONArray(json);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);

                String  model = object.getString("color");
                String  color = object.getString("color");
                String  weight = object.getString("color");

                Asset asset = new Asset(color, model, weight);

                assetList.add(asset);
            }
            setAsset(assetList);


            httpClient.getConnectionManager().shutdown();

            logger.info("===== 7 ======");

         /*   //TODO: for test
            //setAsset(assetList);
            List<Asset> assList = new ArrayList<>();

            Asset asset = new Asset("Red", "BMW","12");
            assList.add(asset);
            Asset asset2 = new Asset("Blue", "MAZ","14");
            assList.add(asset2);
            Asset asset3 = new Asset("Black", "ZAZ","19");
            assList.add(asset3);

            setAsset(assList);
            logger.info("===== 8 ======");*/


        } catch (ClientProtocolException e) {
            logger.error(e.getMessage());
            e.printStackTrace();

        } catch (IOException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
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
