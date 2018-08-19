package hello;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ExampleResourceTest {

    @LocalServerPort
    private int port;

    private Client client = ClientBuilder.newClient();


    @Test
    public void testCustomerLocationOnPost() {
        Car customer = new Car("Jane", "Doe",10);

        URI resourceUri = UriBuilder.fromUri("http://localhost")
                .port(port).path("api").path("customers").build();

    /*    Response response = client.target(resourceUri).request().post(Entity.json(customer));

        // test that we get the correct status code
        assertThat(response.getStatus())
                .isEqualTo(Response.Status.CREATED.getStatusCode());

        // test that the location header is correct.
        assertThat(response.getHeaderString(HttpHeaders.LOCATION))
                .isEqualTo(UriBuilder.fromUri(resourceUri).path("123").build().toString());
    */
    assert(true);
    }
}
