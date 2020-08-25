package helloworld;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;
import factory.LexRequestFactory;
import intent.*;
import model.*;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

public class ShoppingBotLambdaV2 implements RequestHandler<Map<String, Object>, Object>
{

    @Override
    public Object handleRequest(Map<String, Object> input, Context context)
    {
        LexRequest lexRequest = LexRequestFactory.createLexRequest(input);

//        String content = String.format("** Request came from Ranjeet the bot: %s, Department: %s;" +
//            "You Ordered: %s %s of %s ",
//                            lexRequest.getBotName(),
//                            lexRequest.getDepartmentName(),
//                            lexRequest.getAmount(),
//                            lexRequest.getUnit(),
//                            lexRequest.getProduct()
//        );
                String content = String.format("** Request came from Ranjeet the bot: %s, Department: %s ",
                            lexRequest.getBotName(),
                            lexRequest.getDepartmentName()+""+lexRequest.getUserId()+""+lexRequest.getRewardPoints()
        );

        if (BakeryDepartmentIntent.Name.equalsIgnoreCase(lexRequest.getDepartmentName()))
        {
           // Message message = new Message("PlainText", getEmployees(lexRequest.getDepartmentName()));
            Message message = new Message("PlainText", lexRequest.getDepartmentName());
            return new LexRespond(new DialogAction("Close", "Fulfilled", message));
        }
        else if (RewardPointManagementDepartmentIntent.Name.equalsIgnoreCase(lexRequest.getDepartmentName()))
        {
            //Message message = new Message("PlainText", content);
            Message message = new Message("PlainText", getRewardPoint(lexRequest.getUserId()));
            return new LexRespond(new DialogAction("Close", "Fulfilled", message));
        //for new Internt
            // for add reward point
        } else if(AddRewardPointConversionIntent.Name.equalsIgnoreCase(lexRequest.getDepartmentName())){
            Message message = new Message("PlainText", getAddRewardPoint(lexRequest));
            return new LexRespond(new DialogAction("Close", "Fulfilled", message));
            //// for Min Reedem point
        } else if(MinReedeemPointsIntent.Name.equalsIgnoreCase(lexRequest.getDepartmentName())){
            Message message = new Message("PlainText", "You can reedeem minimum 100 Rewards Points.");
            return new LexRespond(new DialogAction("Close", "Fulfilled", message));
       //for Reedem Reward Point
        } else if(ReedeemRewardPointsIntent.Name.equalsIgnoreCase(lexRequest.getDepartmentName())){
            Message message = new Message("PlainText", getReedemRewardPoint(lexRequest));
            return new LexRespond(new DialogAction("Close", "Fulfilled", message));
            //for Reward point conversion
        } else if(RewardPointConversionIntent.Name.equalsIgnoreCase(lexRequest.getDepartmentName())){
            Message message = new Message("PlainText", "Rewards Point 8000 is equals to 80 Indian Rupees.");
            //Message message = new Message("PlainText", getRewardPoint(lexRequest.getUserId()));
            return new LexRespond(new DialogAction("Close", "Fulfilled", message));
        }
        else
        {
            Message message = new Message("PlainText", "Something went wrong");
            return new LexRespond(new DialogAction("Close", "Fulfilled", message));
        }
    }

    private String getReedemRewardPoint(LexRequest lexRequest) {
        RewardRequestModel  rewardRequestModel = new RewardRequestModel();
        rewardRequestModel.setUserId(Long.parseLong(lexRequest.getUserId()));
       // rewardRequestModel.setReferralRewardPoints(Double.parseDouble(lexRequest.getRewardPoints()));
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://13.232.189.136:8888/reward/redeemRewardsPoint";
        Gson gson = new Gson();
        String requestBody = gson.toJson(rewardRequestModel);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(requestBody, headers);
        ResponseEntity<AddRewardsResponse> response = restTemplate.exchange(url, HttpMethod.PUT, entity,
                AddRewardsResponse.class);
        return "Your reward points reedeemed successfully. Amount "
                + response.getBody().getRewardDetails().getRedeemedRewardAmount()
                +"Indian Rupees will be credited in your Bank Account.";
    }

    private String getAddRewardPoint(LexRequest lexRequest) {
        RewardRequestModel  rewardRequestModel = new RewardRequestModel();
        rewardRequestModel.setUserId(Long.parseLong(lexRequest.getUserId()));
        rewardRequestModel.setReferralRewardPoints(Double.parseDouble(lexRequest.getRewardPoints()));
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://13.232.189.136:8888/reward/earnRewardPointsByPurchase";
        Gson gson = new Gson();
        String requestBody = gson.toJson(rewardRequestModel);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(requestBody, headers);
        ResponseEntity<AddRewardsResponse> response = restTemplate.exchange(url, HttpMethod.POST, entity,
                AddRewardsResponse.class);
        return lexRequest.getRewardPoints()+" Reward points added successfully for user Id "
                + lexRequest.getUserId()+" Total Rewards point are: "
                +response.getBody().getRewardDetails().getRewardPoints();

    }

    private static String getRewardPoint(String userId)
    {
        final String uri = "http://13.232.189.136:8888/reward/getRewardPoints/"+ userId;

        RestTemplate restTemplate = new RestTemplate();
        RewardPointResponseModel result = restTemplate.getForObject(uri, RewardPointResponseModel.class);

        //System.out.println(result.getDepartment());

        if (result.getStatusCode() == 404)
        {
            return "This user Id is not registered in our DB";
        }
        else
        {
            return  "Reedeem point for " + userId + " is successfully fetched. Total Reward Points are " + result.getRewardDetails().getRewardPoints();
        }
    }

//    private String createLexRequest(Map<String, Object> input)
//    {
//        Map<String, Object> botMap = (Map<String, Object>) input.get("bot");
//        String botName = (String) botMap.get("name");
//
//        LexRequest lexRequest = new LexRequest();
//        lexRequest.setBotName(botName);
//
//        String intentName = (String) input.get("currentIntent");
//        lexRequest.setIntentName(intentName);
//        return botName;
//    }

//    private static String getEmployees(String departmentName)
//    {
//        final String uri = "http://13.232.189.136:8888/reward/getChatbotProduct/"+ departmentName;
//
//        RestTemplate restTemplate = new RestTemplate();
//        ProductResponce result = restTemplate.getForObject(uri, ProductResponce.class);
//
//        //System.out.println(result.getDepartment());
//
//        return  " Department: " +result.getDepartment() +
//                " Product Name: " + result.getName() +
//                " Amount: " + result.getAmount() +
//                " Quantity: " + result.getQuantity();
//    }



}
