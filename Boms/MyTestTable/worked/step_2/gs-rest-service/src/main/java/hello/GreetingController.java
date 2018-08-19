package hello;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.google.gson.Gson;
import org.eclipse.collections.impl.factory.Lists;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RestController
@RequestMapping("/api")
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }

    @RequestMapping(value = "/get-assets2", method = RequestMethod.GET)
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseEntity<List<Car>> listAllUsers()  {

        try {

            List<Car> carsList = new ArrayList<>();

            Car car = new Car("VAZ", "red", 111);
            Car car2 = new Car("MAZ", "red", 111);
            Car car3 = new Car("MAZ", "red", 111);

            carsList.add(car);
            carsList.add(car2);
            carsList.add(car3);

            return new ResponseEntity<List<Car>>(carsList, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(value = "/get-asset", method = RequestMethod.GET)
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response GetCars() {

        try {

            Gson gson = new Gson();
            int[] ints = {1, 2, 3, 4, 5};
            gson.toJson(ints);

            int[] ints2 = gson.fromJson("[1,2,3,4,5]", int[].class);

            //-------------------------

            Car car = new Car("VAZ", "red", 111);


            String json = new Gson().toJson(car);

            return Response.ok(json, MediaType.APPLICATION_JSON).build();
            //return Response.ok().entity(json).build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }


}
