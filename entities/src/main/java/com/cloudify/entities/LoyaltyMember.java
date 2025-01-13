package com.cloudify.entities;

import jakarta.persistence.*;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.json.bind.annotation.JsonbTransient;
import java.io.Serializable;


@org.hibernate.annotations.NamedQueries(value =
        {
                @org.hibernate.annotations.NamedQuery(
                        name = "LoyaltyMember.getLoyaltyMember",
                        query = "SELECT p FROM LoyaltyMember p WHERE p.memberId  = :id"
                ),
                @org.hibernate.annotations.NamedQuery(
                        name = "LoyaltyMember.deleteLoyaltyMember",
                        query = "DELETE FROM LoyaltyMember p WHERE p.memberId = :id"
                ),
                @org.hibernate.annotations.NamedQuery(
                        name = "LoyaltyMember.updateLoyaltyMember",
                        query = "UPDATE LoyaltyMember p SET p.points = :points WHERE p.memberId = :id"
                )
        }
)
@Entity
@Table(name = "loyaltymember")
@Schema(description = "Loyalty member details")
public class LoyaltyMember implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memberId")
    @Schema(description = "Unique loyalty member identifier", example = "LM123456")
    private int memberId;

    @JsonbTransient
    @ManyToOne
    @JoinColumn(name = "userid")
    @Schema(description = "User associated with the loyalty program")
    private User user;

    @Column(name = "points")
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
    public String getUserName() {
        return this.user != null ? this.user.getName() : null;
    }

}
