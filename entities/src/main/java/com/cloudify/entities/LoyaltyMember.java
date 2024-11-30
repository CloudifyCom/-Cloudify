package com.cloudify.entities;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.io.Serializable;

@Schema(description = "Loyalty member details")
public class LoyaltyMember implements Serializable {

    @Schema(description = "Unique loyalty member identifier", example = "LM123456")
    private int memberId;

    @Schema(description = "User associated with the loyalty program")
    private User user;

    @Schema(description = "Loyalty points", example = "250")
    private int points;

    public LoyaltyMember() {}

    // Parameterized constructor
    public LoyaltyMember(int memberId, User user, int points) {
        this.memberId = memberId;
        this.user = user;
        this.points = points;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
