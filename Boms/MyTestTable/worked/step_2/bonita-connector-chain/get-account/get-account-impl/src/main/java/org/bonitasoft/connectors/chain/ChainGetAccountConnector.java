/**
 *
 */
package org.bonitasoft.connectors.chain;




import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import org.bonitasoft.engine.connector.ConnectorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.bonitasoft.engine.connector.ConnectorException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
public class ChainGetAccountConnector extends AbstractChainGetAccountImpl {

    @Override
    protected void executeBusinessLogic() throws ConnectorException {
        //Get access to the connector input parameters
        //getUrl();
        //getAccountToken();
        //getAlias();

        final Logger logger = LoggerFactory.getLogger(ChainGetAccountConnector.class);
        logger.info("===== ChainGetAccountConnector ======");
        try {

            logger.info("===== 1 ======");
            DefaultHttpClient httpClient = new DefaultHttpClient();

            logger.info("===== 2 ======");
            HttpGet getRequest = new HttpGet("http://localhost:10010/api/template/get-asset");

            logger.info("===== 3 ======");
            getRequest.addHeader("accept", "application/json");

            logger.info("===== 4 ======");
            HttpResponse response = httpClient.execute(getRequest);




            logger.info("===== 5 ======");
            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatusLine().getStatusCode());
            }

            String json = IOUtils.toString(response.getEntity().getContent());
            JSONArray array = new JSONArray(json);


            List<Map<String, Object>> emails = new ArrayList<Map<String, Object>>();

            Map<String, Object> definition= new HashMap<String,Object >();
            for (int i = 0; i < array.length(); i++) {


                JSONObject object = array.getJSONObject(i);


                JSONObject state = object.getJSONObject("state");
                JSONObject data = state.getJSONObject("data");
                JSONObject car = data.getJSONObject("car");
                logger.info("=====the car  is {}", car);

                definition.put("car",data.getJSONObject("car").toString());
                //  emails.add(mailMap);

            }

            Asset asset = new Asset(definition);
            logger.info("===== 6 ======");

            setAsset(asset);

            logger.info("===== 7 ======");


            httpClient.getConnectionManager().shutdown();
            logger.info("===== 8 ======");

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
