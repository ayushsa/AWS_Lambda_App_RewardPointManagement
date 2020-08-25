package model;

public class RewardRequestModel {
    private Long userId;
    private Double referralRewardPoints;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getReferralRewardPoints() {
        return referralRewardPoints;
    }

    public void setReferralRewardPoints(Double referralRewardPoints) {
        this.referralRewardPoints = referralRewardPoints;
    }
}
