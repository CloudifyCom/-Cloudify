package com.cloudify.entities;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import java.io.Serializable;

@Schema(description = "Loyalty enrollment details")
public class LoyaltyEnrollment implements Serializable {

    public enum Tier {
        Silver, Gold, Platinum
    }

    @Schema(description = "User", required = true, implementation = User.class)
    private User user;

    @Schema(description = "Loyalty points balance", example = "100")
    private Integer pointsBalance;

    @Schema(description = "Loyalty tier", example = "Gold")
    private Tier tier;

    // Default constructor
    public LoyaltyEnrollment(User user, Integer pointsBalance, Tier tier) {
        this.user = user;
        this.pointsBalance = pointsBalance;
        this.tier = tier;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getPointsBalance() {
        return pointsBalance;
    }

    public void setPointsBalance(Integer pointsBalance) {
        this.pointsBalance = pointsBalance;
    }

    public Tier getTier() {
        return tier;
    }

    public void setTier(Tier tier) {
        this.tier = tier;
    }

    @Override
    public String toString() {
        return "LoyaltyEnrollment{" +
                "user=" + user +
                ", pointsBalance=" + pointsBalance +
                ", tier=" + tier +
                '}';
    }
}