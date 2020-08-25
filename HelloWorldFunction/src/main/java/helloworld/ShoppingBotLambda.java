package helloworld;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import model.*;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

public class ShoppingBotLambda implements RequestHandler<Map<String, Object>, Object>
{

    @Override
    public Object handleRequest(Map<String, Object> stringObjectMap, Context context)
    {
        System.out.println(stringObjectMap.toString());

        Message message = new Message("PlainText",
                getEmployees());
        return new LexRespond(new DialogAction("Close", "Fulfilled", message));
    }


    private static String getEmployees()
    {
        final String uri = "http://13.232.189.136:8888/reward/fetchDetails";

        RestTemplate restTemplate = new RestTemplate();
        CustomResponceData result = restTemplate.getForObject(uri, CustomResponceData.class);

        System.out.println(result.getMessage());

        return result.getMessage();
    }

}
