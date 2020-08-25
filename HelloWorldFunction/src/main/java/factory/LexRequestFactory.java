package factory;

import intent.*;
import model.LexRequest;
import java.util.Map;

public class LexRequestFactory {

    public static LexRequest createLexRequest(Map<String, Object> input)
    {
        Map<String, Object> botMap = (Map<String, Object>) input.get("bot");

        Map<String, Object> currentIntent = (Map<String, Object>) input.get("currentIntent");

        LexRequest lexRequest = new LexRequest();

        String botIntentName = (String) currentIntent.get("name");
        String botName = (String) botMap.get("name");
        lexRequest.setBotName(botName);
        lexRequest.setDepartmentName((String) currentIntent.get("name"));

        if (BakeryDepartmentIntent.Name.equalsIgnoreCase(botIntentName))
        {
            Map<String, Object> slots = (Map<String, Object>) currentIntent.get("slots");
            lexRequest.setAmount((String) slots.get("Amount"));
            lexRequest.setProduct((String) slots.get("BakeryProduct"));
            lexRequest.setUnit((String) slots.get("BakeryDepartmentUnit"));

        }
        else if (RewardPointManagementDepartmentIntent.Name.equalsIgnoreCase(botIntentName))
        {
            Map<String, Object> slots = (Map<String, Object>) currentIntent.get("slots");
            lexRequest.setUserId((String) slots.get("userId"));
        //for add reward point
        } else if(AddRewardPointConversionIntent.Name.equalsIgnoreCase(botIntentName)){
            Map<String, Object> slots = (Map<String, Object>) currentIntent.get("slots");
            lexRequest.setUserId((String) slots.get("userId"));
            lexRequest.setRewardPoints((String) slots.get("rewardpoints"));
        //// for Min Reedem point
        } else if(MinReedeemPointsIntent.Name.equalsIgnoreCase(botIntentName)){
            //TODO in future
            //for Reedem reward point
        } else if(ReedeemRewardPointsIntent.Name.equalsIgnoreCase(botIntentName)){
            Map<String, Object> slots = (Map<String, Object>) currentIntent.get("slots");
            lexRequest.setUserId((String) slots.get("userId"));
            //for Reward point conversion
        } else if(RewardPointConversionIntent.Name.equalsIgnoreCase(botIntentName)){
            Map<String, Object> slots = (Map<String, Object>) currentIntent.get("slots");
            lexRequest.setRewardPoints((String) slots.get("rewardpoint"));
        }
        else
        {
            lexRequest = null;
        }

        return lexRequest;
    }
}
