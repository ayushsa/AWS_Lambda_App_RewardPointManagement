package model;

public class AddRewardsResponse {
    public String message;
    public int statusCode;
    public RewardDetails rewardDetails;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public RewardDetails getRewardDetails() {
        return rewardDetails;
    }

    public void setRewardDetails(RewardDetails rewardDetails) {
        this.rewardDetails = rewardDetails;
    }
}
