package model;

import java.util.Date;

public class RewardDetails {

    public int userId;
    public double rewardPoints;
    public int loyalityProgramId;
    public String emailId;
    public double redeemedRewardAmount;
    public String membershipExpiryDate;
    public Date lastModifiedDate;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getRewardPoints() {
        return rewardPoints;
    }

    public void setRewardPoints(double rewardPoints) {
        this.rewardPoints = rewardPoints;
    }

    public int getLoyalityProgramId() {
        return loyalityProgramId;
    }

    public void setLoyalityProgramId(int loyalityProgramId) {
        this.loyalityProgramId = loyalityProgramId;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public double getRedeemedRewardAmount() {
        return redeemedRewardAmount;
    }

    public void setRedeemedRewardAmount(double redeemedRewardAmount) {
        this.redeemedRewardAmount = redeemedRewardAmount;
    }

    public String getMembershipExpiryDate() {
        return membershipExpiryDate;
    }

    public void setMembershipExpiryDate(String membershipExpiryDate) {
        this.membershipExpiryDate = membershipExpiryDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
