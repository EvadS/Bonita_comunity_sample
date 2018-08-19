/**
 *
 */

package org.bonitasoft.connectors.chain;


import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.bonitasoft.engine.connector.ConnectorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.json.JSONObject;


/**
 * The connector execution will follow the steps 1 - setInputParameters() --> the connector receives
 * input parameters values 2 - validateInputParameters() --> the connector can validate input
 * parameters values 3 - connect() --> the connector can establish a connection to a remote server
 * (if necessary) 4 - executeBusinessLogic() --> execute the connector 5 - getOutputParameters() -->
 * output are retrieved from connector 6 - disconnect() --> the connector can close connection to
 * remote server (if any)
 */
public class ChainSpendAssetConnector extends AbstractChainSpendAssetImpl {


    @Override
    protected void executeBusinessLogic() throws ConnectorException {
        final Logger logger = LoggerFactory.getLogger(ChainSpendAssetConnector.class);

        String postUrl = StringUtils.EMPTY;

        if (StringUtils.isNotEmpty(getUrl())) {
            postUrl = getUrl();

        } else {
            postUrl = "http://localhost:10007/api/template/create-car";
        }

        try {

            CarModel p = new CarModel(getModel(), getColor(), getWeight(), getAmount(), getCurrency());
            try (CloseableHttpClient httpclient = HttpClients.createDefault()) {

                Gson gson = new Gson();
                HttpClient httpClient = HttpClientBuilder.create().build();

                HttpPost post = new HttpPost(postUrl);
                post.setHeader("Accept", "application/json");
                post.setHeader("Content-type", "application/json");

                StringEntity postingString = new StringEntity(gson.toJson(p), "UTF-8");//gson.tojson() converts your pojo to json
                post.setEntity(postingString);
                post.setHeader("Content-type", "application/json");

                HttpResponse response = httpClient.execute(post);

                if (response == null) {
                    throw new Exception("response is null");
                }

                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK || response.getStatusLine().getStatusCode() == HttpStatus.SC_CREATED) {
                    logger.info("Register message sent to [{}] for [{}].", postUrl);
                } else {
                    logger.warn("Register message sent failed. Verify below information.");
                    logger.warn("[URL] : " + postUrl);
                    logger.warn("[Message] : " + postUrl);
                    logger.warn("[Reason] : " + EntityUtils.toString(response.getEntity(), "UTF-8"));
                }

                HttpEntity httpEntity = response.getEntity();

                if (httpEntity == null) {
                    throw new Exception("httpEntity is null ");
                }
                String result = StringUtils.EMPTY;

                result = EntityUtils.toString(httpEntity, "utf-8");
                JSONObject jsonObject = new JSONObject(result);
                System.out.println("1");
                String transactionId = jsonObject.getString("transactionId");
                System.out.println("transactionId :" + transactionId);
                TransactionResponse trnsResponse = new TransactionResponse(transactionId);
                setTransactionResponse(trnsResponse);

            }

        } catch (Exception e) {
            throw new ConnectorException("Error while getting transaction id ", e.getCause());
        }
    }


    @Override
    public void connect() throws ConnectorException {
        // [Optional] Open a connection to remote server

    }

    @Override
    public void disconnect() throws ConnectorException {
        // [Optional] Close connection to remote server

    }

}
