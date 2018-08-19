import com.google.gson.Gson;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.bonitasoft.connectors.chain.CarModel;
import org.bonitasoft.connectors.chain.ChainSpendAssetConnector;
import org.bonitasoft.connectors.chain.TransactionResponse;
import org.json.JSONObject;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class RequestTest {

    final Logger logger = LoggerFactory.getLogger(RequestTest.class);

    @Test
    public void test() throws Exception {

        String result = "";
        CarModel p = new CarModel("MAZ","red","10",10000,"USD");

        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            String postUrl = "http://localhost:10007/api/template/create-car";// put in your url
            Gson gson = new Gson();
            HttpClient httpClient = HttpClientBuilder.create().build();

            HttpPost post = new HttpPost(postUrl);
            post.setHeader("Accept", "application/json");
            post.setHeader("Content-type", "application/json");

            StringEntity postingString = new StringEntity(gson.toJson(p), "UTF-8");//gson.tojson() converts your pojo to json
            post.setEntity(postingString);
            post.setHeader("Content-type", "application/json");

            HttpResponse response = httpClient.execute(post);

            if(response == null){
                throw  new Exception("response is nulle");
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

            // Response response = (Response) httpClient.execute(post);
            if(httpEntity == null){
                throw  new Exception("httpEntity is null ");
            }
            try {
                result = EntityUtils.toString(httpEntity, "utf-8");
                JSONObject jsonObject = new JSONObject(result);
                System.out.println("1");
                String transactionId = jsonObject.getString("transactionId");
                System.out.println("transactionId :" + transactionId);
                System.out.println("----------------------------------------");

            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
}
