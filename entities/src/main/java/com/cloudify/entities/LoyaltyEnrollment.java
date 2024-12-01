package com.cloudify.entities;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import java.io.Serializable;

@Schema(description = "Loyalty enrollment details")
public class LoyaltyEnrollment implements Serializable {

    @Schema(description = "User ID", required = true, example = "101")
    private Integer userId;

    // Getters, Setters, Constructors
}
